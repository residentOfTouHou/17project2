package com.cskaoyan.mall.bean.jsonbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StatUser {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;

    private int users;
}
