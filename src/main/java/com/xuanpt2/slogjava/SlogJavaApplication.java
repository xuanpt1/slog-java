package com.xuanpt2.slogjava;

import com.github.jeffreyning.mybatisplus.conf.EnableMPP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMPP
public class SlogJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlogJavaApplication.class, args);
    }

}
