package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact,String> {
}
