package com.sncfc.analytic.dao;

import com.github.pagehelper.Page;
import com.sncfc.analytic.pojo.AreaCompany;
import com.sncfc.analytic.pojo.CenterCompany;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 *
 */
@Repository
public interface CompanyMapper {

    int insertCenterCompany(CenterCompany centerCompany);

    int updateCenterCompany(CenterCompany centerCompany);

    List<CenterCompany> queryCenterCompanies(Map params);

    int countCenterCompany(Map params);

    Page<AreaCompany> queryAreaCompanies(Map params);

    int countAreaCompany(Map params);

    int insertAreaCompany(AreaCompany areaCompany);

    int updateAreaCompany(AreaCompany areaCompany);
}
