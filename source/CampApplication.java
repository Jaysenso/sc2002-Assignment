package source;

import source.Controllers.CampManager;
import source.Database.ApplicationContext;
import source.ViewModels.Application.Apps.HomeViewModel;
import source.ViewModels.Application.Apps.StartViewModel;
import source.ViewModels.ViewManager;

public class CampApplication {
    public static void main(String[] args) {
//        ViewManager manager = new ViewManager(new StartViewModel());
        ApplicationContext.initialize();
        ViewManager manager = new ViewManager(new StartViewModel());
        manager.run();
    }
}
