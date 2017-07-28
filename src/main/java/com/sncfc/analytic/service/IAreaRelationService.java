package com.sncfc.analytic.service;

import com.github.pagehelper.PageInfo;
import com.sncfc.analytic.pojo.AreaRelation;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/3/14.
 */
public interface IAreaRelationService {
    /**
     * 查询--苏宁公司大区关系
     * @return
     */
    PageInfo<AreaRelation> queryAreaRelations(Map params);

    /**
     * 更新--苏宁公司大区关系
     * @param areaRelation
     * @return
     */
    boolean updateAreaRelation(AreaRelation areaRelation);

    /**
     * 新增--苏宁公司大区关系
     * @param areaRelation
     * @return
     */
    boolean insertAreaRelation(AreaRelation areaRelation);

    /**
     * 新增或更新--苏宁公司大区关系
     * @param areaRelation
     * @return
     */
    boolean insertOrUpdateAreaRelation(AreaRelation areaRelation);

    /**
     * 查询大区种类
     * @return
     */
    List queryDistinctAreaRelation();
}
