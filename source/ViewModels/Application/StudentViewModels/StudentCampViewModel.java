package source.ViewModels.Application.StudentViewModels;

import source.Controllers.CampManager;
import source.Controllers.FilterManager;
import source.Database.ApplicationContext;
import source.Entity.Camp;
import source.Utility.InputHandler;
import source.ViewModels.Application.Apps.FilterViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.StaffCampView;
import source.Views.Application.StudentView.StudentCampView;

public class StudentCampViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StaffCampView
     */
    StudentCampView studentCampView;
    private FilterManager filterManager = new FilterManager();
    private CampManager campManager;

    /**
     * A default constructor.
     *
     * @see StaffCampView
     */
    public StudentCampViewModel () {
        studentCampView = new StudentCampView();
        campManager = ApplicationContext.getCampManager();
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
        filterManager.viewAll(campManager.getFiltertype());
        studentCampView.display();
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
                case 1: {
                    int index = InputHandler.tryGetInt(1, campManager.getCamps().size(), "Input camp choice : ", "Invalid Camp");
                    Camp selectedCamp = campManager.getCamps().get(index - 1);
                    viewManager.changeView(new StudentOperationsViewModel(selectedCamp));
                    break;
                }
                case 2: {
                    viewManager.changeView(new FilterViewModel());
                    break;
                }
                case 3: {

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