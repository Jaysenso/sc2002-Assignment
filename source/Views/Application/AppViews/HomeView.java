package source.Views.Application.AppViews;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The HomeView class provides the implementation for the home UI
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public final class HomeView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View All Camps"),
                new Option("2", "View My Profile"),
                new Option("3", "Logout"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
