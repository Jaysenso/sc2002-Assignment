package source.Views;
/**
 * A simple interface that all views would inherit from to render a particular text
 * on the console display.
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public interface IView {
    /**
     * Typical display method to be called when a particular view wants to be rendered.
     */
     void display();
}
