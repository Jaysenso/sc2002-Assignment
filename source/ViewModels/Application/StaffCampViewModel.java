package source.ViewModels.Application;

import source.Controllers.CampManager;
import source.Database.CampDaoImpl;
import source.Database.Dao.CampDao;
import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Entity.Staff;
import source.Faculty.EEE;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffCampView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffCampViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StaffCampView
     */
    StaffCampView staffCampView;
    CampDao dao;
    CampManager cManager;
    ArrayList<Camp> campList;

    /**
     * A default constructor.
     *
     * @see StaffCampView
     */
    public StaffCampViewModel() {
        cManager = new CampManager();
        campList = cManager.getDao().getCamps();
        staffCampView = new StaffCampView();
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
        PrettyPage.printCamps(campList);
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
                    int index = InputHandler.tryGetInt(1, campList.size(), "Input camp choice : ", "Invalid Camp");
                    viewManager.changeView(new StaffViewModel(campList.get(index - 1)));
                    break;
                }
                case 2: {
                    cManager.createCamp();
                    PrettyPage.printCamps(campList);
                    staffCampView.display();
                    break;
                }
                case 3: {
                    System.out.println("filter");
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

    public void viewAll() {
        for (int i = 0; i < dao.getCamps().size(); i++) {
            System.out.println(dao.getCamps().get(i).getCampInfo().getName());
        }
    }

}
