package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Response;
import com.example.demo.bean.ResponseCode;
import com.example.demo.bean.UserInfo;
import com.example.demo.dao.UserinfoRepository;


@RestController
public class UserController {

	@Autowired
	private UserinfoRepository repository;
	
	@PostMapping(path="/user/register")
	public Response register(@RequestParam(value="name",required=true) String name,
			@RequestParam(value="password",required=true) String password) {
		Response res=new Response(ResponseCode.SUCCESS, ResponseCode.MSG_USER_CREATE_SUCCESS);
		UserInfo user=repository.findByName(name);
		if(user!=null) {
			res.setCode(ResponseCode.ERROR_USER_EXIST);
			res.setMessage(ResponseCode.MSG_USER_EXIST);
			return res;
		}
		user=new UserInfo();
		user.setName(name);
		user.setAlias(name);
		user.setPassword(password);
		user.setScore((long)0);
		user.setAgainst((long)0);
		user.setChallenge((long)0);
		user.setCreateTime(System.currentTimeMillis()+"");
		user=repository.save(user);
		List<Object> result=new ArrayList<>();
		result.add(user);
		res.setResult(result);
		return res;
	}
	
	@PostMapping(path="/user/login")
	public Response login(@RequestParam(value="name",required=true) String name,
			@RequestParam(value="password",required=true) String password) {
		Response res=new Response(ResponseCode.SUCCESS, ResponseCode.MSG_USER_GET_SUCCESS);
		UserInfo user=repository.findByName(name);
		if(user!=null) {
			if(user.getPassword().equals(password)){
				List<Object> result=new ArrayList<>();
				result.add(user);
				res.setResult(result);
			}else {
				res.setCode(ResponseCode.ERROR_USER_PSW);
				res.setMessage(ResponseCode.MSG_USER_ILLEGAL);
			}
			return res;
		}
		res.setCode(ResponseCode.ERROR_USER_NOT_EXIST);
		res.setMessage(ResponseCode.MSG_USER_NOT_EXIST);
		return res;
	}
	
	@PostMapping(path="/user/getAllUser")
	public Response getAllUser(@RequestParam(value="name",required=true)String name) {
		List<UserInfo> userList=repository.findAll();
		if(userList!=null) {
			for(int i=0;i<userList.size();i++) {
				if (userList.get(i).getName().equals(name)) {
					userList.remove(i);
					break;
				}
			}
		}
		List<Object> list=new ArrayList<>();
		list.addAll(userList);
		return new Response(ResponseCode.SUCCESS, ResponseCode.MSG_USER_GET_SUCCESS, list);
	}
	
	@PostMapping(path="/user/play")
	public Response playGame(@RequestParam(value="name",required=true)String name,
			@RequestParam(value="isChallenge",required=true)boolean isChallenge) {
		UserInfo user=repository.findByName(name);
		if(isChallenge) {
			long challenge=user.getChallenge();
			user.setChallenge(challenge+1);
		}else {
			long against=user.getAgainst();
			user.setAgainst(against+1);
		}
		repository.save(user);
		return new Response(ResponseCode.SUCCESS,ResponseCode.MSG_USER_UPDATE_SUCCESS);
	}
}
