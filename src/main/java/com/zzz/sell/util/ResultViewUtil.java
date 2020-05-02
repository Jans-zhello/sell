package com.zzz.sell.util;

import com.zzz.sell.view.ResultView;

/**
 *http请求返回前端的结果对象Pojo的工具类
 * @author Jans_zhang
 * 2020/4/29 23:41
 */
public class ResultViewUtil {
    public static ResultView success(Object o){
           ResultView resultView = new ResultView();
           resultView.setData(o);
           resultView.setCode(0);
           resultView.setMsg("成功");
           return  resultView;
    }
    public static  ResultView success(){
        return  success(null);
    }
    public static  ResultView error(Integer code,String msg){
        ResultView resultView = new ResultView();
        resultView.setMsg(msg);
        resultView.setCode(code);
        return  resultView;
    }

}
