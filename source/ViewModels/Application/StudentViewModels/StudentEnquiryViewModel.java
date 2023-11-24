package source.ViewModels.Application.StudentViewModels;

import source.Controllers.EnquiryManager;
import source.Database.ApplicationContext;
import source.Entity.Enquiry;
import source.Entity.Student;
import source.Entity.User;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Utility.StringsUtility;
import source.ViewModels.Application.Apps.LoginViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.ChangePasswordView;
import source.Views.Application.ProfileView;
import source.Views.Application.StudentView.StudentEnquiryView;

import java.util.ArrayList;

/**
 * The ProfileViewModel holds all the logic and necessary UI for displaying the profile
 *
 * @author Marcus
 * @version 1.0
 * @since 11/17/2023
 */
public class StudentEnquiryViewModel extends BaseViewModel implements IViewModel {
    /**
     * The profile student view object shows the UI for the user profile and its options
     *
     * @see StudentEnquiryView
     */
    private StudentEnquiryView studentEnquiryView;
    EnquiryManager enquiryManager = new EnquiryManager();
    Student student = (Student) ApplicationContext.user;
    ArrayList<Enquiry> enquiries;

    /**
     * A default constructor.
     *
     *
     */
    public StudentEnquiryViewModel() {
        super();
        studentEnquiryView = new StudentEnquiryView();
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
        while(true){
            enquiries = enquiryManager.getStudentEnquiries(student.getName());
            PrettyPage.printEnquiries(enquiries);
            studentEnquiryView.display();
            int choice = InputHandler.tryGetInt(1, 3, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    //view
                    viewEnquiry();
                    break;
                }
                case 3: {
                    //delete
                    int index = InputHandler.tryGetInt(1, enquiries.size(),"Enter Enquiry No.","Invalid Enquiry");
                    enquiryManager.deleteStudentEnquiry(enquiries.get(index));
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

    public void viewEnquiry(){
        int index = InputHandler.tryGetInt(1, enquiries.size(), "Select Enquiry: ", "Enquiry not found");
        boolean isLooping = true;
        while(isLooping){
            Option[] options = {
                    new Option("1", "Edit Enquiry"),
                    new Option("2", "Delete Enquiry"),
                    new Option("3", "Back"),
            };
            PrettyPage.printEnquiry(enquiries.get(index-1));
            PrettyPage.printLinesWithHeader(options, "Choose your option");
            int option = InputHandler.tryGetInt(1, enquiries.size(), "Select Enquiry: ", "Enquiry not found");
            switch (option){
                case 1:{
                    System.out.println("Enter new Title");
                    String newTitle = InputHandler.getString();
                    System.out.println("Enter new Content");
                    String newContent = InputHandler.getString();
                    enquiries.get(index-1).setTitle(newTitle);
                    enquiries.get(index-1).setContent(newContent);
                    enquiryManager.editStudentEnquiry(enquiries.get(index-1));
                    break;
                }
                case 2:{
                    enquiryManager.deleteStudentEnquiry(enquiries.get(index-1));
                    isLooping = false;
                    break;
                }
                case 3:{
                    isLooping = false;
                    break;
                }
            }
        }
    }
}
