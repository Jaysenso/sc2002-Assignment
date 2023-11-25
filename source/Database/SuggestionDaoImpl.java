package source.Database;

import source.Database.Dao.SuggestionDao;
import source.Entity.Suggestion;
import source.FileIO.Serializer.Text.SuggestionDeserializer;
import source.FileIO.Serializer.Text.SuggestionSerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataWriter;
import source.Utility.PrettyPage;

import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The SuggestionDaoImpl implements the functions of SuggestionDao using the DAO Design Pattern.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see BaseDaoImpl
 * @see SuggestionDao
 * @since 11/23/2023
 */
public class SuggestionDaoImpl extends BaseDaoImpl implements SuggestionDao {
    /**
     * An array list of our data
     */
    private ArrayList<Suggestion> suggestionList;

    /**
     * An overloaded constructor that takes in a filePath to populate the DB's files.
     *
     * @param filePath the filePath to read from
     */
    public SuggestionDaoImpl(String filePath) {
        super(filePath);
        //Initialize our suggestion list
        suggestionList = new ArrayList<>();
        //Store our file path
        this.filePath = filePath;
        //Populate our entries using the filePath
        readFile(filePath);
    }

    /**
     * Reads a file and deserializes it using the suggestion deserializer. Updates the staff list stored in this class.
     *
     * @param filePath the path to the file for reading
     * @see SuggestionDeserializer
     */
    @Override
    protected void readFile(String filePath) {
        super.readFile(filePath);
        //Reinitialize the suggestion list
        suggestionList.clear();
        //Finally, we deserialize the data and return our array list
        TextDataDeserializer deserializer = new SuggestionDeserializer();
        ArrayList dataList = deserializer.deserialize(textDataFile.getData());

        for (Object o : dataList) {
            //if o is actually an instance of suggestion, then add it to our list
            if (o instanceof Suggestion) {
                suggestionList.add((Suggestion) o);
            }
        }
    }

    /**
     * Serializes the data stored in this file and writes it to the last known file location.
     *
     * @see SuggestionDeserializer
     */
    @Override
    void saveToFile() {
        TextDataSerializer serializer = new SuggestionSerializer();
        ArrayList<String> serializedSuggestions = serializer.serialize(suggestionList);
        TextDataWriter writer = new TextDataWriter();
        writer.write(filePath, serializedSuggestions);
    }

    /**
     * Creates a suggestion in this database by appending the suggestion into the suggestion list.
     *
     * @return true always.
     */
    @Override
    public boolean createSuggestion(Suggestion suggestion) {
        suggestionList.add(suggestion);
        refresh();
        return true;
    }

    /**
     * Searches the database to see if the suggestion name exists (it is assumed that suggestion names are unique according to the FAQ).
     *
     * @return the suggestion object associated with that suggestion name, null if there is no found entry.
     */
    @Override
    public Suggestion readSuggestion(DatabaseQuery query) {
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
                    return suggestionList.get(i);
            }
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return null;
    }

    /**
     * Searches the database to see if the suggestion name exists (it is assumed that suggestion names are unique according to the FAQ).
     * MUST SATISFY ALL THE QUERIES
     *
     * @return the suggestion object associated with that suggestion name, null if there is no found entry.
     */
    @Override
    public Suggestion readSuggestion(DatabaseQuery[] queries) {
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
                    return suggestionList.get(i);
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return null;
    }

    /**
     * Reads all suggestions that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @return an arraylist of suggestions if found, an empty list if not.
     */
    @Override
    public ArrayList<Suggestion> readSuggestions(DatabaseQuery query) {
        ArrayList<Suggestion> suggestions = new ArrayList<>();
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
                    suggestions.add(suggestions.get(i));
            }
            return suggestions;
        } catch (KeyException e) {
            System.out.println("The " + query.getHeader() + " table does not exist!");
        }
        return suggestions;
    }

    /**
     * Reads all suggestions that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param queries query to check in our header
     * @return an arraylist of suggestions if found, an empty list if not.
     */
    @Override
    public ArrayList<Suggestion> readSuggestions(DatabaseQuery[] queries) {
        ArrayList<Suggestion> suggestions = new ArrayList<>();
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
                    suggestions.add(suggestionList.get(i));
                }
            }
        } catch (KeyException e) {
            PrettyPage.printError("The table did not exist!");
        }
        return suggestions;
    }

    /**
     * Updates the suggestion in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful update, false if object was not found in database.
     */
    @Override
    public boolean updateSuggestion(Suggestion suggestion) {
        int pos = suggestionList.indexOf(suggestion);
        //if the suggestion list contains this suggestion
        if (pos != -1) {
            //Then just copy it in for saving
            suggestionList.set(pos, suggestion);
            refresh();
            return true;
        }
        return false;
    }

    /**
     * Deletes the suggestion in this database by searching the database and replacing that entry.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteSuggestion(DatabaseQuery query) {
        Suggestion suggestion = readSuggestion(query);
        if (suggestion != null) {
            return deleteSuggestion(suggestion);
        }
        return false;
    }

    /**
     * Deletes the suggestion in this database by removing the suggestion and saving to the file instantly.
     *
     * @return true if there was a successful deletion, else false.
     */
    @Override
    public boolean deleteSuggestion(Suggestion suggestion) {
        boolean removed = suggestionList.remove(suggestion);
        if (removed) {
            refresh();
            return true;
        }
        return false;
    }


    /**
     * Acquires the entire list of suggestion objects in the database.
     *
     * @return list of suggestion stored in this database.
     */
    @Override
    public ArrayList<Suggestion> getSuggestions() {
        return this.suggestionList;
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
