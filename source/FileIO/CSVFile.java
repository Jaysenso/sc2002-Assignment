package source.FileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
public class CSVFile extends TextFile implements IReadable, IWritable, IParsable {
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

    @Override
    public void write(String data) {
        FileWriter writer;
        try
        {
            writer = new FileWriter(getFilePath());
            writer.write(data);
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String read() {
        String fileData = "";
        String line = "";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(getFilePath()));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                //Regex to remove all the weird spacings in csv
                line = line.replaceAll("[^\\p{Graph}\n\r\t ]", "");
                fileData += line + "\n";
            }
            setRawData(fileData);
            br.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        //Update the data

        return fileData;
    }

    @Override
    public void parse() {
        HashMap<String, ArrayList<String>> map = getData();
        //Read the file's contents and separate it by the ',' delimiter
        String fileData = getRawData();
        //Seperate all of the lines by '\n'
        String[] lines = fileData.split("\n");
        //The first element of lines is the header by the example format
        String[] headers = lines[0].split(",");

        //Store each header into our map
        for(String header : headers)
        {
            map.put(header, new ArrayList<String>());
        }
        //Then start from 1 to length
        for(int i = 1; i < lines.length; i++)
        {
            String line = lines[i];
            String[] lineData = lines[i].split(",");
            for(int j = 0; j < lineData.length; j++)
            {

                //System.out.println("Putting " + lineData[j] + " into " + headers[j]);
                //Put the data accordingly into the array list
                map.get(headers[j]).add(lineData[j]);
            }
        }
    }
}
