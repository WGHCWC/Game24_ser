package bean;

import java.util.Map;

public class UserInfo {
	 private String userName;
	    private String userPassword;
	    private int score;
	    private int id;
	    private int code;

	    public int getCode() {
	        return code;
	   
	    }

	    public void setCode(int code) {
	        this.code = code;
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getUserPassword() {
	        return userPassword;
	    }

	    public void setUserPassword(String userPassword) {
	        this.userPassword = userPassword;
	    }

	    public int getScore() {
	        return score;
	    }

	    public void setScore(int score) {
	        this.score = score;
	    }

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    @Override
	    public String toString() {
	        return "UserInfoBean{" +
	                "userName='" + userName + '\'' +
	                ", userPassword='" + userPassword + '\'' +
	                ", score=" + score +
	                ", id=" + id +
	                '}';
	    }
	}

