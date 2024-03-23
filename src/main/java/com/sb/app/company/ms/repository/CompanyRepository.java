package com.sb.app.company.ms.repository;

import com.sb.app.company.ms.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
