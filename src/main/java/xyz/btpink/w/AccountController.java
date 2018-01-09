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
import xyz.btpink.w.vo.ClassVO;
import xyz.btpink.w.dao.AttendenceDAO;
import xyz.btpink.w.dao.ClassDAO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AccountController {
	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	ClassDAO cdao;
	
	@Autowired
	AttendenceDAO adao;
	
	//로그인 검증하고 계정을 판별하여 반환함.
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody Account data){
		
		System.out.println("POST : "+ data.getId()+", "+data.getPw());
		//해당 id가 부모인지 선생인지 확인해서 P or T 를 앱에 전송한다.
		String result = accountDAO.Login(data);
		if(result.substring(0,1).equals("T")){
			//그날의 출석정보가 없으면 기본값을 입력하도록하는 SQL 
			
			System.out.println("초기확인작업 시작");

			String memno = result; // 멤버 넘버 가져옴
			ClassVO selClass = cdao.selectClass(memno); // 멤버 넘버에 할당된 클래스 VO 가져옴
			String classno = selClass.getClassNo(); // 클래스 VO에 포함된 클래스 넘버 가져옴.

			
				System.out.println("클래스 넘버 : " + classno);

				adao.initAtd(classno); // 출석부 표시전 초기 확인작업

				System.out.println("초기작업 종료");
			
			
		}
		return result;
	}
}
