package source.Views.Application.StudentView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The StudentEnquiryView class contains the necessary UI elements to display what the student can do when viewing enquiries
 * of that camp
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class StudentEnquiryView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Enquiry"),
                new Option("2", "Delete Enquiry"),
                new Option("3", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
