package com.sncfc.analytic.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by bspei on 2017/6/7.
 */
public interface StoreAnalyticsMapper {
    /**
     * 查询易购门店分期投放信息
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryStoreLoanData(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);

    /**
     * 查询易购门店品类构成分析数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryStoreDeptData(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);

    /**
     * 查询易购投放人数--计算渗透率
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryYgLoanCnt(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);

    /**
     * 查询易购门店人员构成数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryStoreEmpCnt(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);

    /**
     * 查询非易购员工来源渠道数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryNotYgDataCntByChannel(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);

    /**
     * 查询新老客户渠道信息
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryNewOrOldCustomDataByChannel(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);

    /**
     * 查询品类投放及易购销售数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryLoanDataByDept(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);
}
