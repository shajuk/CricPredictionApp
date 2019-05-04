package com.prediction.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Shaju K
 *
 */


@SpringBootApplication
@ComponentScan("com.prediction.app")
public class CricPredictionAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricPredictionAppApplication.class, args);
	}

}
