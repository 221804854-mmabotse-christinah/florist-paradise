package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.CustomerRepository;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer read(Long aLong) {
        return customerRepository.findById(aLong).orElse(null);
    }


    @Override
    public Customer update(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteById(Long aLong) {
        customerRepository.deleteById(aLong);
        return !customerRepository.existsById(aLong);
    }
    @Override
    public List<Customer> getall() {
        return customerRepository.findAll();
    }
}
