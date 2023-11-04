package source.Views;

import source.PrettyPage;

public class MainView implements IView{
    @Override
    public void display() {
        String[] options = {"1", "2", "3", "4"};
        String[] description = {"Dummy Option 1",
                "Dummy Option 2",
                "Dummy Option 3",
                "Dummy Option 4"};

        PrettyPage.printAppTitle("CAMS Application System", 80, 3);
        PrettyPage.printLinesWithDescription(options, description);;
    }
}
