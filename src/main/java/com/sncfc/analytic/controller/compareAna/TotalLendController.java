package com.sncfc.analytic.controller.compareAna;

import com.github.pagehelper.PageInfo;
import com.sncfc.analytic.controller.common.BaseController;
import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.pojo.AreaCompany;
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
@RequestMapping("/totalLend")
public class TotalLendController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(TotalLendController.class);

    @Autowired
    private ICompareAnalyticsService compareAnalyticsService;

    @RequestMapping(value = "init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("totalLend");
        return modelAndView;
    }

    /**
     * 查询-总投放图表1
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryTotalLendTFChart")
    public BaseResultBean queryTotalLendTFChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryTotalLendTFChart(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-总投放图表1",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询-首购复购客户
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @param isNewFlag
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryTotalLendTBChart")
    public BaseResultBean queryTotalLendTBChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate,String isNewFlag){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryTotalLend(beginDate,endDate,compareBeginDate,compareEndDate,isNewFlag);
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
     * 查询-客户申请分析表格
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCustApplyTable")
    public BaseResultBean queryCustApplyTable(String beginDate, String endDate, String compareBeginDate, String compareEndDate,String flag){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            List result = compareAnalyticsService.queryCustApplyTable(beginDate,endDate,compareBeginDate,compareEndDate,flag);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-客户申请分析表格",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 查询-分渠道图表
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCustApplyChart")
    public BaseResultBean queryCustApplyChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryCustApplyChart(beginDate,endDate,compareBeginDate,compareEndDate);
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
     * 查询-客户渗透率图
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCustStlChart")
    public BaseResultBean queryCustStlChart(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            Map result = compareAnalyticsService.queryCustStlChart(beginDate,endDate,compareBeginDate,compareEndDate);
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

}
