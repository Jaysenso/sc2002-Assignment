package source.ViewModels.Application.StudentViewModels;

import source.Database.App;
import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.CampCommitteeView;
import source.Views.Application.StudentView.StudentOperationsView;

public class CampCommitteeViewModel extends BaseViewModel implements IViewModel {

    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StudentOperationsView
     */
    CampCommitteeView campCommitteeView;
    private Student student = (Student) App.getUser();
    private Camp selectedCamp;
    /**
     * A default constructor.
     * *
     * @see source.Views.Application.StudentView.StudentOperationsView
     */
    public CampCommitteeViewModel(Camp selectedCamp) {
        campCommitteeView = new CampCommitteeView();
        this.selectedCamp = selectedCamp;
    }

    /**
     * A function called by ViewManager to pass by reference and conduct any initial setup
     * of the view model.
     *
     * @param viewManager the view manager reference.
     * @see source.ViewModels.ViewManager
     */
    @Override
    public void init(ViewManager viewManager) {
        super.init(viewManager);
        PrettyPage.printCampDetails(selectedCamp);
        campCommitteeView.display();
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
                //View Enquiries
                case 1: {
                    break;
                }
                //View Suggestions
                case 2: {
                    break;
                }
                //Generate Camp Report
                case 3: {
                    break;
                }
                //Back
                case 4: {
                    viewManager.returnToPreviousView();
                    break;
                }
            }
        } while (choice != 1);
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
