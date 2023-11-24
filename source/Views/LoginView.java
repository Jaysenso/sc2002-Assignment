package source.Views;

import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;

public class LoginView implements IView{
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Student"),
                new Option("2", "Staff"),
        };
        PrettyPage.printTitle("Welcome to CAMS!", 1);
        PrettyPage.printLinesWithHeader(options, "Choose how you want to login");
        InputHandler.tryGetInt(1, options.length, "Input choice: ", "Invalid choice!");
    }
}
