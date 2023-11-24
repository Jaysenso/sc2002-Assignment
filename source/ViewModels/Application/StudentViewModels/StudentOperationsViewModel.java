package source.ViewModels.Application.StudentViewModels;

import source.Controllers.CampManager;
import source.Controllers.EnquiryManager;
import source.Database.ApplicationContext;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Registration.CampAttendee;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.StudentOperationsView;
import source.Entity.Camp;

/**
 * The StudentOperationsViewModel holds all the logic and necessary UI elements for student.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class StudentOperationsViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StudentOperationsView
     */
    StudentOperationsView studentOperationsView;
    Student student = (Student) ApplicationContext.user;
    CampManager campManager = ApplicationContext.getCampManager();
    EnquiryManager enquiryManager = new EnquiryManager();
    private Camp selectedCamp;
    /**
     * A default constructor.
     *
     * @see StudentOperationsView
     */
    public StudentOperationsViewModel(Camp selectedCamp) {
        studentOperationsView = new StudentOperationsView();
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
        PrettyPage.printCampDetails(selectedCamp);
        studentOperationsView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            choice = InputHandler.tryGetInt(1, 3, "Input choice: ", "Invalid choice!");
            switch (choice) {
                //Register Camp
                case 1: {
                    boolean registerResult = selectedCamp.registerAttendees(student);
                    break;
                }
                //Make Enquiries
                case 2: {
                    Enquiry enquiry = enquiryManager.getUserQuery(selectedCamp.getCampInfo().getName(), student.getName());
                    enquiryManager.addEnquiry(enquiry, student);
                    break;
                }
                //Apply Camp Committee
                case 3: {
                    boolean registerResult = selectedCamp.registerCommittees(student);
                    break;
                }
                //Back
                case 4: {
                    viewManager.returnToPreviousView();
                    break;
                }
            }
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