package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Contact {
    @Id
    private String email;
    private String phoneNumber;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "streetNumber", referencedColumnName = "streetNumber"),
            @JoinColumn(name = "streetName", referencedColumnName = "streetName"),
            @JoinColumn(name = "postalCode", referencedColumnName = "postalCode")
    })
    private  Address address;

    public Contact() {
    }

    public Contact(ContactBuilder builder) {
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(email, contact.email) && Objects.equals(phoneNumber, contact.phoneNumber) && Objects.equals(address, contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phoneNumber, address);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
    public static class ContactBuilder {
        private String email;
        private String phoneNumber;
        private Address address;

        public ContactBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ContactBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }
        public ContactBuilder copy(Contact contact) {
            this.email = contact.email;
            this.phoneNumber = contact.phoneNumber;
            this.address = contact.address;
            return this;
        }
        public Contact build() {
            return new Contact(this);
        }
    }

}
