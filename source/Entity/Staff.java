package source.Entity;

import source.Faculty.Faculty;

public class Staff extends User {
    public Staff(String name, String userID, String password, Faculty facultyInfo) {
        super(name, userID, password, facultyInfo);
    }
}
