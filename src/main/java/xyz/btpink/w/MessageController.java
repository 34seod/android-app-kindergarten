package xyz.btpink.w;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.btpink.w.dao.*;
import xyz.btpink.w.vo.*;

@Controller
public class MessageController {
	@Autowired
	MessageDAO messageDAO;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestBody Map<String, String> data){
		String id = data.get("id");
		String msg = data.get("msg");
		
		System.out.println(msg+"@"+id);
		//아들 학생번호 + 반번호
		Student info = messageDAO.getInfo(id);
		System.out.println(info);
		
		
		String result = messageDAO.save(new Demand(info.getStdno(),msg,info.getClassno(),info.getName()));
		return "asdf";
	}
	@RequestMapping(value = "/getmsg", method = RequestMethod.POST)
	@ResponseBody
	public String getmsg(@RequestBody Map<String, String> data){
		String id = data.get("id");
		
		System.out.println(id);
		
		String result = messageDAO.getmsg(id);
		return "asdf";
	}
}
