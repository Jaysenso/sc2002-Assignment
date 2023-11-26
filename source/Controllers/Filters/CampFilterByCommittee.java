package source.Controllers.Filters;

import source.Entity.Camp;
import source.Entity.Student;

import java.util.ArrayList;

/**
 * The CampFilterByCommittee class provides an implementation of filtering camps by committee
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public class CampFilterByCommittee implements CampFilterOperation {
    /**
     * The camp committee name to filter
     */
    public String name;

    /**
     * An overloaded constructor to initialize the attendee name
     *
     * @param name the camp committee name
     */
    public CampFilterByCommittee(String name) {
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
            for (Student s : c.getCampCommitteeMembers()) {
                if (s.getName().equals(name)) {
                    filteredCamps.add(c);
                    break;
                }
            }
        }
        return filteredCamps;
    }
}
