package com.example.client;

import com.example.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("customers")

public interface CustomerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    List<Customer> getStores();
}
