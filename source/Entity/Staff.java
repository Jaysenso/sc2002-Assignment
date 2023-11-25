package source.Entity;

import source.Faculty.Faculty;

import java.util.ArrayList;

/**
 * The Staff class holds all information
 * about its created camps and other key information
 *
 * @author Isaac Chun
 * @version 1.4
 * @since 11/23/2023
 */
public class Staff extends User {

    /**
     * The list of camps the staff has created
     */
    private final ArrayList<Camp> createdCamps;

    /**
     * An overloaded constructor
     */
    public Staff(
            String name,
            String userID,
            String password,
            Faculty facultyInfo) {
        super(name, userID, password, facultyInfo);

        createdCamps = new ArrayList<>();
    }

    /**
     * Acquires all the camps the staff has created
     *
     * @return the created camp list
     */
    public ArrayList<Camp> getCreatedCamps() {
        return this.createdCamps;
    }

    /**
     * Adds a camp to his created camps
     *
     * @param camp the camp to add
     */
    public void addCreatedCamp(Camp camp) {
        //Prevent duplicates
        for (Camp c : createdCamps) {
            if (c.equals(camp))
                return;
        }
        this.createdCamps.add(camp);
    }
}
