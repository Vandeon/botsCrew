package com.firstBot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@Value("${lgs.token.access}")
	String accessToken;
	
	@PostMapping
	public void getMessage(@RequestParam(value="access_token") String access) {
		if(access.equals(accessToken)) {
			System.out.println("get");
		}else {
			System.out.println("Dont get");
		}
		System.out.println("Cutched");
	}

}
