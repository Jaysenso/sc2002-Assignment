package source;

import source.ViewModels.LoginViewModel;
import source.ViewModels.ViewManager;

public class CampApplication {
    public static void main(String[] args) {
        ViewManager manager = new ViewManager(new LoginViewModel());
        manager.run();


    }
}
