package com.sncfc.analytic.service.impl;

import com.github.pagehelper.Page;
import com.sncfc.analytic.dao.RateMapper;
import com.sncfc.analytic.pojo.Rate;
import com.sncfc.analytic.service.IRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by 123 on 2017/3/14.
 */
@Service
@Transactional(propagation= Propagation.REQUIRED,rollbackForClassName="Exception")
public class RateServiceImpl implements IRateService {

    @Autowired
    private RateMapper rateMapper;

    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public Page<Rate> queryRates() {
        return rateMapper.queryRates();
    }

    @Override
    @Transactional(propagation= Propagation.NOT_SUPPORTED)
    public boolean updateRate(Rate rate) {
        return rateMapper.updateRate(rate)>0;
    }
}