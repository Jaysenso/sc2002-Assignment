package source.Utility;

/**
 * A class that contains static functions to handle pretty printing of the UI
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/6/2023
 */
public class SubOptions {
    /**
     * The description of this sub option
     */
    private final String description;
    /**
     * The proportion of space to take up in the given box
     */
    private final double proportion;

    /**
     * An overloaded constructor that initializes the descriptino and proportion
     *
     * @param description the description
     * @param proportion  the proportion of space (0.0 to 1.0f)
     */
    public SubOptions(String description, double proportion) {
        this.description = description;
        this.proportion = proportion;
    }

    /**
     * Returns the description of this sub option
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the proportion of this sub option
     *
     * @return proportion
     */
    public double getProportion() {
        return proportion;
    }
}
