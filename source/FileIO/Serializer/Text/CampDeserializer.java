package source.FileIO.Serializer.Text;

import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Faculty.Faculty;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The CampDeserializer class deserializes a hashmap of parsed data a list of Camp objects to be used.
 * NOTE: Requires a file read to go through the Parser!
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Camp
 * @since 11/4/2023
 */
public class CampDeserializer implements TextDataDeserializer {
    @Override
    public ArrayList deserialize(HashMap<String, ArrayList<String>> parsedData) {
        ArrayList campList = new ArrayList();
        int len = parsedData.get("camp_name").size();
        if(len <= 0)
            return new ArrayList();
        for (int i = 0; i < len; i++) {
            //Get all the individual attributes
            String campName = parsedData.get("camp_name").get(i);
            int currentSlots = Integer.parseInt(parsedData.get("current_slot").get(i));
            int maxSlots = Integer.parseInt(parsedData.get("max_slots").get(i));
            int committeeSlots = Integer.parseInt(parsedData.get("committee_slots").get(i));
            String description = parsedData.get("description").get(i);
            String staffID = parsedData.get("staff_in_charge").get(i);

            LocalDate startDate = LocalDate.parse(parsedData.get("start_date").get(i));
            LocalDate endDate = LocalDate.parse(parsedData.get("start_date").get(i));
            LocalDate closingDate = LocalDate.parse(parsedData.get("start_date").get(i));
            boolean visibility = Boolean.valueOf(parsedData.get("visibility").get(i));
            String faculty = "source.Faculty." + parsedData.get("faculty").get(i);
            Faculty f = null;
            try {
                f = (Faculty) Class.forName(faculty).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            campList.add(new Camp(new CampInfo(
                    campName,
                    currentSlots,
                    maxSlots,
                    committeeSlots,
                    description,
                    staffID,
                    startDate,
                    endDate,
                    closingDate,
                    f
            ),
                    visibility,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    new ArrayList<>()));
        }
        return campList;
    }
}
