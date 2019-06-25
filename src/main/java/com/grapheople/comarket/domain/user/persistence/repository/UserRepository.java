package com.grapheople.comarket.domain.user.persistence.repository;

import com.grapheople.comarket.domain.user.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountName(String accountName);
}
