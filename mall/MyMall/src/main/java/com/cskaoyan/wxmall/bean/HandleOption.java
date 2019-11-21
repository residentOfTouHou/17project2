package com.cskaoyan.wxmall.bean;

import lombok.Data;

@Data
public class HandleOption {
    boolean cancel;
    boolean delete;
    boolean pay;
    boolean comment;
    boolean confirm;
    boolean refund;
    boolean rebuy;

    public HandleOption(boolean cancel, boolean delete, boolean pay, boolean comment, boolean confirm, boolean refund, boolean rebuy) {
        this.cancel = cancel;
        this.delete = delete;
        this.pay = pay;
        this.comment = comment;
        this.confirm = confirm;
        this.refund = refund;
        this.rebuy = rebuy;
    }
}
