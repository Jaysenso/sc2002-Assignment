package source.FileIO;
/**
 * An interface that can be realized by writers to force them to have write functions
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public interface IWritable {
    public void write(String data);
}
