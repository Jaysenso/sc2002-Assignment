package source.ViewModels.Application.StudentViewModels;

import source.Controllers.ReportGenerator;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.Application.Apps.ReplyEnquiryViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.CampCommitteeView;
import source.Views.Application.StudentView.StudentOperationsView;
/**
 * The CampCommitteeViewModel holds all the logic and necessary UI elements for CampCommittee
 *
 * @author J'sen Ong
 * @version 1.0
 * @since 11/12/2023
 */
public class CampCommitteeViewModel extends BaseViewModel implements IViewModel {

    /**
     * The CampCommitteeViewModel object shows the UI when the user access a camp that he is camp committee, presenting the user with options a camp committee can take
     *
     * @see CampCommitteeView
     */
    private final CampCommitteeView campCommitteeView;

    /**
     * student is a downcasted object of user.
     *
     * @see Student
     */
    private Student student = (Student) App.getUser();

    /**
     * The selectedCamp object stores the camp that the staff selects
     *
     * @see Camp
     */
    private final Camp selectedCamp;

    /**
     * A default constructor.
     * *
     */
    public CampCommitteeViewModel(Camp selectedCamp) {
        campCommitteeView = new CampCommitteeView();
        this.selectedCamp = selectedCamp;
    }

    /**
     * A function called by ViewManager to pass by reference and conduct any initial setup
     * of the view model.
     *
     * @param viewManager the view manager reference.
     * @see source.ViewModels.ViewManager
     */
    @Override
    public void init(ViewManager viewManager) {
        super.init(viewManager);
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            PrettyPage.printCampDetails(selectedCamp);
            campCommitteeView.display();
            choice = InputHandler.tryGetInt(1, 4, "Input choice: ", "Invalid choice!");
            switch (choice) {
                //View Enquiries
                case 1: {
                    viewManager.changeView(new ReplyEnquiryViewModel(selectedCamp));
                    break;
                }
                //View Suggestions
                case 2: {
                    viewManager.changeView(new CampCommitteeSuggestionViewModel(selectedCamp));
                    break;
                }
                //Generate Camp Report
                case 3: {
                    String filePath = DirectoryUtility.REPORT_DATA_PATH + selectedCamp.getCampInfo().getName() + "_attendanceReport.txt";
                    ReportGenerator generator = new ReportGenerator(filePath
                    );
                    generator.generateAttendanceReport(selectedCamp, student.getName() + " (Camp Committee Member)");
                    PrettyPage.printLine(new Option("Success", "Attendance report was generated at " + filePath));
                    break;
                }
                //Back
                case 4: {
                    viewManager.returnToPreviousView();
                    break;
                }
            }
        } while (choice != 1);
    }

    /**
     * A function that is called when the ViewManager swaps view, any clean up code such as
     * flushing the console output or cleaning up any lists or data
     */
    @Override
    public void cleanup() {
        System.out.flush(); //NOTE: Does not work in IntelliJ IDEA as it is not a real terminal.
    }
}
