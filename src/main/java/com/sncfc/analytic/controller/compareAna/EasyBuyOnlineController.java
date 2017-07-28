package com.sncfc.analytic.controller.compareAna;

import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.pojo.BaseResultBean;
import com.sncfc.analytic.service.IOnLineAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/3/14.
 */
@Controller
@RequestMapping("/easyBuyOnline")
public class EasyBuyOnlineController {

    private static final Logger logger = LoggerFactory.getLogger(EasyBuyOnlineController.class);

    @Autowired
    private IOnLineAnalyticsService onLineAnalyticsService;

    @RequestMapping(value = "init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("easyBuyOnline");
        return modelAndView;
    }

    /**
     * 查询投放金额
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryLoanAmtData")
    public BaseResultBean queryLoanAmtData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = onLineAnalyticsService.queryLoanAmtData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-投放金额",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询营销策略
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryMarketingStrategyData")
    public BaseResultBean queryMarketingStrategyData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = onLineAnalyticsService.queryMarketingStrategyData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-营销策略",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询品类构成
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryDeptConstituteData")
    public BaseResultBean queryDeptConstituteData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = onLineAnalyticsService.queryDeptConstituteData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-客户渗透率",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }


    /**
     * 查询客户渗透率
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCustomPermeabilityData")
    public BaseResultBean queryCustomPermeabilityData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = onLineAnalyticsService.queryCustomPermeabilityData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-客户渗透率",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询人员构成数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryPersonConstituteData")
    public BaseResultBean queryPersonConstituteData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = onLineAnalyticsService.queryPersonConstituteData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-人员构成数据",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询非易购员工来源渠道数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryPersonNotYgChannelData")
    public BaseResultBean queryPersonNotYgChannelData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = onLineAnalyticsService.queryPersonNotYgChannelData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-人员构成数据",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询新老客户渠道数据
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryNewOrOldCustomDataByChannelData")
    public BaseResultBean queryNewOrOldCustomDataByChannelData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = onLineAnalyticsService.queryNewOrOldCustomDataByChannelData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-新老客户渠道数据",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 客群分析 营销能力分析
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryMarketingAnalysisData")
    public BaseResultBean queryMarketingAnalysisData(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            List result = onLineAnalyticsService.queryMarketingAnalysisData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-营销能力分析",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }


}
