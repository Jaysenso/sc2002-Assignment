package source.Controllers.Filters;

import source.Database.App;
import source.Entity.Camp;
import source.Entity.Student;
import source.Faculty.Faculty;
import source.Faculty.NTU;
import source.Utility.PrettyPage;

import java.util.ArrayList;

/**
 * The SuggestionDeserializer class deserializes a hashmap of parsed data a list of Student objects to be used.
 * NOTE: Requires a file read to go through the Parser!
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Student
 * @since 11/22/2023
 */
public class CampFilterByStudent implements CampFilterOperation {
    /**
     * A generic filter function
     *
     * @param camps list of camps
     */
    @Override
    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        if (!(App.getUser() instanceof Student)) {
            PrettyPage.printError("The current logged in user is not a Student!");
        }
        //Access the student context from here, leads to bad coupling though
        Student s = (Student) App.getUser();
        Faculty f = s.getFacultyInfo();
        Faculty ntu = new NTU();
        ArrayList<Camp> filteredCamps = new ArrayList<>();
        //Then look through those camps and find if this student exists
        for (Camp c : camps) {
            Faculty campFaculty = c.getCampInfo().getFaculty();
            //if the camp isn't visible continue as we are a student
            if (!c.getVisibility())
                continue;
            //Else check the faculty of this camp
            if (campFaculty.getClass() == ntu.getClass() || campFaculty.getClass() == f.getClass()) {
                //If its either ntu or equals to the student, add it to the list
                filteredCamps.add(c);
            }
        }
        return filteredCamps;
    }
}
