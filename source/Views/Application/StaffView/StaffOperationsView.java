package source.Views.Application.StaffView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StaffOperationsView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Enquiries"),
                new Option("2", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Go Back");
    }
}
