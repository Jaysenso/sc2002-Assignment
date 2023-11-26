package source.Views.Application;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The Filter class contains the necessary UI elements and filtering techniques applied
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class FilterView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Filter by camp name"),
                new Option("2", "Filter by attendee name"),
                new Option("3", "Filter by camp committee"),
                new Option("4", "Filter by camp location"),
                new Option("5", "Generate for all created camps"),
                new Option("6", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
