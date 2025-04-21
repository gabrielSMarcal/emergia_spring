package br.com.example.emergia;

import br.com.example.emergia.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EmergiaApplication implements CommandLineRunner {

	private static ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(EmergiaApplication.class, args);
	}

	public static ApplicationContext getContext() {
		return context;
	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main();
		main.main(args);
	}

}
