package com.example.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ShopApplication
 * - Spring Boot 애플리케이션의 진입점 (main class)
 * - @SpringBootApplication: 컴포넌트 스캔, 설정 자동 등록, 설정 클래스 지정
 */
@SpringBootApplication
public class ShopApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
}
