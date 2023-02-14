package com.advertisementShop.AdvertisementShop;

import com.advertisementShop.AdvertisementShop.config.audit.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing
@Configuration
@EnableTransactionManagement
public class AdvertisementShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvertisementShopApplication.class, args);
	}

	@Bean
	public AuditorAware<String> myAuditorProvider() {
		return new AuditorAwareImpl();
	}

}
