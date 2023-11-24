package source.Views.Application.StudentView;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;


public class CampCommitteeView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Enquiries"),
                new Option("2", "View Suggestions"),
                new Option("3", "Generate Camp Report"),
                new Option("4", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
