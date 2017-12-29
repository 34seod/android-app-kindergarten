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
public class MapController {
	
	private String lat = "37.56";
	private String lng = "126.97";
	
	//위치정보를 서버에 저장한다.
	@RequestMapping(value = "/location", method = RequestMethod.POST)
	@ResponseBody
	public String location(@RequestBody Map<String, String> data){
		lat = data.get("lat");
		lng = data.get("lng");
		
		System.out.println("POST : "+ lat+", "+ lng);
		String result = lat+", "+lng;
		return result;
	}

	//위치정보 요청 시 서버에 저장된 정보를 넘긴다.
	@RequestMapping(value = "/getLocation", method = RequestMethod.POST)
	@ResponseBody
	public String getLocation(){
		String result = lat+"@"+lng;
		System.out.println(result);
		return result;
	}
}
