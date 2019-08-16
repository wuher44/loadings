package pl.margol.loadings.Config;



import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails marcinGolebiewski = User.builder()
                .username("wuher44")
                .password(passwordEncoder.encode("w"))
                .roles("FORWARDER", "ADMIN")
                .build();

        UserDetails maciejWawrzak = User.builder()
                .username("maciek")
                .password(passwordEncoder.encode("m"))
                .roles("FORWARDER")
                .build();



        return new InMemoryUserDetailsManager(marcinGolebiewski, maciejWawrzak);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/driver/edit/**").hasRole("ADMIN")
                .mvcMatchers("/driver/delete/**").hasRole("ADMIN")
                .mvcMatchers("/addTruckSet").hasRole("ADMIN")
                .mvcMatchers("/truckSet/edit/**").hasRole("ADMIN")
            //    .mvcMatchers("/addLoading").permitAll()
           //     .mvcMatchers("/start").permitAll()
            //    .mvcMatchers("/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/error")//.authenticated()
                .and()
                .formLogin();

        http.headers().frameOptions().disable();

    }
}
