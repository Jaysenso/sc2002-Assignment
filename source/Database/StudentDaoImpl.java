package source.Database;

import source.Database.Dao.StudentDao;
import source.Entity.Student;
import source.FileIO.Serializer.Text.StudentDeserializer;
import source.FileIO.Serializer.Text.StudentSerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;
import source.Utility.PrettyPage;

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
    public Student readStudent(DatabaseQuery query) {
        //Look through our text data file entries and get see if it exists
        HashMap<String, ArrayList<String>> mp = textDataFile.getData();
        //Check if the key exists, can throw exception here to be dealt with later
        try {
            //If the map does not contain our table, then query fails
            if (!mp.containsKey(query.getHeader()))
                throw new KeyException();

            ArrayList<String> stringData = mp.get(query.getHeader());
            //Iterate and try to find
            for (int i = 0; i < stringData.size(); i++) {
                if (query.getQuery().equals(stringData.get(i)))
                    return studentList.get(i);
            }
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return null;
    }

    /**
     * Searches the database to see if the student name exists (it is assumed that student names are unique according to the FAQ).
     * MUST SATISFY ALL THE QUERIES
     *
     * @return the student object associated with that student name, null if there is no found entry.
     */
    @Override
    public Student readStudent(DatabaseQuery[] queries) {
        //Look through our text data file entries and get see if it exists
        HashMap<String, ArrayList<String>> mp = textDataFile.getData();
        int len = mp.get("name").size();
        try {
            //Loop through from start to end and check at every instance if it satisfies our queries
            for (int i = 0; i < len; i++) {
                boolean satisfied = true;
                for (int j = 0; j < queries.length; j++) {
                    //If the map does not contain our table, then query fails
                    if (!mp.containsKey(queries[j].getHeader()))
                        throw new KeyException();

                    ArrayList<String> stringData = mp.get(queries[j].getHeader());
                    String data = stringData.get(i);
                    //if data equals our query at this itr
                    if (data.equals(queries[j].getQuery())) {
                        continue;
                    }
                    //Else we did not satisfy this loop
                    satisfied = false;
                    break;
                }
                if (satisfied) {
                    return studentList.get(i);
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return null;
    }

    /**
     * Reads all students that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @return an arraylist of students if found, an empty list if not.
     */
    @Override
    public ArrayList<Student> readStudents(DatabaseQuery query) {
        ArrayList<Student> students = new ArrayList<>();
        //Look through our text data file entries and get see if it exists
        HashMap<String, ArrayList<String>> mp = textDataFile.getData();
        //Check if the key exists, can throw exception here to be dealt with later
        try {
            //If the map does not contain our table, then query fails
            if (!mp.containsKey(query.getHeader()))
                throw new KeyException();

            ArrayList<String> stringData = mp.get(query.getHeader());
            //Iterate and try to find
            for (int i = 0; i < stringData.size(); i++) {
                if (query.getQuery().equals(stringData.get(i)))
                    students.add(students.get(i));
            }
            return students;
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return students;
    }

    /**
     * Reads all students that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param queries query to check in our header
     * @return an arraylist of students if found, an empty list if not.
     */
    @Override
    public ArrayList<Student> readStudents(DatabaseQuery[] queries) {
        ArrayList<Student> students = new ArrayList<>();
        //Look through our text data file entries and get see if it exists
        HashMap<String, ArrayList<String>> mp = textDataFile.getData();
        int len = mp.get("name").size();
        try {
            //Loop through from start to end and check at every instance if it satisfies our queries
            for (int i = 0; i < len; i++) {
                boolean satisfied = true;
                for (int j = 0; j < queries.length; j++) {
                    //If the map does not contain our table, then query fails
                    if (!mp.containsKey(queries[j].getHeader()))
                        throw new KeyException();

                    ArrayList<String> stringData = mp.get(queries[j].getHeader());
                    String data = stringData.get(i);
                    //if data equals our query at this itr
                    if (data.equals(queries[j].getQuery())) {
                        continue;
                    }
                    //Else we did not satisfy this loop
                    satisfied = false;
                    break;
                }
                if (satisfied) {
                    students.add(studentList.get(i));
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return students;
    }

    /**
     * Updates the student in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful update, false if object was not found in database.
     */
    @Override
    public boolean updateStudent(Student student) {
        int i = 0;
        for (; i < studentList.size(); i++) {
            if (studentList.get(i).equals(student)) {
                break;
            }
        }
        if (i != studentList.size()) {
            studentList.set(i, student);
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
    public boolean deleteStudent(DatabaseQuery query) {
        Student student = readStudent(query);
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
    public ArrayList<Student> getStudents() {
        return this.studentList;
    }

    /**
     * Refreshes this dao by saving the contents and re-reading the contents.
     */
    @Override
    public void refresh() {
        saveToFile();
        readFile(filePath);
    }
}
