package xyz.btpink.w;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.btpink.w.dao.FaceApiDAO;
import xyz.btpink.w.faceAPI.Addperson;
import xyz.btpink.w.util.Base64ToImgDecoder;

@Controller
public class FaceApiController {

	@Autowired
	FaceApiDAO faceApiDAO;
	
	//로그인 검증하고 계정을 판별하여 반환함.
	@RequestMapping(value = "/saveImage", method = RequestMethod.POST)
	@ResponseBody
	public String saveImage(@RequestBody Map<String, String> data) throws Exception{
		System.out.println("base64 수신");
		Base64ToImgDecoder base64Decoder = new Base64ToImgDecoder();
		String filename = base64Decoder.decoder(data.get("img"), "addFace");
		System.out.println(filename);
		Thread.sleep(3000);
		
		String url = "http://203.233.199.76:8989/w/resources/add_Face/"+filename;
		
		
		new Addperson().addPerson();
		
		String result = "";
		
		return result;
	}
	 
	
}
