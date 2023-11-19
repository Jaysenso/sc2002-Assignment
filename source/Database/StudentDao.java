package source.Database;

import source.Entity.Student;

import java.util.List;

/**
 * The StudentDAO interface provides operations that follow the CRUD operations for persistent data storage. Also provides a method to get an entire list of students
 * and delete them.
 *
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/19/2023
 */
public interface StudentDao {
    /**
     * Creates a student in the subsequent database
     * @param student the student to add
     */
    boolean createStudent(Student student);
    /**
     * Reads a student from the subsequent database
     * @param studentName the student
     */
    Student readStudent(String studentName);
    /**
     * Updates a student in the subsequent database
     * @param student the student to add
     */
    boolean updateStudent(Student student);
    /**
     * Deletes a student in the subsequent database
     * @param student the student to add
     */
    boolean deleteStudent(Student student);
    /**
     * Creates a student in the subsequent database
     * @return the list of students stored in the database
     */
    List<Student> getStudents();
}
