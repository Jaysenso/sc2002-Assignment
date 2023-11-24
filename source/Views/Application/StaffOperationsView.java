package source.Views.Application;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StaffOperationsView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Back"), //will call another view for all camps or created camp
                new Option("2", "View Enquiries"),
                new Option("3", "View Suggestions"),
                new Option("4", "Edit Camp"),
                new Option("5", "Delete Camp"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
