package source.FileIO;

import java.io.*;

/**
 * ADD COMMENTS LATER
 * @see source.FileIO.DataFile
 * @see source.FileIO.IReadable
 * @see source.FileIO.IWritable
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public class CSVFile extends TextFormattedFile {
    /**
     * The character to use to parse the data
     */
    private char delimiter;
    public CSVFile()
    {
        super();
        this.delimiter = ',';
    }

    public CSVFile(String filePath, char delimiter)
    {
        super(filePath);
        this.delimiter = delimiter;
    }
}
