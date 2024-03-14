package com.pbukki.creswave.audit;

import com.pbukki.creswave.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    private final AuthService authService;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("pbukki");
    }
}
