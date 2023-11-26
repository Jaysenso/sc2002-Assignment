package source.Database;

import source.Database.Dao.EnquiryDao;
import source.Entity.Camp;
import source.Entity.Enquiry;
import source.FileIO.Serializer.Text.EnquiryDeserializer;
import source.FileIO.Serializer.Text.EnquirySerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;
import source.Utility.PrettyPage;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;

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
     * @param enquiry the enquiry to create
     * @return true always.
     */
    @Override
    public boolean createEnquiry(Enquiry enquiry) {
        enquiryList.add(enquiry);
        refresh();
        return true;
    }

    /**
     * Searches the database to see if the Enquiry name exists (it is assumed that Enquiry names are unique according to the FAQ).
     *
     * @param query the database query object
     * @return the Enquiry object associated with that Enquiry name, null if there is no found entry.
     */
    @Override
    public Enquiry readEnquiry(DatabaseQuery query) {
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
                    return enquiryList.get(i);
            }
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return null;
    }

    /**
     * Reads an enquiry from the subsequent database using a given query and from which table
     * Should be mainly used for name queries.
     *
     * @param queries queries to check that gives us the first result that satisfies the requirements.
     * @return the enquiry object if found
     */
    @Override
    public Enquiry readEnquiry(DatabaseQuery[] queries) {
        //Look through our text data file entries and get see if it exists
        HashMap<String, ArrayList<String>> mp = textDataFile.getData();
        //Check if the key exists, can throw exception here to be dealt with later
        int len = mp.get("camp_name").size();
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
                    return enquiryList.get(i);
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return null;
    }

    /**
     * Reads all enquiries that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @return an arraylist of enquiries if found, an empty list if not.
     */
    @Override
    public ArrayList<Enquiry> readEnquiries(DatabaseQuery query) {
        ArrayList<Enquiry> enquiries = new ArrayList<>();
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
                    enquiries.add(enquiryList.get(i));
            }
            return enquiries;
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return enquiries;
    }

    /**
     * Reads all Enquiries that satisfies all the properties (Overloaded)
     * NOTE: List can be empty if no results are found
     *
     * @param queries an array of database queries
     * @return an arraylist of Enquiry if found, an empty list if not.
     */
    @Override
    public ArrayList<Enquiry> readEnquiries(DatabaseQuery[] queries) {
        if (queries.length == 0)
            return new ArrayList<>();
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        //Look through our text data file entries and get see if it exists
        HashMap<String, ArrayList<String>> mp = textDataFile.getData();
        int len = mp.get("camp_name").size();

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
                    enquiries.add(enquiryList.get(i));
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return enquiries;
    }

    /**
     * Updates the Enquiry in this database by searching the database and replacing that entry.
     *
     * @param enquiry the enquiry to update
     * @return true if there was a successful update, false if object was not found in database.
     */
    @Override
    public boolean updateEnquiry(Enquiry enquiry) {
        int i = 0;
        for (; i < enquiryList.size(); i++) {
            if (enquiryList.get(i).equals(enquiry)) {
                break;
            }
        }
        if (i != enquiryList.size()) {
            enquiryList.set(i, enquiry);
            refresh();
            return true;
        }
        return false;
    }

    /**
     * Deletes the Enquiry in this database by searching the database and replacing that entry.
     *
     * @param query the query into our database
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteEnquiry(DatabaseQuery query) {
        Enquiry Enquiry = readEnquiry(query);
        if (Enquiry != null) {
            return deleteEnquiry(Enquiry);
        }
        return false;
    }

    /**
     * Deletes the Enquiry in this database by removing the Enquiry and saving to the file instantly.
     *
     * @param enquiry the enquiry
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteEnquiry(Enquiry enquiry) {
        int idx = 0;
        boolean removed = false;
        for (Enquiry e : enquiryList) {
            if (e.equals(enquiry)) {
                removed = true;
                break;
            }
            idx++;
        }
        //Remove at idx
        enquiryList.remove(idx);
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
