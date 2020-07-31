package com.ljh.redis.dto;

/**
 * @Author: Liu.jihong
 * @Date: 2020/7/31 9:20
 */
public class ApiReturnUtil {
    private static ApiReturnObject apiReturnObject;
    public static ApiReturnObject error(String message){
        apiReturnObject.setMessage(message);
        return apiReturnObject;
    }
    public static ApiReturnObject success(String cid){
        apiReturnObject.setCid(cid);
        return apiReturnObject;
    }
}
