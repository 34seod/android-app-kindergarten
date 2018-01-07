package xyz.btpink.w;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.btpink.w.dao.FaceApiDAO;
import xyz.btpink.w.faceAPI.Addface;
import xyz.btpink.w.faceAPI.Addperson;
import xyz.btpink.w.faceAPI.Train;
import xyz.btpink.w.util.Base64ToImgDecoder;
import xyz.btpink.w.vo.Student;
import xyz.btpink.w.dao.ClassDAO;
import xyz.btpink.w.dao.AttendenceDAO;
import xyz.btpink.w.dao.StudentDAO;
import xyz.btpink.w.vo.Account;
import xyz.btpink.w.vo.Attendence;
import xyz.btpink.w.vo.ClassVO;

@Controller
public class FaceApiController {

	@Autowired
	FaceApiDAO faceApiDAO;
	
	@Autowired
	StudentDAO sdao;
	
	@Autowired
	ClassDAO cdao;

	@Autowired
	AttendenceDAO adao;
	
	@RequestMapping(value = "/addface", method = RequestMethod.POST)
	@ResponseBody
	public String addFace(@RequestBody Student data) throws Exception{
		System.out.println("base64 수신 - addface");
		//System.out.println(data);
		Base64ToImgDecoder base64Decoder = new Base64ToImgDecoder();
		String filename = base64Decoder.decoder(data.getImage(), "addFace");
		System.out.println(filename);
		Thread.sleep(3000);
		
		String url = "http://203.233.199.74:8999/w/resources/add_Face/"+filename;
		System.out.println(data.getName());
		
		String personId = new Addperson().addPerson(data.getName());
		System.out.println("addPerson 성공!!");
		
		new Addface().addface(personId, url);
		new Train().train(); //트레이닝
		
		//sapply(data, filename);
		
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
	
//	public void sapply(Student student, String filename){
//		int age = ageCal(student); // 나이계산 메소드 호출
//		student.setAge(age);
//		//String filename = file.getOriginalFilename();
//		student.setParentno("");// 학부모 번호를 불러오는 과정 정해질때까지 더미로...
//		student.setImage(filename);
//
//		Account loginuser = (Account) session.getAttribute("User");
//		System.out.println(loginuser);
//		String memno = loginuser.getMemNo();
//		System.out.println("dao 가기전 맴버넘버 가져오냐 ?" + memno);
//		ClassVO selClass = cdao.selectClass(memno);
//		if (selClass == null) {
//			student.setClassno("");
//		} else {
//			student.setClassno(selClass.getClassNo());
//		}
//		System.out.println("selClass 다오 갔다옴" + selClass);
//		System.out.println("학생들록 값 : " + student);
//		int result = sdao.insert(student);
//		if (result == 1) {
//			System.out.println("DB입력성공");
//		}
//		Attendence atd = new Attendence(student.getStdno(), "", student.getClassno(), "", "", "", "", "", "", 0.0);
//		adao.insertInitAtd(atd);
//	}
	
	public int ageCal(Student student) {

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		// Date currenttime = new Date();
		Date birthday = null;
		try {
			birthday = formatter.parse(student.getBirth());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		Calendar birth = new GregorianCalendar();
		Calendar today = new GregorianCalendar();

		birth.setTime(birthday);
		today.setTime(new Date());

		int factor = 0;

		// if (today.get(Calendar.DAY_OF_YEAR) <
		// birth.get(Calendar.DAY_OF_YEAR)) {
		//
		// factor = -1;
		//
		// }

		int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + factor;
		System.out.println("나이계산결과 : " + age);

		// SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		// Date currenttime = new Date();
		// Date birthday = null;
		// try {
		// birthday = formatter.parse(student.getBirth());
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// // e.printStackTrace();
		// }
		// // System.out.println("현재시간" + currenttime);
		// // System.out.println("생일" + birthday);
		//
		// long diff = currenttime.getTime() - birthday.getTime();
		// long diffdays = diff / (24 * 60 * 60 * 1000);
		// int age = (int) diffdays / 365;
		// System.out.println("나이 : " + age);


		return age;

	}

}
