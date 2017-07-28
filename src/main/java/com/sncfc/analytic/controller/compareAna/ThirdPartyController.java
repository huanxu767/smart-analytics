package com.sncfc.analytic.controller.compareAna;

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

import java.util.List;
import java.util.Map;

/**
 * Created by 123 on 2017/3/14.
 */
@Controller
@RequestMapping("/thirdParty")
public class ThirdPartyController {

    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyController.class);
    @Autowired
    private ICompareAnalyticsService compareAnalyticsService;

    @RequestMapping(value = "init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("thirdParty");
        return modelAndView;
    }

    /**
     * 查询-第三方产品明细
     * @param beginDate
     * @param endDate
     * @param compareBeginDate
     * @param compareEndDate
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryThirdPartyTable")
    public BaseResultBean queryThirdPartyTable(String beginDate, String endDate, String compareBeginDate, String compareEndDate){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            List<Map> result = compareAnalyticsService.queryThirdPartyTable(beginDate,endDate,compareBeginDate,compareEndDate);
            baseResultBean.setResult(result);
            baseResultBean.success();
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("查询-第三方产品明细",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
}
