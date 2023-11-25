package source.Controllers;

import source.Database.App;
import source.Database.CampDaoImpl;
import source.Database.Dao.CampDao;
import source.Database.DatabaseQuery;
import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Entity.Student;
import source.Faculty.Faculty;
import source.Utility.*;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;

public final class CampManager {
    private CampDao campDao;

    public CampManager() {
        this.campDao = new CampDaoImpl(DirectoryUtility.CAMP_LIST_PATH);
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

    public ArrayList<Camp> getCamps() {
        return campDao.getCamps();
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

    public boolean registerAttendees(Student student, Camp selectedCamp) {

        //Create a date range validator that ranges from the start and end date
        DateRangeValidator checker = new DateRangeValidator(selectedCamp.getCampInfo().getStartDate(), selectedCamp.getCampInfo().getEndDate());
        //check if student is already part of the camp
        if (student.isAttendee(selectedCamp)) {
            PrettyPage.printError("Error: You have already registered.");
            return false;
        }
        //check if student has registered for camps that collide with the selectedCamp
        for (Camp camp : student.getRegisteredCamps()) {
            if (checker.isWithinRange(camp.getCampInfo().getStartDate()) || checker.isWithinRange(camp.getCampInfo().getEndDate())) {
                PrettyPage.printError("Error : You are already registered for a camp on the same date.");
                return false;
            }
        }
        //check if the selected camp has slots
        if (selectedCamp.getCampInfo().getCurrentSlots() >= selectedCamp.getCampInfo().getMaxSlots()) {
            PrettyPage.printError("Error : Camp is already full.");
            return false;
        }
        //Check if student is registering for a camp that is past its registration period
        if (LocalDate.now().isAfter(selectedCamp.getCampInfo().getClosingDate())) {
            PrettyPage.printError("Error : Registration period has closed.");
            return false;
        }


        student.addRegisteredCamps(selectedCamp);
        selectedCamp.addAttendee(student);
        PrettyPage.printLine(new Option("Success", "You Have Registered Successfully for " + selectedCamp.getCampInfo().getName()));
        return true;
    }

    public boolean registerCommittees(Student student, Camp selectedCamp) {

        if (student.isAttendee(selectedCamp)) {
            PrettyPage.printError("Error: You are already an Attendee for this camp.");
            return false;
        }

        if (student.getIsCampCommittee() == selectedCamp) {
            PrettyPage.printError("Error : You are already a camp committee for this camp.");
            return false;
        }

        if (student.getIsCampCommittee() != null && student.getIsCampCommittee() != selectedCamp) {
            PrettyPage.printError("Error: You are already Camp Committee for another camp.");
            return false;
        }

        DateRangeValidator checker = new DateRangeValidator(selectedCamp.getCampInfo().getStartDate(), selectedCamp.getCampInfo().getEndDate());
        for (Camp camp : student.getRegisteredCamps()) {

            if (checker.isWithinRange(camp.getCampInfo().getStartDate()) || checker.isWithinRange(camp.getCampInfo().getEndDate())) {
                PrettyPage.printError("Error : You are already registered for a camp on the same date.");
                return false;
            }
        }

        if (selectedCamp.getCampInfo().getCampCommitteeSlots() >= selectedCamp.getCampInfo().getMaxCampCommitteeSlots()) {
            PrettyPage.printError("Error : Camp Committee slots are full.");
            return false;
        }

        if (LocalDate.now().isAfter(selectedCamp.getCampInfo().getClosingDate())) {
            PrettyPage.printError("Error : Registration period has closed.");
            return false;
        }

        student.addRegisteredCamps(selectedCamp);
        student.setIsCampCommittee(selectedCamp);
        selectedCamp.addCommittee(student);
        selectedCamp.addAttendee(student);
        PrettyPage.printLine(new Option("Success", "You Have Registered Successfully for " + selectedCamp.getCampInfo().getName()));
        return true;
    }

    public boolean withdrawAttendees(Student attendee, Camp selectedCamp) {
        if (!attendee.isAttendee(selectedCamp)) {
            PrettyPage.printError("You are not part of the camp!");
            return false;

        } else {
            selectedCamp.withdrawAttendee(attendee);
            attendee.removeRegisteredCamps(selectedCamp);
            updateCamp(selectedCamp);
            PrettyPage.printLine(new Option("Success", "You Have withdrew from " + selectedCamp.getCampInfo().getName()));
            return true;
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

    public void loadContext() {
        campDao.loadContext();
    }

    public ArrayList<Camp> readCamps(DatabaseQuery query) {
        return campDao.readCamps(query);
    }

    public void showCamps() {
        PrettyPage.printCamps(campDao.getCamps());
    }
}
