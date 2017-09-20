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
	
	@PostMapping(path="/user/createUser")
	public Response saveUser(@RequestParam(value="name",required=true) String name,
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
		user.setPassword(password);
		user.setScot(0);
		user.setCreateTime(System.currentTimeMillis()+"");
		repository.save(user);
		return res;
	}
	
	@PostMapping(path="/user/getUser")
	public Response getUser(@RequestParam(value="name",required=true) String name,
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
}
