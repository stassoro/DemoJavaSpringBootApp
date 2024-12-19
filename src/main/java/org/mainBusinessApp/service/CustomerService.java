package org.mainBusinessApp.service;

import org.mainBusinessApp.model.Customer;

import java.util.List;

public interface CustomerService {
    // Save operation
    Customer saveCustomer(Customer customer);

    // Read operation
    List<Customer> fetchCustomerList();

    // Update operation
    Customer updateCustomer(Long customerId, Customer customer);

    // Delete operation
    void deleteCustomerById(Long customerId);

}
