package source.ViewModels.Application.StudentViewModels;

import source.CampOperations.RegisterAttendees;
import source.CampOperations.RegisterCommittees;
import source.CampOperations.UpdateCamp;
import source.CampOperations.WithdrawAttendees;
import source.Controllers.CampManager;
import source.Controllers.EnquiryManager;
import source.Controllers.StudentManager;
import source.Database.App;
import source.Database.DatabaseQuery;
import source.EnquiryOperations.AddEnquiry;
import source.EnquiryOperations.GetUserQuery;
import source.Entity.Camp;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.StudentOperationsView;

import java.util.ArrayList;

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
    private final StudentOperationsView studentOperationsView;

    /**
     * Student is a downcasted object of user
     */
    private final Student student = (Student) App.getUser();

    /**
     * The Camp Manager object serves as a DB and abstracts the relevant methods to read/write camp list
     *
     * @see CampManager
     */
    private final CampManager campManager;

    /**
     * The enquiryManager object serves as an abstraction for all the relevant enquiry methods
     *
     * @see EnquiryManager
     */
    private final EnquiryManager enquiryManager;

    /**
     * The selectedCamp object stores the camp that the student selects
     *
     * @see Camp
     */
    private final Camp selectedCamp;

    /**
     * The student manager referenced
     *
     * @see StudentManager
     */
    private final StudentManager studentManager;

    /**
     * A default constructor.
     *
     * @see StudentOperationsView
     */
    public StudentOperationsViewModel(Camp selectedCamp) {
        studentOperationsView = new StudentOperationsView();
        this.studentManager = App.getStudentManager();
        this.campManager = App.getCampManager();
        this.enquiryManager = new EnquiryManager();
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
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            PrettyPage.printCampDetails(selectedCamp);
            campManager.getCampDao().loadContext();
            printApplicableEnquiries();
            studentOperationsView.display();
            choice = InputHandler.tryGetInt(1, 5, "Input choice: ", "Invalid choice!");
            switch (choice) {
                //Register Camp
                case 1: {
                    boolean registerResult = campManager.operate(new RegisterAttendees(student, selectedCamp));
                    if (registerResult) {
                        campManager.operate(new UpdateCamp(selectedCamp, campManager));
                        studentManager.updateStudent(student);
                    }
                    break;
                }
                //Make Enquiries
                case 2: {
                    GetUserQuery getUserQuery = new GetUserQuery(selectedCamp.getCampInfo().getName(), student.getName());
                    enquiryManager.operate(getUserQuery);
                    Enquiry enquiry = getUserQuery.getEnquiry();
                    enquiryManager.operate(new AddEnquiry(student, enquiry, enquiryManager));
                    //Update camp enquiry
                    selectedCamp.addEnquiry(enquiry);
                    campManager.operate(new UpdateCamp(selectedCamp,campManager));
                    PrettyPage.printLine(new Option("Success", "You have successfully sent your enquiry!"));
                    break;
                }
                //Apply Camp Committee
                case 3: {
                    boolean registerResult = campManager.operate(new RegisterCommittees(student, selectedCamp));

                    if (registerResult) {
                        campManager.operate(new UpdateCamp(selectedCamp, campManager));
                        studentManager.updateStudent(student);
                        viewManager.returnToPreviousView();
                    }

//                    PrettyPage.printCampDetails(selectedCamp);
//                    printApplicableEnquiries();
//                    studentOperationsView.display();
//                    handleInputs();
                    break;
                }
                //Withdraw from Camp
                case 4: {
                    boolean withdrawResult = campManager.operate(new WithdrawAttendees(student, selectedCamp, campManager));
                    campManager.operate(new UpdateCamp(selectedCamp, campManager));
                    studentManager.updateStudent(student);
                    break;
                }
                //Back
                case 5: {
                    viewManager.returnToPreviousView();
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

    /**
     * Gets a list of applicable entries to the selected camp based on the user
     */
    public ArrayList<Enquiry> getApplicableEnquiries() {
        ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
        for (Enquiry e : enquiryManager.getEnquiryDao().getEnquiries()) {
            if (e.getCreatedBy().equals(student.getName()) && e.getCampName().equals(selectedCamp.getCampInfo().getName()))
                enquiries.add(e);
        }
        return enquiries;
    }

    /**
     * Acquires the applicable enquiries and prints it out
     */
    public void printApplicableEnquiries() {
        ArrayList<Enquiry> enquiries = getApplicableEnquiries();
        if (!enquiries.isEmpty()) {
            PrettyPage.printEnquiries(enquiries);
        }
    }
}
