package source;
public abstract class User {
    private String userID;
    private String password;
    private Faculty facultyInfo;

    public User()
    {
        this.userID = "FANGKAI001";
        this.password = "password";
        this.facultyInfo = null;
    }

    public User(String userID, String password, Faculty facultyInfo)
    {
        this.userID = userID;
        this.password = password;
        this.facultyInfo = facultyInfo;
    }
}
