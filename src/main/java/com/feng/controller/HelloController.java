package com.feng.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feng.service.HelloService;
import com.feng.util.Job;

@RestController
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/hello")
	public List<Job> toSay(){
		
		List<Job> list = new ArrayList<Job>();

		for (int i = 0; i < 3; i++) {
			Job jsb = new Job("smileName"+i, "goBus"+i);
			list.add(jsb);
		}
		
		return list;
	}

}
