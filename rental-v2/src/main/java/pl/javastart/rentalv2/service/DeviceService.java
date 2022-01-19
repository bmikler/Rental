package pl.javastart.rentalv2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.rentalv2.entity.Category;
import pl.javastart.rentalv2.entity.Device;
import pl.javastart.rentalv2.exception.CategoryNotFoundException;
import pl.javastart.rentalv2.repository.CategoryRepository;
import pl.javastart.rentalv2.repository.DeviceRepository;

import java.util.List;
import java.util.Scanner;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final CategoryRepository categoryRepository;

    public DeviceService(DeviceRepository deviceRepository, CategoryRepository categoryRepository) {
        this.deviceRepository = deviceRepository;
        this.categoryRepository = categoryRepository;
    }

    public void printAllDevicesAvailable() {

        List<Device> available = deviceRepository.getAllAvailable();

        if (available.size() < 1)
            System.err.println("No devices available.");
        else
            available.forEach(System.out::println);

    }

    @Transactional
    public void addNewDevice() {

        try {
            Device device = readDevice();
            deviceRepository.save(device);
        } catch (CategoryNotFoundException e) {
            System.err.println(e.getMessage());
        }


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

        System.out.println("Chose category:");
        categoryRepository.findAll().forEach(System.out::println);

        System.out.println("Prompt category name:");
        String categoryName = scanner.nextLine();

        Category category = categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found."));

        return new Device(name, description, quantity, price, category);

    }




}
