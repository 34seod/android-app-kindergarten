package xyz.btpink.w.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.btpink.w.vo.Attendence;
import xyz.btpink.w.vo.MainParam;
import xyz.btpink.w.dao.StudentDAO;
import xyz.btpink.w.vo.IdentfyVO;
import xyz.btpink.w.dao.AttendenceMapper;

/**
 * 게시판 관련 DAO
 */
@Repository
public class AttendenceDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	StudentDAO sdao;
	
	
	public MainParam getMainParam(String classno){
		
		AttendenceMapper mapper = sqlSession.getMapper(AttendenceMapper.class);
		MainParam result = mapper.getMainParam(classno);
		return result;
		
	}
	
	public MainParam getMainParama(){
		AttendenceMapper mapper = sqlSession.getMapper(AttendenceMapper.class);
		MainParam result = mapper.getMainParama();
		return result;
	}

	public ArrayList<Attendence> getEmotion(String id){
		AttendenceMapper mapper = sqlSession.getMapper(AttendenceMapper.class);
		ArrayList<Attendence> result = mapper.getEmotion(id);
		return result;
	}
	
	public int insertInitAtd(Attendence atd){
		AttendenceMapper mapper = sqlSession.getMapper(AttendenceMapper.class);
		return mapper.insertInitAtd(atd);
	}
	
	public int identfy(IdentfyVO identfy) {
		// TODO Auto-generated method stub
		AttendenceMapper mapper = sqlSession.getMapper(AttendenceMapper.class);

		return mapper.identfy(identfy);
	}
	public int late(IdentfyVO identfyVO) {
		// TODO Auto-generated method stub
		AttendenceMapper mapper = sqlSession.getMapper(AttendenceMapper.class);
		return mapper.late(identfyVO);
	}
	
	public int initAtd(String classno) {
		AttendenceMapper mapper = sqlSession.getMapper(AttendenceMapper.class);
		ArrayList<Attendence> result = new ArrayList<>();
		result = mapper.checkAtd(classno); // 오늘 생성된 해당 반의 출석부 목록이 있는지 확인
		System.out.println("출석부 목록 체크 결과 : " + result);

		int iresult = 0;

		if (result.size() == 0) {
			System.out.println("if문 진입");
			ArrayList<String> sresult = sdao.getStdno(classno);
			System.out.println("학생번호 리스트 출력 : " + sresult);

			for (int i = 0; i < sresult.size(); i++) {
				System.out.println("for문 진입");
				String stdno = sresult.get(i);
				System.out.println(stdno);
				Attendence atd = new Attendence(stdno, "", classno, "", "", "", "", "", "",0.0);
				System.out.println("atd 객체 확인 : " + atd);
				iresult = mapper.insertInitAtd(atd); // 입력 작업
			}
		}

		return iresult;

	}
	
}
