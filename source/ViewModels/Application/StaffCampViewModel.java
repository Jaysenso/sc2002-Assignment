package source.ViewModels.Application;

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
    Camp camp;
    CampDao dao;

    /**
     * A default constructor.
     *
     * @see StaffCampView
     */
    public StaffCampViewModel() {
        dao = new CampDaoImpl(DirectoryUtility.CAMP_LIST_PATH);
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
        PrettyPage.printTitle("CampList : ", 1);
        viewAll();
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
                    List<Camp> campList = dao.getCamps();
                    int index = InputHandler.tryGetInt(1, campList.size(), "Input camp choice : ", "Invalid Camp");
                    viewManager.changeView(new StaffViewModel(campList.get(index - 1)));
                    break;
                }
                case 2: {
                    createCamp();
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

    public void createCamp() {
        String name;
        LocalDate startDate, endDate, regDate;
        System.out.println("Enter Camp Name: ");
        name = InputHandler.getString();
        System.out.println("Enter start date in the format yyyy-MM-dd: ");
        startDate = LocalDate.parse(InputHandler.getString());

        System.out.println("Enter end date in the format yyyy-MM-dd:");
        endDate = LocalDate.parse(InputHandler.getString());
        System.out.println("Enter registration closing date in the format yyyy-MM-dd:");
        regDate = LocalDate.parse(InputHandler.getString());

        System.out.println("Enter Location: ");
        String location = InputHandler.getString();
        System.out.println("Enter number of Attendee slots: ");
        int totalSlots = InputHandler.getInt();
        int commSlots = InputHandler.tryGetInt(0, 10, "Enter number of Committee Member slots: ", "invalid");
        System.out.println("Enter a brief description: ");
        String description = InputHandler.getString();

        CampInfo info =  new CampInfo(
                name,
                location,
                0,
                totalSlots,
                0,
                commSlots,
                description,
                "abdul",
                startDate,
                endDate,
                regDate,
                new EEE()
        );

        dao.createCamp(new Camp(info, true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }
}
