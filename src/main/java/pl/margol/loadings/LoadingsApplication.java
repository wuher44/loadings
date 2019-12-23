package pl.margol.loadings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication

public class LoadingsApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(){
        return createSpringApplicationBuilder().sources(LoadingsApplication.class);
    }

    public static void main(String[] args) {

        SpringApplication.run(LoadingsApplication.class, args);
    }


}
