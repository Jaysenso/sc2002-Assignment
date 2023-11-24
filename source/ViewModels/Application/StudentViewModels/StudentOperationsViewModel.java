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
     * The StudentOperationsView object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StudentOperationsView
     */
    StudentOperationsView studentOperationsView;

    /**
     * student is a downcasted object of user
     *
     */
    Student student = (Student) ApplicationContext.user;

    /**
     * The Camp Manager object serves as a DB and abstracts the relevant methods to read/write camp list
     *
     * @see CampManager
     */
    CampManager campManager = ApplicationContext.getCampManager();

    /**
     * The enquiryManager object serves as an abstraction for all the relevant enquiry methods
     *
     * @see EnquiryManager
     */
    EnquiryManager enquiryManager = new EnquiryManager();

    /**
     * The selectedCamp object stores the camp that the student selects
     *
     * @see Camp
     */
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
            choice = InputHandler.tryGetInt(1, 4, "Input choice: ", "Invalid choice!");
            switch (choice) {
                //Register Camp
                case 1: {
                    boolean registerResult = selectedCamp.registerAttendees(student);
                    campManager.updateCamp(selectedCamp);
                    PrettyPage.printCampDetails(selectedCamp);
                    studentOperationsView.display();
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
                    if(registerResult) {
                        campManager.updateCamp(selectedCamp);
                        viewManager.changeView(new CampCommitteeViewModel(selectedCamp));
                    }
                    else {
                        PrettyPage.printCampDetails(selectedCamp);
                        studentOperationsView.display();
                        handleInputs();
                    }
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
