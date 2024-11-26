package com.oocl.springbootemployee.service;

import com.oocl.springbootemployee.model.Company;
import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<Company> findAll(int pageIndex, int pageSize) {
        List<Company> allCompanies = companyRepository.findAll();
        int startIndex = (pageIndex - 1) * pageSize;

        if (startIndex >= allCompanies.size()) {
            return Collections.emptyList();
        }

        int endIndex = Math.min(startIndex + pageSize, allCompanies.size());
        return allCompanies.subList(startIndex, endIndex);
    }

    public Company findById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    public List<Employee> getEmployeesByCompanyId(Integer id) {
        return companyRepository.findById(id)
                .map(Company::getEmployees)
                .orElse(null);
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public Company update(Integer id, Company company) {
        return companyRepository.findById(id)
                .map(existingCompany -> {
                    if (company.getName() != null) {
                        existingCompany.setName(company.getName());
                    }
                    if (company.getEmployees() != null) {
                        existingCompany.setEmployees(company.getEmployees());
                    }
                    return companyRepository.save(existingCompany);
                })
                .orElse(null);
    }

    public void delete(Integer id) {
        companyRepository.deleteById(id);
    }
}