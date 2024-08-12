package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.CustomerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    private Customer customer1, customer2;
    List<Contact> contactList;

    @BeforeEach
    void setUp() {
        Address address1 = AddressFactory.buildAddress(9, "Lower Street", "Mowbray", "Cape Town", "5100");

        Contact contact1 = ContactFactory.buildContact("johndoe@example.com", "0123456789", address1);
        contactList = new ArrayList<>();
        contactList.add(contact1);

        customer1 = CustomerFactory.buildCustomer(1L, "John", "Doe", contactList, "john_doe", "password123");

    }

    @Test
    @Order(1)
    void save() {
        Customer savedCustomer1 = service.save(customer1);
        assertNotNull(savedCustomer1);
        System.out.println("Saved Customer 1: " + savedCustomer1);

    }

    @Test
    @Order(2)
    void read() {
        Customer read = service.read(customer1.getCustomerId());
        assertNotNull(read);
        assertEquals(customer1.getFirstName(), read.getFirstName());
        System.out.println("Read Customer: " + read);
    }

    @Test
    @Order(3)
    void update() {
        Customer updated = new Customer.CustomerBuilder().copy(customer1)
                .setFirstName("Johnny")
                .build();
        Customer updatedCustomer = service.save(updated);
        assertNotNull(updatedCustomer);
        assertEquals("Johnny", updatedCustomer.getFirstName());
        System.out.println("Updated Customer: " + updatedCustomer);
    }

    @Test
    @Order(4)
    void deleteById() {

        service.deleteById(customer1.getCustomerId());
        Customer deleted = service.read(customer1.getCustomerId());
        assertNull(deleted);
        System.out.println("Deleted Customer: " + customer1);
    }

    @Test
    @Order(5)
    void getall() {
        service.save(customer1);
        List<Customer> allCustomers = service.getall();
        System.out.println("All Customers: " + allCustomers);
    }
}
