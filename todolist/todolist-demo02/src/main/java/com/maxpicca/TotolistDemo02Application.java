package com.maxpicca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling   // 开启定时功能  任务调度
public class TotolistDemo02Application {

    public static void main(String[] args) {
        SpringApplication.run(TotolistDemo02Application.class, args);
    }

}
