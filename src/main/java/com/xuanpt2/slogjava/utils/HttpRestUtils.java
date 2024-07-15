package com.xuanpt2.slogjava.utils;

import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class HttpRestUtils {
    public static String get(String url, MultiValueMap<String, String> params) throws IOException{
        return httpRestClient(url, HttpMethod.GET, params);
    }

    private static String httpRestClient(String url, HttpMethod method, MultiValueMap<String, String> params) throws IOException{
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10*1000);
        requestFactory.setReadTimeout(10*1000);
        RestTemplate client = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();

        //headers.setContentType(MediaType.APPLICATION_ATOM_XML);
        //不指定格式，直接获取 格式各站不定

        HttpEntity<MultiValueMap<String, String >> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);
        ResponseEntity<String> response = null;

        try {
            response = client.exchange(url, method, requestEntity, String.class);
            return response.getBody();
        }catch (HttpClientErrorException e){
            System.out.println( "------------- 出现异常 HttpClientErrorException -------------");
            System.out.println(e.getMessage());
            System.out.println(e.getStatusText());
            System.out.println( "-------------responseBody-------------");
            System.out.println( e.getResponseBodyAsString());
            e.printStackTrace();
            return "";
        }catch (Exception e){
            System.out.println( "------------- HttpRestUtils.httpRestClient() 出现异常 Exception -------------");
            System.out.println(e.getMessage());
            return "";
        }
    }
}
