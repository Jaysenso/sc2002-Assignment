package source.FileIO.Serializer.Text;

import source.Entity.Student;
import source.Faculty.Faculty;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The StudentDeserializer class deserializes a hashmap of parsed data a list of Student objects to be used.
 * NOTE: Requires a file read to go through the Parser!
 *
 * @author Isaac Chun
 * @version 1.0
 * @see Student
 * @since 11/4/2023
 */
public class StudentDeserializer implements TextDataDeserializer {
    /**
     * Deserializes the data to and creates student objects based on a hash map of values.
     *
     * @param parsedData the parsed data
     * @return list of student objects
     */
    @Override
    public ArrayList deserialize(HashMap<String, ArrayList<String>> parsedData) {
        ArrayList studentList = new ArrayList();
        int len = parsedData.get("name").size();
        if (len == 0)
            return new ArrayList();
        for (int i = 0; i < len; i++) {
            String name = parsedData.get("name").get(i);
            String userid = parsedData.get("userid").get(i);
            String faculty = "source.Faculty." + parsedData.get("faculty").get(i);
            Faculty f = null;
            try {
                f = (Faculty) Class.forName(faculty).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            String password = parsedData.get("password").get(i);
            int accumulatedPoints = Integer.valueOf(parsedData.get("points").get(i));
            Student student = new Student(name, userid, password, f, accumulatedPoints);
            studentList.add(student);
        }
        return studentList;
    }
}
