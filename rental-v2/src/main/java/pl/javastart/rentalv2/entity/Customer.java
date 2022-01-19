package pl.javastart.rentalv2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String lastName;
    private String pesel;
    private String documentNumber;
    @ManyToMany(mappedBy = "borrowers")
    private List<Device> borrowedDevices = new ArrayList<>();

    public Customer() {
    }

    public Customer(String lastName, String pesel, String documentNumber) {
        this.lastName = lastName;
        this.pesel = pesel;
        this.documentNumber = documentNumber;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public List<Device> getBorrowedDevices() {
        return borrowedDevices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (!lastName.equals(customer.lastName)) return false;
        if (!pesel.equals(customer.pesel)) return false;
        return documentNumber.equals(customer.documentNumber);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + lastName.hashCode();
        result = 31 * result + pesel.hashCode();
        result = 31 * result + documentNumber.hashCode();
        return result;
    }
}
