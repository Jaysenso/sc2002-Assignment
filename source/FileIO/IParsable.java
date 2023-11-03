package source.FileIO;
/**
 * An interface that can be realized by subclasses to denote it as parsable
 * Particularly, for file types such as CSV that needs parsing on its on delimiters ','
 * to get its data.
 * @see CSVFile
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public interface IParsable {
    void parse();
}
