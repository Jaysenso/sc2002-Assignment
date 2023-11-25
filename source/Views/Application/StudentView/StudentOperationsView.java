package source.Views.Application.StudentView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The StudentOperationsView class contains the necessary UI elements to display what the student can do when viewing a camp
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class StudentOperationsView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Register Camp"),
                new Option("2", "Make Enquiry"),
                new Option("3", "Apply Camp Committee"),
                new Option("4", "Withdraw from Camp"),
                new Option("5", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
