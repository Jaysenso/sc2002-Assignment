package source.Database;

import source.Database.Dao.StaffDao;
import source.Entity.Staff;
import source.FileIO.Serializer.Text.StaffDeserializer;
import source.FileIO.Serializer.Text.StaffSerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;
import source.Utility.PrettyPage;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The StaffDaoImpl implements the functions of StaffDao using the DAO Design Pattern.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see BaseDaoImpl
 * @see StaffDao
 * @since 11/4/2023
 */
public class StaffDaoImpl extends BaseDaoImpl implements StaffDao {
    /**
     * An array list of our data
     */
    private ArrayList<Staff> staffList;

    /**
     * An overloaded constructor that takes in a filePath to populate the DB's files.
     *
     * @param filePath the filePath to read from
     */
    public StaffDaoImpl(String filePath) {
        super(filePath);
        //Initialize our staff list
        staffList = new ArrayList<>();
        //Store our file path
        this.filePath = filePath;
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
        //Reinitialize the staff list
        staffList.clear();
        //Finally, we deserialize the data and return our array list
        TextDataDeserializer deserializer = new StaffDeserializer();
        ArrayList dataList = deserializer.deserialize(textDataFile.getData());

        for (Object o : dataList) {
            //if o is actually an instance of staff, then add it to our list
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
        ArrayList<String> serializedStaffs = serializer.serialize(staffList);
        TextDataWriter writer = new TextDataWriter();
        writer.write(filePath, serializedStaffs);
    }

    /**
     * Creates a staff in this database by appending the staff into the staff list.
     *
     * @return true always.
     */
    @Override
    public boolean createStaff(Staff staff) {
        staffList.add(staff);
        refresh();
        return true;
    }

    /**
     * Searches the database to see if the staff name exists (it is assumed that staff names are unique according to the FAQ).
     *
     * @return the staff object associated with that staff name, null if there is no found entry.
     */
    @Override
    public Staff readStaff(DatabaseQuery query) {
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
                    return staffList.get(i);
            }
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return null;
    }

    /**
     * Searches the database to see if the staff name exists (it is assumed that staff names are unique according to the FAQ).
     * MUST SATISFY ALL THE QUERIES
     *
     * @return the staff object associated with that staff name, null if there is no found entry.
     */
    @Override
    public Staff readStaff(DatabaseQuery[] queries) {
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
                    return staffList.get(i);
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return null;
    }

    /**
     * Reads all staffs that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @return an arraylist of staff if found, an empty list if not.
     */
    @Override
    public ArrayList<Staff> readStaffs(DatabaseQuery query) {
        ArrayList<Staff> staffs = new ArrayList<>();
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
                    staffs.add(staffList.get(i));
            }
            return staffs;
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return staffs;
    }

    /**
     * Reads all staffs that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param queries query to check in our header
     * @return an arraylist of staff if found, an empty list if not.
     */
    @Override
    public ArrayList<Staff> readStaffs(DatabaseQuery[] queries) {
        ArrayList<Staff> staffs = new ArrayList<>();
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
                    staffs.add(staffList.get(i));
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return staffs;
    }

    /**
     * Updates the staff in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful update, false if object was not found in database.
     */
    @Override
    public boolean updateStaff(Staff staff) {
        int pos = staffList.indexOf(staff);
        //if the staff list contains this staff
        if (pos != -1) {
            //Then just copy it in for saving
            staffList.set(pos, staff);
            refresh();
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
    public boolean deleteStaff(DatabaseQuery query) {
        Staff staff = readStaff(query);
        if (staff != null) {
            return deleteStaff(staff);
        }
        return false;
    }

    /**
     * Deletes the staff in this database by removing the staff and saving to the file instantly.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteStaff(Staff staff) {
        boolean removed = staffList.remove(staff);
        if (removed) {
            refresh();
            return true;
        }
        return false;
    }


    /**
     * Acquires the entire list of staff objects in the database.
     *
     * @return list of staff stored in this database.
     */
    @Override
    public ArrayList<Staff> getStaffs() {
        return this.staffList;
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
