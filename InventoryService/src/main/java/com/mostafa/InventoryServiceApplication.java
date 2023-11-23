package com.mostafa;

import com.mostafa.entity.Inventory;
import com.mostafa.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(InventoryRepository inventoryRepository){
		return args -> {
			List<Inventory> inventoryList = inventoryRepository.findAll();

			if(CollectionUtils.isEmpty(inventoryList)){
				inventoryList = Stream.of(
						new Inventory(0L,"iphone_13", 100),
						new Inventory(0L,"iphone_13_red", 0)
				).toList();

				inventoryRepository.saveAll(inventoryList);
			}
		};
	}
}
