package source.Views.Application;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StaffCampView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Select Camp"),
                new Option("2", "Create Camp"),
                new Option("3", "Filter Camp List"),
                new Option("4", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
