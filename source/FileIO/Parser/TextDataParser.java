package source.FileIO.Parser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The TextDataParser interface allows the parsing of data from an array list of strings,
 * and converts it to a hashmap with the values of header(key) and columns(values).
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public interface TextDataParser {
    /**
     * An abstract method for subclasses to implement the parsing of data from an array list of string.
     *
     * @param data An ArrayList of strings that contain line by line data.
     */
    HashMap<String, ArrayList<String>> parse(ArrayList<String> data);
}
