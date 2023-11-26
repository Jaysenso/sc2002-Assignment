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


    public boolean deleteCamp(Camp camp) {
        String input = "";
        do {
            System.out.println("Confirm? y/n");
            try {
                input = InputHandler.getString();
            } catch (Exception e) {
                PrettyPage.printError("Invalid Confirmation");
            }
        } while (!input.equals("y") && !input.equals("n"));

        if (input.equals("y")) {
            return campDao.deleteCamp(camp);
        } else {
            return false;
        }
    }

    public boolean registerAttendees(Student student, Camp selectedCamp) {

        //Create a date range validator that ranges from the start and end date
        DateRangeValidator checker = new DateRangeValidator(selectedCamp.getCampInfo().getStartDate(), selectedCamp.getCampInfo().getEndDate());
        //check if student is already part of the camp
        if (student.isAttendee(selectedCamp)) {
            PrettyPage.printError("Error: You have already registered.");
            return false;
        }
        //check if student has registered for camps that collide with the selectedCamp
        for (Camp camp : student.getRegisteredCamps()) {
            if (checker.isWithinRange(camp.getCampInfo().getStartDate()) || checker.isWithinRange(camp.getCampInfo().getEndDate())) {
                PrettyPage.printError("Error : You are already registered for a camp on the same date.");
                return false;
            }
        }
        //check if the selected camp has slots
        if (selectedCamp.getCampInfo().getCurrentSlots() >= selectedCamp.getCampInfo().getMaxSlots()) {
            PrettyPage.printError("Error : Camp is already full.");
            return false;
        }
        //Check if student is registering for a camp that is past its registration period
        if (LocalDate.now().isAfter(selectedCamp.getCampInfo().getClosingDate())) {
            PrettyPage.printError("Error : Registration period has closed.");
            return false;
        }


        student.addRegisteredCamps(selectedCamp);
        selectedCamp.addAttendee(student);
        PrettyPage.printLine(new Option("Success", "You Have Registered Successfully for " + selectedCamp.getCampInfo().getName()));
        return true;
    }

    public boolean registerCommittees(Student student, Camp selectedCamp) {

        if (student.isAttendee(selectedCamp)) {
            PrettyPage.printError("Error: You are already an Attendee for this camp.");
            return false;
        }

        if (student.getIsCampCommittee() == selectedCamp) {
            PrettyPage.printError("Error : You are already a camp committee for this camp.");
            return false;
        }

        if (student.getIsCampCommittee() != null && student.getIsCampCommittee() != selectedCamp) {
            PrettyPage.printError("Error: You are already Camp Committee for another camp.");
            return false;
        }

        DateRangeValidator checker = new DateRangeValidator(selectedCamp.getCampInfo().getStartDate(), selectedCamp.getCampInfo().getEndDate());
        for (Camp camp : student.getRegisteredCamps()) {

            if (checker.isWithinRange(camp.getCampInfo().getStartDate()) || checker.isWithinRange(camp.getCampInfo().getEndDate())) {
                PrettyPage.printError("Error : You are already registered for a camp on the same date.");
                return false;
            }
        }

        if (selectedCamp.getCampInfo().getCampCommitteeSlots() >= selectedCamp.getCampInfo().getMaxCampCommitteeSlots()) {
            PrettyPage.printError("Error : Camp Committee slots are full.");
            return false;
        }

        if (LocalDate.now().isAfter(selectedCamp.getCampInfo().getClosingDate())) {
            PrettyPage.printError("Error : Registration period has closed.");
            return false;
        }

        student.addRegisteredCamps(selectedCamp);
        student.setIsCampCommittee(selectedCamp);
        selectedCamp.addCommittee(student);
        selectedCamp.addAttendee(student);
        PrettyPage.printLine(new Option("Success", "You Have Registered Successfully for " + selectedCamp.getCampInfo().getName()));
        return true;
    }

    public boolean withdrawAttendees(Student attendee, Camp selectedCamp) {
        if (!attendee.isAttendee(selectedCamp)) {
            PrettyPage.printError("You are not part of the camp!");
            return false;

        } else {
            selectedCamp.withdrawAttendee(attendee);
            attendee.removeRegisteredCamps(selectedCamp);
            updateCamp(selectedCamp);
            PrettyPage.printLine(new Option("Success", "You Have withdrew from " + selectedCamp.getCampInfo().getName()));
            return true;
        }
    }

    public Camp readCamp(DatabaseQuery query) {
        return campDao.readCamp(query);
    }

    public boolean updateCamp(Camp camp) {
        return campDao.updateCamp(camp);
    }

    public void refresh() {
        campDao.refresh();
    }

    public void loadContext() {
        campDao.loadContext();
    }

    public ArrayList<Camp> readCamps(DatabaseQuery query) {
        return campDao.readCamps(query);
    }

    public void showCamps() {
        PrettyPage.printCamps(campDao.getCamps());
    }
}
