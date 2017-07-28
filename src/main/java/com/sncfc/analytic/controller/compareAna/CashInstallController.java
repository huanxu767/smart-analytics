package com.sncfc.analytic.controller.compareAna;

import com.github.pagehelper.PageInfo;
import com.sncfc.analytic.controller.common.BaseController;
import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.pojo.BaseResultBean;
import com.sncfc.analytic.service.ICompareAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/3/14.
 */
@Controller
@RequestMapping("/cashInstall")
public class CashInstallController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(CashInstallController.class);
    @Autowired
    private ICompareAnalyticsService compareAnalyticsService;
    @RequestMapping(value = "init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cashInstall");
        return modelAndView;
    }

    /**
     * 查询-现金分期 额度影响
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCashEdyxChart")
    public BaseResultBean queryCashEdyxChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryCashEdyxChart(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-现金分期额度影响图表",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询-现金分期营销活动明细
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryTicketActivityName")
    public BaseResultBean queryTicketActivityName(HttpServletRequest request){
        BaseResultBean baseResultBean= new BaseResultBean();
        Map params = getParameterMap(request);
//        5:随借随还
        params.put("prodid","5");
//        params.put("endDate","20171108");
//        params.put("beginDate","20161107");
        try {
            PageInfo<Map> activity = compareAnalyticsService.queryTicketActivityName(params);
            baseResultBean.setResult(activity);
            baseResultBean.success();
        }catch (Exception e){
            logger.error("查询-现金分期营销活动明细",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 现金分期-客群分析 非苏宁员工来源渠道
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCashInstallChannel")
    public BaseResultBean queryCashInstallChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryCashInstallChannel(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-现金分期-非苏宁员工来源渠道",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 现金分期-客群分析人员构成
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCashInstallSuningOrNot")
    public BaseResultBean queryCashInstallSuningOrNot(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryCashInstallSuningOrNot(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-现金分期-客群分析人员构成",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 现金分期-客群分析首购与复购
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCashGroupNewAndOld")
    public BaseResultBean queryCashGroupNewAndOld(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryCashGroupNewAndOld(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-现金分期-客群分析首购与复购",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 现金分期-现金分期分期投放信息
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCashLoanData")
    public BaseResultBean queryCashLoanData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryCashLoanData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-现金分期-现金分期分期投放信息",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 现金分期-客单分析
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCashCustChannel")
    public BaseResultBean queryCashCustChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            List result = compareAnalyticsService.queryCashCustChannel(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-现金分期-客单分析",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

}
