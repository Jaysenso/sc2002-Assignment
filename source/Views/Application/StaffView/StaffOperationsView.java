package source.Views.Application.StaffView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The StaffOperationsView class contains the necessary UI elements to display what the staff can do when he's not in charge
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class StaffOperationsView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Enquiries"),
                new Option("2", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Go Back");
    }
}
