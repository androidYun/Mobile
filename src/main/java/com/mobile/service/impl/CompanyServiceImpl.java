package com.mobile.service.impl;

import com.mobile.dao.ICompanyDao;
import com.mobile.pojo.Company;
import com.mobile.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    ICompanyDao iCompanyDao;

    @Override
    public int insertCompany(int userId, String companyName, String companyAddress) {
        Company company = new Company();
        company.setUserId(userId);
        company.setCompanyName(companyName);
        company.setCompanyAddress(companyAddress);
        company.setCreateTime(new Date());
        int companyId = iCompanyDao.insertCompany(company);
        return companyId;
    }

    @Override
    public Company getCompanyByName(String companyName) {
        return iCompanyDao.getCompanyByName(companyName);
    }

    @Override
    public Company getCompanyById(int companyId) {
        return iCompanyDao.getCompanyById(companyId);
    }

    @Override
    public void deleteCompanyById(int companyId) {
         iCompanyDao.deleteCompanyById(companyId);
    }

    @Override
    public List<Company> selectCompanyList(int pageSize, int pageNumber) {
        return iCompanyDao.selectCompanyList(pageSize, pageNumber);
    }
}
