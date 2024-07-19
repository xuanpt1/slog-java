package com.xuanpt2.slogjava.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


/**
 * @author xuanpt2
 */
public class JwtUtils {
    /**
     *
     */
    private static final String KEY = "haruhikage";

    private static final HashSet<String> blackList = new HashSet<>();
    //加入黑名单方法
    public static boolean invalidate(String token){
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            DecodedJWT verify = jwtVerifier.verify(token);
            Map<String, Claim> claims = verify.getClaims();
            //取出UUID丢进黑名单中
            return blackList.add(verify.getId());
        } catch (JWTVerificationException e) {
            return false;
        }
    }




    /**
     * 根据用户信息生成JWT令牌
     * @param user UserDetails形式的User对象
     * @return 根据User信息创建的JWT
     */
    public static String createJwt(UserDetails user){
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(Calendar.SECOND, 3600 * 24 * 7);
        return JWT.create()
                //额外添加一个UUID用于记录黑名单，将其作为JWT的ID属性jti
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("name", user.getUsername())
                .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withExpiresAt(calendar.getTime())
                .withIssuedAt(now)
                .sign(algorithm);
    }

    /**
     * 根据JWT令牌校验用户信息
     * @param token JWT令牌
     * @return 校验生成的用户信息
     */
    public static UserDetails resolveJwt(String token){
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            DecodedJWT verify = jwtVerifier.verify(token);
            //判断是否存在于黑名单中，如果存在，则返回null表示失效
            if(blackList.contains(verify.getId())) {
                return null;
            }
            Map<String, Claim> claims = verify.getClaims();
            if(new Date().after(claims.get("exp").asDate())) {
                return null;
            }
            return User
                    .withUsername(claims.get("name").asString())
                    .password("")
                    .authorities(claims.get("authorities").asArray(String.class))
                    .build();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}