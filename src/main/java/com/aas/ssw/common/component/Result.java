package com.aas.ssw.common.component;

import lombok.Data;

import java.io.Serializable;

/**该类用于封装后台方法执行后的返回结果信息
 *
 * @author xl
 * @date 2017/8/16
 */
@Data
public class Result<T> implements Serializable{


    private static final long serialVersionUID = 937954638527393958L;
    /**
     * 是否成功的标志
     */
    private String flag;
    /**
     * 具体信息
     */
    private String msg;
    /**
     * 前台需要的数据
     */
    private T data;
    /**
     * 数据总量，前台分页用
     */
    private Integer total;



    public static <T> Result getInfo(String flag, String msg, T data, Integer total){
        Result result = new Result();
        result.setFlag(flag);
        result.setMsg(msg);
        result.setData(data);
        result.setTotal(total);
        return result;
    }






}
