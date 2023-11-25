package source.ViewModels.Application.StudentViewModels;

import source.Controllers.CampManager;
import source.Controllers.FilterManager;
import source.Database.App;
import source.Database.DatabaseQuery;
import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.Application.Apps.FilterViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.StudentCampView;

import java.util.ArrayList;

public class StudentCampViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StudentCampView
     */
    StudentCampView studentCampView;
    Student student = (Student) App.getUser();
    private FilterManager filterManager = new FilterManager();
    private CampManager campManager;

    /**
     * A default constructor.
     *
     * @see StudentCampView
     */
    public StudentCampViewModel() {
        studentCampView = new StudentCampView();
        campManager = App.getCampManager();
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
        updateApplicableCamps();
        filterManager.showCamps(campManager.getCamps());
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            studentCampView.display();
            choice = InputHandler.tryGetInt(1, 4, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    if (campManager.getCamps().isEmpty()) {
                        PrettyPage.printError("There are no camps to view!");
                    } else {
                        int index = InputHandler.tryGetInt(1, campManager.getCamps().size(), "Input camp choice : ", "Invalid Camp");
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
                    viewManager.changeView(new FilterViewModel());
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


    /**
     * updates the applicable camps of the current logged-in user.
     */
    private void updateApplicableCamps() {
        ArrayList<Camp> registeredCamps = new ArrayList<>();
        ArrayList<Camp> campList = campManager.getCamps();
        for (Camp camp : campList) {
            for (Student attendee : camp.getAttendees()) {
                if (student == attendee) {
                    registeredCamps.add(camp);
                    break;
                }
            }
        }
        student.setRegisteredCamps(registeredCamps);
    }

}
