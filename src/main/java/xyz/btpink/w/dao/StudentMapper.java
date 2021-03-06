package xyz.btpink.w.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.btpink.w.vo.Student;

/**
 * 게시판 관련 Mybatis 사용 메서드
 */
public interface StudentMapper {
	public int insertBoard();

	public ArrayList<Student> joinCheck(Student vo);

	public void parentUpdate(Student student);

	public int insertStudent(Student student);

	public int update(Student stu);

	public int updateA(Student stu);

	public ArrayList<String> getStdno(String classno);

	public ArrayList<Student> allStuList();

	public int changeStuHogam(Student stu);

	public void allClassnoNull();

	public void allHateNull();

	public ArrayList<Student> selectStu(String classno);
	
	public Student mySon(String stdno);
	
	public String getName(String personId);
}
