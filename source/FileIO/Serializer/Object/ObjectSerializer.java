package source.FileIO.Serializer.Object;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * The ObjectSerializer provides functionalities for serializing a list of objects/data into
 * a database file.
 * NOTE!: This only works with classes that implement the Serializable interface.
 * This class works in a very general manner, and can be used for any object types that satisfy the criteria mentioned above this line.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public class ObjectSerializer {
    public void serialize(String filePath, List data) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(filePath);
            out = new ObjectOutputStream(fos);
            out.writeObject(data);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
