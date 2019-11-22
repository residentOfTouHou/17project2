/**
 *
 */
package com.cskaoyan.mall.service.impl;

import com.cskaoyan.mall.bean.generator.Admin;
import com.cskaoyan.mall.bean.generator.AdminExample;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public List<Admin> getAllAdminOderBy(String sort, String order,String username) {
        AdminExample adminExample = new AdminExample();
        if(!"".equals(username) && username != null) {
            adminExample.createCriteria().andUsernameLike("%" + username + "%");
        }
        adminExample.setOrderByClause(sort + " " + order);
         return adminMapper.selectByExample(adminExample);
    }

    @Override
    public int addAdmin(Admin admin) {
        return adminMapper.insertAndReturnId(admin);
    }

    @Override
    public int updateAdminById(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int deleteAdminById(int id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin getAdminByName(String name) {
        return adminMapper.selectAdminByUsername(name);
    }

    @Override
    public List<Admin> getAllAdmin() {
        return adminMapper.selectByExample(new AdminExample());
    }
}
