package com.example.studentmanager.interceptor;

import com.example.studentmanager.utils.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        String responseData = "{\"code\":401,\"message\":\"Unauthorized\"}";
        //token不存在
        if(null != token) {
            Map<String, String> login = JWTUtil.verifyToken(token);
            String username = request.getHeader("username");
            //解密token后的loginId与用户传来的loginId不一致，一般都是token过期
            if(null != username && null != login) {
                if(username.equals(login.get("username"))) {
                    return true;
                }
                else{
                    responseMessage(response, response.getWriter(), responseData);
                    return false;
                }
            }
            else
            {
                responseMessage(response, response.getWriter(), responseData);
                return false;
            }
        }
        else
        {
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
    }

    private void responseMessage(HttpServletResponse response, PrintWriter out, String json) {
        response.setContentType("application/json; charset=utf-8");
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
