package source.Views.Application.StaffView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The StaffInChargeOperationsView class contains the necessary UI elements to display what the staff can do when handling operations
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class StaffInChargeOperationsView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Enquiries"),
                new Option("2", "View Suggestions"),
                new Option("3", "Edit Camp"),
                new Option("4", "Delete Camp"),
                new Option("5", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
