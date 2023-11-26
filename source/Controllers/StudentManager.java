package source.Controllers;

import source.Database.Dao.StudentDao;
import source.Database.DatabaseQuery;
import source.Database.StudentDaoImpl;
import source.Entity.Student;
import source.Utility.DirectoryUtility;

/**
 * The StudentManager class serves as a DB and abstracts the Data Access Object implementations and provides
 * what's necessary for others to be used.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public final class StudentManager {
    /**
     * The student data access object that contains direct implementations to our database..
     */
    private final StudentDao studentDao;

    /**
     * A default constructor.
     */
    public StudentManager() {
        this.studentDao = new StudentDaoImpl(DirectoryUtility.STUDENT_DATA_PATH);
    }

    /**
     * A function to read a student given a query and a header
     *
     * @param query the query
     * @return student object if found, null if not.
     */
    public Student readStudent(DatabaseQuery query) {
        return studentDao.readStudent(query);
    }

    /**
     * A function to update the database given a student.
     *
     * @param student the student object to update
     */
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    /**
     * A function to get a student from the database given their userID
     *
     * @param userID the user id of the student
     * @return student object
     */
    public Student getStudentByID(String userID) {
        for (Student s : studentDao.getStudents()) {
            if (s.getUserID().equals(userID))
                return s;
        }
        return null;
    }

    /**
     * A function to get a student from the database given their name
     *
     * @param name the name of the student
     * @return student object
     */
    public Student getStudentByName(String name) {
        for (Student s : studentDao.getStudents()) {
            if (s.getName().equals(name))
                return s;
        }
        return null;
    }
}
