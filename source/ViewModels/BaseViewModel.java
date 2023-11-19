package source.ViewModels;

/**
 * An abstract class that all view models should inherit from for view model functionalities
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public abstract class BaseViewModel implements IViewModel {
    /**
     * The view manager reference that should be passed in through the constructor.
     */
    protected ViewManager viewManager;
    /**
     * A flag to determine if the console output is flushed on cleanup
     */
    private boolean flushOnExit;

    /**
     * A default constructor.
     */
    public BaseViewModel() {
        this.viewManager = null;
        this.flushOnExit = true;
    }

    /**
     * An overloaded constructor that initializes the view manager and whether to flush on exit.
     * NOTE: THe view manager reference is added on init
     *
     * @param flushOnExit whether to flush the console output on exit.
     * @see ViewManager
     */
    public BaseViewModel(boolean flushOnExit) {
        this.flushOnExit = flushOnExit;
    }

    /**
     * A base implementation of passing by refernece the view manager
     * NOTE: THe view manager reference is added on init
     *
     * @see ViewManager
     */
    @Override
    public void init(ViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
