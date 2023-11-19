package source;

import source.Database.StudentDB;
import source.Entity.Student;
import source.ViewModels.LoginViewModel;
import source.ViewModels.ViewManager;

public class CampApplication {
    public static void main(String[] args) {

        //Student DB wrapper test
        StudentDB sdb = new StudentDB("data/student_list.csv");
        for(Object s : sdb.getStudents())
        {
            System.out.println(s);
        }
        ViewManager manager = new ViewManager(new LoginViewModel());
        manager.run();
    }
}
