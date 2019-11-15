package com.cskaoyan.mall.mytest;

import com.cskaoyan.mall.bean.AddressExample;
import com.cskaoyan.mall.mapper.AddressMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author 杨盛
 * @date 2019/11/15 16:09
 */
public class GeneratorTest {

    SqlSessionFactory sqlSessionFactory;

    SqlSession sqlSession;

    Logger logger = Logger.getLogger(this.getClass());

    @Before
    public void init() throws IOException {
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis.xml"));
    }

    @After
    public void after() {
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void myTest1() {
        sqlSession = sqlSessionFactory.openSession();
        AddressMapper addressMapper = sqlSession.getMapper(AddressMapper.class);
        AddressExample addressExample = new AddressExample();
        addressExample.createCriteria().andAddressIsNotNull();
        long example = addressMapper.countByExample(addressExample);
        logger.info(example);
    }
}
