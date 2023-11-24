package source.Views.Application;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StaffOperationsView implements IView {
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
