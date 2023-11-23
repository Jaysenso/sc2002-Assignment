package source.ViewModels.Application;

import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.HomeView;

public class HomeViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see HomeView
     */
    HomeView homeView;
    private boolean isStaff;

    /**
     * A default constructor.
     *
     * @see HomeView
     */
    public HomeViewModel(boolean userGroup) {
        this.isStaff = userGroup;
        homeView = new HomeView();
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
        PrettyPage.printTitle("Welcome back! (User)", 1);
        homeView.display();
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
                    if(isStaff){
                        viewManager.changeView(new StaffCampViewModel());
                    }
                    else{
                        viewManager.changeView(new StudentCampViewModel());
                    }
                    break;
                }
                case 2: {
                    System.out.println("case 2");
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
