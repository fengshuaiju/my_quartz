package com.feng.service.impl;

import org.springframework.stereotype.Service;

import com.feng.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService{

	public String sayHello(String name) {
		String toSay = "你好啊！！！"+name;
		return toSay;
	}

}
