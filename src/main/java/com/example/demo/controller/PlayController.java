package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.PlayInfo;
import com.example.demo.bean.Response;
import com.example.demo.bean.ResponseCode;
import com.example.demo.dao.PlayinfoRepository;
import com.example.demo.utils.JPushUtils;

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
	

	@Autowired
	private PlayinfoRepository playRepository;
	
	@PostMapping(path="/play/start")
	public Response playStart(@RequestParam(value="challenge",required=true)String challenge,
			@RequestParam(value="against",required=true)String against,
			@RequestParam(value="isChallenge",required=true) boolean isChallenge) {
		PlayInfo play=new PlayInfo(challenge,against);
		play=playRepository.save(play);
		if(isChallenge) {
			JPushUtils.pushPlayinfo2Alias(against, JPushUtils.Type_Play, JPushUtils.SType_Start, play);
		}else {
			JPushUtils.pushPlayinfo2Alias(challenge, JPushUtils.Type_Play, JPushUtils.SType_Start, play);
		}
		return generateResponse(ResponseCode.SUCCESS, ResponseCode.MSG_PLAY_START_SUCCESS, play);
	}
		
	@PostMapping(path="/play/action")
	public Response playAction(@RequestParam(value="playId",required=true) String playId,
			@RequestParam(value="name", required=true) String name,
			@RequestParam(value="action",required=true) String action) {
		long id=Long.parseLong(playId);
		PlayInfo play=playRepository.findOne(id);
		boolean isChallenge=play.getNameA().equals(name);
		int count=play.getRoundCount();
		String playDetail=play.getRoundDetail();
		if(2*count==playDetail.length()) {
			count++;
			playDetail+=action;
		} else {
			if (isChallenge) {
				playDetail=playDetail.substring(0, 2*(count-1))+action+playDetail.charAt(playDetail.length()-1);
			}else {
				playDetail+=action;
			}
		}
		play.setRoundCount(count);
		play.setRoundDetail(playDetail);
		play=playRepository.save(play);
		if(isChallenge) {
			JPushUtils.pushPlayinfo2Alias(play.getNameB(), JPushUtils.Type_Play, JPushUtils.SType_Action, play);
		}else {
			JPushUtils.pushPlayinfo2Alias(play.getNameA(), JPushUtils.Type_Play, JPushUtils.SType_Action, play);
		}
		return generateResponse(ResponseCode.SUCCESS, action, play);
	}
	
	private Response generateResponse(String code,String msg,Object o) {
		List<Object> list=new ArrayList<>();
		list.add(o);
		return new Response(code, msg, list);
	}
	
	@GetMapping(path="/push/test")
	public String testPush() {
		return pushTest();
	}
	
	private String pushTest() {
		JPushClient jpushClient = new JPushClient(JPushUtils.MASTER_SECRET, 
				JPushUtils.APP_KEY, null, ClientConfig.getInstance());
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
