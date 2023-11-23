package source.Database.Dao;

import source.Entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * The StudentDAO interface provides operations that follow the CRUD operations for persistent data storage. Also provides a method to get an entire list of students
 * and delete them.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/19/2023
 */
public interface StudentDao {
    /**
     * Creates a student in the subsequent database (perhaps for sign up feature~)
     *
     * @param student the student to add
     */
    boolean createStudent(Student student);

    /**
     * Reads a student from the subsequent database using the student name
     *
     * @param query query to check in our header
     * @param from  the header to query in
     * @return a student object if found, null if not.
     */
    Student readStudent(String query, String from);

    /**
     * Reads all students that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @param from  the header to query in
     * @return an arraylist of students if found, an empty list if not.
     */
    ArrayList<Student> readStudents(String query, String from);

    /**
     * Updates a student in the subsequent database
     *
     * @param student the student to add
     */
    boolean updateStudent(Student student);

    /**
     * Deletes a student in the subsequent database
     *
     * @param query query to check in our header
     * @param from  the header to query in
     */
    boolean deleteStudent(String query, String from);

    /**
     * Deletes a student in the subsequent database
     */
    boolean deleteStudent(Student student);

    /**
     * Function to get the list of all student objects if needed
     *
     * @return the list of student stored in the database
     */
    List<Student> getStudents();
}
