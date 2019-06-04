package com.xhc.capture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @Author xuhongchun
 * @Description
 * @Date 12:43 2019/6/3
 * @Param
 * @return
 * @throws
 */
@EnableScheduling
@SpringBootApplication
public class CaptureApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaptureApplication.class, args);
    }

}
