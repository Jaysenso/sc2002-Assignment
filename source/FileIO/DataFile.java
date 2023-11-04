package source.FileIO;

/**
 * The DataFile class is a base type abstract class that stores the necessary attributes
 * to a file like filePath that all file types should inherit from
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/4/2023
 */
public abstract class DataFile {
    /**
     * The path of the file in the project directory.
     */
    private String filePath;


    /**
     * A default constructor that initialises the attributes to a default value.
     */
    DataFile() {
        this.filePath = "Unknown File";
    }

    /**
     * An overloaded constructor that initialises the filePath attribute.
     *
     * @param filePath the path of the file.
     */
    DataFile(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Getter method for filePath.
     *
     * @return the path of the file.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Setter method for filePath.
     *
     * @param filePath the path of the file.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
