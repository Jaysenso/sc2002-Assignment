package source;

import source.ViewModels.Application.StartViewModel;
import source.ViewModels.ViewManager;

public class CampApplication {
    public static void main(String[] args) {
        ViewManager manager = new ViewManager(new StartViewModel());
        manager.run();
    }
}
