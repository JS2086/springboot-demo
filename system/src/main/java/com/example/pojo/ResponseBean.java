package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局统一返回结果类
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 返回成功-无数据
     * @return
     */
    public static ResponseBean<String> success(String message) {
        return new ResponseBean<String>(200,message,null);
    }

    /**
     * 返回成功-有数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseBean<T> success(String message,T data) {
        return new ResponseBean<T>(200,message,data);
    }

    /**
     * 失败-无数据
     * @return
     */
    public static ResponseBean<String> error(String message) {
        return new ResponseBean<String>(500, message,null);
    }

    /**
     * 失败-有数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseBean<T> error(String message,T data) {
        return new ResponseBean<T>(500, message,data);
    }
}
