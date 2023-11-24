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
     * The staff data access object that contains direct implementations to our database..
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
     * @param from  the header to search in
     * @return student object if found, null if not.
     */
    public Student readStudent(DatabaseQuery query) {
        return studentDao.readStudent(query);
    }

    /**
     * A function to update the database given a student.
     */
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

}
