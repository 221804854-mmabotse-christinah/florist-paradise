package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    private long customerId;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Contact> contact;
    private String username;
    private String password;

    public Customer() {
    }

    public Customer(CustomerBuilder customerBuilder) {
        this.customerId = customerBuilder.customerId;
        this.firstName = customerBuilder.firstName;
        this.lastName = customerBuilder.lastName;
        this.contact = customerBuilder.contact;
        this.username = customerBuilder.username;
        this.password = customerBuilder.password;
    }

    public long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Contact> getContact() {
        return contact;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName) && Objects.equals(contact, customer.contact) && Objects.equals(username, customer.username) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, contact, username, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contact=" + contact +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class CustomerBuilder {
        private long customerId;
        private String firstName;
        private String lastName;
        private List<Contact> contact;
        private String username;
        private String password;

        public CustomerBuilder setCustomerId(long customerId) {
            this.customerId = customerId;
            return this;
        }

        public CustomerBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerBuilder setContact(List<Contact> contact) {
            this.contact = contact;
            return this;
        }

        public CustomerBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public CustomerBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder copy(CustomerBuilder customerBuilder) {
            this.customerId = customerBuilder.customerId;
            this.firstName = customerBuilder.firstName;
            this.lastName = customerBuilder.lastName;
            this.contact = customerBuilder.contact;
            this.username = customerBuilder.username;
            this.password = customerBuilder.password;
            return this;
        }

        public Customer build() { return new Customer(this); }
    }
}
