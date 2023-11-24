package source.Utility;

/**
 * A helper class that stores options and descriptions in a class, like a pair.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/6/2023
 */
public class Option {
    /**
     * A final field to denote our option text. Should be initialized via constructor.
     */
    private final String option;
    /**
     * A final field to denote our description text. Should be initialized via constructor.
     */
    private final String description;

    /**
     * A standard default constructor.
     */
    public Option() {
        this.option = "Default";
        this.description = "This is a sample description!";
    }

    /**
     * An overloaded constructor that sets the option and description text.
     *
     * @param option      the text for our option
     * @param description the description text
     */
    public Option(String option, String description) {
        this.option = option;
        this.description = description;
    }

    /**
     * A getter method to get our action
     */
    public String getOption() {
        return this.option;
    }

    /**
     * A getter method to get our description
     */
    public String getDescription() {
        return this.description;
    }
}
