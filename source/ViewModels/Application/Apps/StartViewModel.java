package source.ViewModels.Application.Apps;

import source.Database.App;
import source.Utility.InputHandler;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.AppViews.StartView;

/**
 * The StartViewModel holds all the logic and necessary Ui elements for a successful login.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class StartViewModel extends BaseViewModel implements IViewModel {
    /**
     * The start view object that contains the UI for when the application starts.
     *
     * @see StartView
     */
    StartView startView;

    /**
     * A default constructor.
     *
     * @see StartView
     */
    public StartViewModel() {
        startView = new StartView();
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
        startView.display();
        //Reset the application context to be null
        App.setUser(null);
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
        switch (choice) {
            case 1: {
                viewManager.changeView(new LoginViewModel());
                break;
            }
            case 2: {
                System.exit(0);
                break;
            }
            default: {
                break;
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

}
