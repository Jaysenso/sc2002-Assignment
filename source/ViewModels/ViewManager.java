package source.ViewModels;

import java.util.Stack;

/**
 * The ViewManager class handles the movement between view models like a state machine, ensuring easy transition
 * between viewmodels with error handling.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class ViewManager {
    /**
     * Whether the view manager is running.
     */
    private boolean isRunning;
    /**
     * The current view model being run by the view manager.
     */
    private IViewModel currentView;

    /**
     * A stack to allow to go back to previous options without the need to store references
     */
    private Stack<IViewModel> viewModelStack;

    /**
     * A default constructor that initialises the current view to be null.
     */
    public ViewManager() {
        currentView = null;
        viewModelStack = new Stack<>();
    }

    /**
     * An overloaded constructor that sets the initial view to be run
     * NOTE that run is not called yet!
     *
     * @param viewModel the view model to be run
     */
    public ViewManager(IViewModel viewModel) {
        this.currentView = viewModel;
        //Initialize our stack
        viewModelStack = new Stack<>();
    }

    /**
     * Changes the current view to the new view. Calls cleanup on the current view and initialises the new view
     *
     * @param newView the new view model to be run
     */
    public void changeView(IViewModel newView) {
        if (newView == null)
            return;

        //Cleanup current view
        currentView.cleanup();
        //Add current view into the stack
        viewModelStack.push(currentView);
        //Update with new view
        currentView = newView;
        //Initialize the new view
        currentView.init(this);
    }

    public void returnToPreviousView() {
        //if view manager is not running, there is no previous view!
        if (!isRunning)
            return;
        //If our view model stack is not empty
        if (!viewModelStack.empty()) {
            //Cleanup current view
            currentView.cleanup();
            //Pop out the previous view and update with new view
            currentView = viewModelStack.pop();
            //Initialize the new view
            currentView.init(this);
        }
    }

    /**
     * Runs the current view model stored in the instance ONLY if it's not already running
     */
    public void run() {
        //Prevent re-running view managers per instance
        if (currentView == null || isRunning)
            return;
        isRunning = true;
        System.out.println("Starting view: " + currentView.getClass().getSimpleName());
        //Init the new view with a reference to this view manager via dependency injection
        currentView.init(this);
    }
}
