package source.Views.Application;

import source.Entity.Staff;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The StartView class contains the necessary UI elements to display when the app has started.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class StartView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Log in"), //will call another view for all camps or created camp
                new Option("2", "Exit Program"),
        };
        PrettyPage.printTitle("Welcome to CAMS!", 3);
        PrettyPage.printLinesWithHeader(options, "Choose your option.");
    }
}
