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

    private final PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails marcin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("a"))
                .roles("FORWARDER", "ADMIN")
                .build();

        UserDetails john = User.builder()
                .username("john")
                .password(passwordEncoder.encode("j"))
                .roles("FORWARDER")
                .build();

        UserDetails [] users = new UserDetails[]{marcin, john};


        return new InMemoryUserDetailsManager(users);

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
