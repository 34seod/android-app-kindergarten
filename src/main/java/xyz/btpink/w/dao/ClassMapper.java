package xyz.btpink.w.dao;

import java.util.ArrayList;

import xyz.btpink.w.vo.Account;
import xyz.btpink.w.vo.ClassVO;

public interface ClassMapper {

	public ClassVO selectClass(String memNo);

	public ArrayList<ClassVO> allClass();

	public ClassVO duplicateNameCheck(ClassVO cla);

	public int selectNextClassNo();

	public int classInsert(ClassVO cla);

	public ArrayList<ClassVO> allClassList();

	public String mySonClass(String classno);


}


