package source.Database;

import source.Database.Dao.StaffDao;
import source.Database.Dao.StudentDao;
import source.Entity.Staff;
import source.FileIO.Serializer.Text.*;
import source.FileIO.TextDataWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * The StaffDB extends on the base functionalities of BaseDB and realizes the CRUD operations for
 * persistent data storage on staff.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see BaseDB
 * @see StudentDao
 * @since 11/4/2023
 */
public class StaffDB extends BaseDB implements StaffDao {
    /**
     * An array list of staff data
     */
    private ArrayList<Staff> staffList;

    /**
     * An overloaded constructor that takes in a filePath to populate the DB's files.
     *
     * @param filePath the filePath to read from
     */
    public StaffDB(String filePath) {
        //Initialize our student list
        staffList = new ArrayList<>();
        //Populate our entries using the filePath
        readFile(filePath);
    }

    /**
     * Reads a file and deserializes it using the staff deserializer. Updates the staff list stored in this class.
     *
     * @param filePath the path to the file for reading
     * @see StaffDeserializer
     */
    @Override
    protected void readFile(String filePath) {
        super.readFile(filePath);
        //Finally, we deserialize the data and return our array list
        TextDataDeserializer deserializer = new StaffDeserializer();
        ArrayList dataList = deserializer.deserialize(textDataFile.getData());

        for (Object o : dataList) {
            //if o is actually an instance of student, then add it to our list
            if (o instanceof Staff) {
                staffList.add((Staff) o);
            }
        }
    }

    /**
     * Serializes the data stored in this file and writes it to the last known file location.
     *
     * @see StaffSerializer
     */
    @Override
    void saveToFile() {
        TextDataSerializer serializer = new StaffSerializer();
        ArrayList<String> serializedStudents = serializer.serialize(staffList);
        TextDataWriter writer = new TextDataWriter();
        writer.write(filePath, serializedStudents);
    }

    /**
     * Creates a staff in this database by appending the staff into the staff list.
     *
     * @return true always.
     */
    @Override
    public boolean createStaff(Staff staff) {
        staffList.add(staff);
        return true;
    }

    /**
     * Searches the database to see if the staff name exists (it is assumed that staff names are unique according to the FAQ).
     *
     * @return the staff object associated with that staff name, null if there is no found entry.
     */
    @Override
    public Staff readStaff(String staffName) {
        for (Staff s : staffList) {
            if (s.getName().equals(staffName))
                return s;
        }
        return null;
    }

    /**
     * Updates the staff in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful update, false if object was not found in database.
     */
    @Override
    public boolean updateStaff(Staff staff) {
        int pos = staffList.indexOf(staff);
        //if the student list contains this student
        if (pos != -1) {
            //Then just copy it in for saving
            staffList.set(pos, staff);
            return true;
        }
        return false;
    }

    /**
     * Deletes the staff in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteStaff(Staff staff) {
        return staffList.remove(staff);
    }

    /**
     * Acquires the entire list of staff objects in the database.
     *
     * @return list of staff stored in this database.
     */
    @Override
    public List<Staff> getStaffs() {
        return this.staffList;
    }
}
