package source.ViewModels;

import source.Views.LoginView;

public class LoginViewModel extends BaseViewModel implements IViewModel{
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
