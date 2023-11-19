package source.Database;

import source.Database.Dao.StudentDao;
import source.Entity.Student;
import source.FileIO.Serializer.Text.StudentDeserializer;
import source.FileIO.Serializer.Text.StudentSerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * The StudentDB extends on the base functionalities of BaseDB and realizes the CRUD operations for
 * persistent data storage on Students
 *
 * @author Isaac Chun
 * @version 1.0
 * @see BaseDB
 * @see StudentDao
 * @since 11/4/2023
 */
public class StudentDB extends BaseDB implements StudentDao {
    /**
     * An array list of our data
     */
    private ArrayList<Student> studentList;

    /**
     * An overloaded constructor that takes in a filePath to populate the DB's files.
     *
     * @param filePath the filePath to read from
     */
    public StudentDB(String filePath) {
        //Initialize our student list
        studentList = new ArrayList<>();
        //Populate our entries using the filePath
        readFile(filePath);
    }

    /**
     * Reads a file and deserializes it using the student deserializer. Updates the staff list stored in this class.
     *
     * @param filePath the path to the file for reading
     * @see StudentDeserializer
     */
    @Override
    protected void readFile(String filePath) {
        super.readFile(filePath);
        //Finally, we deserialize the data and return our array list
        TextDataDeserializer deserializer = new StudentDeserializer();
        ArrayList dataList = deserializer.deserialize(textDataFile.getData());

        for (Object o : dataList) {
            //if o is actually an instance of student, then add it to our list
            if (o instanceof Student) {
                studentList.add((Student) o);
            }
        }
    }

    /**
     * Serializes the data stored in this file and writes it to the last known file location.
     *
     * @see StudentDeserializer
     */
    @Override
    void saveToFile() {
        TextDataSerializer serializer = new StudentSerializer();
        ArrayList<String> serializedStudents = serializer.serialize(studentList);
        TextDataWriter writer = new TextDataWriter();
        writer.write(filePath, serializedStudents);
    }

    /**
     * Creates a student in this database by appending the staff into the student list.
     *
     * @return true always.
     */
    @Override
    public boolean createStudent(Student student) {
        studentList.add(student);
        return true;
    }

    /**
     * Searches the database to see if the student name exists (it is assumed that student names are unique according to the FAQ).
     *
     * @return the student object associated with that student name, null if there is no found entry.
     */
    @Override
    public Student readStudent(String studentName) {
        for (Student s : studentList) {
            if (s.getName().equals(studentName))
                return s;
        }
        return null;
    }

    /**
     * Updates the student in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful update, false if object was not found in database.
     */
    @Override
    public boolean updateStudent(Student student) {
        int pos = studentList.indexOf(student);
        //if the student list contains this student
        if (pos != -1) {
            //Then just copy it in for saving
            studentList.set(pos, student);
            return true;
        }
        return false;
    }

    /**
     * Deletes the student in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteStudent(Student student) {
        return studentList.remove(student);
    }

    /**
     * Acquires the entire list of student objects in the database.
     *
     * @return list of student stored in this database.
     */
    @Override
    public List<Student> getStudents() {
        return this.studentList;
    }
}
