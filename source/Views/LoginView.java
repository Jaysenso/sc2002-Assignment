package source.Views;

import source.PrettyPage;

public class LoginView implements IView{
    @Override
    public void display() {
        String[] options = {"1", "2"};
        String[] description = {"Student",
                "Staff"};

        PrettyPage.printAppTitle("Login", 80, 1);
        PrettyPage.printLinesWithDescription(options, description);;
    }
}
