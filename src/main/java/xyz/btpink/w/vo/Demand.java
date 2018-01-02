package xyz.btpink.w.vo;


public class Demand {
	String stdno;  //학생번호
	String content; //내용 
	String classno; // 학생 반번호
	String name;
	
	
	public Demand() {}


	public Demand(String stdno, String content, String classno, String name) {
		super();
		this.stdno = stdno;
		this.content = content;
		this.classno = classno;
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStdno() {
		return stdno;
	}


	public void setStdno(String stdno) {
		this.stdno = stdno;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getClassno() {
		return classno;
	}


	public void setClassno(String classno) {
		this.classno = classno;
	}


	
}
