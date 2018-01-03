package xyz.btpink.w;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.btpink.w.dao.AttendenceDAO;
import xyz.btpink.w.dao.ClassDAO;
import xyz.btpink.w.vo.MainParam;
import xyz.btpink.w.vo.ClassVO;

@Controller
public class InfoController {
	@Autowired
	AttendenceDAO adao;
	
	@Autowired
	ClassDAO cdao;
	
	@RequestMapping(value = "/Info", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList<MainParam> Info(@RequestBody Map<String, String> data){
		
		System.out.println("안드로이드 접속 성공");
		
		String memno = data.get("id");
		ClassVO selClass = cdao.selectClass(memno); // 멤버 넘버에 할당된 클래스 VO 가져옴
		String classno = selClass.getClassNo(); // 클래스 VO에 포함된 클래스 넘버 가져옴.
		System.out.println(classno);
		MainParam param = adao.getMainParam(classno);
//		String array[] = {param.getAttend(),param.getEarly(),param.getSick(),param.getAbsent()};
		ArrayList<MainParam> array = new ArrayList<>();
		//System.out.println(data.get("email"));
		array.add(param);
		System.out.println(array);
		
		return array;
		
	}

}
