package com.feng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.feng.service.HelloService;

@Controller
public class HelloController {
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping("/hello")
	public String toSay(@RequestParam(required=false) String name,Model model){
		
		
		String sayHello = helloService.sayHello(name);
		
		model.addAttribute("say", sayHello);
		
		return "hello/hello";
	}

}
