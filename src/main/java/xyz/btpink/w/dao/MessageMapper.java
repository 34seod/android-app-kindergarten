package xyz.btpink.w.dao;

import java.util.ArrayList;

import xyz.btpink.w.vo.*;

public interface MessageMapper {
	public int save(Demand demand);
	public Student getInfo(String id);
	public String getmsg(String id);
}
