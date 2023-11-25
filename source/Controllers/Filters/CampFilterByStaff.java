package source.Controllers.Filters;

import source.Database.App;
import source.Entity.Camp;
import source.Entity.Staff;
import source.Entity.Student;
import source.Faculty.Faculty;
import source.Faculty.NTU;
import source.Utility.PrettyPage;

import java.util.ArrayList;

public class CampFilterByStaff implements CampFilterOperation {

    @Override
    public ArrayList<Camp> filter(ArrayList<Camp> camps) {
        if (!(App.getUser() instanceof Staff)) {
            PrettyPage.printError("The current logged in user is not a Staff!");
        }
        //Access the student context from here, leads to bad coupling though
        Staff s = (Staff) App.getUser();
        ArrayList<Camp> filteredCamps = new ArrayList<>();
        //Then look through those camps and find if this student exists
        for (Camp c : camps) {
            String userID= c.getCampInfo().getStaffInCharge();
            if (userID.equals(App.getUser().getName())) {
                filteredCamps.add(c);
            }
        }
        return filteredCamps;
    }
}
