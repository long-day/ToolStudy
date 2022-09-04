package com.longday.toolstudy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 * @author 君
 */
public class JwtUtil {

    /**
     * 有效期一小时
     */
    public static final Long JWT_TTL = 60 * 60 *1000L;
    /**
     *     设置秘钥明文
     */
    public static final String JWT_KEY = "longDay";

    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return 加密字符串
     */
    public static String createJwt(String subject) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, null, getUuid());
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return String
     */
    public static String createJwt(String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUuid());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                //唯一的ID
                .setId(uuid)
                // 主题  可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer("longDay")
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id id
     * @param subject 主题
     * @param ttlMillis 时间
     * @return 加密token字符串
     */
    public static String createJwt(String id, String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return 秘钥
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
    
    /**
     * 解析
     *
     * @param jwt 秘文
     * @return Claims
     */
    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
    public static void main(String[] args) {
        String jwt = JwtUtil.createJwt("longLongAgo");
        System.out.println("秘文:"+jwt);
        Claims claims = JwtUtil.parseJwt(jwt);
        System.out.println("解码明文"+claims.getSubject());

    }

}
