package xyz.btpink.w;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.btpink.w.dao.*;
import xyz.btpink.w.vo.*;

@Controller
public class CalendarController {
	@Autowired
	AttendenceDAO attendenceDAO;
	
	@RequestMapping(value = "/getEmotion", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<Attendence> save(@RequestBody Map<String, String> data){
		String id = data.get("id");
		
		System.out.println("calender : "+id);
		
		//1. 부모 id로 학생 id를 찾는다.
		//2. 학생 id로 출석정보를 찾는다.
		ArrayList<Attendence> result = attendenceDAO.getEmotion(id);
		
		System.out.println(result.get(0).getToday());
		return result;
	}
	
	
	
}
