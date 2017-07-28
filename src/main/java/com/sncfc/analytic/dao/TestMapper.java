package com.sncfc.analytic.dao;

import com.sncfc.analytic.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * Created by 123 on 2017/3/13.
 */
@Repository
public interface TestMapper {
    User getUser(int id);
    Map getUserMap(int id);

}
