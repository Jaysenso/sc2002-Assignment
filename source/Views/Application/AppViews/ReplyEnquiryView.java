package source.Views.Application.AppViews;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The ReplyEnquiryView class provides the implementation for the reply UI
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class ReplyEnquiryView implements IView {
    /**
     * Holds the implementation to display UI
     */
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Enquiry"),
                new Option("2", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}