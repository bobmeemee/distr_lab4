package nameless.lab4;

import nameless.lab4.server.NamingServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })public class Lab4Application {

	public static void main(String[] args) {

		SpringApplication.run(Lab4Application.class, args);
		// NamingServer namingServer = new NamingServer();

	}

}
