package source.Controllers.UserManagement;

import source.Controllers.StudentManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Student;
import source.Entity.User;
import source.Utility.Option;
import source.Utility.PrettyPage;

import java.util.ArrayList;

/**
 * The IManagement context provides an abstraction layer for user management.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class StudentManagement implements IManagement {
    /**
     * Update the user in the associated databases
     */
    private final Student student;
    /**
     * The student manager reference
     */
    private final StudentManager studentManager;

    /**
     * An overloaded constructor that takes in the current Application user and binds it
     * to student.
     * NOTE: It is guaranteed to work!
     *
     * @param user the current logged-in user
     */
    public StudentManagement(User user) {
        if (user instanceof Student) {
            this.student = (Student) user;
        } else {
            this.student = null;
        }
        //Initialize the student manager to be the app's static reference
        studentManager = App.getStudentManager();
    }

    /**
     * Update the user in the associated databases
     */
    @Override
    public void update() {
        studentManager.updateStudent(student);
    }

    /**
     * Print the profile details
     */
    @Override
    public void getProfile() {
        String registeredCamps = "";
        ArrayList<Camp> rc = student.getRegisteredCamps();
        for (int i = 0; i < rc.size(); i++) {
            registeredCamps += rc.get(i).getCampInfo().getName();
            if (i != rc.size() - 1)
                registeredCamps += ", ";
        }
        if(registeredCamps.isEmpty()){
            registeredCamps = "N/A";
        }

        Option[] options = new Option[]{
                new Option("Name", student.getName()),
                new Option("Faculty", student.getFacultyInfo().getClass().getSimpleName()),
                new Option("UserID", student.getUserID()),
                new Option("Email", student.getUserID() + "@e.ntu.edu.sg"),
                new Option("Camp Committee of",
                        (student.getIsCampCommittee() != null)
                                ? student.getIsCampCommittee().getCampInfo().getName()
                                : "N/A"),
                new Option("Registered Camps", registeredCamps),
                new Option("Points", String.valueOf(student.getAccumulatedPoints()))
        };
        PrettyPage.printLines(options);
    }

}
