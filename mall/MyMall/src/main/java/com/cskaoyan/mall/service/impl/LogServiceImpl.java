/**
 *
 */
package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Log;
import com.cskaoyan.mall.bean.generator.LogExample;
import com.cskaoyan.mall.mapper.LogMapper;
import com.cskaoyan.mall.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public List<Log> getLogsOrderBy(String sort, String order, String username) {
        LogExample logExample = new LogExample();
        if(!"".equals(username) && username != null) {
            logExample.createCriteria().andAdminLike("%" + username + "%");
        }
        logExample.setOrderByClause(sort + " " + order);
        return logMapper.selectByExample(logExample);
    }

    @Override
    public int insertLog(Log log) {
        return logMapper.insertSelective(log);
    }
}
