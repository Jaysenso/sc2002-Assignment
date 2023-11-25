package source.Views.Application.StaffView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The StaffCampView class contains the necessary UI elements to display what the staff can see in the camp view
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class StaffCampView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Select Camp"),
                new Option("2", "Create Camp"),
                new Option("3", "View Created Camps"),
                new Option("4", "Sort Camp List"),
                new Option("5", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
