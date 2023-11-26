package source.ViewModels.Application.StaffViewModels;

import source.Camp.GetCamps;
import source.Camp.UpdateCamp;
import source.Controllers.CampManager;
import source.Database.App;
import source.Entity.Camp;
import source.Faculty.Faculty;
import source.Faculty.NTU;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Utility.StringsUtility;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.EditCampDetailsView;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The EditCampDetailsViewModel class handles the EditCampDetailsView 's logics and present the relevant operations for Staff to edit the camp details.
 *
 * @author J'sen Ong
 * @version 1.0
 * @since 11/17/2023
 */
public class EditCampDetailsViewModel extends BaseViewModel implements IViewModel {
    /**
     * The EditCampDetailsView object shows the UI that contains the relevant options for staff to edit the camp details.
     *
     * @see EditCampDetailsView
     */
    private final EditCampDetailsView editCampDetailsView;
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

    /**
     * A default constructor.
     *
     * @see EditCampDetailsView
     */
    public EditCampDetailsViewModel(Camp selectedCamp) {
        campManager = App.getCampManager();
        this.selectedCamp = selectedCamp;
        editCampDetailsView = new EditCampDetailsView();
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
        editCampDetailsView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            choice = InputHandler.tryGetInt(1, 10, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    viewManager.returnToPreviousView();
                    break;
                }
                case 2: {
                    System.out.print("Enter camp name: ");
                    String newCampName = InputHandler.tryGetString();
                    //Forcefully handle ID changes so that the match is found in the db
                    GetCamps getCamp = new GetCamps(campManager);
                    campManager.useCampService(getCamp);
                    ArrayList<Camp> camps = getCamp.getCamps();
                    for (Camp c : camps) {
                        if (c.getCampInfo().getName().equals(selectedCamp.getCampInfo().getName())) {
                            c.getCampInfo().setName(newCampName);
                            break;
                        }
                    }
                    selectedCamp.getCampInfo().setName(newCampName);
                    break;
                }
                case 3: {
                    LocalDate startDate = InputHandler.tryGetDate("Enter start date in the format " + StringsUtility.DATE_FORMAT + ": ", StringsUtility.DATE_ERROR);
                    selectedCamp.getCampInfo().setStartDate(startDate);
                    break;
                }
                case 4: {
                    LocalDate endDate;
                    while (true) {
                        endDate = InputHandler.tryGetDate("Enter end date in the format " + StringsUtility.DATE_FORMAT + ": ", StringsUtility.DATE_ERROR);
                        if (endDate.isEqual(selectedCamp.getCampInfo().getStartDate()) || endDate.isAfter(selectedCamp.getCampInfo().getStartDate())) {
                            break;
                        } else {
                            PrettyPage.printError("End date must be after start date!");
                        }
                    }
                    selectedCamp.getCampInfo().setEndDate(endDate);
                    break;
                }
                case 5: {
                    LocalDate regDate;
                    while (true) {
                        regDate = InputHandler.tryGetDate("Enter closing registration date in the format " + StringsUtility.DATE_FORMAT + ": ", StringsUtility.DATE_ERROR);
                        if (regDate.isBefore(selectedCamp.getCampInfo().getStartDate())) {
                            break;
                        } else {
                            PrettyPage.printError("Registration date must be before start date!");
                        }
                    }
                    selectedCamp.getCampInfo().setClosingDate(regDate);
                    break;
                }
                case 6: {
                    PrettyPage.printLine(new Option("Note:", "A camp not created in your faculty is automatically created under NTU, everyone can see it."));
                    System.out.print("Do you want to create in your own faculty? (y/n): ");
                    String facultyInput = InputHandler.tryGetString(new String[]{
                            "y", "n"
                    });
                    //Faculty is either the user's own faculty, or ntu
                    Faculty faculty = (facultyInput.equals("y")) ? App.getUser().getFacultyInfo() : new NTU();
                    selectedCamp.getCampInfo().setFaculty(faculty);
                    break;
                }
                case 7: {
                    int totalSlots = InputHandler.tryGetInt(1, 9999, "Enter max number of attendee slots: ", StringsUtility.ATTENDEE_SLOTS_ERROR);
                    selectedCamp.getCampInfo().setMaxSlots(totalSlots);
                    break;
                }
                case 8: {
                    System.out.print("Enter Description: ");
                    String description = InputHandler.tryGetString();
                    selectedCamp.getCampInfo().setDescription(description);
                    break;
                }
                case 9: {
                    if (selectedCamp.getCampInfoCurrentSlots() > 0) {
                        PrettyPage.printError("Cannot edit visibility after students have already registered!");
                        break;
                    }
                    System.out.print("Set Visibility (true,false): ");
                    boolean visibility = Boolean.valueOf(InputHandler.tryGetString(new String[]{
                            "true",
                            "false"
                    }));
                    selectedCamp.setVisibility(visibility);
                    break;
                }
                case 10: {
                    System.out.print("Enter location: ");
                    String location = InputHandler.tryGetString();
                    selectedCamp.getCampInfo().setLocation(location);
                }
                default:
                    break;
            }
            //Update once at the end
            campManager.useCampService(new UpdateCamp(selectedCamp, campManager));
            //Work around to get the camp
            PrettyPage.printCampDetails(selectedCamp);
            editCampDetailsView.display();
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
}