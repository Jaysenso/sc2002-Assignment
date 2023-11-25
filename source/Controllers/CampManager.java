package source.Controllers;

import source.Database.App;
import source.Database.CampDaoImpl;
import source.Database.Dao.CampDao;
import source.Database.DatabaseQuery;
import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Entity.Staff;
import source.Entity.User;
import source.Faculty.Faculty;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;

public final class CampManager {
    private CampDao campDao;
    private ArrayList<Camp> campList;
    private int filtertype = 1;

    //    public void createCamp(){
//        String name = InputHandler.getString();
//        String description = InputHandler.getString();
//    }
    public CampManager() {
        this.campDao = new CampDaoImpl(DirectoryUtility.CAMP_LIST_PATH);
        this.campList = campDao.getCamps();
    }

    public void createCamp() {
        String name;
        LocalDate startDate, endDate, regDate;

        System.out.print("Enter Camp Name: ");
        name = InputHandler.getString();

        System.out.print("Enter start date in the format yyyy-MM-dd: ");
        startDate = LocalDate.parse(InputHandler.getString());
        System.out.print("Enter end date in the format yyyy-MM-dd:");
        endDate = LocalDate.parse(InputHandler.getString());
        System.out.print("Enter registration closing date in the format yyyy-MM-dd:");
        regDate = LocalDate.parse(InputHandler.getString());

        System.out.print("Enter Location: ");
        String location = InputHandler.getString();
        System.out.print("Enter number of Attendee slots: ");
        int totalSlots = InputHandler.getInt();

        int commSlots;
        do {
            int commSlots_userInput = InputHandler.tryGetInt(0, 10, "Enter number of Committee Member slots: ", "invalid");
            if (commSlots_userInput > totalSlots) {
                PrettyPage.printError("The number of Committee Members cannot be greater than the total Attendee slots");
            } else {
                commSlots = commSlots_userInput;
                break;
            }
        } while (true);

        System.out.print("Enter a brief description: ");
        String description = InputHandler.getString();

        Faculty faculty = null;
        do {
            System.out.print("Enter User Group: ");
            try {
                String facultyName = "source.Faculty." + InputHandler.getString();
                faculty = (Faculty) Class.forName(facultyName).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     InvocationTargetException | NoSuchMethodException e) {
                PrettyPage.printError("Faculty Group not found in our System");
            }
        } while (faculty == null);

        System.out.print("Set Visibility :");
        boolean visibility;
        String input = "";
        do {
            System.out.println("VISIBILITY : (1) Hidden | (2) Visible");
            try {
                input = InputHandler.getString();
            } catch (Exception e) {
                PrettyPage.printError("Invalid Confirmation");
            }
        } while (!input.equals("1") && !input.equals("2"));
        if (input.equals("2")) {
            System.out.println("Camp is now Visible");
            visibility = true;
        } else {
            System.out.println("Camp is now Not Visible");
            visibility = false;
        }

        CampInfo info = new CampInfo(
                name,
                location,
                0,
                totalSlots,
                0,
                commSlots,
                description,
                App.getUser().getName(),
                startDate,
                endDate,
                regDate,
                faculty
        );
        campDao.createCamp(new Camp(info, visibility, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    public boolean deleteCamp(Camp camp) {
        String input = "";
        do {
            System.out.println("Confirm? y/n");
            try {
                input = InputHandler.getString();
            } catch (Exception e) {
                PrettyPage.printError("Invalid Confirmation");
            }
        } while (!input.equals("y") && !input.equals("n"));

        if (input.equals("y")) {
            return campDao.deleteCamp(camp);
        } else {
            return false;
        }
    }

    public Camp readCamp(DatabaseQuery query) {
        return campDao.readCamp(query);
    }

    public boolean updateCamp(Camp camp) {

        return campDao.updateCamp(camp);
    }

    public void refresh() {
        campDao.refresh();
    }

    public ArrayList<Camp> getCamps() {
        User user = App.getUser();
        return FilterManager.visibilityFilter(campList, user);
    }

    public void loadContext() {
        campDao.loadContext();
    }

//    public ArrayList<Camp> filterCamps() {
//        ArrayList<Camp> filteredCampList = new ArrayList<>();
//        filteredCampList.addAll(campList);
//        return fil
//    }

    public int getFiltertype() {
        return filtertype;
    }

    public void setFiltertype(int filtertype) {
        this.filtertype = filtertype;
    }

    public ArrayList<Camp> readCamps(DatabaseQuery query) {
        return campDao.readCamps(query);
    }
}
