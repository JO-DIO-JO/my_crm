package com.shsxt.crm;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.exceptions.NoLoginException;
import com.shsxt.crm.exceptions.ParamsException;
import com.shsxt.crm.model.ResultInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    /**
     * 1. 方法返回视图
     * 2. 方法返回json
     * @param request
     * @param response
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("code", 315);
        mv.addObject("msg", "服务器异常...");
        mv.addObject("ctx", request.getContextPath());

        /**
         * 判断异常类型是否为 未登录异常
         */
        if (e instanceof NoLoginException) {
            NoLoginException noLoginException = (NoLoginException) e;
            mv.addObject("code", noLoginException.getCode());
            mv.addObject("msg", noLoginException.getMsg());
            mv.setViewName("no_login");
            return mv;
        }

        if (o instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) o;
            ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
            if (null == responseBody) { // 返回视图
                if (e instanceof ParamsException) {
                    ParamsException paramsException = (ParamsException) e;
                    mv.addObject("code", paramsException.getCode());
                    mv.addObject("msg", paramsException.getMsg());
                }
                return mv;
            } else { // 返回json
                ResultInfo<Object> resultInfo = new ResultInfo<>();
                resultInfo.setCode(300);
                resultInfo.setMsg("服务器异常！");
                if (e instanceof ParamsException) {
                    ParamsException paramsException = (ParamsException) e;
                    resultInfo.setCode(paramsException.getCode());
                    resultInfo.setMsg(paramsException.getMsg());
                }
                // 向浏览器输出 json 格式错误信息
                PrintWriter pw = null;
                response.setContentType("application/json,charset=utf-8");
                response.setCharacterEncoding("utf-8");
                try {
                    pw = response.getWriter();
                    pw.write(JSON.toJSONString(resultInfo));
                    pw.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    if (null != pw) {
                        pw.close();
                    }
                }
                return null;
            }
        } else {
            return mv;
        }
    }

}
