package com.example.studentmanager.controller;

import com.example.studentmanager.entity.ResponseData;
import com.example.studentmanager.utils.JWTUtil;
import com.example.studentmanager.utils.RedisUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "user", description = "the user API")
@RestController
public class UserController {
    private String admin = "admin";
    private String psd = "qwertyuiop";
    @Resource
    private RedisUtil redisUtil;

    @Operation(summary = "Logs user into the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseData.class),
                            examples = {@ExampleObject(value = "{\n" +
                                    "  \"message\": \"Ok\",\n" +
                                    "  \"code\": 200,\n" +
                                    "  \"data\": {\n" +
                                    "    \"username\": \"admin\",\n" +
                                    "    \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9......-2Xz7JH9Ilg_SVbQ\"\n" +
                                    "  }\n" +
                                    "}")})})})
    @PostMapping(value = "/login", consumes = { "application/x-www-form-urlencoded" })
    public ResponseData login(final String username, final String password)
    {
        ResponseData responseData = ResponseData.ok();
        if(username.equals(admin) && password.equals(psd)){
            Map<String, String> map = new HashMap<String, String>();
            map.put("username", username);
            String token = JWTUtil.genToken(map, new Date(System.currentTimeMillis() + 60L* 1000L * 30L));
            redisUtil.set(username, token, 60 * 60 * 2);
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
