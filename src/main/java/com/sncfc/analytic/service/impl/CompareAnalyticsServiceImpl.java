package com.sncfc.analytic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sncfc.analytic.dao.CompareAnalyticsMapper;
import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.service.ICompareAnalyticsService;
import com.sncfc.analytic.util.DateUtil;
import com.sncfc.analytic.util.Format;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 月指标维护
 */
@Service
@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
public class CompareAnalyticsServiceImpl implements ICompareAnalyticsService {

    @Autowired
    private CompareAnalyticsMapper compareAnalyticsMapper;

    @Override
    public Map queryRateTargetChart(int yearNum, int quarterNum) {
        Map resultMap = new HashMap();
        int days = DateUtil.quarterDays(yearNum,quarterNum);
        int dayOfYear = DateUtil.dayOfYear(yearNum);
        int year = DateUtil.year();
        boolean flag = false;
        Map<String,BigDecimal> actMap = compareAnalyticsMapper.querytdmhqd(yearNum,quarterNum);
        Map<String,BigDecimal> configMap = compareAnalyticsMapper.queryCenterTargetConfig(yearNum,quarterNum);
        List actLvList = new ArrayList();
        List targetLvList = new ArrayList();
        if(actMap == null || configMap == null){
            throw new ResultException("没有记录");
        }
//        如果去年 或者 本年前季度
        if(yearNum < year || days < dayOfYear){
            flag = true;
        }
        String[] names = {"总投放","购物贷","现金贷","线上购物贷","门店购物贷","第三方","随借随还","现金分期"};
        for (String key : names) {
            if(StringUtils.isEmpty(actMap.get(key))){
                actLvList.add(0);
                targetLvList.add(0);
            }else{
                Double actValue = Double.parseDouble(actMap.get(key).toString());
                Double configValue = configMap.get(key).doubleValue() * 10000;
                actLvList.add(Format.maximumFractionDigitsOne(actValue*100/configValue));
                if(flag){
                    targetLvList.add(Format.maximumFractionDigitsOne(actValue*100/configValue));
                }else {
                    targetLvList.add(Format.maximumFractionDigitsOne(actValue * 100 / dayOfYear * days / configValue));
                }
            }
        }
        resultMap.put("actLvList",actLvList);
        resultMap.put("targetLvList",targetLvList);
        resultMap.put("names",names);
        return resultMap;
    }

    @Override
    public List<Map> queryThirdPartyTable(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate,endDate,compareBeginDate,compareEndDate);
        List<Map> productList = compareAnalyticsMapper.queryThirdParty(beginDate,endDate);
        List<Map> compareProductList = compareAnalyticsMapper.queryThirdParty(compareBeginDate,compareEndDate);
        List<Map> resultList = new ArrayList<>();
        for (Map tempMap : productList) {
            String prodidOther = tempMap.get("PRODID_OTHER").toString();
            for (Map compareMap : compareProductList) {
                if(prodidOther.equals(compareMap.get("PRODID_OTHER"))){
                    //同期也有记录
                    tempMap.put("COMPTFJE",compareMap.get("TFJE"));
                    tempMap.put("COMPTFRS",compareMap.get("TFRS"));
                    tempMap.put("COMPKDJ",compareMap.get("KDJ"));
                    //投放增长率
                    tempMap.put("TFZZL",Format.upRateStringNoValid(tempMap.get("TFJE").toString(),tempMap.get("COMPTFJE").toString()));
                    //人数增长率
                    tempMap.put("RSZZL",Format.upRateStringNoValid(tempMap.get("TFRS").toString(),tempMap.get("COMPTFRS").toString()));
                    //客单价增长率
                    tempMap.put("KDJZZL",Format.upRateStringNoValid(tempMap.get("KDJ").toString(),tempMap.get("COMPKDJ").toString()));
                }
            }
            resultList.add(tempMap);
        }

        for (Map compareMap : compareProductList) {
            boolean flag = true;
            for (Map productMap : productList) {
                if(compareMap.get("PRODID_OTHER").equals(productMap.get("PRODID_OTHER"))){
                    //当前值已处理
                    flag = false;
                    break;
                }
            }
            if(flag){
                Map comMap = new HashMap();
                comMap.put("PRODID_OTHER",compareMap.get("PRODID_OTHER"));
                comMap.put("COMPTFJE",compareMap.get("TFJE"));
                comMap.put("COMPTFRS",compareMap.get("TFRS"));
                comMap.put("COMPKDJ",compareMap.get("KDJ"));
                resultList.add(comMap);
            }
        }
        return resultList;
    }



    @Override
    public List<Map> queryCustApplyTable(String beginDate, String endDate, String compareBeginDate, String compareEndDate,String flag) {
        validateParams(beginDate,endDate,compareBeginDate,compareEndDate);
        //新客
        List<Map> custApplyList = compareAnalyticsMapper.queryCustApplyTable(beginDate,endDate,flag);
        List<String> qdList = new ArrayList<>();
        List<Map> result = new ArrayList<>();
        for (Map tempMap : custApplyList) {
            if(!qdList.contains(tempMap.get("CUST_CHNL"))){
                qdList.add(tempMap.get("CUST_CHNL").toString()) ;
            }
        }
        for (String channel : qdList) {
            Map row = new HashMap();
            row.put("CUST_CHNL",channel);
            for (Map tempMap : custApplyList) {
                if(tempMap.values().contains(channel)){
                    row.put(tempMap.get("CODE")+"_TOTAL",tempMap.get("TOTAL_NUM"));
                }
            }
            result.add(row);
        }
//        申请成功客户/申请客户
        for (Map temp : result) {
            if(temp.get("CUST_APPLY_TOTAL") != null && !"0".equals(temp.get("CUST_APPLY_TOTAL").toString())
                    && temp.get("CUST_SUCAPY_TOTAL") != null ){
                temp.put("SQTGL",Format.division(temp.get("CUST_SUCAPY_TOTAL").toString(),temp.get("CUST_APPLY_TOTAL").toString(),"-"));
            }else{
                temp.put("SQTGL","-");
            }
        }
//        使用客户/开通成功客户
        for (Map temp : result) {
            if(temp.get("CUST_SUCOPN_TOTAL") != null && !"0".equals(temp.get("CUST_SUCOPN_TOTAL").toString())
                    && temp.get("CUST_USE_TOTAL") != null ){
                temp.put("SYL",Format.division(temp.get("CUST_USE_TOTAL").toString(),temp.get("CUST_SUCOPN_TOTAL").toString(),"-"));
            }else{
                temp.put("SYL","-");
            }
        }
//        开通成功客户/申请开通客户
        for (Map temp : result) {
            if(temp.get("CUST_OPEN_TOTAL") != null && !"0".equals(temp.get("CUST_OPEN_TOTAL").toString())
                    && temp.get("CUST_SUCOPN_TOTAL") != null ){
                temp.put("KTCGL",Format.division(temp.get("CUST_SUCOPN_TOTAL").toString(),temp.get("CUST_OPEN_TOTAL").toString(),"-"));
            }else{
                temp.put("KTCGL","-");
            }
        }
        return result;
    }

    @Override
    public Map queryCustApplyChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate,endDate,compareBeginDate,compareEndDate);
        List<Map> serachList = compareAnalyticsMapper.queryCustChannelChart(beginDate,endDate);
        List<Map> havedList = compareAnalyticsMapper.queryCustChannelChart(beginDate,null);
        System.out.println(havedList);
        List<Map> compareList = compareAnalyticsMapper.queryCustChannelChart(compareBeginDate,compareEndDate);
        Map result = new HashMap();
        for (Map map:serachList) {
            result.put(map.get("CODE"),map.get("TOTAL_NUM"));
        }
        for (Map map:havedList) {
            result.put("HAVE_"+map.get("CODE"),Format.wan(map.get("TOTAL_NUM")));
        }
        for (Map map:compareList) {
            result.put("COM_"+map.get("CODE"),map.get("TOTAL_NUM"));
        }
        result.put("KTCG_ZZ",Format.upRateStringNoValid(result.get("CUST_SUCOPN").toString(),result.get("COM_CUST_SUCOPN").toString()));
        result.put("KTCG_KTL",Format.division(result.get("CUST_SUCOPN").toString(),Format.plus(result.get("CUST_OPEN"),result.get("CUST_AUTOPN"),"-")));
        result.put("SQKT_ZZ",Format.upRateStringNoValid(result.get("CUST_OPEN").toString(),result.get("COM_CUST_OPEN").toString()));
        result.put("ZDKT_ZZ",Format.upRateStringNoValid(result.get("CUST_AUTOPN").toString(),result.get("COM_CUST_AUTOPN").toString()));
        result.put("SQCG_ZZ",Format.upRateStringNoValid(result.get("CUST_SUCAPY").toString(),result.get("COM_CUST_SUCAPY").toString()));
        result.put("SQ_KTL",Format.division(result.get("CUST_SUCAPY").toString(),result.get("CUST_APPLY").toString()));
        result.put("SQYH_ZZ",Format.upRateStringNoValid(result.get("CUST_APPLY").toString(),result.get("COM_CUST_APPLY").toString()));

        result.put("CUST_ZZ",Format.upRateStringNoValid(result.get("CUST_PRECRD").toString(),result.get("COM_CUST_PRECRD").toString()));

        return result;
    }

    @Override
    public Map queryCustStlChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate,endDate,compareBeginDate,compareEndDate);
        Map result = new HashMap();
        Map searchMap = compareAnalyticsMapper.queryCustStlChart(beginDate,endDate);
        Map compareMap = compareAnalyticsMapper.queryCustStlChart(compareBeginDate,compareEndDate);
        List<String> searchList = new ArrayList<>();
        List<String> oldList = new ArrayList<>();
        searchList.add(Format.division(searchMap.get("CUST_RXFYG").toString(),searchMap.get("CUST_YG").toString(),"0"));
        searchList.add(Format.division(searchMap.get("CUST_YGRXF").toString(),searchMap.get("CUST_YG").toString(),"0"));
        searchList.add(Format.division(searchMap.get("CUST_RXFYG").toString(),searchMap.get("CUST_YGRXF").toString(),"0"));

        oldList.add(Format.division(compareMap.get("CUST_RXFYG").toString(),compareMap.get("CUST_YG").toString(),"0"));
        oldList.add(Format.division(compareMap.get("CUST_YGRXF").toString(),compareMap.get("CUST_YG").toString(),"0"));
        oldList.add(Format.division(compareMap.get("CUST_RXFYG").toString(),compareMap.get("CUST_YGRXF").toString(),"0"));

        result.put("searchList",searchList);
        result.put("oldList",oldList);
        return result;
    }

    @Override
    public Map querySjshTfChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate,endDate,compareBeginDate,compareEndDate);
        Map result = new HashMap();
        List<Map> searchList = compareAnalyticsMapper.querySjshTfChart(beginDate,endDate);
        List<Map> compareList = compareAnalyticsMapper.querySjshTfChart(compareBeginDate,compareEndDate);


        //常规金额
        String loanAll = "0";
        //常规人数
        String loanAllCust = "0";
        //活动金额
        String acLoanAll = "0";
        //活动人数
        String acLoanAllCust = "0";

        for (Map temp : searchList) {
            if("0".equals(temp.get("IS_PROMOTION_FLAG").toString())){
                //常规
                loanAll = Format.wan(StringUtils.isEmpty(temp.get("LOAN_ANY"))?"0":temp.get("LOAN_ANY").toString());
                loanAllCust = StringUtils.isEmpty(temp.get("LOAN_ANY_CUST"))?"0":temp.get("LOAN_ANY_CUST").toString();
            }
            if("1".equals(temp.get("IS_PROMOTION_FLAG").toString())){
                //活动
                acLoanAll = Format.wan(StringUtils.isEmpty(temp.get("LOAN_ANY"))?"0":temp.get("LOAN_ANY").toString());
                acLoanAllCust = StringUtils.isEmpty(temp.get("LOAN_ANY_CUST"))?"0":temp.get("LOAN_ANY_CUST").toString();
            }
        }
//        对比期常规金额
        String oloanAll = "0";
        //        对比期常规人数
        String oloanAllCust = "0";
        String oacLoanAll = "0";
        String oacLoanAllCust = "0";
        for (Map temp : compareList) {
            if("0".equals(temp.get("IS_PROMOTION_FLAG").toString())){
                //常规
                oloanAll = Format.wan(StringUtils.isEmpty(temp.get("LOAN_ANY"))?"0":temp.get("LOAN_ANY").toString());
                oloanAllCust = StringUtils.isEmpty(temp.get("LOAN_ANY_CUST"))?"0":temp.get("LOAN_ANY_CUST").toString();
            }
            if("1".equals(temp.get("IS_PROMOTION_FLAG").toString())){
                //活动
                oacLoanAll = Format.wan(StringUtils.isEmpty(temp.get("LOAN_ANY"))?"0":temp.get("LOAN_ANY").toString());
                oacLoanAllCust = StringUtils.isEmpty(temp.get("LOAN_ANY_CUST"))?"0":temp.get("LOAN_ANY_CUST").toString();
            }
        }
        List<String> loanList = new ArrayList<>();
        List<String> compareLoanList = new ArrayList<>();
        List<String> compareUpList = new ArrayList<>();
        loanList.add(loanAll);
        loanList.add(acLoanAll);
        compareLoanList.add(oloanAll);
        compareLoanList.add(oacLoanAll);
        compareUpList.add(Format.upRateStringNoValid(loanAll,oloanAll,"0"));
        compareUpList.add(Format.upRateStringNoValid(acLoanAllCust,oacLoanAllCust,"0"));
        result.put("loanList",loanList);
        result.put("compareLoanList",compareLoanList);
        result.put("compareUpList",compareUpList);


        List<String> loanCustList = new ArrayList<>();
        List<String> compareLoanCustList = new ArrayList<>();
        List<String> compareUpCustList = new ArrayList<>();
        loanCustList.add(loanAllCust);
        loanCustList.add(acLoanAllCust);
        compareLoanCustList.add(oloanAllCust);
        compareLoanCustList.add(oacLoanAllCust);
        compareUpCustList.add(Format.upRateStringNoValid(loanAllCust,oloanAllCust,"0"));
        compareUpCustList.add(Format.upRateStringNoValid(acLoanAllCust,oacLoanAllCust,"0"));
        result.put("peopleLoanList",loanCustList);
        result.put("peopleCompareLoanList",compareLoanCustList);
        result.put("peopleCompareUpList",compareUpCustList);
        return result;
    }

    @Override
    public Map querySjshEdyxChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        Map result = new HashMap();
        List<Map> searchList = compareAnalyticsMapper.querySjshEdyxChart(beginDate, endDate);
        List<Map> compareList = compareAnalyticsMapper.querySjshEdyxChart(compareBeginDate, compareEndDate);
        //X坐标
        List<String> names =new ArrayList<>();
        //人数列表
        List<String> searchCustList =new ArrayList<>();
        //同比期人数列表
        List<String> compareCustList =new ArrayList<>();
        //使用人数增长率
        List<String> custUpList =new ArrayList<>();

        //平均金额列表
        List<String> searchCustAvgList =new ArrayList<>();
        //同比期平均金额列表
        List<String> compareCustAvgList =new ArrayList<>();
        //金额增长率
        List<String> custUpAvgList =new ArrayList<>();

        //使用率
        List<String> searchCustSylList =new ArrayList<>();
        //同比期使用率列表
        List<String> compareCustSylList =new ArrayList<>();

        for (Map searchMap:searchList) {
            names.add(searchMap.get("CRED").toString());
        }

        for (String name : names) {
            //人数
            String loanAnyCust = "0";
            String compareLoanAnyCust= "0";
            String totalCust = "0";
            //金额
            String loanAny = "0";
            String compareLoanAny= "0";
            String compareTotalCust = "0";

            for (Map searchMap:searchList) {
                if(name.equals(searchMap.get("CRED").toString())){
                    loanAnyCust = searchMap.get("LOAN_ANY_CUST").toString();
                    loanAny = searchMap.get("LOAN_ANY").toString();
                    totalCust = searchMap.get("LOAN_ALL_CUST").toString();
                }
            }
            for (Map compareMap:compareList) {
                if(name.equals(compareMap.get("CRED").toString())){
                    compareLoanAnyCust = compareMap.get("LOAN_ANY_CUST").toString();
                    compareLoanAny = compareMap.get("LOAN_ANY").toString();
                    compareTotalCust = compareMap.get("LOAN_ALL_CUST").toString();
                }
            }
            //人数
            searchCustList.add(loanAnyCust);
            compareCustList.add(compareLoanAnyCust);
            custUpList.add(Format.upRateStringNoValid(loanAnyCust,compareLoanAnyCust,"0"));
            //金额
            searchCustAvgList.add(Format.divisionOnly(loanAny,loanAnyCust,"0"));
            compareCustAvgList.add(Format.divisionOnly(compareLoanAny,compareLoanAnyCust,"0"));
            custUpAvgList.add(Format.upRateStringNoValid(Format.divisionOnly(loanAny,loanAnyCust,"0"),Format.divisionOnly(compareLoanAny,compareLoanAnyCust,"0"),"0"));
            //使用率
            searchCustSylList.add(Format.division(loanAnyCust,totalCust,"0"));
            compareCustSylList.add(Format.division(compareLoanAnyCust,compareTotalCust,"0"));
        }

        result.put("names",names);
        //人数
        result.put("searchCustList",searchCustList);
        result.put("compareCustList",compareCustList);
        result.put("custUpList",custUpList);
        //金额
        result.put("searchCustAvgList",searchCustAvgList);
        result.put("compareCustAvgList",compareCustAvgList);
        result.put("custUpAvgList",custUpAvgList);
        //使用率
        result.put("searchCustSylList",searchCustSylList);
        result.put("compareCustSylList",compareCustSylList);
        return result;
    }

    @Override
    public PageInfo<Map> queryTicketActivityName(Map params) {
        Assert.notNull(params.get("pageNum"));
        PageHelper.startPage(Integer.parseInt(params.get("pageNum").toString()), 10);
        PageInfo<Map> centerCompanies = new PageInfo<>(compareAnalyticsMapper.queryTicketActivityName(params));
        return centerCompanies;
    }


    @Override
    public Map queryCashEdyxChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        Map result = new HashMap();
        List<Map> searchList = compareAnalyticsMapper.queryCashEdyxChart(beginDate, endDate);
        System.out.println("searchList:"+searchList);
        List<Map> compareList = compareAnalyticsMapper.queryCashEdyxChart(compareBeginDate, compareEndDate);
        System.out.println("compareList:"+compareList);

        //X坐标
        List<String> names =new ArrayList<>();
        //人数列表
        List<String> searchCustList =new ArrayList<>();
        //同比期人数列表
        List<String> compareCustList =new ArrayList<>();
        //使用人数增长率
        List<String> custUpList =new ArrayList<>();

        //平均金额列表
        List<String> searchCustAvgList =new ArrayList<>();
        //同比期平均金额列表
        List<String> compareCustAvgList =new ArrayList<>();
        //金额增长率
        List<String> custUpAvgList =new ArrayList<>();

        //使用率
        List<String> searchCustSylList =new ArrayList<>();
        //同比期使用率列表
        List<String> compareCustSylList =new ArrayList<>();

        for (Map searchMap:searchList) {
            names.add(searchMap.get("CRED").toString());
        }

        for (String name : names) {
            //人数
            String loanAnyCust = "0";
            String compareLoanAnyCust= "0";
            String totalCust = "0";
            //金额
            String loanAny = "0";
            String compareLoanAny= "0";
            String compareTotalCust = "0";

            for (Map searchMap:searchList) {
                if(name.equals(searchMap.get("CRED").toString())){
                    loanAnyCust = searchMap.get("LOAN_INSTALL_CUST").toString();
                    loanAny = searchMap.get("LOAN_INSTALL").toString();
                    totalCust = searchMap.get("LOAN_ALL_CUST").toString();
                }
            }
            for (Map compareMap:compareList) {
                if(name.equals(compareMap.get("CRED").toString())){
                    compareLoanAnyCust = compareMap.get("LOAN_INSTALL_CUST").toString();
                    compareLoanAny = compareMap.get("LOAN_INSTALL").toString();
                    compareTotalCust = compareMap.get("LOAN_ALL_CUST").toString();
                }
            }
            //人数
            searchCustList.add(loanAnyCust);
            compareCustList.add(compareLoanAnyCust);
            custUpList.add(Format.upRateStringNoValid(loanAnyCust,compareLoanAnyCust,"0"));
            //金额
            searchCustAvgList.add(Format.divisionOnly(loanAny,loanAnyCust,"0"));
            compareCustAvgList.add(Format.divisionOnly(compareLoanAny,compareLoanAnyCust,"0"));
            custUpAvgList.add(Format.upRateStringNoValid(Format.divisionOnly(loanAny,loanAnyCust,"0"),Format.divisionOnly(compareLoanAny,compareLoanAnyCust,"0"),"0"));
            //使用率
            searchCustSylList.add(Format.division(loanAnyCust,totalCust,"0"));
            compareCustSylList.add(Format.division(compareLoanAnyCust,compareTotalCust,"0"));
        }

        System.out.println("searchCustList:"+searchCustList);
        System.out.println("compareCustList:"+compareCustList);
        System.out.println("custUpList:"+custUpList);

        result.put("names",names);
        //人数
        result.put("searchCustList",searchCustList);
        result.put("compareCustList",compareCustList);
        result.put("custUpList",custUpList);
        //金额
        result.put("searchCustAvgList",searchCustAvgList);
        result.put("compareCustAvgList",compareCustAvgList);
        result.put("custUpAvgList",custUpAvgList);
        //使用率
        result.put("searchCustSylList",searchCustSylList);
        result.put("compareCustSylList",compareCustSylList);
        return result;
    }

    @Override
    public Map queryAnyGroupNewAndOld(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        Map result = new HashMap();
        List<Map> searchList = compareAnalyticsMapper.queryAnyGroupNewAndOld(beginDate, endDate);
        List<Map> compareList = compareAnalyticsMapper.queryAnyGroupNewAndOld(compareBeginDate, compareEndDate);
        //首购客户人数
        String firstBuyCust = "0";
        //复购客户人数
        String againBuyCust = "0";
        //首购客户人数-对比期
        String firstBuyCustCompare = "0";
        //复购客户人数-对比期
        String againBuyCustCompare = "0";

        //首购客户人数增长
        String firstBuyCustUp = "0";
        //复购客户人数增长
        String againBuyCustUp = "0";
        //首购客户人数占比-查询期
        String firstBuyCustPersent = "0";
        //复购客户人数占比-查询期
        String againBuyCustPersent = "0";
        //首购客户人数占比-查询期
        String firstBuyCustPersentCompare = "0";
        //复购客户人数占比-查询期
        String againBuyCustPersentCompare = "0";
        for (Map temp : searchList) {
            if("SHOUGOU".equals(temp.get("TYPE").toString())){
                firstBuyCust = temp.get("LOAN_ANY_CUST").toString();
            }
            if("FUGOU".equals(temp.get("TYPE").toString())){
                againBuyCust = temp.get("LOAN_ANY_CUST").toString();
            }
        }
        for (Map temp : compareList) {
            if("SHOUGOU".equals(temp.get("TYPE").toString())){
                firstBuyCustCompare = temp.get("LOAN_ANY_CUST").toString();
            }
            if("FUGOU".equals(temp.get("TYPE").toString())){
                againBuyCustCompare = temp.get("LOAN_ANY_CUST").toString();
            }
        }

        firstBuyCustUp = Format.upRateStringNoValid(firstBuyCust,firstBuyCustCompare,"0");
        againBuyCustUp = Format.upRateStringNoValid(againBuyCust,againBuyCustCompare,"0");
        firstBuyCustPersent = Format.division(firstBuyCust,(Double.parseDouble(firstBuyCust)+ Double.parseDouble(againBuyCust))+"","0");
        againBuyCustPersent = Format.division(againBuyCust,(Double.parseDouble(firstBuyCust)+ Double.parseDouble(againBuyCust))+"","0");

        firstBuyCustPersentCompare = Format.division(firstBuyCustCompare,(Double.parseDouble(firstBuyCustCompare)+ Double.parseDouble(againBuyCustCompare))+"","0");
        againBuyCustPersentCompare = Format.division(againBuyCustCompare,(Double.parseDouble(firstBuyCustCompare)+ Double.parseDouble(againBuyCustCompare))+"","0");

        result.put("firstBuyCust",firstBuyCust);
        result.put("againBuyCust",againBuyCust);
        result.put("firstBuyCustUp",firstBuyCustUp);
        result.put("againBuyCustUp",againBuyCustUp);
        result.put("firstBuyCustPersent",firstBuyCustPersent);
        result.put("againBuyCustPersent",againBuyCustPersent);
        result.put("firstBuyCustPersentCompare",firstBuyCustPersentCompare);
        result.put("againBuyCustPersentCompare",againBuyCustPersentCompare);
        //人数列表
        List<String> searchCustList =new ArrayList<>();
        //同比期人数列表
        List<String> compareCustList =new ArrayList<>();
        //使用人数增长率
        List<String> custUpList =new ArrayList<>();
        searchCustList.add(firstBuyCust);
        searchCustList.add(againBuyCust);
        compareCustList.add(firstBuyCustCompare);
        compareCustList.add(againBuyCustCompare);
        custUpList.add(firstBuyCustUp);
        custUpList.add(againBuyCustUp);

        result.put("searchCustList",searchCustList);
        result.put("compareCustList",compareCustList);
        result.put("custUpList",custUpList);
        return result;
    }

    public Map queryAnyGroupSuningOrNot(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        Map result = new HashMap();
        List<Map> searchList = compareAnalyticsMapper.queryAnyGroupSuningOrNot(beginDate, endDate);
        List<Map> compareList = compareAnalyticsMapper.queryAnyGroupSuningOrNot(compareBeginDate, compareEndDate);
        //苏宁客户人数
        String firstBuyCust = "0";
        //非苏宁客户人数
        String againBuyCust = "0";
        //苏宁客户人数-对比期
        String firstBuyCustCompare = "0";
        //非苏宁客户人数-对比期
        String againBuyCustCompare = "0";

        //苏宁客户人数增长
        String firstBuyCustUp = "0";
        //非苏宁客户人数增长
        String againBuyCustUp = "0";

        for (Map temp : searchList) {
            if("SUNING".equals(temp.get("TYPE").toString())){
                firstBuyCust = temp.get("TOTAL_NUM").toString();
            }
            if("NOTSUNING".equals(temp.get("TYPE").toString())){
                againBuyCust = temp.get("TOTAL_NUM").toString();
            }
        }
        for (Map temp : compareList) {
            if("SUNING".equals(temp.get("TYPE").toString())){
                firstBuyCustCompare = temp.get("TOTAL_NUM").toString();
            }
            if("NOTSUNING".equals(temp.get("TYPE").toString())){
                againBuyCustCompare = temp.get("TOTAL_NUM").toString();
            }
        }

        firstBuyCustUp = Format.upRateStringNoValid(firstBuyCust,firstBuyCustCompare,"0");
        againBuyCustUp = Format.upRateStringNoValid(againBuyCust,againBuyCustCompare,"0");

        //人数列表
        List<String> searchCustList =new ArrayList<>();
        //同比期人数列表
        List<String> compareCustList =new ArrayList<>();
        //使用人数增长率
        List<String> custUpList =new ArrayList<>();
        searchCustList.add(firstBuyCust);
        searchCustList.add(againBuyCust);
        compareCustList.add(firstBuyCustCompare);
        compareCustList.add(againBuyCustCompare);
        custUpList.add(firstBuyCustUp);
        custUpList.add(againBuyCustUp);

        result.put("searchCustList",searchCustList);
        result.put("compareCustList",compareCustList);
        result.put("custUpList",custUpList);
        return result;
    }
    public Map queryAnyCustChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        List<Map> searchList = compareAnalyticsMapper.queryAnyCustChannel(beginDate, endDate);
        List<Map> compareList = compareAnalyticsMapper.queryAnyCustChannel(compareBeginDate, compareEndDate);
        return chart(searchList,compareList);
    }
    @Override
    public Map queryCashInstallChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        List<Map> searchList = compareAnalyticsMapper.queryCashInstallChannel(beginDate, endDate);
        List<Map> compareList = compareAnalyticsMapper.queryCashInstallChannel(compareBeginDate, compareEndDate);
        return chart(searchList,compareList);
    }

    private static Map chart(List<Map> searchList,List<Map> compareList){
        Map result = new HashMap();
        List<String> names = new ArrayList<>();
        List<String> searchVal = new ArrayList<>();
        List<String> compareVal = new ArrayList<>();
        List<String> up = new ArrayList<>();

        for (Map tempMap : searchList) {
            if(!names.contains(tempMap.get("CUST_CHNL"))){
                names.add(tempMap.get("CUST_CHNL").toString()) ;
            }
        }
        for (String channel : names) {
            String seaVal ="0";
            String compVal ="0";
            for (Map searchTemp : searchList) {
                if(channel.equals(searchTemp.get("CUST_CHNL").toString())){
                    seaVal = searchTemp.get("TOTAL_NUM").toString();
                }
            }
            for (Map compareTemp : compareList) {
                if(channel.equals(compareTemp.get("CUST_CHNL").toString())){
                    compVal = compareTemp.get("TOTAL_NUM").toString();
                }
            }
            searchVal.add(seaVal);
            compareVal.add(compVal);
            up.add(Format.upRateStringNoValid(seaVal,compVal,"0"));
        }
        result.put("names",names);
        result.put("searchVal",searchVal);
        result.put("compareVal",compareVal);
        result.put("up",up);
        return result;
    }

    @Override
    public Map queryCashInstallSuningOrNot(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        Map result = new HashMap();
        List<Map> searchList = compareAnalyticsMapper.queryCashInstallSuningOrNot(beginDate, endDate);
        List<Map> compareList = compareAnalyticsMapper.queryCashInstallSuningOrNot(compareBeginDate, compareEndDate);
        //苏宁客户人数
        String firstBuyCust = "0";
        //非苏宁客户人数
        String againBuyCust = "0";
        //苏宁客户人数-对比期
        String firstBuyCustCompare = "0";
        //非苏宁客户人数-对比期
        String againBuyCustCompare = "0";

        //苏宁客户人数增长
        String firstBuyCustUp = "0";
        //非苏宁客户人数增长
        String againBuyCustUp = "0";

        for (Map temp : searchList) {
            if("SUNING".equals(temp.get("TYPE").toString())){
                firstBuyCust = temp.get("TOTAL_NUM").toString();
            }
            if("NOTSUNING".equals(temp.get("TYPE").toString())){
                againBuyCust = temp.get("TOTAL_NUM").toString();
            }
        }
        for (Map temp : compareList) {
            if("SUNING".equals(temp.get("TYPE").toString())){
                firstBuyCustCompare = temp.get("TOTAL_NUM").toString();
            }
            if("NOTSUNING".equals(temp.get("TYPE").toString())){
                againBuyCustCompare = temp.get("TOTAL_NUM").toString();
            }
        }

        firstBuyCustUp = Format.upRateStringNoValid(firstBuyCust,firstBuyCustCompare);
        againBuyCustUp = Format.upRateStringNoValid(againBuyCust,againBuyCustCompare);

        //人数列表
        List<String> searchCustList =new ArrayList<>();
        //同比期人数列表
        List<String> compareCustList =new ArrayList<>();
        //使用人数增长率
        List<String> custUpList =new ArrayList<>();
        searchCustList.add(firstBuyCust);
        searchCustList.add(againBuyCust);
        compareCustList.add(firstBuyCustCompare);
        compareCustList.add(againBuyCustCompare);
        custUpList.add(firstBuyCustUp);
        custUpList.add(againBuyCustUp);

        result.put("searchCustList",searchCustList);
        result.put("compareCustList",compareCustList);
        result.put("custUpList",custUpList);
        return result;
    }

    @Override
    public Map queryCashGroupNewAndOld(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        Map result = new HashMap();
        List<Map> searchList = compareAnalyticsMapper.queryCashGroupNewAndOld(beginDate, endDate);
        List<Map> compareList = compareAnalyticsMapper.queryCashGroupNewAndOld(compareBeginDate, compareEndDate);
        //首购客户人数
        String firstBuyCust = "0";
        //复购客户人数
        String againBuyCust = "0";
        //首购客户人数-对比期
        String firstBuyCustCompare = "0";
        //复购客户人数-对比期
        String againBuyCustCompare = "0";

        //首购客户人数增长
        String firstBuyCustUp = "0";
        //复购客户人数增长
        String againBuyCustUp = "0";
        //首购客户人数占比-查询期
        String firstBuyCustPersent = "0";
        //复购客户人数占比-查询期
        String againBuyCustPersent = "0";
        //首购客户人数占比-查询期
        String firstBuyCustPersentCompare = "0";
        //复购客户人数占比-查询期
        String againBuyCustPersentCompare = "0";
        for (Map temp : searchList) {
            if("SHOUGOU".equals(temp.get("TYPE").toString())){
                firstBuyCust = temp.get("LOAN_INSTALL_CUST").toString();
            }
            if("FUGOU".equals(temp.get("TYPE").toString())){
                againBuyCust = temp.get("LOAN_INSTALL_CUST").toString();
            }
        }
        for (Map temp : compareList) {
            if("SHOUGOU".equals(temp.get("TYPE").toString())){
                firstBuyCustCompare = temp.get("LOAN_INSTALL_CUST").toString();
            }
            if("FUGOU".equals(temp.get("TYPE").toString())){
                againBuyCustCompare = temp.get("LOAN_INSTALL_CUST").toString();
            }
        }

        firstBuyCustUp = Format.upRateStringNoValid(firstBuyCust,firstBuyCustCompare);
        againBuyCustUp = Format.upRateStringNoValid(againBuyCust,againBuyCustCompare);
        firstBuyCustPersent = Format.division(firstBuyCust,(Double.parseDouble(firstBuyCust)+ Double.parseDouble(againBuyCust))+"","0");
        againBuyCustPersent = Format.division(againBuyCust,(Double.parseDouble(firstBuyCust)+ Double.parseDouble(againBuyCust))+"","0");

        firstBuyCustPersentCompare = Format.division(firstBuyCustCompare,(Double.parseDouble(firstBuyCustCompare)+ Double.parseDouble(againBuyCustCompare))+"","0");
        againBuyCustPersentCompare = Format.division(againBuyCustCompare,(Double.parseDouble(firstBuyCustCompare)+ Double.parseDouble(againBuyCustCompare))+"","0");

        result.put("firstBuyCust",firstBuyCust);
        result.put("againBuyCust",againBuyCust);
        result.put("firstBuyCustUp",firstBuyCustUp);
        result.put("againBuyCustUp",againBuyCustUp);
        result.put("firstBuyCustPersent",firstBuyCustPersent);
        result.put("againBuyCustPersent",againBuyCustPersent);
        result.put("firstBuyCustPersentCompare",firstBuyCustPersentCompare);
        result.put("againBuyCustPersentCompare",againBuyCustPersentCompare);
        //人数列表
        List<String> searchCustList =new ArrayList<>();
        //同比期人数列表
        List<String> compareCustList =new ArrayList<>();
        //使用人数增长率
        List<String> custUpList =new ArrayList<>();
        searchCustList.add(firstBuyCust);
        searchCustList.add(againBuyCust);
        compareCustList.add(firstBuyCustCompare);
        compareCustList.add(againBuyCustCompare);
        custUpList.add(firstBuyCustUp);
        custUpList.add(againBuyCustUp);

        result.put("searchCustList",searchCustList);
        result.put("compareCustList",compareCustList);
        result.put("custUpList",custUpList);
        return result;
    }

    @Override
    public Map queryCashLoanData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        List<Map> datas = compareAnalyticsMapper.queryCashLoanData(beginDate, endDate, compareBeginDate, compareEndDate);
        String[] cterms = {"1" ,"3" ,"6" ,"9" ,"12" ,"24", "36" ,"48" ,"60"};
        DecimalFormat df = new DecimalFormat("0.0");

        List<String> names = new ArrayList<String>();
        List<String> normals = new ArrayList<String>();
        List<String> activities = new ArrayList<String>();
        for(int i=0,t=cterms.length; i<t; i++){
            names.add(cterms[i] + "期");

            BigDecimal normalQueryLoan = findData(datas, 0, "chaxun", cterms[i]);
            if(normalQueryLoan == null){
                normals.add("0");
            }else{
                BigDecimal normalCompareLoan = findData(datas, 0, "duibi", cterms[i]);
                if(normalCompareLoan == null || normalCompareLoan.compareTo(BigDecimal.ZERO) == 0){
                    normals.add("0");
                }else{
                    normals.add(df.format(normalQueryLoan.subtract(normalCompareLoan).divide(normalCompareLoan, 3, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
                }
            }

            BigDecimal activityQueryLoan = findData(datas, 1, "chaxun", cterms[i]);
            if(activityQueryLoan == null){
                activities.add("0");
            }else{
                BigDecimal activityCompareLoan = findData(datas, 1, "duibi", cterms[i]);
                if(activityCompareLoan == null || activityCompareLoan.compareTo(BigDecimal.ZERO) == 0){
                    activities.add("0");
                }else{
                    activities.add(df.format(activityQueryLoan.subtract(activityCompareLoan).divide(activityCompareLoan, 3, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
                }
            }

        }

        Map<String, List<String>> result = new HashMap<String, List<String>>();
        result.put("names", names);
        result.put("normals", normals);
        result.put("activities", activities);
        return result;
    }


    private BigDecimal findData(List<Map> datas, int isPromotionFlag, String tp, String cterm){
        if(datas == null || datas.size() == 0){
            return null;
        }

        for(int i=0,t=datas.size(); i<t; i++){
            Map data = datas.get(i);
            if(((BigDecimal)data.get("IS_PROMOTION_FLAG")).compareTo(new BigDecimal(isPromotionFlag)) == 0 && tp.equals(data.get("TP")) && cterm.equals(data.get("CTERM"))){
                return data.get("LOAN_INSTALL") == null ? null : (BigDecimal)data.get("LOAN_INSTALL");
            }
        }
        return null;
    }

    @Override
    public List<Map> queryCashCustChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        validateParams(beginDate, endDate, compareBeginDate, compareEndDate);
        List<Map> searchList = compareAnalyticsMapper.queryCashCustChannel(beginDate, endDate);
        List<Map> compareList = compareAnalyticsMapper.queryCashCustChannel(compareBeginDate, compareEndDate);
        String[] cterms = {"3" ,"6" ,"9" ,"12" ,"24", "36" ,"48" ,"60"};
        List<Map> result = new ArrayList<>();
        for (String cterm:cterms){
            Map tempMap = new HashMap();
            String tfje = "0";
            String comtfje = "0";
            String tfjers = "0";
            String comtfjers = "0";
            for (Map temp : searchList) {
                if(cterm.equals(temp.get("CTERM"))){
                    tfje = temp.get("LOAN_INSTALL").toString();
                    tfjers =  temp.get("LOAN_INSTALL_CUST").toString();
                }
            }
            for (Map temp : compareList) {
                if(cterm.equals(temp.get("CTERM"))){
                    comtfje = temp.get("LOAN_INSTALL").toString();
                    comtfjers = temp.get("LOAN_INSTALL_CUST").toString();
                }
            }
            tempMap.put("cterm",cterm+"期");
            tempMap.put("tfje",Format.wan(tfje));
            tempMap.put("comtfje",Format.wan(comtfje));
            tempMap.put("tfup",Format.upRateStringNoValid(tfje,comtfje,"0"));
            tempMap.put("tfjers",tfjers);
            tempMap.put("comtfjers",comtfjers);
            tempMap.put("tfrsup",Format.upRateStringNoValid(tfjers,comtfjers,"0"));

            String kdj = Format.divisionOnly(tfje,tfjers,"0");
            String comtkdj = Format.divisionOnly(comtfje,comtfjers,"0");

            tempMap.put("kdj",kdj);
            tempMap.put("comtkdj",comtkdj);
            tempMap.put("kdjup",Format.upRateStringNoValid(kdj,comtkdj,"0"));
            result.add(tempMap);
        }
        return result;
    }

    @Override
    public Map queryTotalLend(String beginDate, String endDate, String compareBeginDate, String compareEndDate, String idNewFlag) {
        validateParams(beginDate,endDate,compareBeginDate,compareEndDate);
        Map result = new HashMap();
        Map<String,Object> searchResult = compareAnalyticsMapper.queryTotalLend(beginDate,endDate,idNewFlag);
        Map<String,Object> compareResult = compareAnalyticsMapper.queryTotalLend(compareBeginDate,compareEndDate,idNewFlag);
        result.putAll(searchResult);
        result.put("LOAN_ALL_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ALL_CUST"),compareResult.get("LOAN_ALL_CUST"),"-" ));
        result.put("LOAN_ALL_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ALL"),compareResult.get("LOAN_ALL"),"-" ));

        result.put("GXL",Format.division(searchResult.get("LOAN_ALL"),searchResult.get("TOTAL_AMT"),"-"));
        result.put("LOAN_ALL",Format.wan(result.get("LOAN_ALL")));


        result.put("LOAN_ONLINE_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ONLINE_CUST"),compareResult.get("LOAN_ONLINE_CUST"),"-" ));
        result.put("LOAN_ONLINE_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ONLINE"),compareResult.get("LOAN_ONLINE"),"-" ));
        result.put("LOAN_ONLINE",Format.wan(result.get("LOAN_ONLINE")));


        result.put("LOAN_SHOP_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_SHOP_CUST"),compareResult.get("LOAN_SHOP_CUST"),"-" ));
        result.put("LOAN_SHOP_UP",Format.upRateStringNoValid(searchResult.get("LOAN_SHOP"),compareResult.get("LOAN_SHOP"),"-" ));
        result.put("LOAN_SHOP",Format.wan(result.get("LOAN_SHOP")));

        result.put("LOAN_OTHER_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_OTHER_CUST"),compareResult.get("LOAN_OTHER_CUST"),"-" ));
        result.put("LOAN_OTHER_UP",Format.upRateStringNoValid(searchResult.get("LOAN_OTHER"),compareResult.get("LOAN_OTHER"),"-" ));
        result.put("LOAN_OTHER",Format.wan(result.get("LOAN_OTHER")));



        result.put("LOAN_ANY_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ANY_CUST"),compareResult.get("LOAN_ANY_CUST"),"-" ));
        result.put("LOAN_ANY_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ANY"),compareResult.get("LOAN_ANY"),"-" ));
        result.put("LOAN_ANY",Format.wan(result.get("LOAN_ANY")));

        result.put("LOAN_INSTALL_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_INSTALL_CUST"),compareResult.get("LOAN_INSTALL_CUST"),"-" ));
        result.put("LOAN_INSTALL_UP",Format.upRateStringNoValid(searchResult.get("LOAN_INSTALL"),compareResult.get("LOAN_INSTALL"),"-" ));
        result.put("LOAN_INSTALL",Format.wan(result.get("LOAN_INSTALL")));
        return result;
    }

    @Override
    public Map queryTotalLendTFChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        validateParams(beginDate,endDate,compareBeginDate,compareEndDate);
        Map result = new HashMap();
        Map searchResult = compareAnalyticsMapper.queryTotalLendProduct(beginDate,endDate);
        Map compareResult = compareAnalyticsMapper.queryTotalLendProduct(compareBeginDate,compareEndDate);
        result.putAll(searchResult);

        String kdj = "-";
        String compKdj = "-";
        result.put("LOAN_ALL",Format.wan(searchResult.get("LOAN_ALL")));

        result.put("LOAN_ALL_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ALL"),compareResult.get("LOAN_ALL"),"-" ));
        result.put("LOAN_ALL_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ALL_CUST"),compareResult.get("LOAN_ALL_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("LOAN_ALL"),searchResult.get("LOAN_ALL_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("LOAN_ALL"),compareResult.get("LOAN_ALL_CUST"),"-" );
        result.put("LOAN_ALL_KDJ",kdj);
        result.put("LOAN_ALL_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));

        result.put("LOAN_BUY",Format.wan(searchResult.get("LOAN_BUY")));
        result.put("LOAN_BUY_UP",Format.upRateStringNoValid(searchResult.get("LOAN_BUY"),compareResult.get("LOAN_BUY"),"-" ));
        result.put("LOAN_BUY_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_BUY_CUST"),compareResult.get("LOAN_BUY_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("LOAN_BUY"),searchResult.get("LOAN_BUY_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("LOAN_BUY"),compareResult.get("LOAN_BUY_CUST"),"-" );
        result.put("LOAN_BUY_KDJ",kdj);
        result.put("LOAN_BUY_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));
        //易购整体
        result.put("AMOUT_YG",Format.wan(searchResult.get("AMOUT_YG")));
        result.put("AMOUT_YG_UP",Format.upRateStringNoValid(searchResult.get("AMOUT_YG"),compareResult.get("AMOUT_YG"),"-" ));
        result.put("AMOUT_YG_CUST_UP",Format.upRateStringNoValid(searchResult.get("AMOUT_YG_CUST"),compareResult.get("AMOUT_YG_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("AMOUT_YG"),searchResult.get("AMOUT_YG_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("AMOUT_YG"),compareResult.get("AMOUT_YG_CUST"),"-" );
        result.put("AMOUT_YG_KDJ",kdj);
        result.put("AMOUT_YG_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));
//        易购整体投放金额/苏宁投放金额
        String stl = Format.division(searchResult.get("AMOUT_YG"),searchResult.get("LOAN_RXFSN"),"-" );
        String compStl = Format.division(compareResult.get("AMOUT_YG"),compareResult.get("LOAN_RXFSN"),"-" );
        result.put("STL",stl);
        result.put("STL_UP",Format.subtraction(stl,compStl,"-"));
//        任性付客户苏宁消费金额/苏宁投放金额
        System.out.println("任性付客户苏宁消费金额LOAN_RXFCUST:"+searchResult.get("LOAN_RXFCUST"));
        String kdstl = Format.division(searchResult.get("LOAN_RXFCUST"),searchResult.get("LOAN_RXFSN"),"-" );
        String compKdstl = Format.division(compareResult.get("LOAN_RXFCUST"),compareResult.get("LOAN_RXFSN"),"-" );
        result.put("KDSTL",kdstl);
        result.put("KDSTL_UP",Format.subtraction(kdstl,compKdstl,"-"));
//        易购整体渗透率/易购整体可达渗透率
        result.put("SJKD", Format.division(stl,kdstl,"-"));
        result.put("SJKD_UP",Format.subtraction(Format.division(stl,kdstl,"-"),Format.division(compStl,compKdstl,"-"),"-"));


        result.put("LOAN_CASH",Format.wan(searchResult.get("LOAN_CASH")));
        result.put("LOAN_CASH_UP",Format.upRateStringNoValid(searchResult.get("LOAN_CASH"),compareResult.get("LOAN_CASH"),"-" ));
        result.put("LOAN_CASH_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_CASH_CUST"),compareResult.get("LOAN_CASH_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("LOAN_CASH"),searchResult.get("LOAN_CASH_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("LOAN_CASH"),compareResult.get("LOAN_CASH_CUST"),"-" );
        result.put("LOAN_CASH_KDJ",kdj);
        result.put("LOAN_CASH_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));

        result.put("LOAN_OTHER",Format.wan(searchResult.get("LOAN_OTHER")));
        result.put("LOAN_OTHER_UP",Format.upRateStringNoValid(searchResult.get("LOAN_OTHER"),compareResult.get("LOAN_OTHER"),"-" ));
        result.put("LOAN_OTHER_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_OTHER_CUST"),compareResult.get("LOAN_OTHER_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("LOAN_OTHER"),searchResult.get("LOAN_OTHER_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("LOAN_OTHER"),compareResult.get("LOAN_OTHER_CUST"),"-" );
        result.put("LOAN_OTHER_KDJ",kdj);
        result.put("LOAN_OTHER_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));

        result.put("LOAN_ANY",Format.wan(searchResult.get("LOAN_ANY")));
        result.put("LOAN_ANY_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ANY"),compareResult.get("LOAN_ANY"),"-" ));
        result.put("LOAN_ANY_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ANY_CUST"),compareResult.get("LOAN_ANY_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("LOAN_ANY"),searchResult.get("LOAN_ANY_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("LOAN_ANY"),compareResult.get("LOAN_ANY_CUST"),"-" );
        result.put("LOAN_ANY_KDJ",kdj);
        result.put("LOAN_ANY_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));

        result.put("LOAN_INSTALL",Format.wan(searchResult.get("LOAN_INSTALL")));
        result.put("LOAN_INSTALL_UP",Format.upRateStringNoValid(searchResult.get("LOAN_INSTALL"),compareResult.get("LOAN_INSTALL"),"-" ));
        result.put("LOAN_INSTALL_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_INSTALL_CUST"),compareResult.get("LOAN_INSTALL_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("LOAN_INSTALL"),searchResult.get("LOAN_INSTALL_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("LOAN_INSTALL"),compareResult.get("LOAN_INSTALL_CUST"),"-" );
        result.put("LOAN_INSTALL_KDJ",kdj);
        result.put("LOAN_INSTALL_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));

        result.put("LOAN_SHOP",Format.wan(searchResult.get("LOAN_SHOP")));
        result.put("LOAN_SHOP_UP",Format.upRateStringNoValid(searchResult.get("LOAN_SHOP"),compareResult.get("LOAN_SHOP"),"-" ));
        result.put("LOAN_SHOP_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_SHOP_CUST"),compareResult.get("LOAN_SHOP_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("LOAN_SHOP"),searchResult.get("LOAN_SHOP_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("LOAN_SHOP"),compareResult.get("LOAN_SHOP_CUST"),"-" );
        result.put("LOAN_SHOP_KDJ",kdj);
        result.put("LOAN_SHOP_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));

        result.put("LOAN_ONLINE",Format.wan(searchResult.get("LOAN_ONLINE")));
        result.put("LOAN_ONLINE_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ONLINE"),compareResult.get("LOAN_ONLINE"),"-" ));
        result.put("LOAN_ONLINE_CUST_UP",Format.upRateStringNoValid(searchResult.get("LOAN_ONLINE_CUST"),compareResult.get("LOAN_ONLINE_CUST"),"-" ));
        kdj = Format.divisionOnly(searchResult.get("LOAN_ONLINE"),searchResult.get("LOAN_ONLINE_CUST"),"-" );
        compKdj = Format.divisionOnly(compareResult.get("LOAN_ONLINE"),compareResult.get("LOAN_ONLINE_CUST"),"-" );
        result.put("LOAN_ONLINE_KDJ",kdj);
        result.put("LOAN_ONLINE_KDJ_UP",Format.upRateStringNoValid(kdj,compKdj));


        return result;

    }

    @Override
    public List<Map> queryRateTargetTable(int year, int quarter) {
        List<Map> datas = compareAnalyticsMapper.queryRateTargetData(year, quarter);
        List<Map> weights = compareAnalyticsMapper.queryRateTargetWeightData();

        //参与排序数据，达成率正常
        List<Map> sortDatas = new ArrayList<Map>();
        //不参与排序数据,达成率为空
        List<Map> unSortDatas = new ArrayList<Map>();

        for(int i=0,t=datas.size(); i<t; i++){
            Map data = datas.get(i);
            Map numberData = new HashMap();
            //门店总投放
            BigDecimal loanShop = data.get("LOAN_SHOP") == null ? null : (BigDecimal)data.get("LOAN_SHOP");
            //门店总投放目标
            BigDecimal targetLoanShop = data.get("TARGET_LOAN_SHOP") == null ? null : (BigDecimal)data.get("TARGET_LOAN_SHOP");

            //门店新客投放
            BigDecimal loanShopNewcust = data.get("LOAN_SHOP_NEWCUST") == null ? null : (BigDecimal)data.get("LOAN_SHOP_NEWCUST");
            //门店新客投放目标
            BigDecimal targetLoanShopNewcust = data.get("TARGET_LOAN_SHOP_NEWCUST") == null ? null : (BigDecimal)data.get("TARGET_LOAN_SHOP_NEWCUST");

            //门店新客数
            BigDecimal shopNewcust = data.get("SHOP_NEWCUST") == null ? null : (BigDecimal)data.get("SHOP_NEWCUST");
            //门店新客数目标
            BigDecimal targetShopNewcust = data.get("TARGET_SHOP_NEWCUST") == null ? null : (BigDecimal)data.get("TARGET_SHOP_NEWCUST");

            //现金贷总投放
//            BigDecimal loanCash = data.get("LOAN_CASH") == null ? null : (BigDecimal)data.get("LOAN_CASH");
            //现金贷总投放目标
//            BigDecimal targetLoanCash = data.get("TARGET_LOAN_CASH") == null ? null : (BigDecimal)data.get("TARGET_LOAN_CASH");

            //自拓现金贷投放
            BigDecimal loanCashself = data.get("LOAN_CASHSELF") == null ? null : (BigDecimal)data.get("LOAN_CASHSELF");
            //自拓现金贷投放目标
            BigDecimal targetLoanCashself = data.get("TARGET_LOAN_CASHSELF") == null ? null : (BigDecimal)data.get("TARGET_LOAN_CASHSELF");

            //自拓现金贷新客户数
            BigDecimal cashselfNewcust = data.get("CASHSELF_NEWCUST") == null ? null : (BigDecimal)data.get("CASHSELF_NEWCUST");
            //自拓现金贷新客户数目标
            BigDecimal targetCashselfNewcust = data.get("TARGET_CASHSELF_NEWCUST") == null ? null : (BigDecimal)data.get("TARGET_CASHSELF_NEWCUST");

            //第三方投放
            BigDecimal loanOther = data.get("LOAN_OTHER") == null ? null : (BigDecimal)data.get("LOAN_OTHER");
            //第三方投放目标
            BigDecimal targetLoanOther = data.get("TARGET_LOAN_OTHER") == null ? null : (BigDecimal)data.get("TARGET_LOAN_OTHER");

            //第三方新客户数
            BigDecimal otherNewcust = data.get("OTHER_NEWCUST") == null ? null : (BigDecimal)data.get("OTHER_NEWCUST");
            //第三方新客户数目标
            BigDecimal targetOtherNewcust = data.get("TARGET_OTHER_NEWCUST") == null ? null : (BigDecimal)data.get("TARGET_OTHER_NEWCUST");

            //APP申请人数
            BigDecimal appCustNo = data.get("APP_CUST_NO") == null ? null : (BigDecimal)data.get("APP_CUST_NO");
            //APP申请人数目标
            BigDecimal targetAppCustNo = data.get("TARGET_APP_APPLY") == null ? null : (BigDecimal)data.get("TARGET_APP_APPLY");


            numberData.put("AREA_NAME", data.get("AREA_NAME"));
            //开始计算达成率
            //门店总投放
            BigDecimal loanShopRate = computeArriveRate(loanShop, targetLoanShop);
            numberData.put("LOAN_SHOP_RATE", loanShopRate);
            //门店新客投放
            BigDecimal loanShopNewcustRate = computeArriveRate(loanShopNewcust, targetLoanShopNewcust);
            numberData.put("LOAN_SHOP_NEWCUST_RATE", loanShopNewcustRate);
            //门店新客数
            BigDecimal shopNewcustRate = computeArriveRate(shopNewcust, targetShopNewcust);
            numberData.put("SHOP_NEWCUST_RATE", shopNewcustRate);
            //现金贷总投放
//            BigDecimal loanCashRate = computeArriveRate(loanCash, targetLoanCash);
//            numberData.put("LOAN_CASH_RATE", loanCashRate);
            //自拓现金贷投放
            BigDecimal loanCashselfRate = computeArriveRate(loanCashself, targetLoanCashself);
            numberData.put("LOAN_CASHSELF_RATE", loanCashselfRate);
            //自拓现金贷新客户数
            BigDecimal cashselfNewcustRate = computeArriveRate(cashselfNewcust, targetCashselfNewcust);
            numberData.put("CASHSELF_NEWCUST_RATE", cashselfNewcustRate);
            //第三方投放
            BigDecimal loanOtherRate = computeArriveRate(loanOther, targetLoanOther);
            numberData.put("LOAN_OTHER_RATE", loanOtherRate);
            //第三方新客户数
            BigDecimal otherNewcustRate = computeArriveRate(otherNewcust, targetOtherNewcust);
            numberData.put("OTHER_NEWCUST_RATE", otherNewcustRate);
            //APP申请人数
            BigDecimal appCustNoRate = computeArriveRate(appCustNo, targetAppCustNo);
            numberData.put("APP_CUST_NO_RATE", appCustNoRate);

            if(loanShopRate == null || loanShopNewcustRate == null || shopNewcustRate == null || /*loanCashRate == null ||*/ loanCashselfRate == null
                    || cashselfNewcustRate == null || loanOtherRate == null || otherNewcustRate == null || appCustNoRate == null){
                unSortDatas.add(numberData);
            }else{
                sortDatas.add(numberData);
            }
        }

        boolean ok = true;
        //计算每一项得分,在计算得分方法总判断了阈值维护是否合理，如不合理则全部无法计算得分，全部不参与排名
        //门店总投放
        if(computeScore(sortDatas, weights, "10001", "LOAN_SHOP_RATE")){
            if(computeScore(sortDatas, weights, "10003", "LOAN_SHOP_NEWCUST_RATE")){
                if(computeScore(sortDatas, weights, "10002", "SHOP_NEWCUST_RATE")){
//                    if(computeScore(sortDatas, weights, "10004", "LOAN_CASH_RATE")){
                    if(computeScore(sortDatas, weights, "10006", "LOAN_CASHSELF_RATE")){
                        if(computeScore(sortDatas, weights, "10005", "CASHSELF_NEWCUST_RATE")){
                            if(computeScore(sortDatas, weights, "10007", "LOAN_OTHER_RATE")){
                                if(computeScore(sortDatas, weights, "10008", "OTHER_NEWCUST_RATE")){
                                    if(!computeScore(sortDatas, weights, "10009", "APP_CUST_NO_RATE")){
                                        return operateData(sortDatas, unSortDatas, false);
                                    }
                                }else{
                                    return operateData(sortDatas, unSortDatas, false);
                                }
                            }else{
                                return operateData(sortDatas, unSortDatas, false);
                            }
                        }else{
                            return operateData(sortDatas, unSortDatas, false);
                        }
                    }else{
                        return operateData(sortDatas, unSortDatas, false);
                    }
//                    }else{
//                        return operateData(sortDatas, unSortDatas, false);
//                    }
                }else{
                    return operateData(sortDatas, unSortDatas, false);
                }
            }else{
                return operateData(sortDatas, unSortDatas, false);
            }
        }else{
            return operateData(sortDatas, unSortDatas, false);
        }
        return operateData(sortDatas, unSortDatas, true);
    }

    private boolean computeScore(List<Map> sortDatas, List<Map> weights, String kpiCode, String quota){
        Map weightCfg = null;
        for(int i=0, t=weights.size(); i<t; i++){
            Map w = weights.get(i);
            if(kpiCode.equals(w.get("KPI_CODE"))){
                weightCfg = w;
                break;
            }
        }
        if(weightCfg == null){
            return false;
        }

        BigDecimal weight,kpiUp,kpiDown;
        if(weightCfg.get("WEIGHT") == null){
            return false;
        }else{
            weight = (BigDecimal)weightCfg.get("WEIGHT");
        }

        if(weightCfg.get("KPI_UP") == null){
            return false;
        }else{
            kpiUp = (BigDecimal)weightCfg.get("KPI_UP");
        }

        if(weightCfg.get("KPI_DOWN") == null){
            return false;
        }else{
            kpiDown = (BigDecimal)weightCfg.get("KPI_DOWN");
        }

        Collections.sort(sortDatas, new QuotaComparator<Map>(quota));
        for(int i=0,t=sortDatas.size(); i<t; i++){
            Map sortData = sortDatas.get(i);
            BigDecimal rate = (BigDecimal)sortData.get(quota);
            rate = rate.multiply(new BigDecimal(100));
            if(i < 3){//排序倒数前三名的要判断是否比最小阈值小，如果小直接给零分，如果不小根据正常计算逻辑计算
                if(rate.compareTo(kpiDown) < 0){
                    sortData.put(quota + "_SCORE", BigDecimal.ZERO);
                }else{
                    if(rate.compareTo(kpiUp) > 0){
                        sortData.put(quota + "_SCORE", kpiUp.multiply(weight));
                    }else{
                        sortData.put(quota + "_SCORE", rate.multiply(weight));
                    }
                }
            }else{
                if(rate.compareTo(kpiUp) > 0){
                    sortData.put(quota + "_SCORE", kpiUp.multiply(weight));
                }else{
                    sortData.put(quota + "_SCORE", rate.multiply(weight));
                }
            }
        }
        return true;
    }

    private BigDecimal computeArriveRate(BigDecimal actual, BigDecimal target){
        if(actual == null || target == null || target.compareTo(BigDecimal.ZERO) == 0){
            return null;
        }else{
            return actual.divide(target, 4, BigDecimal.ROUND_HALF_UP);
        }
    }

    private List<Map> operateData(List<Map> sortDatas, List<Map> unSortDatas, boolean ord){
        List<Map> results = new ArrayList<Map>();
        if(ord){
            for(int i=0, t=sortDatas.size(); i<t; i++){
                Map sortData = sortDatas.get(i);
                BigDecimal loanShopRateScore = (BigDecimal)sortData.get("LOAN_SHOP_RATE_SCORE");
                BigDecimal loanShopNewcustRateScore = (BigDecimal)sortData.get("LOAN_SHOP_NEWCUST_RATE_SCORE");
                BigDecimal shopNewcustRateScore = (BigDecimal)sortData.get("SHOP_NEWCUST_RATE_SCORE");
//                BigDecimal loanCashRateScore = (BigDecimal)sortData.get("LOAN_CASH_RATE_SCORE");
                BigDecimal loanCashselfRateScore = (BigDecimal)sortData.get("LOAN_CASHSELF_RATE_SCORE");
                BigDecimal cashselfNewcustRateScore = (BigDecimal)sortData.get("CASHSELF_NEWCUST_RATE_SCORE");
                BigDecimal loanOtherRateScore = (BigDecimal)sortData.get("LOAN_OTHER_RATE_SCORE");
                BigDecimal otherNewcustRateScore = (BigDecimal)sortData.get("OTHER_NEWCUST_RATE_SCORE");
                BigDecimal appCustNoRateScore = (BigDecimal)sortData.get("APP_CUST_NO_RATE_SCORE");
                sortData.put("SCORE", loanShopRateScore.add(loanShopNewcustRateScore).add(shopNewcustRateScore)/*.add(loanCashRateScore)*/
                        .add(loanCashselfRateScore).add(cashselfNewcustRateScore).add(loanOtherRateScore).add(otherNewcustRateScore)
                        .add(appCustNoRateScore));
            }

            Collections.sort(sortDatas, new Comparator<Map>() {
                @Override
                public int compare(Map o1, Map o2) {
                    BigDecimal b1 = (BigDecimal)o1.get("SCORE");
                    BigDecimal b2 = (BigDecimal)o2.get("SCORE");
                    return b2.compareTo(b1);
                }
            });

            formatData(results, sortDatas, true);
            formatData(results, unSortDatas, false);
        }else{
            formatData(results, sortDatas, false);
            formatData(results, unSortDatas, false);
        }
        return results;
    }

    private void formatData(List<Map> results, List<Map> datas, boolean ord){
        for(int i=0,t=datas.size(); i<t; i++){
            Map data = datas.get(i);
            Map result = new HashMap();
            DecimalFormat df = new DecimalFormat("0.0%");
            //大区名称
            result.put("AREA_NAME", data.get("AREA_NAME") == null ? "" : data.get("AREA_NAME").toString());
            //门店总投放
            result.put("LOAN_SHOP_RATE", data.get("LOAN_SHOP_RATE") == null ? "-" : df.format(data.get("LOAN_SHOP_RATE")));
            //门店新客投放
            result.put("LOAN_SHOP_NEWCUST_RATE", data.get("LOAN_SHOP_NEWCUST_RATE") == null ? "-" : df.format(data.get("LOAN_SHOP_NEWCUST_RATE")));
            //门店新客数
            result.put("SHOP_NEWCUST_RATE", data.get("SHOP_NEWCUST_RATE") == null ? "-" : df.format(data.get("SHOP_NEWCUST_RATE")));
            //现金贷总投放
//            result.put("LOAN_CASH_RATE", data.get("LOAN_CASH_RATE") == null ? "-" : df.format(data.get("LOAN_CASH_RATE")));
            //自拓现金贷投放
            result.put("LOAN_CASHSELF_RATE", data.get("LOAN_CASHSELF_RATE") == null ? "-" : df.format(data.get("LOAN_CASHSELF_RATE")));
            //自拓现金贷新客户数
            result.put("CASHSELF_NEWCUST_RATE", data.get("CASHSELF_NEWCUST_RATE") == null ? "-" : df.format(data.get("CASHSELF_NEWCUST_RATE")));
            //第三方投放
            result.put("LOAN_OTHER_RATE", data.get("LOAN_OTHER_RATE") == null ? "-" : df.format(data.get("LOAN_OTHER_RATE")));
            //第三方新客户数
            result.put("OTHER_NEWCUST_RATE", data.get("OTHER_NEWCUST_RATE") == null ? "-" : df.format(data.get("OTHER_NEWCUST_RATE")));
            //APP申请人数
            result.put("APP_CUST_NO_RATE", data.get("APP_CUST_NO_RATE") == null ? "-" : df.format(data.get("APP_CUST_NO_RATE")));
            if(ord){
                result.put("ORD", String.valueOf(i + 1));
            }else{
                result.put("ORD", "-");
            }
            results.add(result);
        }

    }

    private void validateParams(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        if(StringUtils.isEmpty(beginDate)||
                StringUtils.isEmpty(endDate)||
                StringUtils.isEmpty(compareBeginDate)||
                StringUtils.isEmpty(compareEndDate)){
            throw new ResultException("参数不全");
        }
        if(DateUtil.moreThanThirtyOneDays(beginDate,endDate) ||
                DateUtil.moreThanThirtyOneDays(compareBeginDate,compareEndDate)){
            throw new ResultException("时间区间不能超过31天");
        }
    }

    public class QuotaComparator<T> implements Comparator<T> {

        private String quota;

        public QuotaComparator (){};

        public QuotaComparator (String quota){
            this.quota = quota;
        };

        @Override
        public int compare(T o1, T o2) {
            Map m1 = (Map)o1;
            Map m2 = (Map)o2;
            BigDecimal b1 = (BigDecimal)m1.get(quota);
            BigDecimal b2 = (BigDecimal)m2.get(quota);
            return b1.compareTo(b2);
        }
    }

}
