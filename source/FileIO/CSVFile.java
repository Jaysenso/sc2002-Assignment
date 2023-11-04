package source.FileIO;

/**
 * The CSVFile class holds the appropriate information. It's only responsibility
 * is to store the rawData and the parsed data for future use.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see TextDataFile
 * @since 11/4/2023
 */
public class CSVFile extends TextDataFile {
    /**
     * The character to use to parse the data
     */
    private char delimiter;

    /**
     * A default constructor.
     */
    public CSVFile() {
        super();
        this.delimiter = ',';
    }

    /**
     * An overloaded constructor that allows the setting of filePath and the delimiter.
     *
     * @param filePath  path to the file
     * @param delimiter the delimiter/seperator of this file
     */
    public CSVFile(String filePath, char delimiter) {
       // super(filePath);
        //this.delimiter = delimiter;
    }
}
