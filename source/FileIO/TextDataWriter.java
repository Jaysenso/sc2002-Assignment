package source.FileIO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The TextDataWriter class is a specific class that writes data into a file that is known to contain
 * text (strings)
 *
 * @author Isaac Chun
 * @version 1.0
 * @see IWritable
 * @since 11/4/2023
 */
public class TextDataWriter implements IWritable {

    /**
     * A write function to write a data string into a given file
     *
     * @param filePath the path of the file to write to
     * @param data     the data to write into the file.
     */
    public void write(String filePath, ArrayList<String> data) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(filePath));
            for (String s : data) {
                out.println(s);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
