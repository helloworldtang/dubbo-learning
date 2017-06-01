package com.tangcheng.dubbo.service;

import com.tangcheng.dubbo.vo.City;

public interface CityDubboService {
    City find(String name);
}