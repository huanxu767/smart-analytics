package com.sncfc.analytic.controller.common;

import com.bi.framework.sso.SsoUtil;
import com.bi.framework.sso.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 123 on 2017/3/14.
 */
@Controller
@RequestMapping("/")
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "index")
    public ModelAndView main(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Ticket ticket = SsoUtil.getTicket(request.getSession());
        modelAndView.addObject("empNo",ticket.getEmpNo());
        return modelAndView;
    }

    /**
     * 下载页
     * @return
     */
    @RequestMapping(value = "sale/downLoad")
    public ModelAndView downLoad(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("downLoad");
        return modelAndView;
    }



}
