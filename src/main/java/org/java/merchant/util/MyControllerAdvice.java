package org.java.merchant.util;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyControllerAdvice {
    /**
     * 全局异常捕捉处理
     * @param ex
     * @return
     */
    //代码写完开启 错误拦截!!!
    @ExceptionHandler(value = Exception.class)
    public ModelAndView errorHandler(Exception ex) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("404");
        return modelAndView;
    }

    /**
     * 拦截捕捉自定义异常 MyException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ModelAndView myErrorHandler(MyException ex) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("404");
        modelAndView.addObject("code", ex.getCode());
        modelAndView.addObject("msg", ex.getMsg());
        return modelAndView;
    }
}
