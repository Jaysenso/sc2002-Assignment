package source.Database;

import source.Entity.Student;
import source.FileIO.Serializer.Text.StudentSerializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * The StudentDB extends on the base functionalities of BaseDB and realizes the CRUD operations for
 * persistent data storage.
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

    @Override
    protected ArrayList readFile(String filePath) {
        ArrayList currStudentList = super.readFile(filePath);
        for (Object o : currStudentList) {
            //if o is actually an instance of student, then add it to our list
            if (o instanceof Student) {
                studentList.add((Student) o);
            }
        }
        return studentList;
    }

    @Override
    void saveToFile() {
        TextDataSerializer serializer = new StudentSerializer();
        ArrayList<String> serializedStudents = serializer.serialize(studentList);
        TextDataWriter writer = new TextDataWriter();
        writer.write(filePath, serializedStudents);
    }

    @Override
    public boolean createStudent(Student student) {
        studentList.add(student);
        return true;
    }

    @Override
    public Student readStudent(String studentName) {
        for (Student s : studentList) {
            if (s.getName().equals(studentName))
                return s;
        }
        return null;
    }

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

    @Override
    public boolean deleteStudent(Student student) {
        return studentList.remove(student);
    }

    @Override
    public List<Student> getStudents() {
        return this.studentList;
    }
}
