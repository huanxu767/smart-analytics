package com.sncfc.analytic.service.impl;

import com.sncfc.analytic.dao.TestMapper;
import com.sncfc.analytic.pojo.User;
import com.sncfc.analytic.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by 123 on 2017/3/14.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public void updateTestTransaction(){

    }

    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public void updateTest(){
        User user = testMapper.getUser(1);
        System.out.println(user.toString());
        Map map = testMapper.getUserMap(1);
        System.out.println(map);
    }
}