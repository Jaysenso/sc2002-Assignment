package source.Entity;

import source.Faculty.Faculty;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String name;
    private String userID;
    private String password;
    private Faculty facultyInfo;

    public User() {
        this.name = "Fangkai";
        this.userID = "FANGKAI001";
        this.password = "password";
        this.facultyInfo = null;
    }

    public User(String name, String userID, String password, Faculty facultyInfo) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.facultyInfo = facultyInfo;
    }

    public String getName() {
        return this.name;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getPassword() {
        return this.password;
    }

    public Faculty getFacultyInfo() {
        return this.facultyInfo;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFacultyInfo(Faculty facultyInfo) {
        this.facultyInfo = facultyInfo;
    }

    public void setName(String name) {
        this.name = name;
    }
}
