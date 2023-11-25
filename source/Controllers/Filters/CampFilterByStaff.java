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
 * This class provides implementation of filtering the camps by staff, allowing staff to see particular camps.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Staff
 * @since 11/22/2023
 */
public class CampFilterByStaff implements CampFilterOperation {
    /**
     * A generic filter function
     *
     * @param camps list of camps
     */
    @Override
    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        if (!(App.getUser() instanceof Staff)) {
            PrettyPage.printError("The current logged in user is not a Staff!");
        }
        //Access the student context from here, leads to bad coupling though
        Student s = (Student) App.getUser();
        Faculty f = s.getFacultyInfo();
        Faculty ntu = new NTU();
        ArrayList<Camp> filteredCamps = new ArrayList<>();
        //Then look through those camps and find if this student exists
        for (Camp c : camps) {
            Faculty campFaculty = c.getCampInfo().getFaculty();
            //Else check the faculty of this camp
            if (campFaculty.getClass() == ntu.getClass() || campFaculty.getClass() == f.getClass()) {
                //If its either ntu or equals to the staff, add it to the list
                filteredCamps.add(c);
            }
        }
        return filteredCamps;
    }
}
