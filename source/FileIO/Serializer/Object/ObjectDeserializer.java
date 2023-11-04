package source.FileIO.Serializer.Object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The ObjectDeserializer provides functionalities for deserializing a database file into a list
 * of serialized objects.
 * NOTE!: This only works with classes that implement the Serializable interface.
 * This class works in a very general manner, and can be used for any object types that satisfy the criteria mentioned above this line.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see java.io.Serializable
 * @since 11/4/2023
 */
public class ObjectDeserializer {
    public List deserialize(String filePath) {
        List data = null;
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(filePath);
            in = new ObjectInputStream(fis);
            data = (ArrayList) in.readObject();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return data;
    }
}
