package source.FileIO.Serializer.Object;

import java.util.List;

/**
 * The IObjectDeserializable interface gives a method signature for deserializing a database file into a list of serialized objects.
 * The actual implementation is dependent on the classes that realize this interface.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public interface IObjectDeserializable {
    public List deserialize(String filePath);
}
