package com.chatRobot.dao;

import com.chatRobot.pojo.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICompanyDao {

    int insertCompany(Company company);

    List<Company> selectCompanyList(@Param("pageSize") int pageSize, @Param("pageNumber") int pageNumber);

    Company getCompanyByName(String companyName);

    Company getCompanyById(int companyId);

    void deleteCompanyById(int companyId);
}
