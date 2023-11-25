package source.Views.Application.StudentView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The StudentCampView class contains the necessary UI elements to display what the student can do when viewing camps
 * of that camp
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class StudentCampView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Select Camp"),
                new Option("2", "Sort Camp List"),
                new Option("3", "View My Registered Camps"),
                new Option("4", "View My Enquiries"),
                new Option("5", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
