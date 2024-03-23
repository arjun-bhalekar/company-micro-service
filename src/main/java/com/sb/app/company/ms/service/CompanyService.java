package com.sb.app.company.ms.service;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    void createCompany(Company company);
    Boolean updateCompany(Long id, Company updatedCompany);

    Company getCompanyById(Long id);

    boolean deleteCompanyById(Long id);
}