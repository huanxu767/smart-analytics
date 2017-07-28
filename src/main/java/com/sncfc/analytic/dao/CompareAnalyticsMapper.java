package com.sncfc.analytic.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/6/5.
 */
@Repository
public interface CompareAnalyticsMapper {
    /**
     * 查询达成值
     * @param yearNum
     * @param quarterNum
     * @return
     */
    Map querytdmhqd(@Param("yearNum")int yearNum, @Param("quarterNum")int quarterNum);

    /**
     * 查询总部指标配置
     * @param yearNum
     * @param quarterNum
     * @return
     */
    Map queryCenterTargetConfig(@Param("yearNum")int yearNum, @Param("quarterNum")int quarterNum);

    /**
     * 查询第三方产品明细
     * @param beginDate
     * @param endDate
     * @return
     */
    List queryThirdParty(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

    /**
     * 总投放 图1
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    Map queryTotalLendTFChart(@Param("beginDate")String beginDate,@Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);

    /**
     * 总投放 表格
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @param idNewFlag
     * @return
     */
    Map queryTotalLendTBChart(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate, @Param("isNewFlag")String idNewFlag);

    /**
     * 客户申请分析
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryCustApplyTable(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("flag")String flag);
    /**
     * 分渠道-使用人数
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryCusts(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

    /**
     * 分渠道图表
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryCustChannelChart(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

    /**
     * 客户渗透率图表
     * @param beginDate
     * @param endDate
     * @return
     */
    Map queryCustStlChart(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

    /**
     * 随借随还客户渗透率图表
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> querySjshTfChart(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
    /**
     * 随借随还客户额度使用图表
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> querySjshEdyxChart(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
    /**
     * 随借随还营销活动明细图表
     * @param params
     * @return
     */
    List<Map> queryTicketActivityName(Map params);
    /**
     * 现金分期客户额度使用图表
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryCashEdyxChart(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

    /**
     * 随借随还 客群分析 首购与复购
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryAnyGroupNewAndOld(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
    /**
     * 随借随还 客群分析 是否苏宁员工
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryAnyGroupSuningOrNot(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
    /**
     * 随借随还 非易购员工来源渠道
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryAnyCustChannel(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
    /**
     * 现金分期 非易购员工来源渠道
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryCashInstallChannel(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
    /**
     * 现金分期 客群分析 是否苏宁员工
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryCashInstallSuningOrNot(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
    /**
     * 现金分期 客群分析 首购与复购
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryCashGroupNewAndOld(@Param("beginDate")String beginDate, @Param("endDate")String endDate);
    /**
     * 查询现金分期分期投放信息
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    List<Map> queryCashLoanData(@Param("beginDate")String beginDate, @Param("endDate")String endDate, @Param("compareBeginDate")String compareBeginDate, @Param("compareEndDate")String compareEndDate);
    /**
     * 现金分期 客群分析 客单分析
     * @param beginDate
     * @param endDate
     * @return
     */
    List<Map> queryCashCustChannel(@Param("beginDate")String beginDate, @Param("endDate")String endDate);

    /**
     * 查询达成率区域达成及排名基础数据
     * @param yearNum
     * @param quarterNum
     * @return
     */
    List<Map> queryRateTargetData(@Param("yearNum")int yearNum, @Param("quarterNum")int quarterNum);

    /**
     * 查询达成率权重
     * @return
     */
    List<Map> queryRateTargetWeightData();
    /**
     * 总投放 首购复购
     * @param beginDate
     * @param endDate
     * @return
     */
    Map queryTotalLend(@Param("beginDate")String beginDate, @Param("endDate")String endDate,@Param("isNewFlag")String isNewFlag);
    /**
     * 总投放 产品分析
     * @param beginDate
     * @param endDate
     * @return
     */
    Map queryTotalLendProduct(@Param("beginDate")String beginDate, @Param("endDate")String endDate);


}
