package source.FileIO;

import org.w3c.dom.Text;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The DataReader class is a class that uses abstractions to read the contents
 * of any readable file.
 * @see IReadable
 *
 * @author  Isaac Chun
 * @version 1.0
 * @since   11/4/2023
 */
public class TextFormatWriter implements IWritable {
    private final TextFormattedFile textFormattedFile;

    public TextFormatWriter(TextFormattedFile textFormattedFile)
    {
        //Dependency injection
        this.textFormattedFile = textFormattedFile;
    }
    public void write(String data)
    {
        //Check the validity of the file before attempting to write
        if(this.textFormattedFile == null)
            return;

        FileWriter writer;
        try
        {
            writer = new FileWriter(textFormattedFile.getFilePath());
            writer.write(data);
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
