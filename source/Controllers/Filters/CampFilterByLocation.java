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
 * The CampFilterByLocation class provides an implementation of filtering camps by location
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public class CampFilterByLocation implements CampFilterOperation {

    /**
     * The location to filter by
     */
    public String location;

    /**
     * An overloaded constructor to initialize the location
     *
     * @param location the location
     */
    public CampFilterByLocation(String location) {
        this.location = location;
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
            String campLoc = c.getCampInfo().getLocation();
            if (campLoc.equals(location)) {
                filteredCamps.add(c);
            }
        }
        return filteredCamps;
    }
}
