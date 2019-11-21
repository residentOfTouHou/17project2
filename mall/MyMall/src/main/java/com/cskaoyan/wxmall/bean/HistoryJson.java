package com.cskaoyan.wxmall.bean;

import java.util.List;

public class HistoryJson {

    /**
     * errno : 0
     * data : ["13test","1","123456789","123"]
     * errmsg : 成功
     */

    private int errno;
    private String errmsg;
    private List<String> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
