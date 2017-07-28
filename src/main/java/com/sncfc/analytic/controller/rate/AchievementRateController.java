package com.sncfc.analytic.controller.rate;

import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.pojo.BaseResultBean;
import com.sncfc.analytic.service.ICompareAnalyticsService;
import com.sncfc.analytic.util.DateUtil;
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
@RequestMapping("/achieve")
public class AchievementRateController {

    private static final Logger logger = LoggerFactory.getLogger(AchievementRateController.class);

    @Autowired
    private ICompareAnalyticsService compareAnalyticsService;
    //开始时间
    private int beginYear = 2017;

    @RequestMapping(value = "init")
    public ModelAndView init(){
        //当前季度
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("quarter", DateUtil.quarter());
        modelAndView.addObject("years", DateUtil.getYearsFromBeginOn(beginYear));
        modelAndView.setViewName("achievementRate");
        return modelAndView;
    }


    /**
     * 查询-总部达成率图表
     * @param year
     * @param quarter
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryRateTargetChart")
    public BaseResultBean queryRateTargetChart(int year,int quarter){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryRateTargetChart(year,quarter);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-总部达成率图表",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 查询-总部达成率表格
     * @param year
     * @param quarter
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryRateTargetTable")
    public BaseResultBean queryRateTargetTable(int year,int quarter){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            List result = compareAnalyticsService.queryRateTargetTable(year,quarter);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-总部达成率表格",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
}
