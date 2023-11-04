package source.FileIO.Parser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Parser class is a realization of TextDataParser to parse strings and separate values by a delimiter
 *
 * @author Isaac Chun
 * @version 1.0
 * @see TextDataParser
 * @since 11/4/2023
 */
public class Parser implements TextDataParser {
    /**
     * Indicates whether the parser takes into account headers or not.
     */
    private final boolean hasHeader;

    /**
     * The delimiter to seperate between entries.
     */
    private final String DELIMITER;

    /**
     * A default constructor. Assumes the csv to be parsed has a header.
     */
    public Parser() {
        this.hasHeader = true;
        this.DELIMITER = ",";
    }

    /**
     * An overloaded constructor that sets the values of whether the header is taken into account
     * when parsing.
     *
     * @param hasHeader whether to parse with a header.
     * @param delimiter the delimiter to use to parse our data
     */
    public Parser(boolean hasHeader, String delimiter) {
        this.hasHeader = hasHeader;
        this.DELIMITER = delimiter;
    }

    /**
     * A realization of the parser interface. Parses an arraylist of data.
     *
     * @param data An ArrayList of strings that contain line by line data.
     * @return A map of headers (keys) and the entire column as data (value). If hasHeader was not specified,
     * it returns a hashmap with the row numbers as keys.
     */
    @Override
    public HashMap<String, ArrayList<String>> parse(ArrayList<String> data) {
        //Make a new map that stores our header and values to be put
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

        //Run different functionalities within our class, while using the outer parse function.
        if (hasHeader) {
            parseWithHeader(map, data);
        } else {
            parseWithoutHeader(map, data);
        }
        //Return our data
        return map;
    }

    /**
     * Parses an array list of string data while taking the header into account. Not meant to be called directly!
     * It is meant to be private function!
     *
     * @param map  A pass by reference hashmap
     * @param data An ArrayList of strings that contain line by line data.
     */
    private void parseWithHeader(HashMap<String, ArrayList<String>> map, ArrayList<String> data) {
        //The first element of data is the header by the example format
        String[] headers = data.get(0).split(String.valueOf(DELIMITER));
        //Store each header into our map
        for (String header : headers) {
            map.put(header, new ArrayList<String>());
        }

        //Then start from 1 to length
        for (int i = 1; i < data.size(); i++) {
            //Get the current line
            String line = data.get(i);
            String[] lineData = line.split(String.valueOf(DELIMITER));
            for (int j = 0; j < lineData.length; j++) {
                //System.out.println("Putting " + lineData[j] + " into " + headers[j]);
                //Put the data accordingly into the array list
                map.get(headers[j]).add(lineData[j]);
            }
        }
    }

    /**
     * Parses an array list of string data while not taking the header into account. Not meant to be called directly!
     * It is meant to be private function!
     *
     * @param map  A pass by reference hashmap
     * @param data An ArrayList of strings that contain line by line data.
     */
    private void parseWithoutHeader(HashMap<String, ArrayList<String>> map, ArrayList<String> data) {
        //Initialize up to data length our map.
        for (int i = 0; i < data.size(); i++) {
            map.put(String.valueOf(i), new ArrayList<String>());
        }

        //Then start from 1 to length
        for (int i = 0; i < data.size(); i++) {
            //Get the current line
            String line = data.get(i);
            String[] lineData = line.split(",");
            for (int j = 0; j < lineData.length; j++) {
                //System.out.println("Putting " + lineData[j] + " into " + headers[j]);
                //Put the data accordingly into the array list
                map.get(i).add(lineData[j]);
            }
        }
    }
}
