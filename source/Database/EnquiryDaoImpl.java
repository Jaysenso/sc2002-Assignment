package source.Database;

import source.Database.Dao.EnquiryDao;
import source.Entity.Enquiry;
import source.FileIO.Serializer.Text.EnquiryDeserializer;
import source.FileIO.Serializer.Text.EnquirySerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The EnquiryDaoImpl implements the functions of EnquiryDao using the DAO Design Pattern.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see BaseDaoImpl
 * @see EnquiryDao
 * @since 11/4/2023
 */
public class EnquiryDaoImpl extends BaseDaoImpl implements EnquiryDao {
    /**
     * An array list of our data
     */
    private ArrayList<Enquiry> enquiryList;

    /**
     * An overloaded constructor that takes in a filePath to populate the DB's files.
     *
     * @param filePath the filePath to read from
     */
    public EnquiryDaoImpl(String filePath) {
        super(filePath);
        //Initialize our Enquiry list
        enquiryList = new ArrayList<>();
        //Store our file path
        this.filePath = filePath;
        //Populate our entries using the filePath
        readFile(filePath);
    }

    /**
     * Reads a file and deserializes it using the Enquiry deserializer. Updates the staff list stored in this class.
     *
     * @param filePath the path to the file for reading
     * @see EnquiryDeserializer
     */
    @Override
    protected void readFile(String filePath) {
        super.readFile(filePath);
        //Reinitialize the Enquiry list
        enquiryList.clear();
        //Finally, we deserialize the data and return our array list
        TextDataDeserializer deserializer = new EnquiryDeserializer();
        ArrayList dataList = deserializer.deserialize(textDataFile.getData());

        for (Object o : dataList) {
            //if o is actually an instance of Enquiry, then add it to our list
            if (o instanceof Enquiry) {
                enquiryList.add((Enquiry) o);
            }
        }
    }

    /**
     * Serializes the data stored in this file and writes it to the last known file location.
     *
     * @see EnquiryDeserializer
     */
    @Override
    void saveToFile() {
        TextDataSerializer serializer = new EnquirySerializer();
        ArrayList<String> serializedEnquiries = serializer.serialize(enquiryList);
        TextDataWriter writer = new TextDataWriter();
        writer.write(filePath, serializedEnquiries);
    }

    /**
     * Creates an enquiry in this database by appending the Enquiry into the Enquiry list.
     *
     * @return true always.
     */
    @Override
    public boolean createEnquiry(Enquiry Enquiry) {
        enquiryList.add(Enquiry);
        refresh();
        return true;
    }

    /**
     * Searches the database to see if the Enquiry name exists (it is assumed that Enquiry names are unique according to the FAQ).
     *
     * @return the Enquiry object associated with that Enquiry name, null if there is no found entry.
     */
    @Override
    public Enquiry readEnquiry(String query, String from) {
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
                    return enquiryList.get(i);
            }
        } catch (KeyException e) {
            System.out.println("The " + from + " table does not exist!");
        }
        return null;
    }

    /**
     * Reads all enquirie that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @param from  the header to query in
     * @return an arraylist of enquirie if found, an empty list if not.
     */
    @Override
    public ArrayList<Enquiry> readEnquiries(String query, String from) {
        ArrayList<Enquiry> enquirie = new ArrayList<>();
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
                    enquirie.add(enquiryList.get(i));
            }
            return enquirie;
        } catch (KeyException e) {
            System.out.println("The " + from + " table does not exist!");
        }
        return enquirie;
    }

    /**
     * Updates the Enquiry in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful update, false if object was not found in database.
     */
    @Override
    public boolean updateEnquiry(Enquiry Enquiry) {
        int pos = enquiryList.indexOf(Enquiry);
        //if the Enquiry list contains this Enquiry
        if (pos != -1) {
            //Then just copy it in for saving
            enquiryList.set(pos, Enquiry);
            refresh();
            return true;
        }
        return false;
    }

    /**
     * Deletes the Enquiry in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteEnquiry(String query, String from) {
        Enquiry Enquiry = readEnquiry(query, from);
        if (Enquiry != null) {
            return deleteEnquiry(Enquiry);
        }
        return false;
    }

    /**
     * Deletes the Enquiry in this database by removing the Enquiry and saving to the file instantly.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteEnquiry(Enquiry Enquiry) {
        boolean removed = enquiryList.remove(Enquiry);
        if (removed) {
            refresh();
            return true;
        }
        return false;
    }


    /**
     * Acquires the entire list of Enquiry objects in the database.
     *
     * @return list of Enquiry stored in this database.
     */
    @Override
    public ArrayList<Enquiry> getEnquiries() {
        return this.enquiryList;
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
