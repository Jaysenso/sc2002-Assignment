package source.FileIO.Serializer.Text;

import source.Controllers.CampManager;
import source.Database.App;
import source.Database.DatabaseQuery;
import source.Entity.Camp;
import source.Entity.Student;
import source.Entity.Suggestion;
import source.Faculty.Faculty;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The SuggestionDeserializer class deserializes a hashmap of parsed data a list of Student objects to be used.
 * NOTE: Requires a file read to go through the Parser!
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Student
 * @since 11/4/2023
 */
public class SuggestionDeserializer implements TextDataDeserializer {
    /**
     * Deserializes the data to and creates suggestion objects based on a hash map of values.
     */
    @Override
    public ArrayList deserialize(HashMap<String, ArrayList<String>> parsedData) {
        ArrayList suggestionList = new ArrayList();
        int len = parsedData.get("camp_name").size();
        if (len == 0)
            return new ArrayList();

        for (int i = 0; i < len; i++) {
            //Get all the individual attributes
            String campName = parsedData.get("camp_name").get(i);
            String createdBy = parsedData.get("created_by").get(i);
            String repliedBy = parsedData.get("replied_by").get(i);
            String content = parsedData.get("content").get(i);
            boolean isProcessed = Boolean.valueOf(parsedData.get("isProcessed").get(i));
            boolean isApproved = Boolean.valueOf(parsedData.get("isApproved").get(i));
            LocalDate createdDate = LocalDate.parse(parsedData.get("created_date").get(i));
            LocalDate repliedDate = LocalDate.parse(parsedData.get("replied_date").get(i));
            Suggestion s = new Suggestion(
                    campName,
                    createdBy,
                    repliedBy,
                    content,
                    isProcessed,
                    isApproved,
                    createdDate,
                    repliedDate
            );
            suggestionList.add(s);
        }
        return suggestionList;
    }
}
