package xyz.btpink.w;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.btpink.w.dao.*;
import xyz.btpink.w.vo.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AccountController {
	@Autowired
	AccountDAO accountDAO;
	
	//로그인 검증하고 계정을 판별하여 반환함.
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody Account data){
		
		System.out.println("POST : "+ data.getId()+", "+data.getPw());
		//해당 id가 부모인지 선생인지 확인해서 P or T 를 앱에 전송한다.
		String result = accountDAO.Login(data);
		return result;
	}
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	@ResponseBody
//	public String login(@RequestBody Map<String, String> data){
//		
//		System.out.println("POST : "+ data.get("id")+", "+data.get("pw"));
//		//해당 id가 부모인지 선생인지 확인해서 P or T 를 앱에 전송한다.
//		String result = accountDAO.Login(new Account(data.get("id"), data.get("pw")));
//		return result;
//	}
	
}
