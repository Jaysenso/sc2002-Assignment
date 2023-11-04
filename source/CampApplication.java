package source;

import source.FileIO.*;
import source.Parser.CSVParser;
import source.Parser.DataParser;

public class CampApplication {
    public static void main (String[] args)
    {
        CSVFile csv= new CSVFile("data/staff_list.csv",',');
        //Create a text format reader from our IReadable that takes in a text file
        IReadable textFormatReader = new TextFormatReader(csv);
        textFormatReader.read();
        DataParser parser = new CSVParser();
        parser.parse(csv);
        IWritable writer = new TextFormatWriter(csv);
        writer.write(csv.getRawData());
        System.out.println(csv.getRawData());
    }
}
