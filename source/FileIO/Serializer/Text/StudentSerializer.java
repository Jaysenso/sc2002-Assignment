package source.FileIO.Serializer.Text;

import source.Entity.Student;
import source.Entity.User;
import source.Utility.SerializeBuilder;

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
public class StudentSerializer extends BaseSerializer implements TextDataSerializer {

    /**
     * Holds the headers of our csv files.
     */
    private final String[] headers = {
            "name",
            "userid",
            "email",
            "password",
            "faculty",
            "points",
            "registered_camps",
            "camp_committee_of"};

    /**
     * A default constructor.
     */
    public StudentSerializer() {
        super();
    }

    /**
     * An overloaded constructor that specifies the delimiter on how to seperate entries in our file
     *
     * @param delimiter seperating character between entries
     */
    public StudentSerializer(char delimiter, boolean useHeader) {
        super(delimiter, useHeader);
    }

    /**
     * Serializes a list of objects into a string ready to be put into our other classes.
     *
     * @param objects An arraylist of objects to serialize
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
            if (objects.get(i) instanceof Student user) {
                String[] attendingCamps = new String[user.getRegisteredCamps().size()];

                //Store the name of the camps in the string and then build it
                for (int j = 0; j < user.getRegisteredCamps().size(); j++) {
                    attendingCamps[j] = user.getRegisteredCamps().get(j).getCampInfo().getName();
                }
                //Build committee string
                String serializedCamps = SerializeBuilder.buildSerializedString(attendingCamps,
                        '|'
                );
                if (serializedCamps.isEmpty())
                    serializedCamps = "N/A";

                String campCommiteeOf = (user.getIsCampCommittee() == null) ? "N/A" : user.getIsCampCommittee().getCampInfo().getName();

                //Build our string
                String studentData = SerializeBuilder.buildSerializedString(
                        new String[]{
                                user.getName(),
                                user.getUserID(),
                                user.getUserID() + "@e.ntu.edu.sg",
                                user.getPassword(),
                                user.getFacultyInfo().getClass().getSimpleName(),
                                String.valueOf(user.getAccumulatedPoints()),
                                serializedCamps,
                                campCommiteeOf
                        },
                        delimiter
                );
                serializedData.add(studentData);
            }
        }
        return serializedData;
    }
}
