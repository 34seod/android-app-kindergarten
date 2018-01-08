package xyz.btpink.w.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.btpink.w.vo.Attendence;
import xyz.btpink.w.vo.MainParam;
import xyz.btpink.w.vo.IdentfyVO;
import xyz.btpink.w.dao.AttendenceMapper;

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
	
}
