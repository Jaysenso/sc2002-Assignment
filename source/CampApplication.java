package source;

import source.Controllers.CampManager;
import source.Database.ApplicationContext;
import source.ViewModels.Application.HomeViewModel;
import source.ViewModels.ViewManager;

public class CampApplication {
    public static void main(String[] args) {
//        ViewManager manager = new ViewManager(new StartViewModel());
        ApplicationContext.setCampManager(new CampManager());
        ViewManager manager = new ViewManager(new HomeViewModel(true));
        manager.run();
    }
}
