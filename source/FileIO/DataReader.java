package source.FileIO;
/**
 * The DataReader class is a class that uses abstractions to read the contents
 * of any readable file.
 * @see IReadable
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public class DataReader {
    /**
     * A function to read data from a readable file.
     * @param readableFile an object that is readable by realizing @see IReadable
     */
    public String readData(IReadable readableFile)
    {
        return readableFile.read();
    }
}
