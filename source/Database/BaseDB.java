package source.Database;

import source.Entity.Student;
import source.FileIO.Parser.Parser;
import source.FileIO.Serializer.Text.StudentDeserializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.TextDataFile;
import source.FileIO.TextDataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The BaseDB class provides the basic functionalities for reading and saving to a file, while providing
 * abstract methods that subclasses can implement to do their own saving and reading.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public abstract class BaseDB {
    /**
     * The file path where the database object got its data from.
     */
    protected String filePath;

    /**
     * A function to read some data from a file and return an array list of objects.
     *
     * @param filePath path to the file
     * @see Object
     * @see TextDataReader
     * @see Parser
     * @see TextDataFile
     * @see source.FileIO.Serializer.Text.TextDataSerializer
     */
    protected ArrayList readFile(String filePath) {
        try {
            //Just some error checking
            if (filePath.isEmpty())
                throw new IOException();

            //Update our filePath for the last read filePath that is valid
            this.filePath = filePath;

            //Instantiate our text data reader
            TextDataReader reader = new TextDataReader();
            //Return a string of data given from the reader
            ArrayList<String> rawData = reader.read(filePath);
            //We then create a parser to parse our data
            Parser parser = new Parser();
            //Set the data in our map given the parse results
            HashMap<String, ArrayList<String>> data = parser.parse(rawData);
            //Populate a text data file that is stored in this db
            TextDataFile newFile = new TextDataFile(filePath, rawData, data);
            //Finally, we deserialize the data and return our array list
            TextDataDeserializer deserializer = new StudentDeserializer();
            ArrayList dataList = deserializer.deserialize(newFile.getData());
            return dataList;

        } catch (Exception e) {
            //Placeholder
            e.printStackTrace();
        }
        //Else just return an empty array list.
        return new ArrayList<>();
    }

    /**
     * An abstract method to force subsequent databases to implement their own logic in saving to their respective files.
     */
    abstract void saveToFile();
}
