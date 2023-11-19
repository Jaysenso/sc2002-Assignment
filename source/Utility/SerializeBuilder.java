package source.Utility;

/**
 * A utility class that provides static function to build strings into serialized formats with ease
 *
 * @see StringBuilder
 */
public class SerializeBuilder {
    /**
     * A utility class that provides static function to build strings into serialized formats with ease
     *
     * @param entries   an array of strings
     * @param delimiter the separating character between entries
     */
    public static String buildSerializedString(String[] entries, char delimiter) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < entries.length; i++) {
            //if not at last index, append delimiter
            sb.append(entries[i]);
            if (i != entries.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
}
