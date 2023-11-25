package source.ViewModels.Application.StaffViewModels;

import source.Controllers.CampManager;
import source.Controllers.EnquiryManager;
import source.Controllers.FilterManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Staff;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.Application.Apps.FilterViewModel;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.StaffCampView;
import source.Views.Application.StaffView.StaffOperationsView;

import java.util.ArrayList;
import java.util.Objects;

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
    StaffOperationsView staffOperationsView;
    private Staff staff = (Staff) App.getUser();

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

    private EnquiryManager enquiryManager = new EnquiryManager();

    /**
     * A default constructor.
     *
     * @see StaffCampView
     */
    public StaffCampViewModel() {
        staffOperationsView = new StaffOperationsView();
        staffCampView = new StaffCampView();
        campManager = App.getCampManager();
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
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            filterManager.viewAll(campManager.getFiltertype());
            staffCampView.display();
            choice = InputHandler.tryGetInt(1, 5, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    int index = InputHandler.tryGetInt(1, campManager.getCamps().size(), "Input camp choice : ", "Invalid Camp");
                    Camp selectedCamp = campManager.getCamps().get(index - 1);
                    if(Objects.equals(App.getUser().getName(), selectedCamp.getCampInfo().getStaffInCharge())){
                        viewManager.changeView(new StaffInChargeOperationsViewModel(selectedCamp));
                    }
                    else{
                        staffOperations(selectedCamp);
                    }
                    break;
                }
                case 2: {
                    campManager.createCamp();
                    PrettyPage.printCamps(campManager.getCamps());
                    staffCampView.display();
                    break;
                }
                case 3: {
                    ArrayList<Camp> staffCreatedCampList = filterManager.filterStaffCamps(campManager.getCamps(), staff);
                    filterManager.showCamps(staffCreatedCampList);
                    int index1 = InputHandler.tryGetInt(1, staffCreatedCampList.size(), "Input camp choice : ", "Invalid Camp");
                    Camp selectedCamp = staffCreatedCampList.get(index1 - 1);
                    viewManager.changeView(new StaffInChargeOperationsViewModel(selectedCamp));
                    break;
                }
                case 4: {
                    viewManager.changeView(new FilterViewModel());
                    break;
                }
                case 5: {
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

    public void staffOperations(Camp selectedCamp){
        boolean isLooping = true;
        while(isLooping){
            PrettyPage.printCampDetails(selectedCamp);
            staffOperationsView.display();
            int choice = InputHandler.tryGetInt(1,2, "Choose option: ", "Invalid Key");
            switch (choice){
                case 1:{
                    PrettyPage.printEnquiries(enquiryManager.getCampEnquiries(selectedCamp.getCampInfo().getName()));
                    Option[] options = {
                            new Option("1", "Back"),
                    };
                    PrettyPage.printLinesWithHeader(options, "Go Back");
                    int back = InputHandler.tryGetInt(1,1, "Go Back? : ", "Invalid Key");
                    if(back==1) isLooping = false;
                    break;
                }
                case 2:{
                    isLooping = false;
                    break;
                }
            }
        }
    }
}
