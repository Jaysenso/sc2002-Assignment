package source.FileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The TextFormatReader class is a specific class that reads data from a file that is known to contain
 * texts (Strings).
 *
 * @author Isaac Chun
 * @version 1.0
 * @see IReadable
 * @since 11/4/2023
 */
public class TextDataReader implements IReadable {
    public ArrayList<String> read(String filePath) {
        ArrayList<String> data = new ArrayList<String>();
        String line = "";
        try {
            //Instantiate a new buffered reader that opens a file reader at our given path.
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                //Regex to remove all the weird spacings in csv
                line = line.replaceAll("[^\\p{Graph}\n\r\t ]", "");
                data.add(line);
            }
            br.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Update the data, return null if no data was able to be read
        return null;
    }
}
