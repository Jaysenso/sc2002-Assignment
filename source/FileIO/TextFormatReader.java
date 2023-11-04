package source.FileIO;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFormatReader implements IReadable {
    private final TextFormattedFile textFormattedFile;

    public TextFormatReader(TextFormattedFile textFormattedFile)
    {
        //Dependency injection
        this.textFormattedFile = textFormattedFile;
    }

    public String read()
    {
        //Check if the file is null before proceeding
        if(textFormattedFile == null)
            return null;

        StringBuilder sb = null;
        String line = "";
        try
        {
            //Instantiate new string builder object
            sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader(textFormattedFile.getFilePath()));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                //Regex to remove all the weird spacings in csv
                line = line.replaceAll("[^\\p{Graph}\n\r\t ]", "");
                sb.append(line + "\n");
            }
            //Set the raw data from our string builder
            textFormattedFile.setRawData(sb.toString());
            br.close();

            return sb.toString();

        }catch(IOException e)
        {
            e.printStackTrace();
        }
        //Update the data, return null if no data was able to be read
        return null;
    }
}
