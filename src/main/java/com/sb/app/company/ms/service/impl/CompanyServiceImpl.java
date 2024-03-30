package com.sb.app.company.ms.service.impl;

import com.sb.app.company.ms.dto.ReviewMessage;
import com.sb.app.company.ms.entity.Company;
import com.sb.app.company.ms.feignclients.ReviewClient;
import com.sb.app.company.ms.repository.CompanyRepository;
import com.sb.app.company.ms.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ReviewClient reviewClient;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = getCompanyById(reviewMessage.getCompanyId());
        if(Objects.nonNull(company)) {
            Double averageRating = reviewClient.getAverageRating(reviewMessage.getCompanyId());
            company.setRating(averageRating);
            companyRepository.save(company);
        }
    }
}
