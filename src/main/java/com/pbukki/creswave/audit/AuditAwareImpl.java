package com.pbukki.creswave.audit;

import com.pbukki.creswave.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    private final AuthService authService;

    @Override
    public Optional<String> getCurrentAuditor() {

        // Retrieve the currently authenticated user from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // If authentication is not null and the user is authenticated, return the username
        if (authentication != null && authentication.isAuthenticated()) {
            return Optional.of(authentication.getName());
        } else {
            // If no user is authenticated, return an empty optional
            return Optional.empty();
        }
    }
}
