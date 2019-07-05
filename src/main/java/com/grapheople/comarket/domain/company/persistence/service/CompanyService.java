package com.grapheople.comarket.domain.company.persistence.service;


import com.grapheople.comarket.common.utils.EmailUtils;
import com.grapheople.comarket.domain.company.persistence.entity.Company;
import com.grapheople.comarket.domain.company.persistence.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company getCompany(String email) {
        return companyRepository.findByEmailDomain(EmailUtils.getEmailDomain(email));
    }
}
