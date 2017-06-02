package com.tangcheng.dubbo.client.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tangcheng.dubbo.constant.Version;
import com.tangcheng.dubbo.service.CityDubboService;
import org.springframework.stereotype.Component;

@Component
public class AbcService {
    @Reference(version = Version.V_1_0)
    public CityDubboService cityDubboService;
}