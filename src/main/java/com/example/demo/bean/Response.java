package com.example.demo.bean;

import java.util.List;

public class Response {

	private String code;
	private String message;
	private List<Object> result;
	
	public Response(String code,String message) {
		this.code=code;
		this.message=message;
	}
	
	public Response(String code,String message,List<Object> objects) {
		this.code=code;
		this.message=message;
		result=objects;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Object> getResult() {
		return result;
	}

	public void setResult(List<Object> result) {
		this.result = result;
	}
}
