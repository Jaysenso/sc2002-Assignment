package source.ViewModels.Application;

import source.Database.CampDaoImpl;
import source.Database.Dao.CampDao;
import source.Entity.Camp;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffCampView;
import source.Views.Application.StudentCampView;

import java.util.List;

public class StudentCampViewModel extends BaseViewModel implements IViewModel {
    /**
     * The student view object shows the UI when the user is logged in as a Student, presenting the user with options a student can take.
     *
     * @see StaffCampView
     */
    StudentCampView studentCampView;
    CampDao dao;

    /**
     * A default constructor.
     *
     * @see StaffCampView
     */
    public StudentCampViewModel () {
        dao = new CampDaoImpl(DirectoryUtility.CAMP_LIST_PATH);
        studentCampView = new StudentCampView();
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
        PrettyPage.printCamps(dao.getCamps());
        studentCampView.display();
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        do {
            choice = InputHandler.tryGetInt(1, 3, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    List<Camp> campList = dao.getCamps();
                    int index = InputHandler.tryGetInt(1, campList.size(), "Input camp choice : ", "Invalid Camp");
                    viewManager.changeView(new StudentViewModel(campList.get(index-1)));
                    break;
                }
                case 2: {
                    System.out.println("FILTER CAMP LIST");
                    break;
                }
                case 3: {
                    viewManager.returnToPreviousView();
                    break;
                }
            }
        } while (choice != 3);
    }

    /**
     * A function that is called when the ViewManager swaps view, any clean up code such as
     * flushing the console output or cleaning up any lists or data
     */
    @Override
    public void cleanup() {
        System.out.flush(); //NOTE: Does not work in IntelliJ IDEA as it is not a real terminal.
    }

    public void viewAll(){
        for(int i = 0; i < dao.getCamps().size(); i++){
            System.out.println(dao.getCamps().get(i).getCampInfo().getName());
        }
    }
}
