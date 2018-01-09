package xyz.btpink.w.dao;

import java.util.ArrayList;

import xyz.btpink.w.vo.Account;
import xyz.btpink.w.vo.Attendence;
import xyz.btpink.w.vo.MainParam;
import xyz.btpink.w.vo.IdentfyVO;

public interface AttendenceMapper {
	public MainParam getMainParam(String classno);

	public MainParam getMainParama();

	public ArrayList<Attendence> getEmotion(String id);
	
	public int insertInitAtd(Attendence atd);
	
	public int identfy(IdentfyVO identfy);
	
	public int late(IdentfyVO identfyVO);
	
	public ArrayList<Attendence> checkAtd(String classno);

}
