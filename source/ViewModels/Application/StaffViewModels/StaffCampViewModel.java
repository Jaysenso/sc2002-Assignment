package source.ViewModels.Application.StaffViewModels;

import source.Controllers.CampManager;
import source.Controllers.EnquiryManager;
import source.Controllers.StaffManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Entity.Staff;
import source.Faculty.Faculty;
import source.Faculty.NTU;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Utility.StringsUtility;
import source.ViewModels.Application.Apps.SortViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.StaffCampView;
import source.Views.Application.StaffView.StaffOperationsView;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The StaffCampViewModel class handles the staffCampViewModel's logics and present the relevant operations that staff can use in the camp list view.
 *
 * @author J'sen Ong
 * @version 1.0
 * @since 11/17/2023
 */
public class StaffCampViewModel extends BaseViewModel implements IViewModel {
    /**
     * The StaffCampView object shows the UI for when the staff view all camps
     *
     * @see StaffCampView
     */
    private final StaffCampView staffCampView;
    /**
     * The StaffOperationsView object shows the UI for staff operations
     */
    private final StaffOperationsView staffOperationsView;
    /**
     * The Camp Manager object serves as a DB and abstracts the relevant methods to read/write camp list
     *
     * @see CampManager
     */
    private final CampManager campManager;
    /**
     * The Camp Manager object serves as a DB and abstracts the relevant methods to read/write staff list
     *
     * @see StaffManager
     */
    private final StaffManager staffManager;

    /**
     * A default constructor.
     *
     * @see StaffCampView
     */
    public StaffCampViewModel() {
        staffOperationsView = new StaffOperationsView();
        staffCampView = new StaffCampView();
        campManager = App.getCampManager();
        staffManager = App.getStaffManager();
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
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            ArrayList<Camp> camps = campManager.getCamps();
            PrettyPage.printCamps(camps);
            staffCampView.display();
            choice = InputHandler.tryGetInt(1, 5, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    int index = InputHandler.tryGetInt(1, camps.size(), "Input camp choice: ", "Invalid choice!");
                    Camp selectedCamp = camps.get(index - 1);
                    if (App.getUser().getName().equals(selectedCamp.getCampInfo().getStaffInCharge())) {
                        viewManager.changeView(new StaffInChargeOperationsViewModel(selectedCamp));
                    } else {
                        staffOperations(selectedCamp);
                    }
                    break;
                }
                case 2: { //CREATE CAMP
                    //Handle the creation of new camps by staff
                    Camp camp = createNewCamp();
                    //Create the camp in our manager
                    campManager.createCamp(camp);
                    //Add it to our user
                    Staff s = (App.getUser() instanceof Staff) ? (Staff) App.getUser() : null;
                    if (s != null) {
                        s.addCreatedCamp(camp);
                        staffManager.updateStaff(s);
                    }
                    PrettyPage.printLine(new Option("Success", "You have created the camp."));
                    //Then print out all the camps after creating that new camp
                    PrettyPage.printCamps(campManager.getCamps());
                    break;
                }
                case 3: {
                    viewCreatedCamps();
                    break;
                }
                case 4: {
                    viewManager.changeView(new SortViewModel(campManager.getCamps()));
                    break;
                }
                case 5: {
                    viewManager.returnToPreviousView();
                }
            }
        } while (true);
    }

    /**
     * A function that is called when the ViewManager swaps view, any clean up code such as
     * flushing the console output or cleaning up any lists or data
     */
    @Override
    public void cleanup() {
        System.out.flush(); //NOTE: Does not work in IntelliJ IDEA as it is not a real terminal.
    }

    /**
     * A function that encloses staff operations
     */
    public void staffOperations(Camp selectedCamp) {
        //Do it locally
        EnquiryManager enquiryManager = App.getEnquiryManager();
        boolean isLooping = true;
        while (isLooping) {
            //Print the camp details first
            PrettyPage.printCampDetails(selectedCamp);
            staffOperationsView.display();
            int choice = InputHandler.tryGetInt(1, 2, "Choose option: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    PrettyPage.printEnquiries(selectedCamp.getEnquiryList());
                    Option[] options = {
                            new Option("1", "Back"),
                    };
                    PrettyPage.printLinesWithHeader(options, "Go Back");
                    int back = InputHandler.tryGetInt(1, 1, "Go Back?: ", "Invalid choice!");
                    if (back == 1) isLooping = false;
                    break;
                }
                case 2: {
                    isLooping = false;
                    break;
                }
            }
        }
    }

    /**
     * A function to handle the sub logic of viewing created camps
     */
    public void viewCreatedCamps() {
        //Get the staff object
        Staff s = (App.getUser() instanceof Staff) ? (Staff) App.getUser() : null;
        if (s == null) {
            PrettyPage.printError("Invalid user!");
        }
        ArrayList<Camp> createdCamps = s.getCreatedCamps();
        if (createdCamps.isEmpty()) {
            PrettyPage.printTitle("You have not created any camps!", 1);
        } else {
            PrettyPage.printCamps(createdCamps);
        }

        Option[] options = {
                new Option("1", "Select Camp"),
                new Option("2", "Back"),
        };
        while (true) {
            PrettyPage.printLinesWithHeader(options, "Choose your option");

            int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    if (s.getCreatedCamps().isEmpty()) {
                        PrettyPage.printTitle("There are no camps to view!", 1);
                    } else {
                        int index1 = InputHandler.tryGetInt(1, createdCamps.size(), "Input camp choice: ", "Invalid choice!");
                        Camp selectedCamp = createdCamps.get(index1 - 1);
                        viewManager.changeView(new StaffInChargeOperationsViewModel(selectedCamp));
                    }
                    break;
                }
                case 2: {
                    return;
                }
            }
        }
    }

    /**
     * A function to handle the sub logic of creating a camp in this view model
     *
     * @return the newly created camp after getting user input
     */
    public Camp createNewCamp() {
        //Input the camp name
        System.out.print("Enter camp name: ");
        String name = InputHandler.getString();

        //Get our dates using our input handler
        LocalDate startDate = InputHandler.tryGetDate("Enter start date in the format " + StringsUtility.DATE_FORMAT + ": ", StringsUtility.DATE_ERROR);
        LocalDate endDate, regDate;
        while (true) {
            endDate = InputHandler.tryGetDate("Enter end date in the format " + StringsUtility.DATE_FORMAT + ": ", StringsUtility.DATE_ERROR);
            if (endDate.isEqual(startDate) || endDate.isAfter(startDate)) {
                break;
            } else {
                PrettyPage.printError("End date must be after start date!");
            }
        }
        while (true) {
            regDate = InputHandler.tryGetDate("Enter closing registration date in the format " + StringsUtility.DATE_FORMAT + ": ", StringsUtility.DATE_ERROR);
            if (regDate.isBefore(startDate)) {
                break;
            } else {
                PrettyPage.printError("Registration date must be before start date!");
            }
        }
        System.out.print("Enter location: ");
        String location = InputHandler.tryGetString();
        int totalSlots = InputHandler.tryGetInt(1, 9999, "Enter number of attendee slots: ", StringsUtility.ATTENDEE_SLOTS_ERROR);
        int commSlots = InputHandler.tryGetInt(1, totalSlots, "Enter number of Committee Member slots: ", StringsUtility.CAMP_COMMITTEE_OVERFLOW);

        System.out.print("Enter a brief description: ");
        String description = InputHandler.tryGetString();

        PrettyPage.printLine(new Option("Note:", "A camp not created in your faculty is automatically created under NTU, everyone can see it."));
        System.out.print("Do you want to create in your own faculty? (y/n): ");
        String facultyInput = InputHandler.tryGetString(new String[]{
                "y", "n"
        });

        //Faculty is either the user's own faculty, or ntu
        Faculty faculty = (facultyInput.equals("y")) ? App.getUser().getFacultyInfo() : new NTU();

        System.out.print("Set Visibility (true,false): ");
        boolean visibility = Boolean.valueOf(InputHandler.tryGetString(new String[]{
                "true",
                "false"
        }));
        CampInfo info = new CampInfo(
                name,
                location,
                0,
                totalSlots,
                0,
                commSlots,
                description,
                App.getUser().getName(),
                startDate,
                endDate,
                regDate,
                faculty
        );
        Camp newCamp = new Camp(info, visibility, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        return newCamp;
    }
}
