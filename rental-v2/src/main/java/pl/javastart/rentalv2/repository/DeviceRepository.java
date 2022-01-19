package pl.javastart.rentalv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.javastart.rentalv2.entity.Device;

import java.util.List;


public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT d FROM Device d WHERE d.quantityInStock > 0")
    List<Device> getAllAvailable();

}
