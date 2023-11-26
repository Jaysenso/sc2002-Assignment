package source.ViewModels.Application.StaffViewModels;

import source.CampOperations.DeleteCamp;
import source.Controllers.CampManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Staff;
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
    StaffInChargeOperationsView staffOperationsView;

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
        boolean isLooping = true;
        PrettyPage.printTitle("Generate Camp Report for:", 3);
        while (isLooping) {
            Option[] options = {
                    new Option("1", "Attendees"),
                    new Option("2", "Camp Committee"),
                    new Option("3", "Both"),
                    new Option("4", "Back"),
            };

            PrettyPage.printLinesWithHeader(options, "Choose your option.");
            int choice = InputHandler.tryGetInt(1, 4, "", "");
            switch (choice) {
                case 1: { //ATTENDEE REPORT

                    break;
                }
                case 2: { //COMMITTEE REPORT

                    break;
                }
                case 3: { //BOTH REPORT

                    break;
                }
                case 4: { //BACK

                    isLooping = false;
                    break;
                }
            }
        }
    }
}
