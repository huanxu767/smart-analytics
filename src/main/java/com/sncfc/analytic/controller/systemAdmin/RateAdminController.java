package com.sncfc.analytic.controller.systemAdmin;

import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.pojo.BaseResultBean;
import com.sncfc.analytic.pojo.Rate;
import com.sncfc.analytic.service.IRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 123 on 2017/3/14.
 */
@Controller
@RequestMapping("/rateAdmin")
public class RateAdminController {

    private static final Logger logger = LoggerFactory.getLogger(RateAdminController.class);

    @Autowired
    private IRateService iRateService;

    @RequestMapping(value = "init")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rateAdmin");
        return modelAndView;
    }

    /**
     * 查询达成率列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryRates")
    public BaseResultBean queryRates(){
        BaseResultBean  resultBean = new BaseResultBean();
        try {
            List<Rate> rates = iRateService.queryRates();
            resultBean.success();
            resultBean.setResult(rates);
        }catch (Exception e){
            logger.error("",e);
            resultBean.failure();
        }
        return resultBean;
    }
    /**
     * 更新达成率权重
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateRate")
    public BaseResultBean updateRate(Rate rate){
        BaseResultBean  resultBean = new BaseResultBean();
        try {
            boolean flag = iRateService.updateRate(rate);
            if(flag){
                resultBean.success();
            }else{
                resultBean.failure();
            }
        } catch (Exception e){
            logger.error("",e);
            resultBean.failure();
        }
        return resultBean;
    }

}
