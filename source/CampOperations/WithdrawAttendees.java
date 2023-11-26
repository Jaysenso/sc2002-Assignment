package source.CampOperations;

import source.Controllers.CampManager;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Entity.Student;
import source.Entity.Camp;

/**
 * The WithdrawAttendees class holds the logic to withdraw an attendee from a camp
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class WithdrawAttendees implements CampOperations {
    private final Student attendee;
    /**
     * The selected camp reference
     */
    private final Camp selectedCamp;
    /**
     * The camp manager reference
     */
    private final CampManager campManager;

    /**
     * Overloaded constructor to initialize a camp and manager
     *
     * @param attendee     the camp
     * @param selectedCamp the selected camp
     * @param campManager  the camp manager reference
     */
    public WithdrawAttendees(Student attendee, Camp selectedCamp, CampManager campManager) {
        this.selectedCamp = selectedCamp;
        this.attendee = attendee;
        this.campManager = campManager;
    }

    /**
     * The execution function
     */
    @Override
    public boolean execute() {
        if (!attendee.isAttendee(selectedCamp)) {
            PrettyPage.printError("You are not part of the camp!");
            return false;

        } else {
            //Successful withdrawal
            //Blacklist this user
            selectedCamp.addBlacklisted(attendee);
            selectedCamp.withdrawAttendee(attendee);
            attendee.removeRegisteredCamps(selectedCamp);
            campManager.operate(new UpdateCamp(selectedCamp, campManager));
            PrettyPage.printLine(new Option("Success", "You Have withdrew from " + selectedCamp.getCampInfo().getName()));
            return true;
        }
    }
}
