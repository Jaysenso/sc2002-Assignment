package source.ViewModels.Application;

import source.Controllers.CampManager;
import source.Database.ApplicationContext;
import source.Entity.Camp;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffOperationsView;

/**
 * The StaffOperationsViewModel holds all the logic and necessary UI elements for staff.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class StaffOperationsViewModel extends BaseViewModel implements IViewModel {
    /**
     * The staff view object shows the UI when the user is logged in as a Staff, presenting the user with options a staff can take.
     *
     * @see StaffOperationsView
     */
    StaffOperationsView staffOperationsView;
    Camp selectedCamp;
    CampManager campManager;

    /**
     * An overloaded constructor that initializes a selected camp and a manager
     * NOTE: This view model should only be accessed by the StaffCampViewModel
     * @param selectedCamp the selected camp
     * ..@param campManager the camp manager reference.
     *
     * @see StaffCampViewModel
     * @see StaffOperationsView
     */
    public StaffOperationsViewModel(Camp selectedCamp) {
        staffOperationsView = new StaffOperationsView();
        campManager = ApplicationContext.getCampManager();
        this.selectedCamp = selectedCamp;
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
        campManager.loadContext();
        PrettyPage.printCampDetails(selectedCamp);
        //PrettyPage.printCamps(cManager.getCamps());
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

            case 1: {
                System.out.println("View Enquiries");
                break;
            }
            case 2: {
                System.out.println("View Suggestions");
                break;
            }
            case 3: {
                viewManager.changeView(new EditCampDetailsViewModel(selectedCamp));
                break;
            }
            case 4: {
                if(campManager.deleteCamp(selectedCamp))
                    System.out.println("Camp Deleted");
                viewManager.returnToPreviousView();
                break;
            }
            case 5: {
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

}
