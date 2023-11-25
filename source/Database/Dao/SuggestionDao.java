package source.Database.Dao;

import source.Database.DatabaseQuery;
import source.Entity.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * The SuggestionDAO interface provides operations that follow the CRUD operations for persistent data storage. Also provides a method to get an entire list of students
 * and delete them.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/19/2023
 */
public interface SuggestionDao {
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
     * @return a student object if found, null if not.
     */
    Student readStudent(DatabaseQuery query);

    /**
     * Reads a student from the subsequent database using the student name
     * MUST SATISFY ALL THE QUERIES
     *
     * @param queries query to check in our header
     * @return a student object if found, null if not.
     */
    Student readStudent(DatabaseQuery[] queries);

    /**
     * Reads all students that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @return an arraylist of students if found, an empty list if not.
     */
    ArrayList<Student> readStudents(DatabaseQuery query);

    /**
     * Reads all students that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param queries query to check in our header
     * @return an arraylist of students if found, an empty list if not.
     */
    ArrayList<Student> readStudents(DatabaseQuery[] queries);

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
     */
    boolean deleteStudent(DatabaseQuery query);

    /**
     * Deletes a student in the subsequent database
     */
    boolean deleteStudent(Student student);

    /**
     * Function to get the list of all student objects if needed
     *
     * @return the list of student stored in the database
     */
    ArrayList<Student> getStudents();

    /**
     * Function to refresh the database by saving this context and then reading it again.
     */
    void refresh();
}
