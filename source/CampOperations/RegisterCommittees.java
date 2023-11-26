package source.CampOperations;

import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.DateRangeValidator;
import source.Utility.Option;
import source.Utility.PrettyPage;

import java.time.LocalDate;

/**
 * The RegisterComnittees class holds the logic to add a committee member to the camp
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class RegisterCommittees implements CampOperations {
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
    public RegisterCommittees(Student student, Camp selectedCamp) {
        this.student = student;
        this.selectedCamp = selectedCamp;
    }

    /**
     * The execution function
     */
    @Override
    public boolean execute() {
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
        selectedCamp.addAttendee(student);
        PrettyPage.printLine(new Option("Success", "You Have Registered Successfully for " + selectedCamp.getCampInfo().getName()));
        return true;
    }
}