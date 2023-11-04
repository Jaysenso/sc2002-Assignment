package source.ViewModels;

import source.Views.LoginView;

public class LoginViewModel implements IViewModel{
    LoginView view;
    @Override
    public void init(ViewManager viewManager) {
        view = new LoginView();
        view.display();
    }

    @Override
    public void cleanup() {

    }
}
