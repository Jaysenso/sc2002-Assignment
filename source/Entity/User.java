package source.Entity;

import source.Faculty.Faculty;

import java.io.Serializable;

/**
 * The User class is a base class for all potential parties. It is serializable and should be extended by subclasses looking
 * to use this application.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Serializable
 * @since 11/4/2023
 */
public abstract class User implements Serializable {
    /**
     * The name of the user
     */
    private String name;
    /**
     * The useID of the user
     */
    private String userID;
    /**
     * The password of the user
     */
    private String password;
    /**
     * The Faculty the user belongs to.
     *
     * @see Faculty
     */
    private Faculty facultyInfo;

    /**
     * A default constructor.
     */
    public User() {
        this.name = "Fangkai";
        this.userID = "FANGKAI001";
        this.password = "password";
        this.facultyInfo = null;
    }

    /**
     * An overloaded constructor that initialises the properties of the class
     *
     * @param name        the name of the user
     * @param userID      the ID of the user
     * @param password    the password of the user (might not be safe to put it here though)
     * @param facultyInfo the faculty the user belongs to
     * @see Faculty
     */
    public User(String name, String userID, String password, Faculty facultyInfo) {
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.facultyInfo = facultyInfo;
    }

    /**
     * A method to access the user's name
     *
     * @return name of the student
     */
    public String getName() {
        return this.name;
    }

    /**
     * A method to access the user's id
     *
     * @return the user id of the student
     */
    public String getUserID() {
        return this.userID;
    }

    /**
     * A method to access the user's password
     *
     * @return user password (not hashed!)
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * A method to access the user's faculty
     *
     * @return the faculty of the student
     */
    public Faculty getFacultyInfo() {
        return this.facultyInfo;
    }

    /**
     * A method to set the user's id
     *
     * @param userID the new user id of the student
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * A method to set the user's password
     *
     * @param password the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * A method to set the user's faculty
     *
     * @param facultyInfo the new faculty info
     */
    public void setFacultyInfo(Faculty facultyInfo) {
        this.facultyInfo = facultyInfo;
    }

    /**
     * A method to set the user's name
     *
     * @param name new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }
}
