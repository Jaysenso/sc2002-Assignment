package source.FileIO.Serializer.Text;

import source.Entity.Camp;
import source.Faculty.Faculty;

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
        int len = parsedData.get("name").size();
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            String name = parsedData.get("name").get(i);
            String userid = parsedData.get("userid").get(i);
            String faculty = "source.Faculty." + parsedData.get("faculty").get(i);
            Faculty f = null;
            try {
                f = (Faculty) Class.forName(faculty).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            String password = parsedData.get("password").get(i);
            //campList.add(new Camp());
        }
        return campList;
    }
}
