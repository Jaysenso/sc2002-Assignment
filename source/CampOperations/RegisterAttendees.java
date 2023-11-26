package source.CampOperations;

import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.DateRangeValidator;
import source.Utility.Option;
import source.Utility.PrettyPage;

import java.time.LocalDate;

/**
 * The RegisterAttendees class holds the logic to add an attendee to the camp
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class RegisterAttendees implements CampOperations {
    /**
     * The student reference
     */
    private final Student student;
    /**
     * The selected camp reference
     */
    private final Camp selectedCamp;

    /**
     * Overloaded constructor to initialize a camp and manager
     *
     * @param student      the student
     * @param selectedCamp the selected canp
     */
    public RegisterAttendees(Student student, Camp selectedCamp) {
        this.student = student;
        this.selectedCamp = selectedCamp;
    }

    /**
     * The execution function
     */
    @Override
    public boolean execute() {
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
}