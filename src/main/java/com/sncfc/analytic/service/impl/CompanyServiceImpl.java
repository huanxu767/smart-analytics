package com.sncfc.analytic.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sncfc.analytic.dao.CompanyMapper;
import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.pojo.AreaCompany;
import com.sncfc.analytic.pojo.CenterCompany;
import com.sncfc.analytic.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 月指标维护
 */
@Service
@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<CenterCompany> queryCenterCompanies(Map params) {
        List<CenterCompany> centerCompanies = companyMapper.queryCenterCompanies(params);
        return centerCompanies;
    }

    @Override
    public boolean addOrUpdateCenterCompany(CenterCompany centerCompany) {
        Map params = new HashMap();
        params.put("yearNum",centerCompany.getYearNum());
        params.put("quarterNum",centerCompany.getQuarterNum());
        if(StringUtils.isEmpty(centerCompany.getId())){
           int sum = companyMapper.countCenterCompany(params);
            //新增 判断是否已存在
            if(sum > 0){
                throw new ResultException("不允许重复添加");
            }
            return companyMapper.insertCenterCompany(centerCompany)>0;
        }else{
            params.put("notId",centerCompany.getId());
            int sum = companyMapper.countCenterCompany(params);
            if(sum > 0){
                throw new ResultException("不允许重复添加");
            }
            return companyMapper.updateCenterCompany(centerCompany)>0;
        }
    }

    @Override
    public PageInfo<AreaCompany> queryAreaCompanies(Map params) {
        Assert.notNull(params.get("pageNum"));
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()), 10);
        PageInfo<AreaCompany> centerCompanies = new PageInfo<>(companyMapper.queryAreaCompanies(params));
        return centerCompanies;
    }

    @Override
    public boolean addOrUpdateAreaCompany(AreaCompany areaCompany) {
        Map params = new HashMap();
        params.put("yearNum",areaCompany.getYearNum());
        params.put("quarterNum",areaCompany.getQuarterNum());
        params.put("areaCode",areaCompany.getAreaCode());
        if(StringUtils.isEmpty(areaCompany.getId())){
            int sum = companyMapper.countAreaCompany(params);
            //新增 判断是否已存在
            if(sum > 0){
                throw new ResultException("不允许重复添加");
            }
            return companyMapper.insertAreaCompany(areaCompany)>0;
        }else{
            params.put("notId",areaCompany.getId());
            int sum = companyMapper.countAreaCompany(params);
            if(sum > 0){
                throw new ResultException("不允许重复添加");
            }
            return companyMapper.updateAreaCompany(areaCompany)>0;
        }
    }
}
