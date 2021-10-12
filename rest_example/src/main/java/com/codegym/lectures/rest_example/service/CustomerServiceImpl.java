package com.codegym.lectures.rest_example.service;

import com.codegym.lectures.rest_example.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerServiceImpl implements CustomerService{
    private static final Map<Integer, Customer> CUSTOMER_REPOSITORY_MAP = new HashMap<>();
    private static final AtomicInteger CUSTOMER_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Customer customer) {
        final int customerId = CUSTOMER_ID_HOLDER.incrementAndGet();
        customer.setId(customerId);
        CUSTOMER_REPOSITORY_MAP.put(customerId, customer);
    }

    @Override
    public List<Customer> readAll() {
        return new ArrayList<>(CUSTOMER_REPOSITORY_MAP.values());
    }

    @Override
    public Customer read(int id) {
        return CUSTOMER_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Customer customer, int id) {
        if(CUSTOMER_REPOSITORY_MAP.containsKey(id)){
            customer.setId(id);
            CUSTOMER_REPOSITORY_MAP.put(id, customer);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return CUSTOMER_REPOSITORY_MAP.remove(id) != null;
    }
}
