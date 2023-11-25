package source.Database.Dao;

import source.Database.DatabaseQuery;
import source.Entity.Suggestion;

import java.util.ArrayList;

/**
 * The SuggestionDAO interface provides operations that follow the CRUD operations for
 * persistent data storage. Also provides a method to get an entire list of suggestions
 * and delete them.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/19/2023
 */
public interface SuggestionDao {
    /**
     * Creates a suggestion in the subsequent database
     *
     * @param suggestion the suggestion to add
     * @return true if managed to create, false if not
     */
    boolean createSuggestion(Suggestion suggestion);

    /**
     * Reads a suggestion from the subsequent database using the suggestion name
     *
     * @param query query to check in our header
     * @return a suggestion object if found, null if not.
     */
    Suggestion readSuggestion(DatabaseQuery query);

    /**
     * Reads a suggestion from the subsequent database using the suggestion name
     * MUST SATISFY ALL THE QUERIES
     *
     * @param queries query to check in our header
     * @return a suggestion object if found, null if not.
     */
    Suggestion readSuggestion(DatabaseQuery[] queries);

    /**
     * Reads all suggestions that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param query query to check in our header
     * @return an arraylist of suggestions if found, an empty list if not.
     */
    ArrayList<Suggestion> readSuggestions(DatabaseQuery query);

    /**
     * Reads all suggestions that satisfies a particular property.
     * NOTE: List can be empty if no results are found
     *
     * @param queries query to check in our header
     * @return an arraylist of suggestions if found, an empty list if not.
     */
    ArrayList<Suggestion> readSuggestions(DatabaseQuery[] queries);

    /**
     * Updates a suggestion in the subsequent database
     *
     * @param suggestion the suggestion to add
     * @return true if managed to update, false if not
     */
    boolean updateSuggestion(Suggestion suggestion);

    /**
     * Deletes a suggestion in the subsequent database
     *
     * @param query query to check in our header
     * @return true if managed to delete, false if not
     */
    boolean deleteSuggestion(DatabaseQuery query);

    /**
     * Deletes a suggestion in the subsequent database
     *
     * @return true if managed to delete, false if not
     */
    boolean deleteSuggestion(Suggestion suggestion);

    /**
     * Function to get the list of all suggestion objects if needed
     *
     * @return the list of suggestion stored in the database
     */
    ArrayList<Suggestion> getSuggestions();

    /**
     * Function to refresh the database by saving this context and then reading it again.
     */
    void refresh();
}
