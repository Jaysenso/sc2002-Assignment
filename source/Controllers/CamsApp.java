package source.Controllers;

import source.Database.App;
import source.ViewModels.Application.Apps.StartViewModel;
import source.ViewModels.ViewManager;

/**
 * The CamsApp abstracts the entire application into a simple run function.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class CamsApp {
    /**
     * The main function to be called to start the entire program.
     */
    public void run() {
        App.initialize();
        ViewManager manager = new ViewManager(new StartViewModel());
        manager.run();
    }
}
