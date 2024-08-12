package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Address;

import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {
    Address address1;
    Address address2;
    Address address3;

    @BeforeEach
    void setUp() {
        address1 = AddressFactory.buildAddress(9, "Lower Street", "Mowbray", "Cape Town", "5100");
        address2 = AddressFactory.buildAddress(30, "","Foreshore", "Cape Town", "5099");
        address3 = address1;
    }

    @Test
    void checkIfNull() {
        assertNull(address2);
        System.out.println(address2);
    }

    @Test
    void checkIfNotNull() {
        assertNotNull(address1);
        System.out.println(address1);
    }

    @Test
    void equalityTest() {
        assertNotEquals(address1, address2);
        System.out.println("NOT EQUAL");
    }

    @Test
    void identityTest() {
        assertSame(address1, address3);
        System.out.println("IDENTICAL");
    }
}
