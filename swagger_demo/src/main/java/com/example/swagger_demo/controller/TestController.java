package com.example.swagger_demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test/type/conversion")
    public void typeConversionTest(String goodsName, float weight, int type, Boolean onSale) {
        System.out.println("goodsName:" + goodsName);
        System.out.println("weight:" + weight);
        System.out.println("type:" + type);
        System.out.println("onSale:" + onSale);
    }
}
