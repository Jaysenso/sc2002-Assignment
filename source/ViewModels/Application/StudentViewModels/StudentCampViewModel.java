package source.ViewModels.Application.StudentViewModels;

import source.Controllers.CampManager;
import source.Controllers.Sorting.*;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.Application.Apps.SortViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.AppViews.SortView;
import source.Views.Application.StudentView.StudentCampView;

import java.util.ArrayList;

public class StudentCampViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StudentCampView
     */
    private StudentCampView studentCampView;
    private SortView sortView;
    Student student = (Student) App.getUser();
    private CampManager campManager;
    private ArrayList<Camp> sortedCamps;

    /**
     * A default constructor.
     *
     * @see StudentCampView
     */
    public StudentCampViewModel() {
        studentCampView = new StudentCampView();
        campManager = App.getCampManager();
        //Initially, the filtered camps are all the normal camps
        sortedCamps = campManager.getCamps();
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
        //Show the filtered version
        PrettyPage.printCamps(sortedCamps);
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            //Display the student camp view
            studentCampView.display();
            choice = InputHandler.tryGetInt(1, 4, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    if (campManager.getCamps().isEmpty()) {
                        PrettyPage.printError("There are no camps to view!");
                    } else {
                        int index = InputHandler.tryGetInt(1, campManager.getCamps().size(), "Input camp choice: ", "Invalid Camp Selected");
                        Camp selectedCamp = campManager.getCamps().get(index - 1);

                        if (selectedCamp.isCommittee(student)) {
                            viewManager.changeView(new CampCommitteeViewModel(selectedCamp));
                        } else {
                            viewManager.changeView(new StudentOperationsViewModel(selectedCamp));
                        }
                    }
                    break;
                }
                case 2: {
                    //Handle UI and inputs for sorting
                    viewManager.changeView(new SortViewModel(sortedCamps));
                    break;
                }
                case 3: {
                    viewManager.changeView(new StudentEnquiryViewModel());
                    break;
                }
                case 4: {
                    viewManager.returnToPreviousView();
                    break;
                }
            }
        } while (choice != 3);
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
