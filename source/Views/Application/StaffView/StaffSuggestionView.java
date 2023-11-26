package source.Views.Application.StaffView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The StaffSuggestionView class contains the necessary UI elements to display suggestions for staff
 *
 * @author Marcus
 * @version 1.0
 * @since 11/17/2023
 */
public class StaffSuggestionView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Suggestion"),
                new Option("2", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option.");
    }
}
