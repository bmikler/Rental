package pl.javastart.rentalv2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.rentalv2.entity.Category;
import pl.javastart.rentalv2.entity.Device;
import pl.javastart.rentalv2.exception.CategoryNotFoundException;
import pl.javastart.rentalv2.repository.CategoryRepository;
import pl.javastart.rentalv2.repository.DeviceRepository;

import java.util.Scanner;

@Service
public class DeviceService {

    private DeviceRepository deviceRepository;
    private CategoryRepository categoryRepository;

    public DeviceService(DeviceRepository deviceRepository, CategoryRepository categoryRepository) {
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
    }

    public void printAllDevicesAvailable() {
        deviceRepository.getAllAvailable().forEach(System.out::println);
    }

    @Transactional
    public void addNewDevice() {

        Device device = readDevice();
        deviceRepository.save(device);

    }

    private Device readDevice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Prompt name:");
        String name = scanner.nextLine();
        System.out.println("Prompt description:");
        String description = scanner.nextLine();
        System.out.println("Prompt quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Prompt price:");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Prompt category id:");
        long categoryId = Long.parseLong(scanner.nextLine());
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found."));

        return new Device(name, description, quantity, price, category);

    }

}
