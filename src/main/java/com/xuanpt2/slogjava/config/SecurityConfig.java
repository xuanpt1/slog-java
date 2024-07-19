package com.xuanpt2.slogjava.config;

import com.alibaba.fastjson2.JSON;
import com.xuanpt2.slogjava.utils.JwtUtils;
import com.xuanpt2.slogjava.vo.TResponseVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import com.xuanpt2.slogjava.filter.JwtAuthenticationFilter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author itbaima.cn
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(conf -> {

                    conf.requestMatchers(HttpMethod.GET,"/blogContents/getAllContents").permitAll();
                    conf.requestMatchers(HttpMethod.POST,"/blogContents/getContentById").permitAll();
                    conf.requestMatchers(HttpMethod.POST,"/blogContents/getContentsAbsByMetaId").permitAll();
                    conf.requestMatchers(HttpMethod.POST,"/blogComments/getCommentsByCid").permitAll();
                    conf.requestMatchers(HttpMethod.POST,"/blogComments/saveComment").permitAll();
                    conf.requestMatchers(HttpMethod.GET,"/blogGroups/getAllGroups").permitAll();
                    conf.requestMatchers(HttpMethod.POST,"/blogGroups/getGroupContentsByGroupId").permitAll();
                    conf.requestMatchers(HttpMethod.GET,"/blogGroups/join/{uri}").permitAll();
                    conf.requestMatchers(HttpMethod.GET,"/blogMetas/getAllMetas").permitAll();
                    conf.requestMatchers(HttpMethod.GET,"/blogRssContents/getAllRssContents").permitAll();
                    conf.requestMatchers(HttpMethod.GET,"/blogRssContents/getAllRssContents").permitAll();
                    conf.requestMatchers("/login").permitAll();
                    conf.anyRequest().authenticated();
                })
                .formLogin(conf -> {
                    //一般分离之后，为了统一规范接口，使用 /api/模块/功能 的形式命名接口
                    conf.loginProcessingUrl("/login");
                    conf.successHandler(this::handleProcess);
                    conf.failureHandler(this::handleProcess);
                    conf.permitAll();
                })
                .cors(conf -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.addAllowedOrigin("*");
                    cors.setAllowCredentials(true);  //允许跨域请求中携带Cookie
                    cors.addAllowedHeader("*");   //其他的也可以配置，为了方便这里就 * 了
                    cors.addAllowedMethod("*");
                    cors.addExposedHeader("*");
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", cors);  //直接针对于所有地址生效
                    conf.configurationSource(source);
                })
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(conf -> {
                    conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .exceptionHandling(conf -> {
                    //配置授权相关异常处理器
                    conf.accessDeniedHandler(this::handleProcess);
                    //配置验证相关异常的处理器
                    conf.authenticationEntryPoint(this::handleProcess);
                })
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    @Bean
//    public WebSecurityCustomizer ignoringCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/api/blogContents/getAllContents");
//    }


    //自定义成功失败处理器
    //这个跟之前一样的写法，整合到一起处理，统一返回JSON格式
    private void handleProcess(HttpServletRequest request,
                               HttpServletResponse response,
                               Object exceptionOrAuthentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if(exceptionOrAuthentication instanceof AccessDeniedException exception) {
            writer.write(JSON.toJSONString(TResponseVo.error(403, exception.getMessage())));
        } else if(exceptionOrAuthentication instanceof AuthenticationException exception) {
            writer.write(JSON.toJSONString(TResponseVo.error(401, exception.getMessage())));
        } else if(exceptionOrAuthentication instanceof Authentication authentication){
            //不过这里需要注意，在登录成功的时候需要返回我们生成的JWT令牌，这样客户端下次访问就可以携带这个令牌了，令牌过期之后就需要重新登录才可以
            writer.write(JSON.toJSONString(TResponseVo.success(JwtUtils.createJwt((User) authentication.getPrincipal()))));
        }
    }

    private void onLogoutSuccess(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Authentication authentication) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            //将Token加入黑名单
            if(JwtUtils.invalidate(token)) {
                //只有成功加入黑名单才会退出成功
                writer.write(JSON.toJSONString(TResponseVo.success("退出登录成功")));
                return;
            }
        }
        writer.write(JSON.toJSONString(TResponseVo.error(400, "退出登录失败")));
    }
}
