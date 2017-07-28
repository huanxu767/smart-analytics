package com.sncfc.analytic.service;

import java.util.List;
import java.util.Map;

/**
 * Created by bspei on 2017/6/7.
 */
public interface IOnLineAnalyticsService {
    /**
     * 查询投放金额
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryLoanAmtData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 查询营销策略
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryMarketingStrategyData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 查询品类构成
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryDeptConstituteData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 查询客户渗透率
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryCustomPermeabilityData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);

    /**
     * 查询人员构成数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryPersonConstituteData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 查询非易购员工来源渠道数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryPersonNotYgChannelData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 查询新老客户渠道数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryNewOrOldCustomDataByChannelData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 查询营销能力分析数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryMarketingAnalysisData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
}
