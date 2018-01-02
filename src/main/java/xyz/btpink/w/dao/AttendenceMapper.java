package xyz.btpink.w.dao;

import java.util.ArrayList;

import xyz.btpink.w.vo.Account;
import xyz.btpink.w.vo.Attendence;
import xyz.btpink.w.vo.MainParam;

public interface AttendenceMapper {
	public MainParam getMainParam(String classno);

	public MainParam getMainParama();


}
