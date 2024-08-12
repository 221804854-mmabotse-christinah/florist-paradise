package za.ac.cput.service;

import za.ac.cput.domain.Contact;

import java.util.List;

public class ContactService implements IContactService {
    @Override
    public List<Contact> getall() {
        return List.of();
    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }

    @Override
    public Contact read(String s) {
        return null;
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public boolean deleteById(String s) {
        return false;
    }
}
