package source.Controllers;


import source.Database.Dao.SuggestionDao;
import source.Database.SuggestionDaoImpl;
import source.Utility.DirectoryUtility;

/**
 * The SuggestionManager class abstracts the relevant suggestion operations CampCommitteeMember/staff can use.
 *
 * @author Pan Haolun
 * @version 1.0
 * @since 11/20/2023
 */
public final class SuggestionManager {

    /**
     * The suggest data access object that contains direct implementations to our database..
     */
    private final SuggestionDao suggestionDao;

    /**
     * A default constructor.
     */
    public SuggestionManager() {
        suggestionDao = new SuggestionDaoImpl(DirectoryUtility.SUGGESTIONS_DATA_PATH);
    }
}
