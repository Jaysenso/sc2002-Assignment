package source.ViewModels.Application.Apps;

import source.Database.ApplicationContext;
import source.Entity.CampInfo;
import source.Entity.Staff;
import source.Entity.User;
import source.Utility.DateTimeFormatter;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.Application.ProfileViewModel;
import source.ViewModels.Application.StaffViewModels.StaffCampViewModel;
import source.ViewModels.Application.StudentViewModels.StudentCampViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.AppViews.HomeView;

public class HomeViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see HomeView
     */
    HomeView homeView;

    /**
     * A default constructor.
     *
     * @see HomeView
     */
    public HomeViewModel(boolean userGroup) {
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
        PrettyPage.printTitle("Welcome back " + ApplicationContext.user.getUserID(), 1);
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
                if (ApplicationContext.user instanceof Staff) {
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