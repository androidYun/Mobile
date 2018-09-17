package com.mobile.service;

import com.mobile.pojo.Company;

import java.util.List;

public interface CompanyService {
    int insertCompany(int userId, String companyName, String companyAddress);

    Company getCompanyByName(String companyName);

    Company getCompanyById(int companyId);

    void deleteCompanyById(int companyId);

    List<Company> selectCompanyList(int pageSize, int pageNumber);
}
