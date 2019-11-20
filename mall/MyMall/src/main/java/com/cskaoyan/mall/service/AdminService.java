package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.generator.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> getAllAdminOderBy(String sort, String order, String username);

    int addAdmin(Admin admin);

    int updateAdminById(Admin admin);

    int deleteAdminById(int id);

    Admin getAdminByName(String name);
}
