package com.example.studentmanager.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil {
    private static final String SECRET = "euitrydbnseotu9347857025620";

    private static String ISSUER = "sys_user";

    /**
     * 生成token
     * @param claims
     * @param expireDatePoint  过期时间点
     * @return
     */
    public static String genToken(Map<String, String> claims, Date expireDatePoint){

        try {
            //使用HMAC256进行加密
            Algorithm algorithm = Algorithm.HMAC256(SECRET);

            //创建jwt
            JWTCreator.Builder builder = JWT.create().
                    withIssuer(ISSUER). //发行人
                    withExpiresAt(expireDatePoint); //过期时间点

            //传入参数
            claims.forEach((key,value)-> {
                builder.withClaim(key, value);
            });

            //签名加密
            return builder.sign(algorithm);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密jwt
     * @param token
     * @return
     * @throws RuntimeException
     */
    public static Map<String,String> verifyToken(String token) throws RuntimeException{
        Algorithm algorithm = null;
        try {
            //使用HMAC256进行加密
            algorithm = Algorithm.HMAC256(SECRET);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

        //解密
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        Map<String, String> resultMap = new HashMap<>();
        try {
            DecodedJWT jwt =  verifier.verify(token);
            Map<String, Claim> map = jwt.getClaims();
            map.forEach((k,v) -> resultMap.put(k, v.asString()));
        } catch (TokenExpiredException e){

        }
        return resultMap;
    }
}
