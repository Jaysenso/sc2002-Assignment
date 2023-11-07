package source.Entity;

import source.Faculty.Faculty;

public class Staff extends User{
    public Staff() {
        super();
    }

    public Staff(String name, String userID, String password, Faculty facultyInfo) {
        super(name, userID, password, facultyInfo);
    }

    @Override
    public String toString() {
        return "Staff Name: " + getName() + " | ID: " + getUserID() + " | PW: " + getPassword() + " | Faculty: " + getFacultyInfo();
    }
}
