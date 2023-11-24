package source.ViewModels.Application;

import source.Controllers.CampManager;
import source.Entity.Camp;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView;

/**
 * The StaffViewModel holds all the logic and necessary UI elements for staff.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class StaffViewModel extends BaseViewModel implements IViewModel {
    /**
     * The staff view object shows the UI when the user is logged in as a Staff, presenting the user with options a staff can take.
     *
     * @see StaffView
     */
    StaffView staffView;
    Camp selectedCamp;
    CampManager cManager;

    /**
     * A default constructor.
     *
     * @see StaffView
     */
    public StaffViewModel(Camp camp) {
        staffView = new StaffView();
        this.selectedCamp = camp;
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
        staffView.display();
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
                viewManager.returnToPreviousView();
                break;
            }
            case 2: {
                System.out.println("View Enquiries");
                break;
            }
            case 3: {
                System.out.println("View Suggestions");
                break;
            }
            case 4: {
                System.out.println("edit");
                break;
            }
            case 5: {
                if(cManager.deleteCamp(selectedCamp))
                    System.out.println("Camp Deleted");
                PrettyPage.printCamps(cManager.getCamps());
                //viewManager.returnToPreviousView();
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
