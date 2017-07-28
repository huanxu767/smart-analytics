package com.sncfc.analytic.controller.dashbord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 123 on 2017/3/14.
 */
@Controller
@RequestMapping("/dashbord")
public class DashbordController {

    private static final Logger logger = LoggerFactory.getLogger(DashbordController.class);

    /**
     * dashbord
     * @return
     */
    @RequestMapping(value = "init")
    public ModelAndView init(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashbord");
        return modelAndView;
    }
}
