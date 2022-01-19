package pl.javastart.rentalv2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.rentalv2.entity.Customer;
import pl.javastart.rentalv2.repository.CustomerRepository;

import java.util.Scanner;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void addNewCustomer() {

        Customer customer = readCustomer();
        customerRepository.save(customer);
        System.out.println(customer + " saved!");

    }

    private Customer readCustomer() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Prompt Customers name:");
        String name = scanner.nextLine();
        System.out.println("Prompt pesel:");
        String pesel = scanner.nextLine();
        System.out.println("Prompt document number:");
        String documentNo = scanner.nextLine();

        return new Customer(name, pesel, documentNo);

    }


}
