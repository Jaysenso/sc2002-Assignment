
package source.FileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The TextFile class is a concrete class that is both readable and writable and is of the type DataFile
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public class TextFile extends DataFile {
    /**
     * The contents of the file
     */
    private String rawData;

    /**
     * The contents of the file
     */
    private HashMap<String, ArrayList<String>> data;

    /**
     * A default constructor that initialises the attributes to a default value.
     */
    public TextFile()
    {
        super();
        this.rawData = "";
        this.data = new HashMap<String,ArrayList<String>>();
    }

    /**
     * An overloaded constructor that initialises the path of the file using DataFile's constructor
     * @param filePath path to the file
     */
    public TextFile(String filePath)
    {
        super(filePath);
        this.rawData = null;
        this.data = new HashMap<String,ArrayList<String>>();
    }

    public String getRawData()
    {
        return this.rawData;
    }

    protected void setRawData(String rawData)
    {
        this.rawData = rawData;
    }

    public HashMap<String,ArrayList<String>> getData()
    {
        return this.data;
    }

    protected void setData(HashMap<String,ArrayList<String>> data)
    {
        this.data = data;
    }
}
