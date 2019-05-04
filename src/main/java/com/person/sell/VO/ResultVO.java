package com.person.sell.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = -8030966316323066620L;
    private Integer code;
    private String msg;
    /** 数据 */
    private T data;
}
