package com.sb.app.company.ms.controller;

import com.sb.app.company.ms.entity.Company;
import com.sb.app.company.ms.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Company company) {
        companyService.createCompany(company);
        return ResponseEntity.ok("Company added successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (Objects.nonNull(company)) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        if (companyService.updateCompany(id, company))
            return ResponseEntity.ok("Company updated successfully");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {

        Company company = companyService.getCompanyById(id);
        if (Objects.nonNull(company)) {
            companyService.deleteCompanyById(id);
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.notFound().build();
        }

    }


}
