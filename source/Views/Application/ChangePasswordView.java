package source.Views.Application;

import source.Entity.Staff;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

/**
 * The ChangePasswordView class contains the necessary UI elements to display the change password UI
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/17/2023
 */
public class ChangePasswordView implements IView {
    @Override
    public void display() {
        PrettyPage.printTitle("Please change your password!", 1);
    }
}
