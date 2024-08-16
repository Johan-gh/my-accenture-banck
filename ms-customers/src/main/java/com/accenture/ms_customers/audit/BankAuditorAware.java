package com.accenture.ms_customers.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("bankAuditorAware")
public class BankAuditorAware implements AuditorAware<String> {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("ms-customer");
    }
}
