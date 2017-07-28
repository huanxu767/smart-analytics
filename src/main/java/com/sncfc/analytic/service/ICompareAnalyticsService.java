package com.sncfc.analytic.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/6/5.
 */
public interface ICompareAnalyticsService {
    /**
     * 达成率--达成情况及预测
     * @param year
     * @param quarter
     * @return
     */
    Map queryRateTargetChart(int year, int quarter);

    /**
     * 第三方产品明细
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryThirdPartyTable(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 总投放 图1
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryTotalLendTFChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate);

    /**
     * 客户申请分析（辅助分析）表格
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryCustApplyTable(String beginDate, String endDate, String compareBeginDate, String compareEndDate,String flag);
    /**
     * 分渠道图表
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryCustApplyChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 客户渗透率图表
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryCustStlChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 随借随还客户渗透率图表
     * @param beginDate
     * @param endDate
     * @return
     */
    Map querySjshTfChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate);

    /**
     * 随借随还-额度影响
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map querySjshEdyxChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate);

    /**
     * 随借随还-营销活动明细
     * @param params
     * @return
     */
    PageInfo<Map> queryTicketActivityName(Map params);

    /**
     * 现金分期-额度影响
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryCashEdyxChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 随借随还-客群分析 首购与复购
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryAnyGroupNewAndOld(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 随借随还-客群分析是否苏宁员工
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryAnyGroupSuningOrNot(String beginDate, String endDate, String compareBeginDate, String compareEndDate);

    /**
     * 随借随还-客群分析 非苏宁员工来源渠道
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryAnyCustChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate);

    /**
     * 现金分期-客群分析 非苏宁员工来源渠道
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryCashInstallChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 现金分期-客群分析是否苏宁员工
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryCashInstallSuningOrNot(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 现金分期-客群分析首购复购
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryCashGroupNewAndOld(String beginDate, String endDate, String compareBeginDate, String compareEndDate);

    /**
     * 现金分期-营销策略分析
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryCashLoanData(String beginDate, String endDate, String compareBeginDate, String compareEndDate);

    /**
     * 现金分期-客单分析
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryCashCustChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate);
    /**
     * 总投放--首购复购
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryTotalLend(String beginDate, String endDate, String compareBeginDate, String compareEndDate, String idNewFlag);

    /**
     * 达成率--区域达成及排名
     * @param year
     * @param quarter
     * @return
     */
    List<Map> queryRateTargetTable(int year, int quarter);
}
