package source.FileIO;

import java.util.ArrayList;
/**
 * An interface that can be realized by writers to force them to have write functions
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public interface IWritable {
    /**
     * A write function meant to be implemented by classes that realize this interface.
     * @param filePath path to the file
     * @param data array list of strings
     */
    public void write(String filePath, ArrayList<String> data);
}
