package source.FileIO.Serializer.Text;

import source.Entity.Enquiry;
import source.Entity.Staff;
import source.Faculty.Faculty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The EnquiryDeserializer class deserializes a hashmap of parsed data a list of enquiry objects to be used.
 * NOTE: Requires a file read to go through the Parser!
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Staff
 * @since 11/4/2023
 */
public class EnquiryDeserializer implements TextDataDeserializer {
    /**
     * Deserializes the data to and creates enquiry objects based on a hash map of values.
     */
    @Override
    public ArrayList deserialize(HashMap<String, ArrayList<String>> parsedData) {
        ArrayList enquiryList = new ArrayList();
        int len = parsedData.get("camp_name").size();
        if (len == 0)
            return new ArrayList();
        for (int i = 0; i < len; i++) {
            String campName = parsedData.get("camp_name").get(i);
            String createdBy = parsedData.get("created_by").get(i);
            String content = parsedData.get("content").get(i);
            String title = parsedData.get("title").get(i);
            String repliedBy = parsedData.get("replied_by").get(i);
            LocalDate createdDate = LocalDate.parse(parsedData.get("created_on").get(i));
            LocalDate repliedDate = LocalDate.parse(parsedData.get("replied_on").get(i));
            enquiryList.add(new Enquiry(
                    campName,
                    createdBy,
                    repliedBy,
                    content,
                    title,
                    createdDate,
                    repliedDate
            ));
        }
        return enquiryList;
    }
}
