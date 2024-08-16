package com.accenture.ms_accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("bankAuditorAware")
public class BankAuditorAware implements AuditorAware<String> {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("ms-accounts");
    }
}
