package source.Controllers.Filters;

import source.Database.App;
import source.Entity.Camp;
import source.Entity.Staff;
import source.Entity.Student;
import source.Faculty.Faculty;
import source.Faculty.NTU;
import source.Utility.PrettyPage;

import java.util.ArrayList;

/**
 * The CampFilterByCreated class provides an implementation of filtering the camps of the person who created it
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public class CampFilterByCreated implements CampFilterOperation {

    /**
     * The name of whom owns the camps
     */
    public String name;

    /**
     * An overloaded constructor to initialize the attendee name
     *
     * @param name the attendee name
     */
    public CampFilterByCreated(String name) {
        this.name = name;
    }

    /**
     * A generic filter function
     *
     * @param camps list of camps
     */
    @Override
    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        ArrayList<Camp> filteredCamps = new ArrayList<>();
        //Then look through those camps and find if this student exists
        for (Camp c : camps) {
            String userID = c.getCampInfo().getStaffInCharge();
            if (userID.equals(name)) {
                filteredCamps.add(c);
            }
        }
        return filteredCamps;
    }
}
