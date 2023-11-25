package source.Views.Application.AppViews;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The LoginView class provides the implementation for the login UI
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class LoginView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Student"),
                new Option("2", "Staff"),
                new Option("3", "Go back")
        };
        PrettyPage.printTitle("Welcome to CAMS!", 3);
        PrettyPage.printLinesWithHeader(options, "Choose how you want to login");
    }
}
