package xyz.btpink.w.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.btpink.w.vo.*;

@Repository
public class MessageDAO {
	@Autowired
	SqlSession sqlSession;
	
	
	public String save(Demand demand) {
		MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
		int memno = mapper.save(demand);
		String result = "";
		
		
		
		return result;
	}
	public Student getInfo(String id) {
		MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
		Student memno = mapper.getInfo(id);
		return memno;
	}
	public String getmsg(String id) {
		MessageMapper mapper = sqlSession.getMapper(MessageMapper.class);
		String memno = mapper.getmsg(id);
		return "";
	}
}
