package com.frame.gateway.util;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

/*
 *@ClassName AppJwtUtil
 *@Description JWT相关工具类
 *@Author LiuQi
 *@Date 2022/12/22 19:22
 *@Version 1.0
 */
public class AppJwtUtil {
    //TOKEN的有效期一小时(S)
    private static final int TOKEN_TIME_OUT = 3_600;
    // 加密KEY
    private static final String TOKEN_ENCRY_KEY = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";
    //最小刷新间隔(S)
    private static final int REFRESH_TIME = 300;

    /**
     * 获取token*
     *
     * @param id
     * @return
     */
    public static String getToken(Long id) {
        Map<String, Object> claimMaps = new HashMap<>();
        claimMaps.put("id", id);
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date(currentTimeMillis))//签发时间
                .setSubject("system") //说明
                .setIssuer("冰糖雪梨")//签发者信息
                .setAudience("app")//接收用户
                .compressWith(CompressionCodecs.GZIP)//数据压缩方式
                .signWith(SignatureAlgorithm.HS512, generalKey())//加密方式
                .setExpiration(new Date(currentTimeMillis + TOKEN_TIME_OUT * 1000)) //过期时间戳
                .addClaims(claimMaps) //cla信息
                .compact();
    }

    /**
     * 根据字符串 生成加密KEY*
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(TOKEN_ENCRY_KEY.getBytes(StandardCharsets.UTF_8));
        SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return secretKey;

    }

    /**
     * 验证token是否过期*
     *
     * @param claims
     * @return -1 0 有效 1 2 过期
     */
    public static int verifyToken(Claims claims) {
        if (claims == null) {
            return 1;
        }
        try {
            claims.getExpiration().before(new Date());
            //自动刷新token
            if ((claims.getExpiration().getTime() - System.currentTimeMillis()) > REFRESH_TIME * 1000) {
                return -1;
            } else {
                return 0;
            }
        } catch (ExpiredJwtException e) {
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }


    /**
     * 获取 token中的claims信息*
     * @param token
     * @return
     */
    public static Jws<Claims> getJws(String token){
        return Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
    }

    /**
     * 获取payload body 信息*
     * @param token
     * @return
     */
    public static Claims getClaimsBody(String token){
        try {
            return getJws(token).getBody();
        }catch (ExpiredJwtException e){
            return null;
        }
    }

    /**
     * 获取header body 信息*
     * @param token
     * @return
     */
    public static JwsHeader getHeaderBody(String token){
        return getJws(token).getHeader();
    }

    public static void main(String[] args) {
        String token = AppJwtUtil.getToken(110L);
        System.out.println(token);
        Claims claimsBody = getClaimsBody(token);
        System.out.println(claimsBody);
        final int i = verifyToken(claimsBody);
        if(i<1){
            Integer id = (Integer) claimsBody.get("id");
            System.out.printf("用户登录成功 id: %d", id);
        }else {
            System.out.println("token 校验失败！");
        }
    }

}
