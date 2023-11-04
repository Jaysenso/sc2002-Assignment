package source.ViewModels;

import source.Views.MainView;

public class MainViewModel implements IViewModel{

    //Store all relevant views here
    MainView view;
    @Override
    public void init(ViewManager viewManager) {
        view = new MainView();
        view.display();
        //The standard on changing views and running different logic in different view models
        viewManager.changeView(new LoginViewModel());
    }

    @Override
    public void cleanup() {

    }
}
