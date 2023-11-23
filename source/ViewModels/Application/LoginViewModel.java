package source.ViewModels.Application;

import source.Controllers.AuthenticationController;
import source.Database.ApplicationContext;
import source.Database.Dao.StaffDao;
import source.Database.Dao.StudentDao;
import source.Database.StaffDaoImpl;
import source.Database.StudentDaoImpl;
import source.Entity.Staff;
import source.Entity.Student;
import source.Utility.DirectoryUtility;
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
        int choice = InputHandler.tryGetInt(1, 3, "Input choice: ", "Invalid choice!");
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

    public void handleStudentLogin() {
        while (true) {
            //Get the email input
            String email = InputHandler.tryGetEmail("Input your Student NTU email: ", "Invalid NTU email entered!");
            //Access our database through our dao
            StudentDao dao = new StudentDaoImpl(DirectoryUtility.STUDENT_DATA_PATH);
            Student student = dao.readStudent(email, "email");
            //Check if the entry exists
            if (student == null) {
                PrettyPage.printError("The student email you entered does not exist in our system.");
                continue;
            }
            System.out.print("Enter password: ");
            String password = InputHandler.getString();
            //Then attempt to authenticate the user
            boolean success = authenticationController.authenticate(student, password);
            if (!success) {
                if (authenticationController.getTriesLeft() != 0) {
                    PrettyPage.printError("You have " + authenticationController.getTriesLeft() + " tries left.");
                } else {
                    //TODO: Locked out mechanism
                    PrettyPage.printError("You have been locked out. You can only log in after 30 seconds.");
                    break;
                }
            } else {
                //Update application context
                ApplicationContext.user = student;
                //Transition to view models
                viewManager.changeView(new HomeViewModel(false));
                break;
            }
        }
    }

    public void handleStaffLogin() {
        while (true) {
            //Get the email input
            String email = InputHandler.tryGetEmail("Input your Staff NTU email: ", "Invalid NTU email entered!");
            //Access our database through our dao
            StaffDao dao = new StaffDaoImpl(DirectoryUtility.STAFF_DATA_PATH);
            Staff staff = dao.readStaff(email, "email");
            //Check if the entry exists
            if (staff == null) {
                PrettyPage.printError("The staff email you entered does not exist in our system.");
                continue;
            }
            System.out.print("Enter password: ");
            String password = InputHandler.getString();
            //Then attempt to authenticate the user
            boolean success = authenticationController.authenticate(staff, password);
            if (!success) {
                if (authenticationController.getTriesLeft() != 0) {
                    PrettyPage.printError("You have " + authenticationController.getTriesLeft() + " tries left.");
                } else {
                    //TODO: Locked out mechanism
                    PrettyPage.printError("You have been locked out. You can only log in after 30 seconds.");
                    break;
                }
            } else {
                //Update application context
                ApplicationContext.user = staff;
                viewManager.changeView(new HomeViewModel(true));
                break;
            }
        }
    }
}
