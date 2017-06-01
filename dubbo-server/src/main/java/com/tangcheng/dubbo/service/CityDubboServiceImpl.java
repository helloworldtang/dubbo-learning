package com.tangcheng.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.tangcheng.dubbo.constant.Version;
import com.tangcheng.dubbo.exception.NotFound;
import com.tangcheng.dubbo.vo.City;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by tang.cheng on 2017/6/1.
 */
@Service(version = Version.V_1_0_0)
public class CityDubboServiceImpl implements CityDubboService {

    @Value("${random.value:NanYang}")
    private String desc;

    @Override
    public City find(String name) {
        int nextInt = ThreadLocalRandom.current().nextInt();
        if (nextInt > 2) {
            City city = new City();
            city.setName(name);
            city.setDescription(desc);
            return city;
        }
        throw new NotFound(name);
    }
}
