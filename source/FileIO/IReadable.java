package source.FileIO;

import java.util.ArrayList;

/**
 * An interface that can be realized by readers to force them to have the read function
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public interface IReadable {
    public ArrayList<String> read(String filePath);
}
