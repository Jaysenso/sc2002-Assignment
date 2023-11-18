package source.FileIO.Serializer.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The TextDataDeserializer interface denotes signatures that text-based serializers should implement to deserialize already
 * parsed data. This is so that the only job of this class is to deserialize, and not to parse and deserialize data.
 * The parsing should be done by a general parser class to return our hashmap of values.
 *
 * @author Isaac Chun
 * @version 1.0
 * @see source.FileIO.Parser.Parser
 * @since 11/4/2023
 */
public interface TextDataDeserializer {
    public ArrayList deserialize(HashMap<String, ArrayList<String>> parsedData);
}
