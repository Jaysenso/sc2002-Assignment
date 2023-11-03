package source.FileIO;
/**
 * The DataWriter class is a class that uses abstractions to write the contents
 * to any writable file.
 * @see IWritable
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public class DataWriter {

    /**
     * A function to write data into a writable file.
     * @param writableFile an object that is writable by realizing @see IWritable
     * @param content the content to write into the file
     */
    public void writeData(IWritable writableFile, String content) {
        writableFile.write(content);
    }
}
