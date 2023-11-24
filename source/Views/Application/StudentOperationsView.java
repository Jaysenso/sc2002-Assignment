package source.Views.Application;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StudentOperationsView implements IView {
    public void display() {
        Option[] options = {
                new Option("1", "Back"), //will call another view for all camps or created camp
                new Option("2", "View Enquiries"),
                new Option("3", "Register Camp"),
                new Option("4", "Apply Camp Committee"),
                new Option("5", "Make Enquiry"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
