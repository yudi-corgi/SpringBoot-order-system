package com.person.sell.VO;

import lombok.Data;

@Data
public class ResultVO<T> {

    private Integer code;
    private String msg;
    /** 数据 */
    private T data;
}
