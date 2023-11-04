package source.FileIO.Serializer.Object;

import java.util.List;

/**
 * The IObjectSerializable interface provides a method signature for serializing a list of data into a database file.
 * The actual implementation is dependent on the classes that realize this interface.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public interface IObjectSerializable {
    public void serialize(String filePath, List data);
}
