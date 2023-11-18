package source.FileIO.Serializer.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * The TextDataSerializer interface denotes functions that subclass serializers should have to serialize
 * a list of objects into an array list of strings ready to be written into a file of choice.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public interface TextDataSerializer {
    /**
     * An abstract method for subclasses to implement the serializing of data from an array list of objects.
     *
     * @param objects An ArrayList of actual concrete objects
     * @return serialized list of data in string
     */
    public ArrayList<String> serialize(List objects);
}
