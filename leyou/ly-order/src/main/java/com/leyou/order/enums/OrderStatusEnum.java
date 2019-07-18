package com.leyou.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum  OrderStatusEnum {
    UN_PAY(1, "未付款"),
    PAYED(2, "已付款"),
    DELIVERED(3, "已发货未确认"),
    SUCCESS(4, "已确认，未评价"),
    CLOSED(5, "已关闭，交易失败"),
    RATED(6, "已评价"),
    ;
    private int code;
    private String desc;

    public int value() {
        return this.code;
    }
}
