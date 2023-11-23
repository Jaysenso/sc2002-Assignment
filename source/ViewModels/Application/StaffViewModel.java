package source.ViewModels.Application;

import source.Entity.Camp;
import source.Utility.InputHandler;
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
    Camp camp;

    /**
     * A default constructor.
     *
     * @see StaffView
     */
    public StaffViewModel(Camp camp) {
        staffView = new StaffView();
        this.camp = camp;
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
        campDetails(camp);
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
                System.out.println("create camp");
                break;
            }
            case 3: {
                System.out.println("filter");
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

    public void campDetails(Camp camp){
        System.out.println(camp);
    }
}
