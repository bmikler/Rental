package pl.javastart.rentalv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.javastart.rentalv2.controller.MenuController;
import pl.javastart.rentalv2.entity.Category;
import pl.javastart.rentalv2.entity.Device;
import pl.javastart.rentalv2.repository.DeviceRepository;
import pl.javastart.rentalv2.service.RentService;

import java.util.Scanner;

@SpringBootApplication
public class RentalV2Application {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(RentalV2Application.class, args);

		DeviceRepository deviceRepository = context.getBean(DeviceRepository.class);

		Device device = new Device("testDevice", "placeholder", 10, 22.50,
				new Category("testCategory", "placeholder"));

		deviceRepository.save(device);

		MenuController menuController = context.getBean(MenuController.class);
		menuController.mainLoop();

		context.close();


	}

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}

}
