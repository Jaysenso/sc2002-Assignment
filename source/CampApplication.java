package source;

import source.Controllers.AuthenticationController;
import source.ViewModels.Application.StartViewModel;
import source.ViewModels.ViewManager;

public class CampApplication {
    public static void main(String[] args) {
        /*
        //Student DB wrapper test
        StudentDB sdb = new StudentDB("data/student_list.csv");
        for(Object s : sdb.getStudents())
        {
            System.out.println(s);
        }

        //Staff DB wrapper test
        StaffDB db = new StaffDB("data/staff_list.csv");
        for(Object staff : db.getStaffs())
        {
            System.out.println(staff);
        }
         */
        ViewManager manager = new ViewManager(new StartViewModel());
        manager.run();

    }
}
