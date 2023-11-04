
package source.FileIO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The TextFile class is a concrete class that is both readable and writable and is of the type DataFile
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public class TextFormattedFile extends DataFile {
    /**
     * The contents of the file
     */
    private String rawData;

    /**
     * The contents of the file split into headers and columns of data
     */
    private HashMap<String, ArrayList<String>> data;

    /**
     * A default constructor that initialises the attributes to a default value.
     */
    public TextFormattedFile()
    {
        super();
        this.rawData = null;
        this.data = new HashMap<String,ArrayList<String>>();
    }

    /**
     * An overloaded constructor that initialises the path of the file using DataFile's constructor
     * @param filePath path to the file
     */
    public TextFormattedFile(String filePath)
    {
        super(filePath);
        this.rawData = null;
        this.data = new HashMap<String,ArrayList<String>>();
    }

    public String getRawData()
    {
        return this.rawData;
    }

    public void setRawData(String rawData)
    {
        this.rawData = rawData;
    }

    public HashMap<String,ArrayList<String>> getData()
    {
        return this.data;
    }

    public void setData(HashMap<String,ArrayList<String>> data)
    {
        this.data = data;
    }
}
