package source.ViewModels.Application.Apps;

import source.Controllers.AuthenticationController;
import source.Controllers.StaffManager;
import source.Controllers.StudentManager;
import source.Controllers.UserManagement.StaffManagement;
import source.Controllers.UserManagement.StudentManagement;
import source.Database.App;
import source.Database.DatabaseQuery;
import source.Entity.Staff;
import source.Entity.Student;
import source.Entity.User;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.Utility.StringsUtility;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.AppViews.LoginView;
import source.Views.Application.AppViews.StartView;
import source.Views.Application.ChangePasswordView;

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
    private LoginView loginView;

    private ChangePasswordView changePasswordView;

    /**
     * The authentication controller object that handles checking if the user has valid passwords and users
     * Should only exist in the LoginViewModel.
     *
     * @see AuthenticationController
     */
    AuthenticationController authenticationController;

    StudentManager studentManager;
    StaffManager staffManager;

    /**
     * A default constructor
     */
    public LoginViewModel() {
        super();
        authenticationController = new AuthenticationController();
        studentManager = App.getStudentManager();
        staffManager = App.getStaffManager();
        loginView = new LoginView();
        changePasswordView = new ChangePasswordView();
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
            Student student = App.getStudentManager().readStudent(
                    new DatabaseQuery(email, "email"));
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
                //If it is required to change the password then update
                if (checkIfChangePassword(student)) {
                    //If we had to change password, update the dao
                    App.getStudentManager().updateStudent(student);
                }
                //Update application context
                App.setUser(student);
                App.getUserManager().setManagement(new StudentManagement(student));
                //Transition to view models
                viewManager.changeView(new HomeViewModel(false));
                break;
            }
        }
    }

    public void handleStaffLogin() {
        //Access our database through our dao

        while (true) {
            //Get the email input
            String email = InputHandler.tryGetEmail("Input your Staff NTU email: ", "Invalid NTU email entered!");
            Staff staff = App.getStaffManager().readStaff(new DatabaseQuery(email, "email"));
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
                if (checkIfChangePassword(staff)) {
                    //If we had to change password, update the dao
                    App.getStaffManager().updateStaff(staff);
                }
                //Set the user of this application context
                App.setUser(staff);
                App.getUserManager().setManagement(new StaffManagement(staff));

                viewManager.changeView(new HomeViewModel(true));
                break;
            }
        }
    }

    public boolean checkIfChangePassword(User user) {
        if (user.getPassword().equals("password")) {
            //need to reset the password
            //Display the UI for changing password
            changePasswordView.display();
            while (true) {
                String newPassword = InputHandler.tryGetPassword("Input your new password: ",
                        StringsUtility.PASSWORD_MISMATCH);
                //If the input password is the same as the old user password
                if (newPassword.equals(user.getPassword())) {
                    PrettyPage.printError("You can not use your old password!");
                } else {
                    //Else set password and update the dao
                    user.setPassword(newPassword);
                    break;
                }
            }
            return true;
        }
        return false;
    }

}
