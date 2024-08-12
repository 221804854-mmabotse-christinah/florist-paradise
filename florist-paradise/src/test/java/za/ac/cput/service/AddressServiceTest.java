package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.AddressId;
import za.ac.cput.factory.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@Service
class AddressServiceTest {

    @Autowired
    private AddressService service;

    static Address address1;
    static Address address2;

    @BeforeEach
    void setUp() {
        address1 = AddressFactory.buildAddress(9, "Lower Street", "Mowbray", "Cape Town", "5100");
        address2 = AddressFactory.buildAddress(30, "Sir Lowery", "Foreshore", "Cape Town", "5099");
    }

    @Test
    @Order(1)
    void save() {
        Address saved = service.save(address1);
        assertNotNull(saved);
        System.out.println(saved);

        Address savedAddress2 = service.save(address2);
        assertNotNull(savedAddress2);
        System.out.println(savedAddress2);
    }

    @Test
    @Order(2)
    void read() {
        AddressId addressId = new AddressId(address1.getStreetNumber(), address1.getStreetName(), address1.getPostalCode());
        Address read = service.read(addressId);
        assertNotNull(read);
        System.out.println(read);
    }

    @Test
    @Order(3)
    void update() {
        Address updated = new Address.AddressBuilder().copy(address2)
                .setSuburb("Goodwood")
                .buildAddress();
        Address updatedAddress = service.save(updated);
        assertNotNull(updatedAddress);
        assertEquals("Goodwood", updatedAddress.getSuburb());
        System.out.println(updatedAddress);
    }

    @Test
    @Order(4)
    void deleteById() {
        AddressId addressId = new AddressId(address1.getStreetNumber(), address1.getStreetName(), address1.getPostalCode());
        service.deleteById(addressId);
        Address deletedAddress = service.read(addressId);
        assertNull(deletedAddress);
    }

    @Test
    @Order(5)
    void getAll() {
        assertNotNull(service.getall());
        System.out.println(service.getall());
    }
}
