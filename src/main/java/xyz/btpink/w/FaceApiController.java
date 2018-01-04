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
import xyz.btpink.w.vo.Student;

@Controller
public class FaceApiController {

	@Autowired
	FaceApiDAO faceApiDAO;
	
	@RequestMapping(value = "/addface", method = RequestMethod.POST)
	@ResponseBody
	public String addFace(@RequestBody Student data) throws Exception{
		System.out.println("base64 수신 - addface");
		System.out.println(data);
		Base64ToImgDecoder base64Decoder = new Base64ToImgDecoder();
		String filename = base64Decoder.decoder(data.getImage(), "addFace");
		System.out.println(filename);
		Thread.sleep(3000);
		
		String url = "http://203.233.199.76:8989/w/resources/add_Face/"+filename;
		
		
		new Addperson().addPerson();
		
		String result = "";
		
		return result;
	}
	
	@RequestMapping(value = "/detect", method = RequestMethod.POST)
	@ResponseBody
	public String detect(@RequestBody Student data) throws Exception{
		System.out.println("base64 수신 - detect");
		Base64ToImgDecoder base64Decoder = new Base64ToImgDecoder();
		String filename = base64Decoder.decoder(data.getImage(), "detect");
		System.out.println(filename);
		Thread.sleep(3000);
		
		String url = "http://203.233.199.76:8989/w/resources/face_detection/"+filename;
		
		String result = "";
		return result;
	}

}
