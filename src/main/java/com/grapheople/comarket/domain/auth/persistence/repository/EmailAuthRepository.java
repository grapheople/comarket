package com.grapheople.comarket.domain.auth.persistence.repository;

import com.grapheople.comarket.domain.auth.persistence.entity.EmailAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {
    Optional<EmailAuth> findByEmail(String email);
}
