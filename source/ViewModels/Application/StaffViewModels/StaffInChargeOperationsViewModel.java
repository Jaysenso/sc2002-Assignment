package source.ViewModels.Application.StaffViewModels;

import source.CampOperations.DeleteCamp;
import source.Controllers.CampManager;
import source.Controllers.ReportGenerator;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Staff;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.Application.Apps.ReplyEnquiryViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.StaffInChargeOperationsView;

/**
 * The StaffOperationsViewModel holds all the logic and necessary UI elements for staff.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class StaffInChargeOperationsViewModel extends BaseViewModel implements IViewModel {
    /**
     * The staffOperationsView object shows the UI when the user is logged in as a Staff, presenting the user with options a staff can take.
     *
     * @see StaffInChargeOperationsView
     */
    private final StaffInChargeOperationsView staffOperationsView;

    /**
     * The Camp Manager object serves as a DB and abstracts the relevant methods to read/write camp list
     *
     * @see CampManager
     */
    private final CampManager campManager;
    /**
     * The selected camp in this view model
     */
    private final Camp selectedCamp;

    private final Staff staff = (Staff) App.getUser();

    /**
     * An overloaded constructor that initializes a selected camp and a manager
     * NOTE: This view model should only be accessed by the StaffCampViewModel
     *
     * @see StaffCampViewModel
     * @see StaffInChargeOperationsView
     */
    public StaffInChargeOperationsViewModel(Camp selectedCamp) {
        this.selectedCamp = selectedCamp;
        staffOperationsView = new StaffInChargeOperationsView();
        campManager = App.getCampManager();
    }

    /**
     * A function called by ViewManager to pass by reference and conduct any initial setup
     * of the view model.
     *
     * @param viewManager the view manager reference.
     * @see ViewManager
     */
    @Override
    public void init(ViewManager viewManager) {
        super.init(viewManager);
        PrettyPage.printCampDetails(selectedCamp);
        staffOperationsView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice = InputHandler.tryGetInt(1, 6, "Input choice: ", "Invalid choice!");
        switch (choice) {
            case 1: { //REPLY TO AN ENQUIRY
                viewManager.changeView(new ReplyEnquiryViewModel(selectedCamp));
                break;
            }
            case 2: { //VIEW SUGGESTIONS
                viewManager.changeView(new StaffSuggestionViewModel(selectedCamp));
                break;
            }
            case 3: { //EDIT CAMP DETAILS
                viewManager.changeView(new EditCampDetailsViewModel(selectedCamp));
                break;
            }
            case 4: { //GENERATE REPORT
                generateReport();
                break;
            }
            case 5: { //DELETE CAMP
                System.out.print("Confirm deletion? (y/n): ");
                String line = InputHandler.tryGetString(new String[]{"y", "n"});
                if (line.equals("y")) {
                    if (campManager.operate(new DeleteCamp(selectedCamp, campManager))) {
                        staff.removeCreatedCamp(selectedCamp);
                        PrettyPage.printLine(new Option("Success", "Camp successfully deleted"));
                    }
                }
                viewManager.returnToPreviousView();
                break;
            }
            case 6: {
                viewManager.returnToPreviousView();
                break;
            }
        }
    }

    /**
     * A function that is called when the ViewManager swaps view, any clean up code such as
     * flushing the console output or cleaning up any lists or data
     */
    @Override
    public void cleanup() {
        System.out.flush(); //NOTE: Does not work in IntelliJ IDEA as it is not a real terminal.
    }

    public void generateReport() {
        while (true) {
            PrettyPage.printTitle("Generate Camp Report", 1);
            Option[] options = {
                    new Option("1", "Attendance Report"),
                    new Option("2", "Performance Report"),
                    new Option("3", "Both"),
                    new Option("4", "Back"),
            };
            PrettyPage.printLinesWithHeader(options, "Choose your option");
            int choice = InputHandler.tryGetInt(1, 6, "Enter your choice: ", "Invalid choice!");
            switch (choice) {
                case 1: { //Attendance Report
                    String filePath = DirectoryUtility.REPORT_DATA_PATH + selectedCamp.getCampInfo().getName() + "_attendanceReport.txt";
                    ReportGenerator generator = new ReportGenerator(filePath
                    );
                    generator.generateAttendanceReport(selectedCamp, staff.getName() + " (Staff) ");
                    PrettyPage.printLine(new Option("Success", "Attendance report was generated at " + filePath));
                    break;
                }
                case 2: { //Performance Report
                    String filePath = DirectoryUtility.REPORT_DATA_PATH + selectedCamp.getCampInfo().getName() + "_performanceReport.txt";
                    ReportGenerator generator = new ReportGenerator(filePath
                    );
                    generator.generatePerformanceReport(selectedCamp, staff.getName() + " (Staff) ");
                    PrettyPage.printLine(new Option("Success", "Performance report was generated at " + filePath));
                    break;
                }
                case 3: { //Both
                    String filePath = DirectoryUtility.REPORT_DATA_PATH + selectedCamp.getCampInfo().getName() + "_fullReport.txt";
                    ReportGenerator generator = new ReportGenerator(filePath
                    );
                    generator.generateFullReport(selectedCamp, staff.getName() + " (Staff) ");
                    PrettyPage.printLine(new Option("Success", "Full report was generated at " + filePath));
                    break;
                }
                case 4: { //Bye
                    return;
                }
            }
        }
    }
}
