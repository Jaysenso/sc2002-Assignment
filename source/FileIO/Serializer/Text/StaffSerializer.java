package source.FileIO.Serializer.Text;

import source.Entity.Camp;
import source.Entity.Staff;
import source.Utility.SerializeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * The StaffSerializer class serializes a list of Staff objects into an array list of strings ready to be written
 * into a text file of choice.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Staff
 * @since 11/4/2023
 */
public class StaffSerializer extends BaseSerializer implements TextDataSerializer {

    /**
     * Holds the headers of our csv files.
     */
    private final String[] headers = {
            "name",
            "userid",
            "email",
            "password",
            "faculty",
            "created_camps"};

    /**
     * A default constructor.
     */
    public StaffSerializer() {
        super();
    }

    /**
     * An overloaded constructor that specifies the delimiter on how to seperate entries in our file
     *
     * @param delimiter seperating character between entries
     */
    public StaffSerializer(char delimiter, boolean useHeader) {
        super(delimiter, useHeader);
    }

    /**
     * Serializes a list of objects into a string ready to be put into our other classes.
     *
     * @param objects An arraylist of objects to serialize
     * @return list of strings in serialized format
     */
    @Override
    public ArrayList<String> serialize(List objects) {
        //Make a new string builder
        ArrayList<String> serializedData = new ArrayList<String>();
        if (useHeader) {
            String serializedHeader = SerializeBuilder.buildSerializedString(headers, delimiter);
            //Add the header line
            serializedData.add(serializedHeader);
        }
        //Loop through the objects
        for (int i = 0; i < objects.size(); i++) {
            //Build our string
            if (objects.get(i) instanceof Staff user) {
                ArrayList<Camp> camps = user.getCreatedCamps();
                String[] campNames = new String[camps.size()];
                for (int j = 0; j < camps.size(); j++) {
                    campNames[j] = camps.get(j).getCampInfo().getName();
                }
                //Build camp string
                String serializedCamps = SerializeBuilder.buildSerializedString(campNames,
                        '|');

                if (serializedCamps.isEmpty()) {
                    serializedCamps = "N/A";
                }

                String studentData = SerializeBuilder.buildSerializedString(
                        new String[]{
                                user.getName(),
                                user.getUserID(),
                                user.getUserID() + "@e.ntu.edu.sg",
                                user.getPassword(),
                                user.getFacultyInfo().getClass().getSimpleName(),
                                serializedCamps
                        },
                        delimiter
                );
                serializedData.add(studentData);
            }
        }
        return serializedData;
    }
}
