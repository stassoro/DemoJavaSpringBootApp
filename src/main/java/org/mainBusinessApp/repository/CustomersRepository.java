package org.mainBusinessApp.repository;

import org.mainBusinessApp.model.Book;
import org.mainBusinessApp.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends CrudRepository<Customer, Long> {
}
