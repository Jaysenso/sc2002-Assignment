
package source.FileIO;

import source.FileIO.Parser.Parser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The TextDataFile class is a concrete class that is both readable and writable and is a subtype of DataFile.
 * Has properties such as the raw data read, and the parsed data.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see DataFile
 * @since 11/4/2023
 */
public class TextDataFile extends DataFile {
    /**
     * The contents of the file
     */
    private ArrayList<String> rawData;

    /**
     * The contents of the file split into headers and columns of data
     * where the key is the header and the values are the subsequent columns.
     * Is parsed by relevant parsers.
     *
     * @see Parser
     */
    private HashMap<String, ArrayList<String>> data;

    /**
     * A default constructor that initialises the attributes to a default value.
     */
    public TextDataFile() {
        super();
        this.rawData = null;
        this.data = new HashMap<String, ArrayList<String>>();
    }

    /**
     * An overloaded constructor that initialises the path of the file using DataFile's constructor
     *
     * @param filePath path to the file
     */
    public TextDataFile(String filePath, ArrayList<String> rawData, HashMap<String, ArrayList<String>> data) {
        super(filePath);
        this.rawData = rawData;
        this.data = data;
    }

    /**
     * Acquires the raw data of the file in text format.
     *
     * @return the list of strings in raw data
     */
    public ArrayList<String> getRawData() {
        return this.rawData;
    }

    /**
     * Sets the raw data in this file
     *
     * @param rawData the new raw data of this file
     */
    public void setRawData(ArrayList<String> rawData) {
        this.rawData = rawData;
    }

    /**
     * Acquires the parsed data in hashmap format
     *
     * @return the parsed data
     */
    public HashMap<String, ArrayList<String>> getData() {
        return this.data;
    }

    /**
     * Sets the parsed data in this file
     *
     * @param data the new parsed data of this file
     */
    public void setData(HashMap<String, ArrayList<String>> data) {
        this.data = data;
    }
}
