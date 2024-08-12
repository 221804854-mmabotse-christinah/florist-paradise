package za.ac.cput.factory;

import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Customer;
import za.ac.cput.utility.Helper;


import java.util.List;

public class CustomerFactory {
    public static Customer buildCustomer(long customerId, String firstName, String lastName, List<Contact> contact, String username, String password) {
        if (
                Helper.isNullOrEmpty(firstName) ||
                        Helper.isNullOrEmpty(lastName) ||
                        Helper.isNullOrEmpty(username) ||
                        Helper.isNullOrEmpty(password) ||
                        contact == null || contact.isEmpty()
        ) {
            return null;
        }

        return new Customer.CustomerBuilder()
                .setCustomerId(customerId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setContact(contact)
                .setUsername(username)
                .setPassword(password)
                .build();
    }
}
