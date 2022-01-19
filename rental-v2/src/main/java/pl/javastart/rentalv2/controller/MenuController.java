package pl.javastart.rentalv2.controller;

import org.springframework.stereotype.Service;
import pl.javastart.rentalv2.service.CustomerService;
import pl.javastart.rentalv2.service.DeviceService;
import pl.javastart.rentalv2.service.RentService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class MenuController {

    private final Scanner scanner = new Scanner(System.in);
    private final CustomerService customerService;
    private final DeviceService deviceService;
    private final RentService rentService;

    public MenuController(CustomerService customerService, DeviceService deviceService, RentService rentService) {
        this.customerService = customerService;
        this.deviceService = deviceService;
        this.rentService = rentService;
    }

    public void mainLoop() {

        Options option = null;

        do {

            try {
                printMenu();
                option = Options.getOptionByNumber(readOption());
                executeOption(option);
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.err.println("Wrong input " + e);
            }

        } while (option != Options.EXIT);

    }

    private void printMenu() {
        for (Options options : Options.values()) {
            System.out.println(options.ordinal() + ". " + options.getDescription());
        }
    }

    private int readOption() {
        int option = Integer.parseInt(scanner.nextLine());
        return option;
    }

    private void executeOption(Options option) {

        switch (option) {

            case SHOW_ALL_AVAILABLE -> {
                deviceService.printAllDevicesAvailable();
            }
            case ADD_NEW_DEVICE -> {
                deviceService.addNewDevice();
            }
            case RENT_DEVICE -> {
                rentService.rentDevice();
            }
            case RETURN_DEVICE -> {
                rentService.returnDevice();
            }
            case ADD_CUSTOMER -> {
                customerService.addNewCustomer();
            }
            case EXIT -> {
                closeApp();
            }
        }

    }


    private void closeApp(){
        System.out.println("Bye bye!");
    }


}
