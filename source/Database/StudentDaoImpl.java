package source.Database;

import source.Database.Dao.StudentDao;
import source.Entity.Student;
import source.FileIO.Serializer.Text.StudentDeserializer;
import source.FileIO.Serializer.Text.StudentSerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The StudentDaoImpl implements the functions of StudentDao using the DAO Design Pattern.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see BaseDaoImpl
 * @see StudentDao
 * @since 11/4/2023
 */
public class StudentDaoImpl extends BaseDaoImpl implements StudentDao {
    /**
     * An array list of our data
     */
    private ArrayList<Student> studentList;

    /**
     * An overloaded constructor that takes in a filePath to populate the DB's files.
     *
     * @param filePath the filePath to read from
     */
    public StudentDaoImpl(String filePath) {
        super(filePath);
        //Initialize our student list
        studentList = new ArrayList<>();
        //Store our file path
        this.filePath = filePath;
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
        //Reinitialize the student list
        studentList.clear();
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
     * Creates a student in this database by appending the student into the student list.
     *
     * @return true always.
     */
    @Override
    public boolean createStudent(Student student) {
        studentList.add(student);
        refresh();
        return true;
    }

    /**
     * Searches the database to see if the student name exists (it is assumed that student names are unique according to the FAQ).
     *
     * @return the student object associated with that student name, null if there is no found entry.
     */
    @Override
    public Student readStudent(String query, String from) {
        //Look through our text data file entries and get see if it exists
        HashMap<String, ArrayList<String>> mp = textDataFile.getData();
        //Check if the key exists, can throw exception here to be dealt with later
        try {
            //If the map does not contain our table, then query fails
            if (!mp.containsKey(from))
                throw new KeyException();

            ArrayList<String> stringData = mp.get(from);
            //Iterate and try to find
            for (int i = 0; i < stringData.size(); i++) {
                if (query.equals(stringData.get(i)))
                    return studentList.get(i);
            }
        } catch (KeyException e) {
            System.out.println("The " + from + " table does not exist!");
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
            refresh();
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
    public boolean deleteStudent(String query, String from) {
        Student student = readStudent(query, from);
        if (student != null) {
            return deleteStudent(student);
        }
        return false;
    }

    /**
     * Deletes the student in this database by removing the student and saving to the file instantly.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteStudent(Student student) {
        boolean removed = studentList.remove(student);
        if (removed) {
            refresh();
            return true;
        }
        return false;
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

    @Override
    public void refresh() {
        saveToFile();
        readFile(filePath);
    }
}