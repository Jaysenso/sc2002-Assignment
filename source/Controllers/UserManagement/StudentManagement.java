package source.Controllers.UserManagement;

import source.Controllers.StudentManager;
import source.Database.App;
import source.Entity.Student;
import source.Entity.User;

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
}
