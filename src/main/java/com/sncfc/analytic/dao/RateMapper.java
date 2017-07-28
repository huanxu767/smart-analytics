package com.sncfc.analytic.dao;

import com.github.pagehelper.Page;
import com.sncfc.analytic.pojo.Rate;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by 123 on 2017/3/13.
 */
@Repository
public interface RateMapper {

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
    int updateRate(Rate rate);
}
