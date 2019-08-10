package pl.margol.loadings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class LoadingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadingsApplication.class, args);
	}

}
