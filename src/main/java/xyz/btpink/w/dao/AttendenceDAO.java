package xyz.btpink.w.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.btpink.w.vo.Attendence;
import xyz.btpink.w.vo.MainParam;

/**
 * 게시판 관련 DAO
 */
@Repository
public class AttendenceDAO {
	@Autowired
	SqlSession sqlSession;
	
	
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
	
}
