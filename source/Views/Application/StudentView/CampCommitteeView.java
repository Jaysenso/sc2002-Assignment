package source.Views.Application.StudentView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The CampCommitteeView class contains the necessary UI elements to display what the student can do when hes a camp committee
 * of that camp
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class CampCommitteeView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Enquiries"),
                new Option("2", "View my Suggestions"),
                new Option("3", "Generate Camp Report"),
                new Option("4", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
