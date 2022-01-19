package pl.javastart.rentalv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.rentalv2.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
