package source;

import source.Controllers.CamsApp;

/**
 * The CampApplication class is the starting class of this application.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/01/2023
 */
public class CampApplication {
    /**
     * The function that runs first when the application starts.
     */
    public static void main(String[] args) {
        CamsApp app = new CamsApp();
        app.run();

    }
}
