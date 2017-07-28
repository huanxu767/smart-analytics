package com.sncfc.analytic.service;

import com.github.pagehelper.Page;
import com.sncfc.analytic.pojo.Rate;
import java.util.List;

/**
 * Created by 123 on 2017/3/14.
 */
public interface IRateService {
    /**
     * 查询达成率权重
     * @return
     */
    Page<Rate> queryRates();

    /**
     * 修改达成率权重
     * @param rate
     * @return
     */
   boolean updateRate(Rate rate);
}
