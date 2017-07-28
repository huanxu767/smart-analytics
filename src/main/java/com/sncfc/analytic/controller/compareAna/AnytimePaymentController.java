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
import java.util.Map;

/**
 * Created by 123 on 2017/3/14.
 */
@Controller
@RequestMapping("/anytimePayment")
public class AnytimePaymentController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(AnytimePaymentController.class);
    @Autowired
    private ICompareAnalyticsService compareAnalyticsService;

    @RequestMapping(value = "init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("anytimePayment");
        return modelAndView;
    }

    /**
     * 查询-随借随还营销策略分析投放金额、人数对比
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "querySjshTfChart")
    public BaseResultBean querySjshTfChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.querySjshTfChart(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-分渠道图表",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 查询-随借随还 额度影响
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "querySjshEdyxChart")
    public BaseResultBean querySjshEdyxChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.querySjshEdyxChart(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-额度影响图表",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询-营销活动明细
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryTicketActivityName")
    public BaseResultBean queryTicketActivityName(HttpServletRequest request){
        BaseResultBean baseResultBean= new BaseResultBean();
        Map params = getParameterMap(request);
//        4:随借随还
        params.put("prodid","4");
//        params.put("endDate","20171108");
//        params.put("beginDate","20161107");
        try {
            PageInfo<Map> activity = compareAnalyticsService.queryTicketActivityName(params);
            baseResultBean.setResult(activity);
            baseResultBean.success();
        }catch (Exception e){
            logger.error("查询-营销活动明细",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 随借随还-客群分析 首购与复购
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryAnyGroupNewAndOld")
    public BaseResultBean queryAnyGroupNewAndOld(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryAnyGroupNewAndOld(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-随借随还首购与复购",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 随借随还-客群分析 是否苏宁员工
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryAnyGroupSuningOrNot")
    public BaseResultBean queryAnyGroupSuningOrNot(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryAnyGroupSuningOrNot(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-随借随还是否苏宁员工",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 随借随还-客群分析 非苏宁员工来源渠道
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryAnyCustChannel")
    public BaseResultBean queryAnyCustChannel(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryAnyCustChannel(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-非苏宁员工来源渠道",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

}
