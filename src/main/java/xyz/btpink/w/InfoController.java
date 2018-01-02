package xyz.btpink.w;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.btpink.w.dao.AttendenceDAO;
import xyz.btpink.w.vo.MainParam;

@Controller
public class InfoController {
	@Autowired
	AttendenceDAO adao;
	
	@RequestMapping(value = "Info", method=RequestMethod.GET)
	@ResponseBody
	public MainParam Info(){
		
		MainParam param = adao.getMainParam("c1");
		
		
		return param;
		
	}

}
