package pl.javastart.rentalv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.rentalv2.entity.Category;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameIgnoreCase(String name);

}
