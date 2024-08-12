package za.ac.cput.factory;

import za.ac.cput.domain.Address;
import za.ac.cput.domain.Contact;
import za.ac.cput.utility.Helper;


public class ContactFactory {
    public static Contact buildContact(String email, String phoneNumber, Address address) {
        if (
                Helper.isNullOrEmpty(email) ||
                        Helper.isNullOrEmpty(phoneNumber) ||
                        address == null
        ) {
            return null;
        }

        return new Contact.ContactBuilder()
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .build();
    }
}
