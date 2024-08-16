package com.accenture.ms_accounts.services.client;


import com.accenture.ms_accounts.dto.ms_customers.CustomerDto;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ms-customers")
public interface CustomersFeignClient {

    @GetMapping(value = "/api/customer/by/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> findCustomerById(
            @PathVariable
            Long id);
}
