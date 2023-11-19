package source.Views.Application;

import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class StudentView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View All Camps"),
                new Option("2", "View Registered Camps"),
                new Option("3", "Logout"),
        };
        PrettyPage.printTitle("Welcome back! (Student)", 1);
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
