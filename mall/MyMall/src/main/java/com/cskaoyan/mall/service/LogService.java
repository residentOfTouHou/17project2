package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.generator.Log;

import java.util.List;

public interface LogService {
    List<Log> getLogsOrderBy(String sort, String order, String username);

    int insertLog(Log log);
}
