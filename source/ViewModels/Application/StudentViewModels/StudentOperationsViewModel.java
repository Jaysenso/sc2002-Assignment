package source.ViewModels.Application.StudentViewModels;

import source.Camp.RegisterAttendees;
import source.Camp.RegisterCommittees;
import source.Camp.UpdateCamp;
import source.Camp.WithdrawAttendees;
import source.Controllers.CampManager;
import source.Controllers.EnquiryManager;
import source.Controllers.StudentManager;
import source.Database.App;
import source.Database.DatabaseQuery;
import source.Entity.Camp;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.CampCommitteeView;
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
        PrettyPage.printCampDetails(selectedCamp);
        printApplicableEnquiries();
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
            choice = InputHandler.tryGetInt(1, 5, "Input choice: ", "Invalid choice!");
            switch (choice) {
                //Register Camp
                case 1: {
                    boolean registerResult = campManager.useCampService(new RegisterAttendees(student, selectedCamp));
                    if(registerResult) {
                        campManager.useCampService(new UpdateCamp(selectedCamp, campManager));
                        studentManager.updateStudent(student);
                    }
                    PrettyPage.printCampDetails(selectedCamp);
                    studentOperationsView.display();
                    break;
                }
                //Make Enquiries
                case 2: {
                    Enquiry enquiry = enquiryManager.getUserQuery(selectedCamp.getCampInfo().getName(), student.getName());
                    enquiryManager.addEnquiry(enquiry, student);
                    PrettyPage.printLine(new Option("Success", "You have successfully sent your enquiry!"));
                    //reprint the camp and display the operations
                    PrettyPage.printCampDetails(selectedCamp);
                    printApplicableEnquiries();
                    studentOperationsView.display();
                    break;
                }
                //Apply Camp Committee
                case 3: {
                    boolean registerResult = campManager.useCampService(new RegisterCommittees(student,selectedCamp));

                    if(registerResult) {
                        campManager.useCampService(new UpdateCamp(selectedCamp, campManager));
                        studentManager.updateStudent(student);
                        viewManager.returnToPreviousView();
                    }

                    PrettyPage.printCampDetails(selectedCamp);
                    printApplicableEnquiries();
                    studentOperationsView.display();
                    handleInputs();
                    break;
                }
                //Withdraw from Camp
                case 4: {
                    boolean withdrawResult = campManager.useCampService(new WithdrawAttendees(student, selectedCamp, campManager));
                    campManager.useCampService(new UpdateCamp(selectedCamp, campManager));
                    studentManager.updateStudent(student);
                    PrettyPage.printCampDetails(selectedCamp);
                    studentOperationsView.display();

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
        return enquiryManager.readEnquiries(
                new DatabaseQuery[]{
                        new DatabaseQuery(student.getName(), "created_by"),
                        new DatabaseQuery(selectedCamp.getCampInfo().getName(), "camp_name")
                }
        );
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
