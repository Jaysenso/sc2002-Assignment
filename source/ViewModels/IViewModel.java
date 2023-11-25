package source.ViewModels;

/**
 * The IViewModel interface abstracts the functions and provides a state machine like structure
 * for a view manager to carry out state transitions
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public interface IViewModel {
    /**
     * To initialize the view manager
     */
    void init(ViewManager viewManager);

    /**
     * Input handling in the view model
     */
    void handleInputs();

    /**
     * Do any cleanup in view model
     */
    void cleanup();
}
