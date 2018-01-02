package xyz.btpink.w.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import xyz.btpink.w.vo.*;
@Repository
public class AccountDAO implements AccountMapper {
	@Autowired
	SqlSession sqlSession;
	
	
	//DB로부터 memno를 받아서 첫번째 글자를 가져온다 P or T
	@Override
	public String Login(Account account) {
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		String memno = mapper.Login(account);
		String result = "";
		if(memno != null){
			result = memno;
		}else{
			result = "no exsist id";
		}
		return result;
	}

	

}
