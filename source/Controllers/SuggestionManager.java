package source.Controllers;


import source.Database.Dao.SuggestionDao;
import source.Database.SuggestionDaoImpl;
import source.Entity.Camp;
import source.Entity.Suggestion;
import source.Utility.DirectoryUtility;
import source.Utility.InputHandler;

import java.time.LocalDate;
import java.util.ArrayList;

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
        this.suggestionDao = new SuggestionDaoImpl(DirectoryUtility.SUGGESTIONS_DATA_PATH);
    }

    /**
     * Acquires all the suggestions belonging to a camp committee
     *
     * @return an array list of suggestions
     */
    public ArrayList<Suggestion> getCampCommitteeSuggestions(String name) {
        ArrayList<Suggestion> filtered = new ArrayList<>();
        ArrayList<Suggestion> suggestions = suggestionDao.getSuggestions();
        for (Suggestion s : suggestions) {
            if (s.getCreatedBy().equals(name))
                filtered.add(s);
        }
        return filtered;
    }

    /**
     * Acquires all the suggestions in a camp
     *
     * @return an array list of suggestions
     */
    public ArrayList<Suggestion> getCampSuggestions(Camp camp) {
        ArrayList<Suggestion> filtered = new ArrayList<Suggestion>();
        ArrayList<Suggestion> suggestions = suggestionDao.getSuggestions();
        for (Suggestion s : suggestions) {
            if (s.getCampName().equals(camp.getCampInfo().getName()))
                filtered.add(s);
        }
        return filtered;
    }

    public void createSuggestion(String campName, String createdBy) {
        LocalDate createdDate = LocalDate.now();
        System.out.print("Enter Suggestion Content: ");
        String content = InputHandler.getString();
        Suggestion newSuggestion = new Suggestion(campName, createdBy, "", content, false, false, createdDate, null);
        suggestionDao.createSuggestion(newSuggestion);
    }

    /**
     * Deletes a suggestion from the database
     *
     * @param suggestion suggestions
     */
    public void deleteSuggestion(Suggestion suggestion) {
        suggestionDao.deleteSuggestion(suggestion);
    }

    /**
     * Updates a suggestion in our database
     *
     * @param suggestion suggestions
     */
    public void update(Suggestion suggestion) {
        suggestionDao.updateSuggestion(suggestion);
    }

    /**
     * Acquires the list of suggestions
     *
     * @return array list of suggestions
     */
    public ArrayList<Suggestion> getSuggestions() {
        return suggestionDao.getSuggestions();
    }

}
