package org.mainBusinessApp.service;

import org.mainBusinessApp.model.Customer;
import org.mainBusinessApp.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customersRepository.save(customer);

    }

    @Override
    public List<Customer> fetchCustomerList() {
        return (List<Customer>) customersRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer customerDB;
        if (customersRepository.findById(customerId).isPresent()) {
            customerDB = customersRepository.findById(customerId).get();
        }
        else {
            return null;
//            throw new NoSuchElementException("customerId "+customerId+" was not found");
        }

        if (Objects.nonNull(customer.getName()) && !"".equalsIgnoreCase(customer.getName())) {
            customerDB.setName(customer.getName());
        }

        if (Objects.nonNull(customer.getEmail()) && !"".equalsIgnoreCase(customer.getEmail())) {
            customerDB.setEmail(customer.getEmail());
        }

        return customersRepository.save(customerDB);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customersRepository.deleteById(customerId);
    }
}
