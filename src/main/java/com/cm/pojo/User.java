package com.cm.pojo;

public class User {
	private int id;
	private String name;
	private String password;
	private String salt;
	private String phone;
	private String question;
	private String result;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//在显示评价者的时候进行匿名显示
    public String getAnonymousName() {
    	if(null==name)
            return null;
 
        if(name.length()<=1)
            return "*";
 
        if(name.length()==2)
            return name.substring(0,1) +"*";
 
        char[] cs =name.toCharArray();
        for (int i = 1; i < cs.length-1; i++) {
            cs[i]='*';
        }
        return new String(cs);
    }
}
