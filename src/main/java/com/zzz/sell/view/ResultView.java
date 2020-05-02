package com.zzz.sell.view;

import lombok.Data;

/**
 * http请求返回前端的结果对象Pojo
 * @author Jans_zhang
 * 2020/4/29 18:01
 */
@Data
//将值为null的属性不返回给前端
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultView<T> {
     private Integer code;//错误码
     private String msg = "";//提示信息
     private T data;//对象内容
}
