package source.ViewModels.Application.Apps;

import source.Controllers.CampManager;
import source.Controllers.FilterManager;
import source.Database.ApplicationContext;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.AppViews.FilterView;
/**
 * The FilterViewModel holds all the logic and necessary Ui elements for filter camp list.
 *
 * @author Ho Jian Feng
 * @version 1.0
 * @since 11/12/2023
 */
public class FilterViewModel extends BaseViewModel implements IViewModel {

    /**
     * The Filter View object that contains the UI for when the user selects Filter Camp List
     *
     * @see FilterView
     */
    FilterView filterView;

    /**
     * The Filter View Manager object serves as a
     *
     * @see FilterManager
     */
    private FilterManager filterManager;

    /**
     * The Camp Manager object serves as a DB and abstracts the relevant methods to read/write camp list.
     *
     * @see CampManager
     */
    private CampManager campManager;

    /**
     * A default constructor.
     *
     * @see FilterView
     */
    public FilterViewModel() {
        filterManager = new FilterManager();
        campManager = ApplicationContext.getCampManager();
        filterView = new FilterView();
    }

    /**
     * A function called by ViewManager to pass by reference and conduct any initial setup
     * of the view model.
     *
     * @param viewManager the view manager reference.
     * @see ViewManager
     */
    @Override
    public void init(ViewManager viewManager) {
        super.init(viewManager);
        PrettyPage.printTitle("Filters", 1);
        filterView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            choice = InputHandler.tryGetInt(1, 9, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    filterManager.ascendAlphabetical(campManager.getCamps());
                    campManager.setFiltertype(1);
                    viewManager.returnToPreviousView();
                    break;
                }
                case 2: {
                    filterManager.descendAlphabetical(campManager.getCamps());
                    campManager.setFiltertype(2);
                    viewManager.returnToPreviousView();
                    break;
                }
                case 3: {
                    filterManager.ascendAttendee(campManager.getCamps());
                    campManager.setFiltertype(3);
                    viewManager.returnToPreviousView();
                    break;
                }
                case 4: {
                    filterManager.descendAttendee(campManager.getCamps());
                    campManager.setFiltertype(4);
                    viewManager.returnToPreviousView();
                    break;
                }
                case 5: {
                    filterManager.ascendCommittee(campManager.getCamps());
                    campManager.setFiltertype(5);
                    viewManager.returnToPreviousView();
                    break;
                }
                case 6: {
                    filterManager.descendCommittee(campManager.getCamps());
                    campManager.setFiltertype(6);
                    viewManager.returnToPreviousView();
                    break;
                }
                case 7: {
                    filterManager.ascendDate(campManager.getCamps());
                    campManager.setFiltertype(7);
                    viewManager.returnToPreviousView();
                    break;
                }
                case 8: {
                    filterManager.descendDate(campManager.getCamps());
                    campManager.setFiltertype(8);
                    viewManager.returnToPreviousView();
                    break;
                }
                case 9: {
                    viewManager.returnToPreviousView();
                    break;
                }
            }
        } while (true);
    }

    /**
     * A function that is called when the ViewManager swaps view, any clean up code such as
     * flushing the console output or cleaning up any lists or data
     */
    @Override
    public void cleanup() {
        System.out.flush(); //NOTE: Does not work in IntelliJ IDEA as it is not a real terminal.
    }
}