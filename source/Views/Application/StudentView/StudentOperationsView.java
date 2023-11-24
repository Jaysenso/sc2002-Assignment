package source.Views.Application.StudentView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StudentOperationsView implements IView {
    public void display() {
        Option[] options = {
                new Option("1", "Register Camp"),
                new Option("2", "Make Enquiry"),
                new Option("3", "Apply Camp Committee"),
                new Option("4", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
