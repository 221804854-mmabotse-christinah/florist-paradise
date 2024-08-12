package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerFactoryTest {
    Customer customer1;
    Customer customer2;
    Customer customer3;
    List<Contact> contactList;

    @BeforeEach
    void setUp() {
        Contact contact = ContactFactory.buildContact("test@example.com", "0123456789", AddressFactory.buildAddress(9, "Lower Street", "Mowbray", "Cape Town", "5100"));
        contactList = new ArrayList<>();
        contactList.add(contact);

        customer1 = CustomerFactory.buildCustomer(1L, "John", "Doe", contactList, "john_doe", "password123");
        customer2 = CustomerFactory.buildCustomer(2L, "", "Smith", contactList, "john_smith", "password456");
        customer3 = customer1;
    }

    @Test
    void checkIfNull() {
        assertNull(customer2);
        System.out.println(customer2);
    }

    @Test
    void checkIfNotNull() {
        assertNotNull(customer1);
        System.out.println(customer1);
    }

    @Test
    void equalityTest() {
        assertNotEquals(customer1, customer2);
        System.out.println("NOT EQUAL");
    }

    @Test
    void identityTest() {
        assertSame(customer1, customer3);
        System.out.println("IDENTICAL");
    }
}
