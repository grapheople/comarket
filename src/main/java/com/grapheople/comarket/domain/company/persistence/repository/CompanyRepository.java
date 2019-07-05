package com.grapheople.comarket.domain.company.persistence.repository;

import com.grapheople.comarket.domain.company.persistence.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByEmailDomain(String emailDomain);
}
