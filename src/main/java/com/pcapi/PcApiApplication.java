package com.pcapi;

import com.pcapi.entities.Laptop;
import com.pcapi.repositories.LaptopRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PcApiApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(PcApiApplication.class, args);
		LaptopRepo laptopRepo = context.getBean(LaptopRepo.class);


		Laptop laptop1 = new Laptop(1L,"MSI","UltraGamingSeries",2019,425.99F,32,5600,30,24F,3500);
		Laptop laptop2 = new Laptop(2L,"ACER","UltraGamingSeries",2019,425.99F,32,5600,30,24F,3500);
		Laptop laptop3 = new Laptop(3L,"ASUS","UltraGamingSeries",2019,425.99F,32,5600,30,24F,3500);
		Laptop laptop4 = new Laptop(4L,"BENQ","UltraGamingSeries",2019,425.99F,32,5600,30,24F,3500);
		Laptop laptop5 = new Laptop(5L,"INTEL","UltraGamingSeries",2019,425.99F,32,5600,30,24F,3500);

		laptopRepo.save(laptop1);
		laptopRepo.save(laptop2);
		laptopRepo.save(laptop3);
		laptopRepo.save(laptop4);
		laptopRepo.save(laptop5);

	}

}
