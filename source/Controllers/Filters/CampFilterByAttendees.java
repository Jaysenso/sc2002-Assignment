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
 * The CampFilterByAttendees class provides an implementation of filtering camps by attendees
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public class CampFilterByAttendees implements CampFilterOperation {
    /**
     * The attendee name to filter
     */
    public String name;

    /**
     * An overloaded constructor to initialize the attendee name
     *
     * @param name the attendee name
     */
    public CampFilterByAttendees(String name) {
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
            for (Student s : c.getAttendees()) {
                if (s.getName().equals(name)) {
                    filteredCamps.add(c);
                    break;
                }
            }
        }
        return filteredCamps;
    }
}
