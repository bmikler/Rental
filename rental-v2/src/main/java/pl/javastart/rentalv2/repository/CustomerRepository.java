package pl.javastart.rentalv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.rentalv2.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByDocumentNumber(String documentNumber);

}
