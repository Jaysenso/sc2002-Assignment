package source.ViewModels.Application;

import source.Controllers.AuthenticationController;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.LoginView;
import source.Views.Application.StartView;

import java.util.Scanner;

/**
 * The LoginViewModel holds all the logic and necessary Ui elements for a successful login.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class LoginViewModel extends BaseViewModel implements IViewModel {
    /**
     * The login view object that contains the UI for when the user is prompted to log in.
     *
     * @see StartView
     */
    LoginView loginView;

    /**
     * The authentication controller object that handles checking if the user has valid passwords and users
     * Should only exist in the LoginViewModel.
     *
     * @see AuthenticationController
     */
    AuthenticationController authenticationController;

    /**
     * A default constructor
     */
    public LoginViewModel() {
        super();
        authenticationController = new AuthenticationController();
        loginView = new LoginView();
    }

    @Override
    public void init(ViewManager viewManager) {
        super.init(viewManager);
        loginView.display();
        handleInputs();
    }

    @Override
    public void handleInputs() {
        int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
        switch (choice) {
            case 1: {
                handleStudentLogin();
                break;
            }
            case 2: {
                handleStaffLogin();
                break;
            }
            case 3: {
                viewManager.returnToPreviousView();
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void cleanup() {

    }

    //TODO: IMPROVE STUDENT AND STAFF LOGIN, SO MESSY
    public void handleStudentLogin() {
        //Simple login system for now...
        while (true) {
            //Get the email input
            String email = InputHandler.tryGetEmail("Input your student NTU email: ", "Invalid NTU email entered!");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            boolean success = authenticationController.authenticateAsStudent(email, password);
            if (!success) {
                if (authenticationController.getTriesLeft() != 0) {
                    PrettyPage.printError("You have " + authenticationController.getTriesLeft() + " tries left.");
                } else {
                    //TODO: Locked out mechanism
                    PrettyPage.printError("You have been locked out. You can only log in after 30 seconds.");
                    break;
                }
            } else {
                //Transition to view models
                viewManager.changeView(new StudentViewModel());
                break;
            }
        }
    }

    public void handleStaffLogin() {
        while (true) {
            //Get the email input
            String email = InputHandler.tryGetEmail("Input your staff NTU email: ", "Invalid NTU email entered!");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            boolean success = authenticationController.authenticateAsStaff(email, password);
            if (!success) {
                if (authenticationController.getTriesLeft() != 0) {
                    PrettyPage.printError("You have " + authenticationController.getTriesLeft() + " tries left.");
                } else {
                    //TODO: Locked out mechanism
                    PrettyPage.printError("You have been locked out. You can only log in after 30 seconds.");
                    break;
                }
            } else {
                viewManager.changeView(new StaffViewModel());
                break;
            }
        }

    }
}
