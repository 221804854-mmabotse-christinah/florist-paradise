package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ContactFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactServiceTest {

    @Autowired
    private ContactService service;

    Contact contact1;
    Contact contact2;
    Address address1;
    Address address2;

    @BeforeEach
    void setUp() {
        address1 = AddressFactory.buildAddress(9, "Lower Street", "Mowbray", "Cape Town", "5100");
        address2 = AddressFactory.buildAddress(30, "Sir Lowery", "Foreshore", "Cape Town", "5099");
        contact1 = ContactFactory.buildContact("johndoe@example.com", "0123456789", address1);
        contact2 = ContactFactory.buildContact("Leagosmith@example.com", "0123456789", address2);
    }

    @Test
    @Order(1)
    void save() {
        Contact saved = service.save(contact1);
        assertNotNull(saved);
        System.out.println("Saved Contact: " + saved);

        Contact savedContact2 = service.save(contact2);
        assertNotNull(savedContact2);
        System.out.println("Saved Contact: " + savedContact2);
    }

    @Test
    @Order(2)
    void read() {
        Contact saved = service.save(contact1);
        Contact read = service.read(saved.getEmail());
        assertNotNull(read);
        assertEquals(contact1.getEmail(), read.getEmail());
        System.out.println("Read Contact: " + read);
    }

    @Test
    @Order(3)
    void update() {
        Contact saved = service.save(contact1);
        Contact updated = new Contact.ContactBuilder().copy(saved)
                .setEmail("newemail@example.com")
                .build();
        Contact updatedContact = service.save(updated);
        assertNotNull(updatedContact);
        assertEquals("newemail@example.com", updatedContact.getEmail());
        System.out.println("Updated Contact: " + updatedContact);
    }

    @Test
    @Order(4)
    void deleteById() {
        Contact saved = service.save(contact2);
        service.deleteById(saved.getEmail());
        Contact deleted = service.read(saved.getEmail());
        assertNull(deleted);
        System.out.println("Deleted Contact: " + saved);
    }

    @Test
    @Order(5)
    void getall() {
        assertNotNull(service.getall());
        System.out.println("All Contacts: " + service.getall());
    }
}
