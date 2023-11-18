package source.FileIO.Serializer.Text;

import source.Entity.Student;
import source.Entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The StudentSerializer class serializes a list of Student objects into an array list of strings ready to be written
 * into a text file of choice.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Student
 * @since 11/4/2023
 */
public class StudentSerializer implements TextDataSerializer {
    /**
     * The delimiter to seperate between entries.
     */
    private final char DELIMITER;
    /**
     * Denotes whether to serialize this object with a header or not.
     */
    private final boolean useHeader;
    /**
     * Temporary solution till I think of a better one
     * Holds the headers of our csv files.
     */
    private final String[] headers = {"name", "userid", "password", "faculty"};

    /**
     * A default constructor.
     */
    public StudentSerializer() {
        DELIMITER = ',';
        useHeader = true;
    }

    /**
     * An overloaded constructor that specifies the delimiter on how to seperate entries in our file
     *
     * @param delimiter seperating character between entries
     */
    public StudentSerializer(char delimiter, boolean useHeader) {
        this.DELIMITER = delimiter;
        this.useHeader = useHeader;
    }

    /**
     * Serializes a list of objects into a string ready to be put into our other classes.
     *
     * @param objects An arraylist of objects to serialize
     */
    @Override
    public ArrayList<String> serialize(List objects) {

        //Make a new string builder
        StringBuilder sb = new StringBuilder();
        ArrayList<String> serializedData = new ArrayList<String>();
        if (useHeader) {
            for (int i = 0; i < headers.length; i++) {
                //Append the field name as a header
                sb.append(headers[i]);
                //Append delimiter up to the last element
                if (i != headers.length - 1)
                    sb.append(DELIMITER);
            }
            //Add the header line
            serializedData.add(sb.toString());
        }
        //Loop through the objects
        for (int i = 0; i < objects.size(); i++) {
            //Build our string
            Student user = (Student) objects.get(i);
            sb = new StringBuilder();
            sb.append(user.getName());
            sb.append(DELIMITER);
            sb.append(user.getUserID());
            sb.append(DELIMITER);
            sb.append(user.getPassword());
            sb.append(DELIMITER);
            sb.append(user.getFacultyInfo().getClass().getSimpleName());
            serializedData.add(sb.toString());
        }
        return serializedData;
    }
}
