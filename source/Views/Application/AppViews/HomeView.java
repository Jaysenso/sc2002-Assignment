package source.Views.Application.AppViews;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class HomeView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View All Camps"),
                new Option("2", "View My Profile"),
                new Option("3", "Logout"),
        };
        PrettyPage.printLinesWithHeader(options, "Choose your option");
    }
}
