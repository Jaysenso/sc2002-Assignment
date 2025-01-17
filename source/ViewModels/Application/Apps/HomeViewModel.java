package source.ViewModels.Application.Apps;

import source.Database.App;
import source.Entity.Staff;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.Application.ProfileViewModel;
import source.ViewModels.Application.StaffViewModels.StaffCampViewModel;
import source.ViewModels.Application.StudentViewModels.StudentCampViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.AppViews.HomeView;

/**
 * The HomeViewModel holds all the logic and necessary Ui elements for the application home page.
 *
 * @author Ho Jian Feng
 * @version 1.0
 * @since 11/12/2023
 */
public class HomeViewModel extends BaseViewModel implements IViewModel {

    /**
     * The home view object shows the UI when the user is logged in and presenting the user with options he/she can take.
     *
     * @see HomeView
     */
    private final HomeView homeView;

    /**
     * A default constructor.
     */
    public HomeViewModel() {
        homeView = new HomeView();
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
        PrettyPage.printTitle("Welcome back " + App.getUser().getUserID(), 1);
        homeView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        choice = InputHandler.tryGetInt(1, 3, "Input choice: ", "Invalid choice!");
        switch (choice) {
            case 1: {
                if (App.getUser() instanceof Staff) {
                    viewManager.changeView(new StaffCampViewModel());
                } else {
                    viewManager.changeView(new StudentCampViewModel());
                }
                break;
            }
            case 2: {
                viewManager.changeView(new ProfileViewModel());
                break;
            }
            case 3: {
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
