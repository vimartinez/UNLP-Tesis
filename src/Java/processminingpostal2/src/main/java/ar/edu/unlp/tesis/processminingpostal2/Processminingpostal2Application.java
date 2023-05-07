package ar.edu.unlp.tesis.processminingpostal2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class Processminingpostal2Application {

	public static void main(String[] args) {
		SpringApplication.run(Processminingpostal2Application.class, args);
	}
	
	@Bean
	public OpenAPI customOpenAPI(@Value("V1") String appVersion) {
		return new OpenAPI().info(new Info().title("UNLP Tesis - Minería de Procesos Aplicada a la Distribución Postal")
				.version(appVersion)
				.description("Universidad Nacinal de La Plata - Facultad de Informática - Esta API es parte del trabajo final para la Maestría en Ingeniería de Software - Victor Martinez")
				.termsOfService("http://google.com")
				.license(new License().name("Apache 2.0").url("http://google.com.ar")));
	}

}
