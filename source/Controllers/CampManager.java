package source.Controllers;

import source.Database.CampDaoImpl;
import source.Database.Dao.CampDao;
import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Entity.Staff;
import source.Entity.User;
import source.Faculty.EEE;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;
import source.Utility.PrettyPage;

import java.time.LocalDate;
import java.util.ArrayList;

public final class CampManager {
    private CampDao campDao;

//    public void createCamp(){
//        String name = InputHandler.getString();
//        String description = InputHandler.getString();
//    }
    public CampManager() {
        this.campDao = new CampDaoImpl(DirectoryUtility.CAMP_LIST_PATH);
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
        campDao.createCamp(new Camp(info, true, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
    }

    public boolean deleteCamp(Camp camp) {
        String input = "";
        do{
            System.out.println("Confirm? y/n");
            try{
                input = InputHandler.getString();
            }catch (Exception e) {
                PrettyPage.printError("Invalid Confirmation");
            }
        }while(!input.equals("y") && !input.equals("n"));

        return campDao.deleteCamp(camp);
    }

    public void refresh() {
        campDao.refresh();
    }

    public ArrayList<Camp> getCamps() {
        return campDao.getCamps();
    }

    public void loadContext() {
        campDao.loadContext();
    }
}
