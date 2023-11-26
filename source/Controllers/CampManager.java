package source.Controllers;

import source.Database.CampDaoImpl;
import source.Database.Dao.CampDao;
import source.Database.DatabaseQuery;
import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The CampManager class holds all logic for the creation and handling of camps and is the intermediary between
 * the dao and the app logic.
 *
 * @author Isaac Chun
 * @version 1.4
 * @since 11/23/2023
 */
public final class CampManager {
    /**
     * The camp data access object that contains direct implementations to our database
     */
    private final CampDao campDao;

    /**
     * A default constructor.
     */
    public CampManager() {
        this.campDao = new CampDaoImpl(DirectoryUtility.CAMP_LIST_PATH);
    }

    /**
     * A function to take to create a camp and store into our database
     *
     * @param camp the camp to create and store
     */
    public void createCamp(Camp camp) {
        campDao.createCamp(camp);
    }

    /**
     * Acquires the camps stored in this manager
     *
     * @return list of camps
     */
    public ArrayList<Camp> getCamps() {
        return campDao.getCamps();
    }

    /**
     * Deletes a camp in the manager/database
     *
     * @return true if delete was successful, false if not.
     */
    public boolean deleteCamp(Camp camp) {
        //Check if there are already students
        if (camp.getCampInfoCurrentSlots() > 0) {
            PrettyPage.printError("A camp cannot be deleted as students have already registered for it!");
            return false;
        }
        return campDao.deleteCamp(camp);
    }

    /**
     * A helper function to process and register attendees
     *
     * @return true if successful, false if not
     */
    public boolean registerAttendees(Student student, Camp selectedCamp) {
        //Create a date range validator that ranges from the start and end date
        DateRangeValidator checker = new DateRangeValidator(selectedCamp.getCampInfo().getStartDate(), selectedCamp.getCampInfo().getEndDate());

        //Check if camp has blacklisted this student
        for (Student s : selectedCamp.getBlacklisted()) {
            if (s.getName().equals(student.getName())) {
                PrettyPage.printError("You cannot re-enter a camp you withdrew from!");
                return false;
            }
        }
        //check if student is already part of the camp
        if (student.isAttendee(selectedCamp)) {
            PrettyPage.printError("You have already registered in this camp!");
            return false;
        }
        //check if student has registered for camps that collide with the selectedCamp
        for (Camp camp : student.getRegisteredCamps()) {
            if (checker.isWithinRange(camp.getCampInfo().getStartDate()) || checker.isWithinRange(camp.getCampInfo().getEndDate())) {
                PrettyPage.printError("You have conflicts with other camps you are attending!");
                return false;
            }
        }
        //check if the selected camp has slots
        if (selectedCamp.getCampInfo().getCurrentSlots() >= selectedCamp.getCampInfo().getMaxSlots()) {
            PrettyPage.printError("Camp is already full.");
            return false;
        }
        //Check if student is registering for a camp that is past its registration period
        if (LocalDate.now().isAfter(selectedCamp.getCampInfo().getClosingDate())) {
            PrettyPage.printError("Registration period has closed for this camp.");
            return false;
        }
        //Else we are successful. add the references to both sides
        student.addRegisteredCamps(selectedCamp);
        selectedCamp.addAttendee(student);
        PrettyPage.printLine(new Option("Success", "You Have Registered Successfully for " + selectedCamp.getCampInfo().getName()));
        return true;
    }

    /**
     * A helper function to process and register a student as a committee member
     *
     * @return true if successful, false if not
     */
    public boolean registerCommittees(Student student, Camp selectedCamp) {

        DateRangeValidator checker = new DateRangeValidator(selectedCamp.getCampInfo().getStartDate(), selectedCamp.getCampInfo().getEndDate());

        //Check if camp has blacklisted this student
        for (Student s : selectedCamp.getBlacklisted()) {
            if (s.getName().equals(student.getName())) {
                PrettyPage.printError("You cannot re-enter a camp you withdrew from!");
                return false;
            }
        }

        //Deny the student if he's a normal attendee in the current camp
        if (student.isAttendee(selectedCamp)) {
            PrettyPage.printError("You are already an Attendee for this camp.");
            return false;
        }
        //If the student is already a camp committee a camp, find out
        if (student.getIsCampCommittee() != null) {
            if (student.getIsCampCommittee().equals(selectedCamp)) {
                PrettyPage.printError("You are already a camp committee for this camp.");
                return false;
            } else {
                PrettyPage.printError("You are already camp committee for another camp.");
                return false;
            }
        }
        //Loop through all registered camps of the student and check if there are conflicts
        for (Camp camp : student.getRegisteredCamps()) {

            if (checker.isWithinRange(camp.getCampInfo().getStartDate()) || checker.isWithinRange(camp.getCampInfo().getEndDate())) {
                PrettyPage.printError("You have conflicts with other camps you are attending!");
                return false;
            }
        }
        //Check if the committee slots are still available
        if (selectedCamp.getCampInfo().getCampCommitteeSlots() >= selectedCamp.getCampInfo().getMaxCampCommitteeSlots()) {
            PrettyPage.printError("Camp Committee slots are full.");
            return false;
        }
        //Check if student is registering for a camp that is past its registration period
        if (LocalDate.now().isAfter(selectedCamp.getCampInfo().getClosingDate())) {
            PrettyPage.printError("Registration period has closed for this camp.");
            return false;
        }
        //Else we are successful and we can add our references
        student.addRegisteredCamps(selectedCamp);
        student.setIsCampCommittee(selectedCamp);
        selectedCamp.addCommittee(student);
        PrettyPage.printLine(new Option("Success", "You Have Registered Successfully for " + selectedCamp.getCampInfo().getName()));
        return true;
    }

    /**
     * A helper function to withdraw an attendee from the camp and blacklist him
     *
     * @return true if successful, false if not
     */
    public boolean withdrawAttendees(Student attendee, Camp selectedCamp) {
        if (!attendee.isAttendee(selectedCamp)) {
            PrettyPage.printError("You are not part of the camp!");
            return false;

        } else {
            //Successful withdrawal
            //Blacklist this user
            selectedCamp.addBlacklisted(attendee);
            selectedCamp.withdrawAttendee(attendee);
            attendee.removeRegisteredCamps(selectedCamp);
            updateCamp(selectedCamp);
            PrettyPage.printLine(new Option("Success", "You Have withdrew from " + selectedCamp.getCampInfo().getName()));
            return true;
        }
    }

    public boolean updateCamp(Camp camp) {
        return campDao.updateCamp(camp);
    }
}
