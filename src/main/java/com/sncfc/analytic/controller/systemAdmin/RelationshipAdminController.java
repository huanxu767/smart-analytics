package com.sncfc.analytic.controller.systemAdmin;

import com.github.pagehelper.PageInfo;
import com.sncfc.analytic.controller.common.BaseController;
import com.sncfc.analytic.pojo.AreaRelation;
import com.sncfc.analytic.pojo.BaseResultBean;
import com.sncfc.analytic.service.IAreaRelationService;
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
@RequestMapping("/relationshipAdmin")
public class RelationshipAdminController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RelationshipAdminController.class);

    @Autowired
    private IAreaRelationService areaRelationService;

    /**
     * 页面初始化苏宁公司与大区关系
     * @return
     */
    @RequestMapping(value = "init")
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("relationshipAdmin");
        return modelAndView;
    }

    /**
     * 分页查询--苏宁公司与大区关系
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryRelationships")
    public BaseResultBean queryRelationships(HttpServletRequest request){
        BaseResultBean baseResultBean= new BaseResultBean();
        Map params = getParameterMap(request);
        try {
            PageInfo page = areaRelationService.queryAreaRelations(params);
            baseResultBean.setResult(page);
            baseResultBean.success();
        }catch (Exception e){
            logger.error("查询苏宁公司与大区关系",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }

    /**
     * 新增或更新--苏宁公司与大区关系
     * @param areaRelation
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insertOrUpdateRelationship")
    public BaseResultBean insertOrUpdateRelationship(AreaRelation areaRelation){
        BaseResultBean baseResultBean= new BaseResultBean();
        areaRelation.formatDate();
        try {
            boolean flag = areaRelationService.insertOrUpdateAreaRelation(areaRelation);
            if(flag){
                baseResultBean.success();
            }else{
                baseResultBean.failure();
            }
        }catch (Exception e){
            logger.error("新增或更新苏宁公司与大区关系",e);
            baseResultBean.failure();
        }
        return baseResultBean;
    }
}
