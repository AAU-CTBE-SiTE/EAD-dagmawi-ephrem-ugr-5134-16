package com.site.customer_service.repository;


import com.site.customer_service.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface CustomerRepository extends MongoRepository<Customer, String> {

}
