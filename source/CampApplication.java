package source;

import source.ViewModels.MainViewModel;
import source.ViewModels.ViewManager;
import source.Views.MainView;

public class CampApplication {
    public static void main(String[] args) {

        ViewManager manager = new ViewManager(new MainViewModel());
        manager.run();
    }
}
