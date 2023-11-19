package source.ViewModels.Application;

import source.Utility.InputHandler;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView;
import source.Views.Application.StartView;
import source.Views.Application.StudentView;

/**
 * The StudentViewModel holds all the logic and necessary UI elements for student.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class StudentViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StudentView
     */
    StudentView studentView;

    /**
     * A default constructor.
     *
     * @see StudentView
     */
    public StudentViewModel() {
        studentView = new StudentView();
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
        studentView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
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
