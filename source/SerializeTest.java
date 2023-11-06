package source;

import source.Entity.Student;
import source.FileIO.Parser.Parser;
import source.FileIO.Serializer.Object.ObjectDeserializer;
import source.FileIO.Serializer.Object.ObjectSerializer;
import source.FileIO.Serializer.Text.StudentDeserializer;
import source.FileIO.Serializer.Text.StudentSerializer;
import source.FileIO.Serializer.Text.TextDataDeserializer;
import source.FileIO.Serializer.Text.TextDataSerializer;
import source.FileIO.TextDataFile;
import source.FileIO.TextDataReader;
import source.FileIO.TextDataWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SerializeTest {
    public static void main(String[] args) {
        String filePath = "data/student_list.csv";
        //instantiate a reader when you need it
        TextDataReader reader = new TextDataReader();
        //Return a string of data given from the reader
        ArrayList<String> rawData = reader.read(filePath);
        //We then create a parser of type csv to parse our data
        Parser parser = new Parser();
        //Then we set the data in our csv file given the parse results
        HashMap<String, ArrayList<String>> data = parser.parse(rawData);
        TextDataFile newFile = new TextDataFile(filePath, rawData,data);

        TextDataDeserializer deserializer = new StudentDeserializer();
        ArrayList currStudentList = deserializer.deserialize(newFile.getData());

        for(Object s : currStudentList)
        {
            s =(Student)s;
            System.out.println(s);
        }

        TextDataSerializer serializer = new StudentSerializer();
        ArrayList<String> serializedStudents = serializer.serialize(currStudentList);
        TextDataWriter writer = new TextDataWriter();
        writer.write("data/student_list.csv", serializedStudents);

        ObjectSerializer os = new ObjectSerializer();
        os.serialize("data/student_list.dat", currStudentList);

        ObjectDeserializer od = new ObjectDeserializer();
        List obj = od.deserialize("data/student_list.dat");
        for(Object s : obj)
        {
            s =(Student)s;
            //System.out.println(s);
        }

    }
}
