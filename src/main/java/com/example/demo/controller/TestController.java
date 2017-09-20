package com.example.demo.controller;

import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping(path="/user")
	public String getByAccount(@RequestParam(value="account",required=false,defaultValue="阿弥陀佛") String account) {
		return "hello "+account;
	}
	
	@GetMapping(path="/GetPwdErrorVerifyNum")
	public String getImgByAccount(@RequestParam(value="account",required=false) String account) {
		String str="<ReturnStr><ErrorCode>1</ErrorCode><Msg>获取成功</Msg>"
				+ "<VCodeImageUrl>http://img6.3lian.com/c23/desk4/06/06/d/03.jpg</VCodeImageUrl>"
				+ "<VCode>12321</VCode>"
				+ "</ReturnStr>";
		return str.toString();
	}
	
	@PostMapping(path="/getimg")
	public String getImg(@RequestParam(value="account") String account) {
		System.out.println("account="+account);
		return "http://img6.3lian.com/c23/desk4/06/06/d/03.jpg";
	}
	
	@PostMapping(path="/setfile")
	public String setFile(@RequestParam(value="fileStream") String stream) {
		System.out.println("stream="+stream);
		base2File(stream);
		return "http://img6.3lian.com/c23/desk4/06/06/d/03.jpg";
	}
	
	private void base2File(String base64) {
		String path="D:\\test.txt";
		byte[] buffer =base64.getBytes();
        FileOutputStream out;
        try {
            out = new FileOutputStream(path);
            out.write(buffer);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
