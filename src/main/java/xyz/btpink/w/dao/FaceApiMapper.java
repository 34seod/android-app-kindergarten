package xyz.btpink.w.dao;

import java.util.ArrayList;

import xyz.btpink.w.vo.*;

public interface FaceApiMapper {
	public int save(Demand demand);
	public int delmsg(int num);
	public Student getInfo(String id);
	public ArrayList<Demand> getmsg(String id);
}
