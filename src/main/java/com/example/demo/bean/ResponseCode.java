package com.example.demo.bean;

public class ResponseCode {
	public static final String SUCCESS="s_0";
	
	public static final String ERROR_USER_EXIST="e_u_1";
	public static final String ERROR_USER_NOT_EXIST="e_u_2";
	public static final String ERROR_USER_PSW="e_u_3";
	
	public static final String MSG_USER_CREATE_SUCCESS="用户申请成功";
	public static final String MSG_USER_GET_SUCCESS="获取用户成功";
	public static final String MSG_USER_EXIST="该用户名已经被申请了";
	public static final String MSG_USER_NOT_EXIST="该用户名还未注册";
	public static final String MSG_USER_ILLEGAL="该用户名或密码错误";
	
	//
	public static final String ERROR_PLAY_SAVE="e_p_1";
	
	public static final String MSG_PLAY_START_SUCCESS="成功进入游戏";
	public static final String MSG_PLAY_SAVE_ERROR="这一局已经保存失败";
}
