package com.sncfc.analytic.controller.compareAna;

import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.pojo.BaseResultBean;
import com.sncfc.analytic.service.ICompareAnalyticsService;
import com.sncfc.analytic.service.IStoreAnalyticsService;
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
 * 易购门店
 */
@Controller
@RequestMapping("/easyBuyStore")
public class EasyBuyStoreController {

    private static final Logger logger = LoggerFactory.getLogger(EasyBuyStoreController.class);
    @Autowired
    private IStoreAnalyticsService storeAnalyticsService;

    @RequestMapping(value = "init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("easyBuyStore");
        return modelAndView;
    }
    /**
     * 客单分析
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
            Map result = storeAnalyticsService.queryMarketingStrategyData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-客单分析",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 构成分析
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
            Map result = storeAnalyticsService.queryDeptConstituteData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-构成分析",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 客群分析 客户渗透率
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
            Map result = storeAnalyticsService.queryCustomPermeabilityData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-构成分析",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 客群分析 人员构成
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
            Map result = storeAnalyticsService.queryPersonConstituteData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-人员构成",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 客群分析 非易购员工来源渠道数据
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
            Map result = storeAnalyticsService.queryPersonNotYgChannelData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-非易购员工来源渠道数据",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 客群分析 首购复购客户
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
            Map result = storeAnalyticsService.queryNewOrOldCustomDataByChannelData(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-首购复购客户",e);
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
            List result = storeAnalyticsService.queryMarketingAnalysisData(beginDate,endDate,compareBeginDate,compareEndDate);
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
