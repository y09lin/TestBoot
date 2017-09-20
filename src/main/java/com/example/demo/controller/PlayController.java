package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Response;
import com.example.demo.dao.PlayinfoRepository;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

@RestController
public class PlayController {
	private final String MASTER_SECRET="4526f4d4687ff0c86e21d668";
	private final String APP_KEY="6f6684f73666c3f463d00a67";

	@Autowired
	private PlayinfoRepository playRepository;
	
	@PostMapping(path="/play/save")
	public Response savePlay(@RequestParam(value="nameA",required=true)String nameA,
			@RequestParam(value="nameB",required=true)String nameB,
			@RequestParam(value="roundCount",required=true)String roundCount,
			@RequestParam(value="roundDetail",required=true)String roundDetail) {
		return null;
	}
	
	@GetMapping(path="/push/test")
	public String testPush() {
		return pushTest();
	}
	
	private String pushTest() {
		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
		PushPayload payload=PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder()
                		.setTitle("this is push title")
                		.setMsgContent("pushTest")
                		.addExtra("player", "A")
                		.addExtra("action", "cheat")
                		.build())
                .build();
		try {
			PushResult result=jpushClient.sendPush(payload);
			System.out.println(result);
			return result.toString();
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}
		return "test JPush error";
	}
}
