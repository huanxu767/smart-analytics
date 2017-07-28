package com.sncfc.analytic.controller.systemAdmin;

import com.github.pagehelper.PageInfo;
import com.sncfc.analytic.controller.common.BaseController;
import com.sncfc.analytic.exception.ResultException;
import com.sncfc.analytic.pojo.AreaCompany;
import com.sncfc.analytic.pojo.BaseResultBean;
import com.sncfc.analytic.pojo.CenterCompany;
import com.sncfc.analytic.service.IAreaRelationService;
import com.sncfc.analytic.service.ICompanyService;
import com.sncfc.analytic.util.DateUtil;
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
@RequestMapping("/monthGoalAdmin")
public class MonthGoalAdminController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MonthGoalAdminController.class);
    @Autowired
    private IAreaRelationService areaRelationService;
    @Autowired
    private ICompanyService companyService;
    private static Integer BEGIN_YEAR = 2017;

    /**
     * 初始化页面
     * @return
     */
    @RequestMapping(value = "init")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("years", DateUtil.getYearsFromBegin(BEGIN_YEAR));
        modelAndView.addObject("areas",areaRelationService.queryDistinctAreaRelation());
        modelAndView.setViewName("monthGoalAdmin");
        return modelAndView;
    }

    /**
     * 查询-总部月指标
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryCenterCompanies")
    public BaseResultBean queryCenterCompanies(HttpServletRequest request){
        BaseResultBean baseResultBean= new BaseResultBean();
        Map params = getParameterMap(request);
        try {
            List<CenterCompany> centerCompanies = companyService.queryCenterCompanies(params);
            baseResultBean.setResult(centerCompanies);
            baseResultBean.success();
        }catch (Exception e){
            logger.error("查询-总部月指标",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 新增-总部月指标
     * @param centerCompany
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addOrUpdateCenterCompany")
    public BaseResultBean addOrUpdateCenterCompany(CenterCompany centerCompany){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            boolean flag = companyService.addOrUpdateCenterCompany(centerCompany);
            if(flag){
                baseResultBean.success();
            }else{
                baseResultBean.failure();
            }
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("新增-总部月指标",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
    /**
     * 查询-大区月指标
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryAreaCompanies")
    public BaseResultBean queryAreaCompanies(HttpServletRequest request){
        BaseResultBean baseResultBean= new BaseResultBean();
        Map params = getParameterMap(request);
        try {
            PageInfo<AreaCompany> areaCompanies = companyService.queryAreaCompanies(params);
            baseResultBean.setResult(areaCompanies);
            baseResultBean.success();
        }catch (Exception e){
            logger.error("查询-大区月指标",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 新增-大区月指标
     * @param areaCompany
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addOrUpdateAreaCompany")
    public BaseResultBean addOrUpdateAreaCompany(AreaCompany areaCompany){
        BaseResultBean baseResultBean= new BaseResultBean();
        try {
            boolean flag = companyService.addOrUpdateAreaCompany(areaCompany);
            if(flag){
                baseResultBean.success();
            }else{
                baseResultBean.failure();
            }
        }catch (ResultException e){
            baseResultBean.failure();
            baseResultBean.setErrorMsg(e.getResultMsg());
        }catch (Exception e){
            logger.error("新增-大区月指标",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
}
