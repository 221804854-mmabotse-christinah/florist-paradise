package za.ac.cput.service;

import za.ac.cput.domain.Customer;

import java.util.List;

public class CustomerService implements ICustomerService {
    @Override
    public List<Customer> getall() {
        return List.of();
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public Customer read(Long aLong) {
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        return null;
    }

    @Override
    public boolean deleteById(Long aLong) {
        return false;
    }
}
