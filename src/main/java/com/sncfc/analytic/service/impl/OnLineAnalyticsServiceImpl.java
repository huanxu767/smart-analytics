package com.sncfc.analytic.service.impl;

import com.sncfc.analytic.dao.OnLineAnalyticsMapper;
import com.sncfc.analytic.service.IOnLineAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bspei on 2017/6/7.
 */
@Service
public class OnLineAnalyticsServiceImpl implements IOnLineAnalyticsService {

    @Autowired
    private OnLineAnalyticsMapper onLineAnalyticsMapper;

    @Override
    public Map queryLoanAmtData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        List<Map> datas = onLineAnalyticsMapper.queryOnLineLoanData(beginDate, endDate, compareBeginDate, compareEndDate);
        List<String> names = new ArrayList<String>();
        names.add("30天免息");
        names.add("分期");
        List<String> querys = new ArrayList<String>();
        List<String> compares = new ArrayList<String>();
        DecimalFormat df = new DecimalFormat("0");

        for(int i=0,t=names.size(); i<t; i++){
            String ctermType = names.get(i);
            BigDecimal loanOnline = findLoanData(datas, "chaxun", ctermType);
            querys.add(loanOnline == null ? "0" : df.format(loanOnline.divide(new BigDecimal(10000), 4, BigDecimal.ROUND_HALF_UP)));

            BigDecimal compareLoanOnline = findLoanData(datas, "duibi", ctermType);
            compares.add(compareLoanOnline == null ? "0" : df.format(compareLoanOnline.divide(new BigDecimal(10000), 4, BigDecimal.ROUND_HALF_UP)));
        }

        Map<String, List<String>> result = new HashMap<String, List<String>>();

        //下标
        result.put("names", names);
        //查询期
        result.put("querys", querys);
        //对比期
        result.put("compares", compares);
        return result;
    }

    @Override
    public Map queryMarketingStrategyData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        List<Map> datas = onLineAnalyticsMapper.queryOnLineLoanDataByActivity(beginDate, endDate, compareBeginDate, compareEndDate);
        String[] cterms = {"3" ,"6" ,"9" ,"12" ,"24"};
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
        //下标
        result.put("names", names);
        //正常
        result.put("normals", normals);
        //活动
        result.put("activities", activities);
        return result;
    }

    @Override
    public Map queryDeptConstituteData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        List<Map> datas = onLineAnalyticsMapper.queryOnLineDeptData(beginDate, endDate, compareBeginDate, compareEndDate);
        List<String> names = new ArrayList<String>();
        List<String> querys = new ArrayList<String>();
        List<String> compares = new ArrayList<String>();
        List<Map> tabDatas = new ArrayList<Map>();
        DecimalFormat df1 = new DecimalFormat("0");
        DecimalFormat df2 = new DecimalFormat("0.0%");
        DecimalFormat df3 = new DecimalFormat("0.0");

        for(int i=0, t=datas.size(); i<t; i++){
            Map data = datas.get(i);
            String deptNm = data.get("DEPT_NM") == null ? null : data.get("DEPT_NM").toString();
            names.add(deptNm);
            Map<String, String> tabData = new HashMap<String, String>();
            tabData.put("DEPT_NM", deptNm);
            BigDecimal queryCnt = data.get("QUERYCNT") == null ? null : (BigDecimal) data.get("QUERYCNT");
            BigDecimal queryAmt = data.get("QUERYAMT") == null ? null : (BigDecimal) data.get("QUERYAMT");
            BigDecimal compareCnt = data.get("COMPARECNT") == null ? null : (BigDecimal) data.get("COMPARECNT");
            BigDecimal compareAmt = data.get("COMPAREAMT") == null ? null : (BigDecimal) data.get("COMPAREAMT");

            //投放金额
            if(queryAmt == null){
                tabData.put("LOAN_AMT", "0");
            }else{
                tabData.put("LOAN_AMT", df3.format(queryAmt.divide(new BigDecimal(10000), 4, BigDecimal.ROUND_HALF_UP)));
            }
            //投放金额增长率
            if(compareAmt == null || compareAmt.compareTo(BigDecimal.ZERO) == 0){
                tabData.put("AMT_GROWTH_RATE", "-");
            }else{
                tabData.put("AMT_GROWTH_RATE", df2.format(queryAmt.subtract(compareAmt).divide(compareAmt, 4, BigDecimal.ROUND_HALF_UP)));
            }

            //客单价
            BigDecimal queryPerTicketSale = null;
            if(queryAmt == null || queryCnt == null || queryCnt.compareTo(BigDecimal.ZERO) == 0){
                tabData.put("PER_TICKET_SALE", "0");
            }else{
                queryPerTicketSale = queryAmt.divide(queryCnt, 2, BigDecimal.ROUND_HALF_UP);
                tabData.put("PER_TICKET_SALE", df3.format(queryPerTicketSale));
            }

            //客单价增长率
            if(queryPerTicketSale == null || compareAmt == null || compareCnt == null || compareCnt.compareTo(BigDecimal.ZERO) == 0){
                tabData.put("PTS_GROWTH_RATE", "0");
            }else{
                BigDecimal comparePerTicketSale = compareAmt.divide(compareCnt, 2, BigDecimal.ROUND_HALF_UP);
                tabData.put("PTS_GROWTH_RATE", df2.format(queryPerTicketSale.subtract(comparePerTicketSale).divide(comparePerTicketSale, 4, BigDecimal.ROUND_HALF_UP)));
            }

            //投放人数
            if(queryCnt == null){
                tabData.put("LOAN_CNT", "0");
            }else{
                tabData.put("LOAN_CNT", df1.format(queryCnt));
            }

            //投放人数增长率
            if(queryCnt == null || compareCnt == null || compareCnt.compareTo(BigDecimal.ZERO) == 0){
                tabData.put("CNT_GROWTH_RATE", "-");
            }else{
                tabData.put("CNT_GROWTH_RATE", df2.format(queryCnt.subtract(compareCnt).divide(compareCnt, 4, BigDecimal.ROUND_HALF_UP)));
            }

            tabDatas.add(tabData);

            querys.add(queryAmt == null ? "0" : df1.format(queryAmt));
            compares.add(compareAmt == null ? "0" : df1.format(compareAmt));
        }

        Map<String, Object> result = new HashMap<String, Object>();
        //表格数据
        result.put("tabDatas", tabDatas);
        //环形图下标
        result.put("names", names);
        //环形图查询期数据
        result.put("querys", querys);
        //环形图对比期数据
        result.put("compares", compares);
        return result;
    }

    @Override
    public Map queryCustomPermeabilityData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        Map data = onLineAnalyticsMapper.queryYgLoanCnt(beginDate, endDate, compareBeginDate, compareEndDate);
        List<String> names = new ArrayList<String>();
        List<String> querys = new ArrayList<String>();
        List<String> compares = new ArrayList<String>();
        names.add("人数实际渗透率");
        names.add("人数可达渗透率");
        names.add("实际/可达");

        BigDecimal cxRxfyg = data.get("CX_RXFYG") == null ? null : (BigDecimal)data.get("CX_RXFYG");
        BigDecimal cxYgrxf = data.get("CX_YGRXF") == null ? null : (BigDecimal)data.get("CX_YGRXF");
        BigDecimal cxYg = data.get("CX_YG") == null ? null : (BigDecimal)data.get("CX_YG");
        BigDecimal dbRxfyg = data.get("DB_RXFYG") == null ? null : (BigDecimal)data.get("DB_RXFYG");
        BigDecimal dbYgrxf = data.get("DB_YGRXF") == null ? null : (BigDecimal)data.get("DB_YGRXF");
        BigDecimal dbYg = data.get("DB_YG") == null ? null : (BigDecimal)data.get("DB_YG");
        DecimalFormat df = new DecimalFormat("0.0");


        if(cxYg == null || cxYg.compareTo(BigDecimal.ZERO) == 0){
            querys.add("0");
            querys.add("0");
            querys.add("0");
        }else{
            BigDecimal cxsjstl = cxRxfyg.divide(cxYg, 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal cxkdstl = cxYgrxf.divide(cxYg, 4, BigDecimal.ROUND_HALF_UP);
            querys.add(df.format(cxsjstl.multiply(new BigDecimal(100))));
            querys.add(df.format(cxkdstl.multiply(new BigDecimal(100))));
            if(cxYgrxf.compareTo(BigDecimal.ZERO) == 0){
                querys.add("0");
            }else{
                querys.add(df.format(cxRxfyg.divide(cxYgrxf, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
            }
        }

        if(dbYg == null || dbYg.compareTo(BigDecimal.ZERO) == 0){
            compares.add("0");
            compares.add("0");
            compares.add("0");
        }else{
            BigDecimal dbsjstl = dbRxfyg.divide(dbYg, 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal dbkdstl = dbYgrxf.divide(dbYg, 4, BigDecimal.ROUND_HALF_UP);
            compares.add(df.format(dbsjstl.multiply(new BigDecimal(100))));
            compares.add(df.format(dbkdstl.multiply(new BigDecimal(100))));
            if(dbYgrxf.compareTo(BigDecimal.ZERO) == 0){
                compares.add("0");
            }else{
                compares.add(df.format(dbRxfyg.divide(dbYgrxf, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
            }
        }

        Map<String, List<String>> result = new HashMap<String, List<String>>();
        //下标列表
        result.put("names", names);
        //查询期渗透率数据
        result.put("querys", querys);
        //对比期渗透率数据
        result.put("compares", compares);
        return result;
    }

    @Override
    public Map queryPersonConstituteData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        List<Map> datas = onLineAnalyticsMapper.queryOnLineEmpCnt(beginDate, endDate, compareBeginDate, compareEndDate);
        List<String> names = new ArrayList<String>();
        List<String> querys = new ArrayList<String>();
        List<String> compares = new ArrayList<String>();
        List<String> increaseRate = new ArrayList<String>();
        DecimalFormat df1 = new DecimalFormat("0");
        DecimalFormat df2 = new DecimalFormat("0.0");

        for(int i=0, t=datas.size(); i<t; i++){
            Map data = datas.get(i);
            BigDecimal ygStaff = data.get("YG_STAFF") == null ? null : (BigDecimal) data.get("YG_STAFF");
            BigDecimal queryCnt = data.get("QUERYCNT") == null ? null : (BigDecimal) data.get("QUERYCNT");
            BigDecimal compareCnt = data.get("COMPARECNT") == null ? null : (BigDecimal) data.get("COMPARECNT");
            if(ygStaff != null && ygStaff.compareTo(new BigDecimal(1)) == 0){
                names.add("易购员工");
                querys.add(queryCnt == null ? "0" : df1.format(queryCnt));
                compares.add(compareCnt == null ? "0" : df1.format(compareCnt));

                if(queryCnt == null || compareCnt == null || compareCnt.compareTo(BigDecimal.ZERO) == 0){
                    increaseRate.add("0");
                }else{
                    increaseRate.add(df2.format(queryCnt.subtract(compareCnt).divide(compareCnt, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
                }
            }else if(ygStaff != null && ygStaff.compareTo(BigDecimal.ZERO) == 0){
                names.add("非易购员工");
                querys.add(queryCnt == null ? "0" : df1.format(queryCnt));
                compares.add(compareCnt == null ? "0" : df1.format(compareCnt));

                if(queryCnt == null || compareCnt == null || compareCnt.compareTo(BigDecimal.ZERO) == 0){
                    increaseRate.add("0");
                }else{
                    increaseRate.add(df2.format(queryCnt.subtract(compareCnt).divide(compareCnt, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
                }
            }
        }
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        //下标列表
        result.put("names", names);
        //查询期数据
        result.put("querys", querys);
        //对比期数据
        result.put("compares", compares);
        //增长率数据
        result.put("increaseRate", increaseRate);
        return result;
    }

    @Override
    public Map queryPersonNotYgChannelData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        List<Map> datas = onLineAnalyticsMapper.queryNotYgDataCntByChannel(beginDate, endDate, compareBeginDate, compareEndDate);
        List<String> names = new ArrayList<String>();
        List<String> querys = new ArrayList<String>();
        List<String> compares = new ArrayList<String>();
        List<String> increaseRate = new ArrayList<String>();
        DecimalFormat df1 = new DecimalFormat("0");
        DecimalFormat df2 = new DecimalFormat("0.0");
        for(int i=0, t=datas.size(); i<t; i++) {
            Map data = datas.get(i);
            names.add(data.get("CUST_CHNL") == null ? "" : data.get("CUST_CHNL").toString());
            BigDecimal queryCnt = data.get("QUERYCNT") == null ? null : (BigDecimal) data.get("QUERYCNT");
            BigDecimal compareCnt = data.get("COMPARECNT") == null ? null : (BigDecimal) data.get("COMPARECNT");
            querys.add(queryCnt == null ? "0" : df1.format(queryCnt));
            compares.add(compareCnt == null ? "0" : df1.format(compareCnt));

            if(queryCnt == null || compareCnt == null || compareCnt.compareTo(BigDecimal.ZERO) == 0){
                increaseRate.add("0");
            }else{
                increaseRate.add(df2.format(queryCnt.subtract(compareCnt).divide(compareCnt, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100))));
            }
        }
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        //下标列表
        result.put("names", names);
        //查询期数据
        result.put("querys", querys);
        //对比期数据
        result.put("compares", compares);
        //增长率数据
        result.put("increaseRate", increaseRate);
        return result;
    }

    @Override
    public Map queryNewOrOldCustomDataByChannelData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        List<Map> allDatas = onLineAnalyticsMapper.queryNewOrOldCustomDataByChannel(beginDate, endDate, compareBeginDate, compareEndDate);
        List<Map<String, String>> newCustomDatas = new ArrayList<Map<String, String>>();
        List<Map<String, String>> oldCustomDatas = new ArrayList<Map<String, String>>();
        BigDecimal totalNewCntQuery = new BigDecimal(0);
        BigDecimal totalNewCntCompare = new BigDecimal(0);
        BigDecimal totalOldCntQuery = new BigDecimal(0);
        BigDecimal totalOldCntCompare = new BigDecimal(0);

        DecimalFormat df1 = new DecimalFormat("0");
        DecimalFormat df2 = new DecimalFormat("0.0%");

        for(int i=0,t=allDatas.size(); i<t; i++){
            Map allData = allDatas.get(i);
            Map<String, String> newCustomData = new HashMap<String, String>();
            Map<String, String> oldCustomData = new HashMap<String, String>();
            String custChnl = allData.get("CUST_CHNL") == null ? null : allData.get("CUST_CHNL").toString();
            newCustomData.put("CUST_CHNL", custChnl);
            oldCustomData.put("CUST_CHNL", custChnl);
            BigDecimal isNew = allData.get("ISNEW") == null ? null : (BigDecimal) allData.get("ISNEW");

            BigDecimal allQueryCnt = allData.get("QUERYCNT") == null ? null : (BigDecimal) allData.get("QUERYCNT");
            BigDecimal allCompareCnt = allData.get("COMPARECNT") == null ? null : (BigDecimal) allData.get("COMPARECNT");

            if(new BigDecimal(1).compareTo(isNew) == 0){
                newCustomData.put("QUERY_DATA", allQueryCnt == null ? "-" : df1.format(allQueryCnt));
                newCustomData.put("INCREATE_RATE", allQueryCnt == null || allCompareCnt == null || allCompareCnt.compareTo(BigDecimal.ZERO) == 0 ? "0.0%" : df2.format(allQueryCnt.subtract(allCompareCnt).divide(allCompareCnt, 4, BigDecimal.ROUND_HALF_UP)));
                newCustomDatas.add(newCustomData);
                if(allQueryCnt != null){
                    totalNewCntQuery = totalNewCntQuery.add(allQueryCnt);
                }
                if(allCompareCnt != null){
                    totalNewCntCompare = totalNewCntCompare.add(allCompareCnt);
                }
            }else{
                oldCustomData.put("QUERY_DATA", allQueryCnt == null ? "-" : df1.format(allQueryCnt));
                oldCustomData.put("INCREATE_RATE", allQueryCnt == null || allCompareCnt == null || allCompareCnt.compareTo(BigDecimal.ZERO) == 0 ? "0.0%" : df2.format(allQueryCnt.subtract(allCompareCnt).divide(allCompareCnt, 4, BigDecimal.ROUND_HALF_UP)));
                oldCustomDatas.add(oldCustomData);
                if(allQueryCnt != null){
                    totalOldCntQuery = totalOldCntQuery.add(allQueryCnt);
                }
                if(allCompareCnt != null){
                    totalOldCntCompare = totalOldCntCompare.add(allCompareCnt);
                }
            }
        }

        Map<String, Object> result = new HashMap<String, Object>();
        //新客户表格数据
        result.put("newCustomDatas", newCustomDatas);
        //新客户总数
        result.put("newCustomCnt", df1.format(totalNewCntQuery));
        //新客户总增长率
        result.put("newCustomIncreaseRate", totalNewCntCompare.compareTo(BigDecimal.ZERO) == 0 ? "-" : df2.format(totalNewCntQuery.subtract(totalNewCntCompare).divide(totalNewCntCompare, 4, BigDecimal.ROUND_HALF_UP)));


        //老客户表格数据
        result.put("oldCustomDatas", oldCustomDatas);
        //老客户总数
        result.put("oldCustomCnt", df1.format(totalOldCntQuery));
        //老客户总增长率
        result.put("oldCustomIncreaseRate", totalOldCntCompare.compareTo(BigDecimal.ZERO) == 0 ? "-" : df2.format(totalOldCntQuery.subtract(totalOldCntCompare).divide(totalOldCntCompare, 4, BigDecimal.ROUND_HALF_UP)));
        return result;
    }

    @Override
    public List<Map> queryMarketingAnalysisData(String beginDate, String endDate, String compareBeginDate, String compareEndDate) {
        List<Map> datas = onLineAnalyticsMapper.queryLoanDataByDept(beginDate, endDate, compareBeginDate, compareEndDate);
        List<Map> results = new ArrayList<Map>();
        DecimalFormat df1 = new DecimalFormat("0.0");
        DecimalFormat df2 = new DecimalFormat("0.0%");
        for(int i=0, t=datas.size(); i<t; i++){
            Map<String, String> result = new HashMap<String, String>();
            Map data = datas.get(i);
            // DEPT_NM  品类名称
            result.put("DEPT_NM", data.get("DEPT_NM") == null ? null : data.get("DEPT_NM").toString());

            BigDecimal queryBalAmt = data.get("QUERY_BAL_AMT") == null ? null : (BigDecimal)data.get("QUERY_BAL_AMT");
            BigDecimal compareBalAmt = data.get("COMPARE_BAL_AMT") == null ? null : (BigDecimal)data.get("COMPARE_BAL_AMT");
            // QUERY_BAL_AMT 查询期投放金额
            result.put("QUERY_BAL_AMT", queryBalAmt == null ? "-" : df1.format(queryBalAmt.divide(new BigDecimal(10000), 4, BigDecimal.ROUND_HALF_UP)));
            // COMPARE_BAL_AMT 对比期投放金额
            result.put("COMPARE_BAL_AMT", compareBalAmt == null ? "-" : df1.format(compareBalAmt.divide(new BigDecimal(10000), 4, BigDecimal.ROUND_HALF_UP)));
            // INCREATE_RATE 投放增长率
            if(queryBalAmt == null || compareBalAmt == null || compareBalAmt.compareTo(BigDecimal.ZERO) == 0){
                result.put("INCREATE_RATE", "-");
            }else{
                result.put("INCREATE_RATE", df2.format(queryBalAmt.subtract(compareBalAmt).divide(compareBalAmt, 4, BigDecimal.ROUND_HALF_UP)));
            }

            BigDecimal queryPayAmt = data.get("QUERY_PAY_AMT") == null ? null : (BigDecimal)data.get("QUERY_PAY_AMT");
            BigDecimal comparePayAmt = data.get("COMPARE_PAY_AMT") == null ? null : (BigDecimal)data.get("COMPARE_PAY_AMT");
            // QUERY_ACTUAL_SEEP_RATE 查询期实际渗透率
            BigDecimal queryActualSeepRate = null;
            if(queryBalAmt != null && queryPayAmt != null && queryPayAmt.compareTo(BigDecimal.ZERO) != 0){
                queryActualSeepRate = queryBalAmt.divide(queryPayAmt, 4, BigDecimal.ROUND_HALF_UP);
            }
            result.put("QUERY_ACTUAL_SEEP_RATE", queryActualSeepRate == null ? "-" : df2.format(queryActualSeepRate));

            // COMPARE_ACTUAL_SEEP_RATE 对比期实际渗透率
            BigDecimal compareActualSeepRate = null;
            if(compareBalAmt != null && comparePayAmt != null && comparePayAmt.compareTo(BigDecimal.ZERO) != 0){
                compareActualSeepRate = compareBalAmt.divide(comparePayAmt, 4, BigDecimal.ROUND_HALF_UP);
            }
            result.put("COMPARE_ACTUAL_SEEP_RATE", compareActualSeepRate == null ? "-" : df2.format(compareActualSeepRate));

            // ACTUAL_SEEP_INCREATE_RATE 实际渗透率增长率
            if(queryActualSeepRate == null || compareActualSeepRate == null/* || compareActualSeepRate.compareTo(BigDecimal.ZERO) == 0*/){
                result.put("ACTUAL_SEEP_INCREATE_RATE", "-");
            }else{
                result.put("ACTUAL_SEEP_INCREATE_RATE", df2.format(queryActualSeepRate.subtract(compareActualSeepRate)/*.divide(compareActualSeepRate, 4, BigDecimal.ROUND_HALF_UP)*/));
            }

            BigDecimal queryCfcPayAmt = data.get("QUERY_CFC_PAY_AMT") == null ? null : (BigDecimal)data.get("QUERY_CFC_PAY_AMT");
            BigDecimal compareCfcPayAmt = data.get("COMPARE_CFC_PAY_AMT") == null ? null : (BigDecimal)data.get("COMPARE_CFC_PAY_AMT");
            // QUERY_ACCESS_SEEP_RATE 查询期可达渗透率
            BigDecimal queryAccessSeepRate = null;
            if(queryCfcPayAmt != null && queryPayAmt != null && queryPayAmt.compareTo(BigDecimal.ZERO) != 0){
                queryAccessSeepRate = queryCfcPayAmt.divide(queryPayAmt, 4, BigDecimal.ROUND_HALF_UP);
            }
            result.put("QUERY_ACCESS_SEEP_RATE", queryAccessSeepRate == null ? "-" : df2.format(queryAccessSeepRate));

            // COMPARE_ACCESS_SEEP_RATE 对比期可达渗透率
            BigDecimal compareAccessSeepRate = null;
            if(compareCfcPayAmt != null && comparePayAmt != null && comparePayAmt.compareTo(BigDecimal.ZERO) != 0){
                compareAccessSeepRate = compareCfcPayAmt.divide(comparePayAmt, 4, BigDecimal.ROUND_HALF_UP);
            }
            result.put("COMPARE_ACCESS_SEEP_RATE", compareAccessSeepRate == null ? "-" : df2.format(compareAccessSeepRate));

            // ACCESS_SEEP_INCREATE_RATE 可达渗透率增长率
            if(queryAccessSeepRate == null || compareAccessSeepRate == null/* || compareAccessSeepRate.compareTo(BigDecimal.ZERO) == 0*/){
                result.put("ACCESS_SEEP_INCREATE_RATE", "-");
            }else{
                result.put("ACCESS_SEEP_INCREATE_RATE", df2.format(queryAccessSeepRate.subtract(compareAccessSeepRate)/*.divide(compareAccessSeepRate, 4, BigDecimal.ROUND_HALF_UP)*/));
            }

            // QUERY_ACTUAL_ACCESS 查询期实际/可达
            BigDecimal queryActualAccess = null;
            if(queryBalAmt != null && queryCfcPayAmt != null && queryCfcPayAmt.compareTo(BigDecimal.ZERO) != 0){
                queryActualAccess = queryBalAmt.divide(queryCfcPayAmt,  4, BigDecimal.ROUND_HALF_UP);
            }
            result.put("QUERY_ACTUAL_ACCESS", queryActualAccess == null ? "-" : df2.format(queryActualAccess));

            // COMPARE_ACTUAL_ACCESS 对比期实际/可达
            BigDecimal compareActualAccess = null;
            if(compareBalAmt != null && compareCfcPayAmt != null && compareCfcPayAmt.compareTo(BigDecimal.ZERO) != 0){
                compareActualAccess = compareBalAmt.divide(compareCfcPayAmt,  4, BigDecimal.ROUND_HALF_UP);
            }
            result.put("COMPARE_ACTUAL_ACCESS", compareActualAccess == null ? "-" : df2.format(compareActualAccess));

            // ACTUAL_ACCESS_INCREATE_RATE 实际/可达增长率
            if(queryActualAccess == null || compareActualAccess == null/* || compareActualAccess.compareTo(BigDecimal.ZERO) == 0*/){
                result.put("ACTUAL_ACCESS_INCREATE_RATE", "-");
            }else{
                result.put("ACTUAL_ACCESS_INCREATE_RATE", df2.format(queryActualAccess.subtract(compareActualAccess)/*.divide(compareActualAccess, 4, BigDecimal.ROUND_HALF_UP)*/));
            }

            results.add(result);
        }
        return results;
    }

    private BigDecimal findLoanData(List<Map> datas, String tp, String ctermType){
        if(datas == null || datas.size() == 0){
            return null;
        }

        for(int i=0,t=datas.size(); i<t; i++){
            Map data = datas.get(i);
            if(tp.equals(data.get("TP")) && ctermType.equals(data.get("CTERM_TYPE"))){
                return data.get("LOAN_ONLINE") == null ? null : (BigDecimal)data.get("LOAN_ONLINE");
            }
        }
        return null;
    }

    private BigDecimal findData(List<Map> datas, int isPromotionFlag, String tp, String cterm){
        if(datas == null || datas.size() == 0){
            return null;
        }

        for(int i=0,t=datas.size(); i<t; i++){
            Map data = datas.get(i);
            if(((BigDecimal)data.get("IS_PROMOTION_FLAG")).compareTo(new BigDecimal(isPromotionFlag)) == 0 && tp.equals(data.get("TP")) && cterm.equals(data.get("CTERM"))){
                return data.get("LOAN_ONLINE") == null ? null : (BigDecimal)data.get("LOAN_ONLINE");
            }
        }
        return null;
    }

    private Map findNewCustomChnlData(List<Map> newDatas, String chnl){
        for(int i=0,t=newDatas.size(); i<t; i++){
            Map newData = newDatas.get(i);
            if(chnl == null){
                if(newData.get("CUST_CHNL") == null){
                    return newData;
                }
            }else{
                if(chnl.equals(newData.get("CUST_CHNL"))){
                    return newData;
                }
            }
        }
        return null;
    }
}
