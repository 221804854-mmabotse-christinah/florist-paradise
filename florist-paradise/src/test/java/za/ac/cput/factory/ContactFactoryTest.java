package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;

import static org.junit.jupiter.api.Assertions.*;

class ContactFactoryTest {
    Contact contact1;
    Contact contact2;
    Contact contact3;
    Address address;

    @BeforeEach
    void setUp() {
        address = AddressFactory.buildAddress(9, "Lower Street", "Mowbray", "Cape Town", "5100");
        contact1 = ContactFactory.buildContact("test@example.com", "0123456789", address);
        contact2 = ContactFactory.buildContact("", "0123456789", address);
        contact3 = contact1;
    }

    @Test
    void checkIfNull() {
        assertNull(contact2);
        System.out.println(contact2);
    }

    @Test
    void checkIfNotNull() {
        assertNotNull(contact1);
        System.out.println(contact1);
    }

    @Test
    void equalityTest() {
        assertNotEquals(contact1, contact2);
        System.out.println("NOT EQUAL");
    }

    @Test
    void identityTest() {
        assertSame(contact1, contact3);
        System.out.println("IDENTICAL");
    }
}
