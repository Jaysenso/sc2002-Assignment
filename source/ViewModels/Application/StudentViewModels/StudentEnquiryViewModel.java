package source.ViewModels.Application.StudentViewModels;

import source.Controllers.EnquiryManager;
import source.Database.App;
import source.EnquiryOperations.DeleteStudentEnquiry;
import source.EnquiryOperations.GetEnquiries;
import source.EnquiryOperations.UpdateEnquiry;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.StudentEnquiryView;

import java.util.ArrayList;

/**
 * The StudentEnquiryViewModel holds all the logic and necessary UI for read/write the student 's enquiries
 *
 * @author Ho Jian Feng
 * @version 1.0
 * @since 11/17/2023
 */
public class StudentEnquiryViewModel extends BaseViewModel implements IViewModel {
    /**
     * The studentEnquiryView object shows the UI for the student's enquiries and its relevant options
     *
     * @see StudentEnquiryView
     */
    private final StudentEnquiryView studentEnquiryView;

    /**
     * The enquiryManager object serves as an abstraction for all the relevant enquiry methods
     *
     * @see EnquiryManager
     */
    private final EnquiryManager enquiryManager;

    /**
     * Student is a downcast object of user
     */
    private final Student student = (Student) App.getUser();

    /**
     * enquiries arraylist stores a reference of all the enquiries the student has made
     *
     * @see Enquiry
     */
    private ArrayList<Enquiry> enquiries;

    /**
     * A default constructor.
     */
    public StudentEnquiryViewModel() {
        super();
        studentEnquiryView = new StudentEnquiryView();
        enquiryManager = App.getEnquiryManager();
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
        while (true) {
            enquiries = student.getEnquiries();
            //Handle the null text check
            if (enquiries.isEmpty()) {
                PrettyPage.printTitle("You do not have any enquiries!", 1);
                PrettyPage.printLineWithHeader(new Option("1", "Back"), "Choose your option");
                InputHandler.tryGetInt(1, 1, "Input choice: ", "Invalid choice!");
                return;
            } else {
                PrettyPage.printEnquiries(enquiries);
            }
            studentEnquiryView.display();
            int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    viewEnquiry();
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
     * A function to view enquiry
     */
    public void viewEnquiry() {
        //Get the input
        int index = InputHandler.tryGetInt(1, enquiries.size(), "Select Enquiry: ", "Enquiry not found!");
        Enquiry enquiry = enquiries.get(index - 1);
        while (true) {
            Option[] options = {
                    new Option("1", "Edit Enquiry"),
                    new Option("2", "Delete Enquiry"),
                    new Option("3", "Back"),
            };
            PrettyPage.printEnquiry(enquiries.get(index - 1));
            PrettyPage.printLinesWithHeader(options, "Choose your option");
            int option = InputHandler.tryGetInt(1, 3, "Input choice: ", "Enquiry not found!");

            switch (option) {
                case 1: {
                    if (enquiry.getProcessed()) {
                        PrettyPage.printError("You cannot edit the enquiry anymore after it was processed");
                        break;
                    }
                    System.out.print("Enter new title: ");
                    String newTitle = InputHandler.tryGetString();
                    System.out.print("Enter new content: ");
                    String newContent = InputHandler.tryGetString();
                    //Brute force the enquiry to make sure it saves
                    //Forcefully handle ID changes so that the match is found in the db
                    GetEnquiries getEnquiries = new GetEnquiries(enquiryManager);
                    enquiryManager.operate(getEnquiries);
                    for (Enquiry e : getEnquiries.getEnquiries()) {
                        if (e.getTitle().equals(enquiry.getTitle()) && e.getContent().equals(enquiry.getContent())) {
                            e.setTitle(newTitle);
                            e.setContent(newContent);
                            break;
                        }
                    }
                    enquiry.setTitle(newTitle);
                    enquiry.setContent(newContent);
                    //Then update the
                    enquiryManager.operate(new UpdateEnquiry(enquiry, enquiryManager));
                    break;
                }
                case 2: {
                    if (enquiry.getProcessed()) {
                        PrettyPage.printError("You cannot delete the enquiry anymore after it was processed");
                        break;
                    }
                    enquiryManager.operate(new DeleteStudentEnquiry(enquiry, student, enquiryManager));
                    return;
                }
                case 3: {
                    return;
                }
            }
        }
    }
}
