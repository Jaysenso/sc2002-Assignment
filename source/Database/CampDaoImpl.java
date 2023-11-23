package source.Database;

import source.Database.Dao.CampDao;
import source.Entity.Camp;
import source.FileIO.Serializer.Text.CampDeserializer;
import source.FileIO.Serializer.Text.CampSerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * @return the Camp object associated with that Camp name, null if there is no found entry.
     */
    @Override
    public Camp readCamp(String query, String from) {
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
                    return campList.get(i);
            }
        } catch (KeyException e) {
            System.out.println("The " + from + " table does not exist!");
        }
        return null;
    }

    /**
     * Updates the Camp in this database by searching the database and replacing that entry.
     *
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
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteCamp(String query, String from) {
        Camp camp = readCamp(query, from);
        if (camp != null) {
            return deleteCamp(camp);
        }
        return false;
    }

    /**
     * Deletes the Camp in this database by removing the Camp and saving to the file instantly.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteCamp(Camp camp) {
        boolean removed = campList.remove(camp);
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
    public List<Camp> getCamps() {
        return this.campList;
    }

    @Override
    public void refresh() {
        saveToFile();
        readFile(filePath);
    }
}
