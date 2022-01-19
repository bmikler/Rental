package pl.javastart.rentalv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.rentalv2.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByDocumentNumberIgnoreCase(String documentNumber);

}
