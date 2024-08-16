package com.accenture.ms_customers.services.implementation;

import com.accenture.ms_customers.dto.CustomerDto;
import com.accenture.ms_customers.dto.CustomersWithAccountsDto;
import com.accenture.ms_customers.dto.ms_accounts.AccountDto;
import com.accenture.ms_customers.entity.Customer;
import com.accenture.ms_customers.exception.ResourceAlreadyExists;
import com.accenture.ms_customers.exception.ResourceNotFound;
import com.accenture.ms_customers.mapper.CustomerMapper;
import com.accenture.ms_customers.repository.CustomerRepository;
import com.accenture.ms_customers.services.ICustomerService;
import com.accenture.ms_customers.services.client.AccountsFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final AccountsFeignClient accountsFeignClient;


    @Override
    public void createCustomer(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByDocument(customerDto.getDocument());
        if (optionalCustomer.isPresent()) {
            throw new ResourceAlreadyExists("Cliente");
        }
        Customer customer = CustomerMapper.mapDtoToCustomer(customerDto, new Customer());
//        customer.setCreatedDate(LocalDateTime.now());
//        customer.setCreatedBy("admin");
        this.customerRepository.save(customer);
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFound("cliente", "id", id.toString())
                );
        return CustomerMapper.mapCustomerToDto(customer, new CustomerDto());
    }

    @Override
    public CustomerDto fetchCustomerByDocument(String document) {
        Customer customer = customerRepository.findByDocument(document)
                .orElseThrow(
                        () -> new ResourceNotFound("cliente", "documento", document)
                );
        return CustomerMapper.mapCustomerToDto(customer, new CustomerDto());
    }

    @Override
    public CustomerDto fetchCustomerByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFound("cliente", "email", email)
                );
        return CustomerMapper.mapCustomerToDto(customer, new CustomerDto());
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = new CustomerDto();

        Customer customer = customerRepository.findByDocument(customerDto.getDocument())
                .orElseThrow(
                        () -> new ResourceNotFound("cliente", "documento", customerDto.getDocument())
                );

        CustomerMapper.mapDtoToCustomer(customerDto, customer);
        customerRepository.save(customer);

        return customerDto;
    }

    @Override
    public void deleteCustomerByDocument(String document) {
        customerRepository.deleteCustomerByDocument(document);

    }

    @Override
    public void deleteCustomerByEmail(String email) {
        customerRepository.deleteCustomerByEmail(email);
    }

    @Override
    public CustomersWithAccountsDto fetchCustomerWithAccountsByDocument(String document) {
        Customer customer = customerRepository.findByDocument(document)
                .orElseThrow(
                        () -> new ResourceNotFound("cliente", "documento", document)
                );

        CustomersWithAccountsDto fullCustomer = CustomerMapper.mapCustomerToDtoWithAccounts(customer, new CustomersWithAccountsDto());
        List<AccountDto> customerAccounts = accountsFeignClient.fetchAllCustomerAccounts(customer.getCustomerId()).getBody();

        fullCustomer.setAccounts(customerAccounts);
        return fullCustomer;
    }
}
