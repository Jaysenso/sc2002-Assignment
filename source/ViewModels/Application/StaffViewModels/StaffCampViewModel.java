package source.ViewModels.Application.StaffViewModels;

import source.Controllers.CampManager;
import source.Controllers.FilterManager;
import source.Database.ApplicationContext;
import source.Entity.Camp;
import source.Entity.Staff;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.Application.Apps.FilterViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.StaffCampView;
/**
 * The StaffCampViewModel class handles the staffCampViewModel's logics and present the relevant operations that staff can use in the camp list view.
 *
 * @author J'sen Ong
 * @version 1.0
 * @since 11/17/2023
 */
public class StaffCampViewModel extends BaseViewModel implements IViewModel {

    /**
     * The StaffCampView object shows the UI for when the staff view all camps
     *
     * @see StaffCampView
     */
    private StaffCampView staffCampView;

    /**
     * The FilterManager object abstracts the various filter methods that user can use
     *
     * @see FilterManager
     */
    private FilterManager filterManager = new FilterManager();

    /**
     * The Camp Manager object serves as a DB and abstracts the relevant methods to read/write camp list
     *
     * @see CampManager
     */
    private CampManager campManager;

    /**
     * A default constructor.
     *
     * @see StaffCampView
     */
    public StaffCampViewModel() {
        staffCampView = new StaffCampView();
        campManager = ApplicationContext.getCampManager();
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
        filterManager.viewAll(campManager.getFiltertype());
        staffCampView.display();
        handleInputs();

    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            choice = InputHandler.tryGetInt(1, 4, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    int index = InputHandler.tryGetInt(1, campManager.getCamps().size(), "Input camp choice : ", "Invalid Camp");
                    Camp selectedCamp = campManager.getCamps().get(index - 1);
                    viewManager.changeView(new StaffOperationsViewModel(selectedCamp));
                    break;
                }
                case 2: {
                    campManager.createCamp();
                    PrettyPage.printCamps(campManager.getCamps());
                    staffCampView.display();
                    break;
                }
                case 3: {
                    viewManager.changeView(new FilterViewModel());
                    break;
                }
                case 4: {
                    viewManager.returnToPreviousView();
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
