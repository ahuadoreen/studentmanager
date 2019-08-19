package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.utils.JWTUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private String admin = "admin";
    private String psd = "qwertyuiop";

    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseData login(final String username, final String password)
    {
        ResponseData responseData = ResponseData.ok();
        if(username.equals(admin) && password.equals(psd)){
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", username);
            String token = JWTUtil.genToken(map, new Date(System.currentTimeMillis() + 60L* 1000L* 30L));
            //封装成对象返回给客户端
            responseData.putDataValue("username", username);
            responseData.putDataValue("token", token);
        }
        else{
            responseData = ResponseData.customerError();
        }
        return responseData;
    }
}
