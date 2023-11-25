package source.Views.Application.AppViews;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class ReplyEnquiryView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Enquiry"),
                new Option("2", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}