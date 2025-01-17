package source.ViewModels.Application;

import source.Database.App;
import source.Entity.Camp;
import source.Entity.Student;
import source.Entity.User;
import source.Utility.*;
import source.ViewModels.Application.Apps.LoginViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.ChangePasswordView;
import source.Views.Application.ProfileView;

import java.util.ArrayList;

/**
 * The ProfileViewModel holds all the logic and necessary UI for displaying the profile
 *
 * @author J'sen Ong
 * @version 1.0
 * @since 11/17/2023
 */
public class ProfileViewModel extends BaseViewModel implements IViewModel {
    /**
     * The profile student view object shows the UI for the user profile and its options
     *
     * @see ProfileView
     */
    private final ProfileView profileView;
    /**
     * The change password view object shows the UI for the changing password and its options
     *
     * @see ProfileView
     */
    private final ChangePasswordView changePasswordView;

    /**
     * A default constructor.
     */
    public ProfileViewModel() {
        super();
        profileView = new ProfileView();
        changePasswordView = new ChangePasswordView();
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
        printProfile();
        profileView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
        switch (choice) {
            case 1: {
                changePassword(App.getUser());
                break;
            }
            case 2: {
                viewManager.returnToPreviousView();
                break;
            }
            default: {
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

    /**
     * A function to print out the profile view of the user
     */
    public void printProfile() {
        PrettyPage.printTitle("Your Profile", 1);
        App.getUserManager().getProfile();
    }

    /**
     * A function to change the password of the user. Logs them out.
     */
    public void changePassword(User user) {
        //Display the UI for changing password
        changePasswordView.display();
        System.out.println("You will be logged out after you have changed your password.");
        while (true) {
            String newPassword = InputHandler.tryGetPassword("Input your new password: ",
                    StringsUtility.PASSWORD_MISMATCH);
            //If the input password is the same as the old user password
            if (newPassword.equals(user.getPassword())) {
                PrettyPage.printError("You can not use your old password!");
            } else {
                //Else set password and update the dao
                user.setPassword(newPassword);
                //Call save on our context
                App.getUserManager().update();
                //Go back to the login view model
                viewManager.changeView(new LoginViewModel());
                break;
            }
        }
    }
}