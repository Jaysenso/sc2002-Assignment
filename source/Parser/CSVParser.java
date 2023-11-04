package source.Parser;

import source.FileIO.TextFormattedFile;

import java.util.ArrayList;
import java.util.HashMap;

public class CSVParser implements DataParser {
    @Override
    public HashMap<String, ArrayList<String>> parse(TextFormattedFile file) {
        //Check the validity of the file
        if(file == null)
            return null;
        //Get the current data which is pass by reference
        HashMap<String, ArrayList<String>> map = file.getData();
        //Read the file's contents and separate it by the ',' delimiter
        String fileData = file.getRawData();
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
        return map;
    }
}
