package xyz.btpink.w;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import xyz.btpink.w.faceAPI.Detect;
import xyz.btpink.w.vo.IdentfyVO;
import xyz.btpink.w.dao.ClassDAO;
import xyz.btpink.w.dao.AttendenceDAO;
import xyz.btpink.w.dao.StudentDAO;
import xyz.btpink.w.vo.Account;
import xyz.btpink.w.vo.Attendence;
import xyz.btpink.w.vo.ClassVO;

@Controller
public class FaceApiController {

	@Autowired
	AttendenceDAO attedenceDao;

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
	public String addFace(@RequestBody Student data) throws Exception {
		System.out.println("base64 수신 - addface");
		System.out.println(data.getName() + " " + data.getAddress() + " " + data.getBirth() + " " + data.getGender());
		Base64ToImgDecoder base64Decoder = new Base64ToImgDecoder();
		String filename = base64Decoder.decoder(data.getImage(), "addFace");
		System.out.println(filename);

		String stdno = "S" + (filename.split("\\.")[0]);

		System.out.println("stdno : " + stdno);
		Thread.sleep(3000);

		String url = "http://203.233.199.76:8989/w/resources/add_Face/" + filename;
		System.out.println(data.getName()); // 폼에서 입력받은 사용자 이름

		String personId = new Addperson().addPerson(data.getName()); // person
																		// 추가
		System.out.println("addPerson 성공!!");

		new Addface().addface(personId, url); // addFace
		new Train().train(); // Train

		sapply(data, filename, stdno, personId); // DB 등록

		String result = "";

		return result;
	}

	// 출석체크 알고리즘 실행
	@RequestMapping(value = "detectImage",produces="application/json;charset=utf8", method = RequestMethod.POST)
	public @ResponseBody String detectImage(@RequestBody Student data, Model model) throws Exception {

		System.out.println("detectImage 진입");
		String name = "";

		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("kk:mm");
		String str = dayTime.format(new Date(time));
		System.out.println(str);
		int hour = Integer.parseInt(str.split(":")[0]);
		int minite = Integer.parseInt(str.split(":")[1]);

		Base64ToImgDecoder base = new Base64ToImgDecoder();
		String fileName = base.decoder(data.getImage(), "detect");
		System.out.println(fileName);
		Thread.sleep(5000);

		String url = "{\"url\":\"http://203.233.199.76:8989/w/resources/face_detection/" + fileName+"\"}";

		Detect detect = new Detect(url);
		System.out.println("Controller 초기");
		Map<String, IdentfyVO> identfy = detect.getFaceId(fileName);
		System.out.println("ident null 인가요 ?" + identfy);
		if (identfy.size() == 0) {
			identfy = null;
			System.out.println("사람없음으로 들어옴");
		} else {
			for (String result : identfy.keySet()) {
				if (identfy.get(result).getEmotion() == null || identfy.get(result).getEmotion().equals("")) {
					identfy.get(result).setEmotion("neneutral");
				}
				attedenceDao.identfy(identfy.get(result));
			}
		}

		System.out.println("결국 최종 identfy 뭔데? : " + identfy);

		Set key = identfy.keySet();

		for (Iterator iterator = key.iterator(); iterator.hasNext();) {
			String keyName = (String) iterator.next();
			IdentfyVO valueName = (IdentfyVO) identfy.get(keyName);

			System.out.println(keyName + " = " + valueName);

			String personId = valueName.getPersonId();
			name = sdao.getName(personId);
			System.out.println(name);

		}

		System.out.println("Controller 마지막");

		
		return name;
	}

	public void sapply(Student student, String filename, String stdno, String personalid) {

		student.setStdno(stdno);
		student.setPersonalid(personalid);

		int age = ageCal(student); // 나이계산 메소드 호출
		student.setAge(age);
		student.setParentno("");// 학부모 번호를 불러오는 과정 정해질때까지 더미로...
		student.setImage(filename);

		String memno = student.getTeacherid();
		System.out.println("dao 가기전 맴버넘버 가져오냐 ?" + memno);
		ClassVO selClass = cdao.selectClass(memno);
		if (selClass == null) {
			student.setClassno("");
		} else {
			student.setClassno(selClass.getClassNo());
		}
		System.out.println("selClass 다오 갔다옴" + selClass);
		System.out.println("학생들록 값 : " + student);
		int result = sdao.insert(student);
		if (result == 1) {
			System.out.println("DB입력성공");
		}
		Attendence atd = new Attendence(student.getStdno(), "", student.getClassno(), "", "", "", "", "", "", 0.0);
		adao.insertInitAtd(atd);
	}

	public int ageCal(Student student) {

		SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/DD");
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

		int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR) + factor;
		System.out.println("나이계산결과 : " + age);

		return age;

	}

}
