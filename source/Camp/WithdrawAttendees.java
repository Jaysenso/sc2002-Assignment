package source.Camp;

import source.Controllers.CampManager;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Entity.Student;
import source.Entity.Camp;

public class WithdrawAttendees implements CampService{
    private Student attendee;
    private Camp selectedCamp;
    private CampManager campManager;

    public WithdrawAttendees(Student attendee, Camp selectedCamp, CampManager campManager){
        this.selectedCamp = selectedCamp;
        this.attendee = attendee;
        this.campManager = campManager;
    }

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
            campManager.useCampService(new UpdateCamp(selectedCamp, campManager));
            PrettyPage.printLine(new Option("Success", "You Have withdrew from " + selectedCamp.getCampInfo().getName()));
            return true;
        }
    }

    public Student getAttendee() {
        return attendee;
    }

    public void setAttendee(Student attendee) {
        this.attendee = attendee;
    }

    public Camp getSelectedCamp() {
        return selectedCamp;
    }

    public void setSelectedCamp(Camp selectedCamp) {
        this.selectedCamp = selectedCamp;
    }

    public CampManager getCampManager() {
        return campManager;
    }

    public void setCampManager(CampManager campManager) {
        this.campManager = campManager;
    }
}
