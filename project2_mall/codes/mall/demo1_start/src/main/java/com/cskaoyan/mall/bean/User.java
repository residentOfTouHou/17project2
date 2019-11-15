package com.cskaoyan.mall.bean;

import lombok.Data;

@Data
public class User {
    int id;
    String username;
    String password;
    int age;
    String gender;
    String[] hobby; //["coding","basketball"]
}
