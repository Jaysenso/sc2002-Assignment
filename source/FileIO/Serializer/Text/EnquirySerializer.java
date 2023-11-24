package source.FileIO.Serializer.Text;

import source.Entity.Enquiry;
import source.Entity.Staff;
import source.Utility.SerializeBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The EnquirySerializer class serializes a list of Enquiry objects into an array list of strings ready to be written
 * into a text file of choice.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Staff
 * @since 11/4/2023
 */
public class EnquirySerializer extends BaseSerializer implements TextDataSerializer {

    /**
     * Holds the headers of our csv files.
     */
    private final String[] headers = {
            "camp_name",
            "created_by",
            "title",
            "content",
            "created_on",
            "replied_by",
            "replied_on",
            "processed"
    };

    /**
     * A default constructor.
     */
    public EnquirySerializer() {
        super();
    }

    /**
     * An overloaded constructor that specifies the delimiter on how to seperate entries in our file
     *
     * @param delimiter seperating character between entries
     */
    public EnquirySerializer(char delimiter, boolean useHeader) {
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
            //Build our string

            if (objects.get(i) instanceof Enquiry enquiry) {
                LocalDate repliedDate = enquiry.getRepliedDate();
                String rDate = (repliedDate ==null) ? "N/A" : repliedDate.toString();
                String rBy = (enquiry.getRepliedBy().isEmpty()) ? "N/A" : enquiry.getRepliedBy();
                String studentData = SerializeBuilder.buildSerializedString(
                        new String[]{
                                enquiry.getCampName(),
                                enquiry.getCreatedBy(),
                                enquiry.getTitle(),
                                enquiry.getContent(),
                                enquiry.getCreatedDate().toString(),
                                rBy,
                                rDate,
                                String.valueOf(enquiry.getProcessed())
                        },
                        delimiter
                );
                serializedData.add(studentData);
            }
        }
        return serializedData;
    }
}
