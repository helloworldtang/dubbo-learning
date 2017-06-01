package com.tangcheng.dubbo;

import com.tangcheng.dubbo.client.service.AbcService;
import com.tangcheng.dubbo.vo.City;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DubboClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DubboClientApplication.class, args);
        AbcService bean = run.getBean(AbcService.class);
        for (int i = 0; i < 20; i++) {
            try {
                City city = bean.cityDubboService.find("city" + i);
                System.out.println(city);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
