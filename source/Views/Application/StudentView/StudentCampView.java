package source.Views.Application.StudentView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StudentCampView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Select Camp"),
                new Option("2", "Sort Camp List"),
                new Option("3", "View My Enquiries"),
                new Option("4", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
