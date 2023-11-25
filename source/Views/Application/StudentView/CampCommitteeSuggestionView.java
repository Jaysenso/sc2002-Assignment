package source.Views.Application.StudentView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The CampCommitteeSuggestionView class contains the necessary UI elements to display camp committee members suggestions
 *
 * @author Marcus
 * @version 1.0
 * @since 11/17/2023
 */
public class CampCommitteeSuggestionView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Suggestion"),
                new Option("2", "Create Suggestion"),
                new Option("3", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option.");
    }
}
