package source.Database;

import source.Database.Dao.CampDao;
import source.Entity.Camp;
import source.FileIO.Serializer.Text.CampDeserializer;
import source.FileIO.Serializer.Text.CampSerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;
import source.Utility.PrettyPage;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The CampDaoImpl implements the functions of CampDao using the DAO Design Pattern.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see BaseDaoImpl
 * @see CampDao
 * @since 11/4/2023
 */
public class CampDaoImpl extends BaseDaoImpl implements CampDao {
    /**
     * An array list of our data
     */
    private ArrayList<Camp> campList;

    /**
     * An overloaded constructor that takes in a filePath to populate the DB's files.
     *
     * @param filePath the filePath to read from
     */
    public CampDaoImpl(String filePath) {
        super(filePath);
        //Initialize our camp list
        campList = new ArrayList<>();
        //Store our file path
        this.filePath = filePath;
        //Populate our entries using the filePath
        readFile(filePath);
    }

    /**
     * Reads a file and deserializes it using the camp deserializer. Updates the staff list stored in this class.
     *
     * @param filePath the path to the file for reading
     * @see CampDeserializer
     */
    @Override
    protected void readFile(String filePath) {
        super.readFile(filePath);
        //Reinitialize the Camp list
        campList.clear();
        //Finally, we deserialize the data and return our array list
        TextDataDeserializer deserializer = new CampDeserializer();
        ArrayList dataList = deserializer.deserialize(textDataFile.getData());

        for (Object o : dataList) {
            //if o is actually an instance of Camp, then add it to our list
            if (o instanceof Camp) {
                campList.add((Camp) o);
            }
        }
    }

    /**
     * Serializes the data stored in this file and writes it to the last known file location.
     *
     * @see CampDeserializer
     */
    @Override
    void saveToFile() {
        TextDataSerializer serializer = new CampSerializer();
        ArrayList<String> serializedCamps = serializer.serialize(campList);
        TextDataWriter writer = new TextDataWriter();
        writer.write(filePath, serializedCamps);
    }

    /**
     * Creates a Camp in this database by appending the Camp into the Camp list.
     *
     * @param camp the camp to create
     * @return true always.
     */
    @Override
    public boolean createCamp(Camp camp) {
        campList.add(camp);
        refresh();
        return true;
    }

    /**
     * Searches the database to see if the Camp name exists (it is assumed that Camp names are unique according to the FAQ).
     *
     * @param query the database query
     * @return the Camp object associated with that Camp name, null if there is no found entry.
     */
    @Override
    public Camp readCamp(DatabaseQuery query) {
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
                    return campList.get(i);
            }
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return null;
    }

    /**
     * Searches the database to see if the Camp name exists (it is assumed that Camp names are unique according to the FAQ).
     * MUST SATISFY ALL THE QUERIES
     *
     * @param queries the queries to our database
     * @return the Camp object associated with that Camp name, null if there is no found entry.
     */
    @Override
    public Camp readCamp(DatabaseQuery[] queries) {
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
                    return campList.get(i);
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return null;
    }

    /**
     * Reads all camps that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @return an arraylist of camps if found, an empty list if not.
     */
    @Override
    public ArrayList<Camp> readCamps(DatabaseQuery query) {
        ArrayList<Camp> camps = new ArrayList<>();
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
                    camps.add(campList.get(i));
            }
            return camps;
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return camps;
    }

    /**
     * Reads all camps that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param queries queries to check in our header
     * @return an arraylist of camps if found, an empty list if not.
     */
    @Override
    public ArrayList<Camp> readCamps(DatabaseQuery[] queries) {
        ArrayList<Camp> camps = new ArrayList<>();
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
                    camps.add(campList.get(i));
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return camps;
    }

    /**
     * Updates the Camp in this database by searching the database and replacing that entry.
     *
     * @param camp the camp to update
     * @return true if there was a successful update, false if object was not found in database.
     */
    @Override
    public boolean updateCamp(Camp camp) {
        int pos = campList.indexOf(camp);
        //if the Camp list contains this Camp
        if (pos != -1) {
            //Then just copy it in for saving
            campList.set(pos, camp);
            refresh();
            return true;
        }
        return false;
    }

    /**
     * Deletes the Camp in this database by searching the database and replacing that entry.
     *
     * @param query the query into our database
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteCamp(DatabaseQuery query) {
        Camp camp = readCamp(query);
        if (camp != null) {
            return deleteCamp(camp);
        }
        return false;
    }

    /**
     * Deletes the Camp in this database by removing the Camp and saving to the file instantly.
     *
     * @param camp the camp to delete
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteCamp(Camp camp) {
        int idx = 0;
        boolean removed = false;
        for (Camp c : campList) {
            if (c.equals(camp)) {
                removed = true;
                break;
            }
            idx++;
        }
        //Remove at idx
        campList.remove(idx);
        if (removed) {
            refresh();
            return true;
        }
        return false;
    }


    /**
     * Acquires the entire list of Camp objects in the database.
     *
     * @return list of Camp stored in this database.
     */
    @Override
    public ArrayList<Camp> getCamps() {
        return this.campList;
    }

    /**
     * Refreshes this dao by saving the contents and re-reading the contents.
     */
    @Override
    public void refresh() {
        saveToFile();
        readFile(filePath);
    }

    /**
     * Loads the context from the file.
     */
    @Override
    public void loadContext() {
        readFile(filePath);
    }
}
