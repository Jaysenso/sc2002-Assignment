package source.Entity;

import source.Faculty.Faculty;

public class Student extends User {
    public Student() {
        super();
    }

    public Student(String name, String userID, String password, Faculty facultyInfo) {
        super(name, userID, password, facultyInfo);
    }

    @Override
    public String toString() {
        return "Student Name: " + getName() + " | ID: " + getUserID() + " | PW: " + getPassword() + " | Faculty: " + getFacultyInfo();
    }
}
