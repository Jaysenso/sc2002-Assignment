package source.Controllers;

import source.Database.App;
import source.Entity.Camp;
import source.Entity.Staff;
import source.Entity.Student;
import source.Entity.User;
import source.Faculty.SCSE;
import source.Utility.PrettyPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class FilterManager {

    private CampManager campManager = App.getCampManager();
    public void ascendAlphabetical(ArrayList<Camp> campList){
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getName()));
    }
    public void descendAlphabetical(ArrayList<Camp> campList){
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getName()));
        Collections.reverse(campList);
    }

    public void ascendAttendee(ArrayList<Camp> campList){
        campList.sort(Comparator.comparingInt(camp -> camp.getCampInfo().getCurrentSlots()));
    }
    public void descendAttendee(ArrayList<Camp> campList){
        campList.sort(Comparator.comparingInt(camp -> -camp.getCampInfo().getCurrentSlots()));
    }
    public void ascendCommittee(ArrayList<Camp> campList){
        campList.sort(Comparator.comparingInt(camp -> camp.getCampInfo().getCampCommitteeSlots()));
    }
    public void descendCommittee(ArrayList<Camp> campList){
        campList.sort(Comparator.comparingInt(camp -> -camp.getCampInfo().getCampCommitteeSlots()));
    }
    public void ascendDate(ArrayList<Camp> campList){
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getStartDate()));
    }
    public void descendDate(ArrayList<Camp> campList){
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getStartDate()));
        Collections.reverse(campList);
    }

    public void viewAll(int filtertype){
        ArrayList<Camp> camps = campManager.getCamps();
        switch (filtertype) {
            case 1: {
                ascendAlphabetical(camps);
                break;
            }
            case 2: {
                descendAlphabetical(camps);
                break;
            }
            case 3: {
                ascendAttendee(camps);
                break;
            }
            case 4: {
                descendAttendee(camps);
            }
            case 5: {
                ascendCommittee(camps);
                break;
            }
            case 6: {
                descendCommittee(camps);
                break;
            }
            case 7: {
                ascendDate(camps);
                break;
            }
            case 8: {
                descendDate(camps);
                break;
            }
        }
        showCamps(camps);
    }
    public void showCamps(ArrayList<Camp> camps){
        PrettyPage.printCamps(camps);
    }

    public static ArrayList<Camp> visibilityFilter(ArrayList<Camp> camps, User user){
        //if instance of staff, visibility does not apply
        if(user instanceof Staff){
            return camps;
        }

        Student student = (Student) user;
        //if instance of student
        //shallow copy
        ArrayList<Camp> filteredCamps = new ArrayList<>();
        for(Camp c : camps){
            if(c.getVisibility() && c.getCampInfo().getFaculty().getClass().equals(student.getFacultyInfo().getClass())){
                filteredCamps.add(c);
            }
        }
        // re-reference camps to filteredCamps
        return filteredCamps;
    }

    public ArrayList<Camp> filterStaffCamps(ArrayList<Camp> camps, User user){
        ArrayList<Camp> filteredCamps = new ArrayList<>();
        Staff staff = (Staff) App.getUser();
        for(Camp c : camps){
            if(Objects.equals(c.getCampInfo().getStaffInCharge(), staff.getName())){
                filteredCamps.add(c);
            }
        }
        return filteredCamps;
    }
}
