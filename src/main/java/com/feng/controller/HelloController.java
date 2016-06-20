package com.feng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feng.service.HelloService;

@RestController
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/hello")
	public List<String> toSay(){
		
		List<String> list = new ArrayList<String>();
		list.add("StringName");
		list.add("StringNamePackage");
		list.add("String_name");
		list.add("String4_name_package");
		
		return list;
	}

}
