package source.Views.Application;

import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StaffView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Camps"), //will call another view for all camps or created camp
                new Option("2", "View Enquiries"),
                new Option("3", "Create Camp"),
                new Option("4", "Edit Camp"),
                new Option("5", "Delete Camp"),
                new Option("6", "Logout"),
        };
        PrettyPage.printTitle("Welcome back! (Staff)", 1);
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
