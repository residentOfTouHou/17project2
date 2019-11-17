package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.jsonbean.ConfigOrder;
import com.cskaoyan.mall.bean.jsonbean.ConfigWx;
import com.cskaoyan.mall.bean.jsonbean.Express;
import com.cskaoyan.mall.bean.jsonbean.Mall;

public interface ConfigService {

    Mall selectMallConfig();

    int updateMallConfig(Mall mall);

    Express selectExpressConfig();

    int updateExpressConfig(Express express);

    ConfigOrder selectOrderConfig();

    int updateOrderConfig(ConfigOrder configOrder);

    ConfigWx selectWxConfig();

    int updateWxConfig(ConfigWx configWx);
}
