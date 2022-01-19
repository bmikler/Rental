package pl.javastart.rentalv2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.rentalv2.entity.Customer;
import pl.javastart.rentalv2.entity.Device;
import pl.javastart.rentalv2.exception.CustomerNotFoundException;
import pl.javastart.rentalv2.exception.DeviceNotFoundException;
import pl.javastart.rentalv2.exception.NotEnoughDevicesException;
import pl.javastart.rentalv2.repository.CustomerRepository;
import pl.javastart.rentalv2.repository.DeviceRepository;

import java.util.Scanner;

@Service
public class RentService {

    private final Scanner scanner;
    private final CustomerRepository customerRepository;
    private final DeviceRepository deviceRepository;


    public RentService(Scanner scanner, CustomerRepository customerRepository, DeviceRepository deviceRepository) {
        this.scanner = scanner;
        this.customerRepository = customerRepository;
        this.deviceRepository = deviceRepository;
    }

    @Transactional
    public void rentDevice() {

        try {
            Customer customer = readCustomer();
            Device device = readDevice();
            rentDeviceToCustomer(customer, device);
            System.out.println("Device rented!");

        } catch (NotEnoughDevicesException e) {
            System.err.println(e.getMessage());
        } catch (CustomerNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (DeviceNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    @Transactional
    public void returnDevice() {

        try {
            Customer customer = readCustomer();

            if (customer.getBorrowedDevices().size() < 1) {
                System.out.println("No devices rented for this Customer.");
            } else {
                returnAllDevicesFromCustomer(customer);
                System.out.println("All devices returned!");
            }

        } catch (CustomerNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }


    private void rentDeviceToCustomer(Customer customer, Device device) {

        if (device.getQuantityInStock() < 1)
            throw new NotEnoughDevicesException("Not enough devices in stock.");

        device.setQuantityInStock(device.getQuantityInStock() - 1);
        device.addBorrower(customer);

        deviceRepository.save(device);

    }


    @Transactional
    private void returnAllDevicesFromCustomer(Customer customer){

        customer.getBorrowedDevices().forEach(device -> {
            device.setQuantityInStock(device.getQuantityInStock() + 1);
            device.removeBorrower(customer);
            deviceRepository.save(device);
        });

    }

    private Customer readCustomer() {

        System.out.println("Prompt customer document number:");
        String documentNo = scanner.nextLine();

        return customerRepository.findByDocumentNumberIgnoreCase(documentNo)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found."));
    }

    private Device readDevice() {

        System.out.println("All available devices:");
        deviceRepository.getAllAvailable().forEach(System.out::println);
        System.out.println("Chose device to rent. Prompt device id:");
        long deviceId = Long.parseLong(scanner.nextLine());

        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found."));
    }


}
