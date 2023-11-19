package source.ViewModels;

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
     * A default constructor that initialises the current view to be null.
     */
    public ViewManager() {
        currentView = null;
    }

    /**
     * An overloaded constructor that sets the initial view to be run
     * NOTE that run is not called yet!
     *
     * @param viewModel the view model to be run
     */
    public ViewManager(IViewModel viewModel) {
        this.currentView = viewModel;
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
        //Update with new view
        currentView = newView;
        //Initialize the new view
        currentView.init(this);
    }

    /**
     * Runs the current view model stored in the instance ONLY if its not already running
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
