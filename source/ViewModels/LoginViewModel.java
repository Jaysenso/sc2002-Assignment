package source.ViewModels;

import source.Views.LoginView;

/**
 * The LoginViewModel holds all the logic and necessary Ui elements for a successful login.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class LoginViewModel extends BaseViewModel implements IViewModel{
    @Override
    public void init(ViewManager viewManager) {
        LoginView view = new LoginView();
        view.display();
    }

    @Override
    public void cleanup() {

    }
}
