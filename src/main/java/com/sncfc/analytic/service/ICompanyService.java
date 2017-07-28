package com.sncfc.analytic.service;

import com.github.pagehelper.PageInfo;
import com.sncfc.analytic.pojo.AreaCompany;
import com.sncfc.analytic.pojo.CenterCompany;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/3/14.
 */
public interface ICompanyService {

    /**
     * 查询总部月指标
     * @return
     */
    List<CenterCompany> queryCenterCompanies(Map params);

    /**
     * 新增或修改总部
     * @param centerCompany
     * @return
     */
    boolean addOrUpdateCenterCompany(CenterCompany centerCompany);

    /**
     * 分页查询大区列表
     * @param params
     * @return
     */
    PageInfo<AreaCompany> queryAreaCompanies(Map params);
    /**
     * 新增或修改大区
     * @param areaCompany
     * @return
     */
    boolean addOrUpdateAreaCompany(AreaCompany areaCompany);
}
