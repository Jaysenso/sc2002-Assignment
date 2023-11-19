package source.FileIO.Serializer.Text;

/**
 * The BaseSerializer class stores the necessary data for sub serializers for the text format.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public abstract class BaseSerializer {
    /**
     * The delimiter to separate between entries.
     */
    protected char delimiter;
    /**
     * Denotes whether to serialize this object with a header or not.
     */
    protected boolean useHeader;

    /**
     * A default constructor that sets the delimiter to a default value of ',' with headers enabled.
     */
    public BaseSerializer() {
        delimiter = ',';
        useHeader = true;
    }

    /**
     * An overloaded constructor that sets the delimiter and header
     *
     * @param delimiter the delimiter to use for separation
     * @param useHeader whether headers are included in serializing
     */
    public BaseSerializer(char delimiter, boolean useHeader) {
        this.delimiter = delimiter;
        this.useHeader = useHeader;
    }
}
