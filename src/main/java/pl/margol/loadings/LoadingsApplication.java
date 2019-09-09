package pl.margol.loadings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class LoadingsApplication {

    public static void main(String[] args) {
		

        SpringApplication.run(LoadingsApplication.class, args);
    }

}
