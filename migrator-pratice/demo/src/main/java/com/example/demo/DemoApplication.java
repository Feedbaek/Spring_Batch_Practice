package com.example.demo;

import com.example.demo.service.MainService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(DemoApplication.class)
				.web(WebApplicationType.NONE)  // 비-웹
				.run(args);

		SpringApplication.exit(context, () -> 0); // 스프링 컨테이너 정상 종료
	}

	@Bean
	CommandLineRunner run(MainService service) {
		return args -> {
			// 인자 처리 예시
			String input  = args.length > 0 ? args[0] : "default-in";
			String output = args.length > 1 ? args[1] : "default-out";
			service.execute(input, output);
		};
	}
}
