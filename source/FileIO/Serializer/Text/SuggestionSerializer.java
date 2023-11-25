package source.FileIO.Serializer.Text;

import source.Entity.Student;
import source.Entity.Suggestion;
import source.Utility.SerializeBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The SuggestionDeserializer class serializes a list of Student objects into an array list of strings ready to be written
 * into a text file of choice.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Student
 * @since 11/4/2023
 */
public class SuggestionSerializer extends BaseSerializer implements TextDataSerializer {

    /**
     * Holds the headers of our csv files.
     */
    private final String[] headers = {
            "camp_name",
            "created_by",
            "replied_by",
            "content",
            "isProcessed",
            "isApproved",
            "created_date",
            "replied_date"
    };

    /**
     * A default constructor.
     */
    public SuggestionSerializer() {
        super();
    }

    /**
     * An overloaded constructor that specifies the delimiter on how to separate entries in our file
     *
     * @param delimiter separating character between entries
     */
    public SuggestionSerializer(char delimiter, boolean useHeader) {
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
            if (objects.get(i) instanceof Suggestion suggestion) {

                String repliedBy = suggestion.getRepliedBy();
                if (repliedBy.isEmpty())
                    repliedBy = "N/A";

                String repliedDate = "N/A";
                LocalDate date = suggestion.getRepliedDate();
                if (date != null)
                    repliedDate = date.toString();

                //Build our string
                String studentData = SerializeBuilder.buildSerializedString(
                        new String[]{
                                suggestion.getCampName(),
                                suggestion.getCreatedBy(),
                                repliedBy,
                                suggestion.getContent(),
                                String.valueOf(suggestion.getProcessed()),
                                String.valueOf(suggestion.getApproved()),
                                suggestion.getCreatedDate().toString(),
                                repliedDate
                        },
                        delimiter
                );
                serializedData.add(studentData);
            }
        }
        return serializedData;
    }
}
