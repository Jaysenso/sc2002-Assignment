package source.Views.Application;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class LoginView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Student"),
                new Option("2", "Staff"),
                new Option("3", "Go back")
        };
        PrettyPage.printTitle("Welcome to CAMS!", 3);
        PrettyPage.printLinesWithHeader(options, "Choose how you want to login");
    }
}
