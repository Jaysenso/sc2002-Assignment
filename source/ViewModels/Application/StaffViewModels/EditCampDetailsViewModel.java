package source.ViewModels.Application.StaffViewModels;

import source.Controllers.CampManager;
import source.Database.App;
import source.Entity.Camp;
import source.Faculty.Faculty;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.EditCampDetailsView;
import source.Views.Application.StaffView.StaffCampView;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

/**
 * The EditCampDetailsViewModel class handles the EditCampDetailsView 's logics and present the relevant operations for Staff to edit the camp details.
 *
 * @author J'sen Ong
 * @version 1.0
 * @since 11/17/2023
 */
public class EditCampDetailsViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StaffCampView
     */
    EditCampDetailsView editCampDetailsView;
    CampManager campManager;
    Camp selectedCamp;

    /**
     * A default constructor.
     *
     * @see StaffCampView
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
        //Use the camp reference
        //Find the camp with the same name
        Camp camp = campManager.readCamp(selectedCamp.getCampInfo().getName(), "camp_name");
        do {
            choice = InputHandler.tryGetInt(1,10 , "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    viewManager.returnToPreviousView();
                    break;
                }
                case 2: {
                    System.out.print("Enter Camp Name: ");
                    String newCampName = InputHandler.getString();
                    selectedCamp.getCampInfo().setName(newCampName);
                    break;
                }
                case 3: {
                    System.out.print("Enter start date in the format yyyy-MM-dd: ");
                    LocalDate startDate = LocalDate.parse(InputHandler.getString());
                    selectedCamp.getCampInfo().setStartDate(startDate);
                    break;
                }
                case 4: {
                    System.out.print("Enter end date in the format yyyy-MM-dd: ");
                    LocalDate endDate = LocalDate.parse(InputHandler.getString());
                    selectedCamp.getCampInfo().setStartDate(endDate);
                    break;
                }
                case 5: {
                    System.out.print("Enter registration closing date in the format yyyy-MM-dd: ");
                    LocalDate regDate = LocalDate.parse(InputHandler.getString());
                    selectedCamp.getCampInfo().setStartDate(regDate);
                    break;
                }
                case 6: {
                    Faculty f = null;
                    do {
                        System.out.print("Enter User Group: ");
                        try {
                            String facultyName = "source.Faculty." + InputHandler.getString();
                            f = (Faculty) Class.forName(facultyName).getDeclaredConstructor().newInstance();
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                                 InvocationTargetException | NoSuchMethodException e) {
                            PrettyPage.printError("Faculty Group not found in our System");
                        }
                    }while(f == null);
                    selectedCamp.getCampInfo().setFaculty(f);
                    break;
                }
                case 7: {
                    System.out.print("Enter Attendee Slots: ");
                    int totalSlots = InputHandler.getInt();
                    selectedCamp.getCampInfo().setMaxSlots(totalSlots);
                    break;
                }
                case 8: {
                    System.out.print("Enter Description: ");
                    String description = InputHandler.getString();
                    selectedCamp.getCampInfo().setDescription(description);
                    break;
                }
                case 9: {
                    PrettyPage.printTitle("Visibility: " + selectedCamp.getVisibility(), 2);
                    System.out.println("Set to " + !selectedCamp.getVisibility() + " ?");
                    String input = "";
                    do{
                        System.out.print("Confirm? y/n:");
                        try{
                            input = InputHandler.getString();
                        }catch (Exception e) {
                            PrettyPage.printError("Invalid Confirmation");
                        }
                    }while(!input.equals("y") && !input.equals("n"));

                    if(input.equals("y")){
                        selectedCamp.setVisibility(true);
                    }
                    else{
                        selectedCamp.setVisibility(false);
                    }
                    break;
                }
                    default: break;
            }
            //Update once at the end
            camp.shallowCopy(selectedCamp);
            campManager.updateCamp(camp);
            PrettyPage.printCampDetails(camp);
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