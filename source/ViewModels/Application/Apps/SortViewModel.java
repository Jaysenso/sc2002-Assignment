package source.ViewModels.Application.Apps;

import source.Controllers.Sorting.*;
import source.Entity.Camp;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.AppViews.SortView;

import java.util.ArrayList;

/**
 * The FilterViewModel holds all the logic and necessary Ui elements for filter camp list.
 *
 * @author Ho Jian Feng
 * @version 1.0
 * @since 11/12/2023
 */
public class SortViewModel extends BaseViewModel implements IViewModel {

    /**
     * The Filter View object that contains the UI for when the user selects Filter Camp List
     *
     * @see SortView
     */
    SortView sortView;

    /**
     * The Sort Manager object serves as a manager to carry out sorting algorithms on
     * list of camps
     *
     * @see SortManager
     */
    private SortManager sortManager;

    private ArrayList<Camp> sortedCamps;

    /**
     * A default constructor.
     *
     * @see SortView
     */
    public SortViewModel(ArrayList<Camp> camps) {
        this.sortedCamps = camps;
        sortManager = new SortManager();
        sortView = new SortView();
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
        sortView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        choice = InputHandler.tryGetInt(1, 9, "Input choice: ", "Invalid choice!");
        switch (choice) {
            case 1: {
                sortManager.sort(new SortNameAscending(), sortedCamps);
                break;
            }
            case 2: {
                sortManager.sort(new SortNameDescending(), sortedCamps);
                break;
            }
            case 3: {
                sortManager.sort(new SortAttendeesAscending(), sortedCamps);
                break;
            }
            case 4: {
                sortManager.sort(new SortAttendeesDescending(), sortedCamps);
                break;
            }
            case 5: {
                sortManager.sort(new SortCommitteeAscending(), sortedCamps);
                break;
            }
            case 6: {
                sortManager.sort(new SortCommitteeDescending(), sortedCamps);
                break;
            }
            case 7: {
                sortManager.sort(new SortDateAscending(), sortedCamps);
                break;
            }
            case 8: {
                sortManager.sort(new SortDateDescending(), sortedCamps);
                break;
            }
            case 9: {
                break;
            }
        }
        viewManager.returnToPreviousView();
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