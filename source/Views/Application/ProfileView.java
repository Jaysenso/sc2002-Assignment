package source.Views.Application;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The ProfileView class contains the necessary UI elements to display the profile
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class ProfileView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Change Password"), //will call another view for all camps or created camp
                new Option("2", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
