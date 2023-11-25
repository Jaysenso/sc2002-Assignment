package source.Entity;

import source.Faculty.Faculty;

import java.util.ArrayList;

public class Staff extends User {

    /**
     * The list of camps the staff has created
     */
    private ArrayList<Camp> createdCamps;

    public Staff(
            String name,
            String userID,
            String password,
            Faculty facultyInfo) {
        super(name, userID, password, facultyInfo);
    }
}
