package com.example.demo.utils;

import com.example.demo.bean.PlayInfo;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;

public class JPushUtils {
	public static final String MASTER_SECRET="4526f4d4687ff0c86e21d668";
	public static final String APP_KEY="6f6684f73666c3f463d00a67";
	
	public static final String Type_Play="Type_Play";
	public static final String SType_Start="SType_Start";
	public static final String SType_Action="SType_Action";
	
	private static final String P_Round="P_Round";
	private static final String P_NameA="P_NameA";
	private static final String P_NameB="P_NameB";
	private static final String P_Action="P_Action";
	
	public static void pushPlayinfo2Alias(String alias,String type,String subType,PlayInfo play) {
		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
		PushPayload payload=PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder()
                		.setTitle(type)
                		.setMsgContent(subType)
                		.addExtra(P_NameA,play.getNameA())
                		.addExtra(P_NameB,play.getNameB())
                		.addExtra(P_Round,play.getRoundCount()+"")
                		.addExtra(P_Action,play.getRoundDetail())
                		.build())
                .build();
		try {
			jpushClient.sendPushValidate(payload);
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}
	} 
}
