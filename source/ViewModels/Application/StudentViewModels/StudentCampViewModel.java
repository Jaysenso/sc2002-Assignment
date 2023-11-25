package source.ViewModels.Application.StudentViewModels;

import source.Controllers.CampManager;
import source.Controllers.Filters.CampFilterByStudent;
import source.Controllers.Filters.FilterManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.Application.Apps.SortViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.StudentCampView;

import java.util.ArrayList;

/**
 * The StudentCampViewModel holds all the logic and necessary UI elements for student camp view.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class StudentCampViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StudentCampView
     */
    private final StudentCampView studentCampView;
    /**
     * The down casted object of the user.
     *
     * @see StudentCampView
     */
    Student student = (Student) App.getUser();
    /**
     * The camp manager reference
     */
    private final CampManager campManager;
    /**
     * The sorted camps that are displayed in this view model
     */
    private final ArrayList<Camp> sortedCamps;
    /**
     * The filter manager that does filtering of objects
     */
    private final FilterManager filterManager;

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
        filterManager = new FilterManager();
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
            //Show the filtered version
            ArrayList<Camp> filteredCamps = filterManager.filter(new CampFilterByStudent(), sortedCamps);
            PrettyPage.printCamps(filteredCamps);
            //Display the student camp view
            studentCampView.display();
            choice = InputHandler.tryGetInt(1, 5, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    if (filteredCamps.isEmpty()) {
                        PrettyPage.printError("There are no camps to view!");
                    } else {
                        int index = InputHandler.tryGetInt(1, filteredCamps.size(), "Input camp choice: ", "Invalid Camp Selected");
                        Camp selectedCamp = filteredCamps.get(index - 1);

                        if (student.getIsCampCommittee() != null && student.getIsCampCommittee().equals(selectedCamp)) {
                            viewManager.changeView(new CampCommitteeViewModel(selectedCamp));
                        } else {
                            viewManager.changeView(new StudentOperationsViewModel(selectedCamp));
                        }
                    }
                    break;
                }
                case 2: {
                    viewManager.changeView(new SortViewModel(sortedCamps));
                    break;
                }
                case 3: {
                    viewRegisteredCamps();
                    break;
                }
                case 4: {
                    viewManager.changeView(new StudentEnquiryViewModel());
                    break;
                }
                case 5: {
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

    /**
     * A function to handle the sub logic of viewing registered camps
     */
    public void viewRegisteredCamps() {
        ArrayList<Camp> registeredCamps = student.getRegisteredCamps();
        PrettyPage.printCamps(registeredCamps);
        Option[] options = {
                new Option("1", "Select Camp"),
                new Option("2", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
        int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
        switch (choice) {
            case 1: {
                if (student.getRegisteredCamps().isEmpty()) {
                    PrettyPage.printError("There are no camps to view!");
                } else {
                    int index = InputHandler.tryGetInt(1, registeredCamps.size(), "Input camp choice: ", "Invalid Camp Selected");
                    Camp selectedCamp = registeredCamps.get(index - 1);
                    if (student.getIsCampCommittee() != null && student.getIsCampCommittee().equals(selectedCamp)) {
                        viewManager.changeView(new CampCommitteeViewModel(selectedCamp));
                    } else {
                        viewManager.changeView(new StudentOperationsViewModel(selectedCamp));
                    }
                }
                break;
            }
            case 2: {
                break;
            }
        }
    }
}
