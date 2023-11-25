package source.Controllers;


import source.Database.Dao.SuggestionDao;
import source.Database.DatabaseQuery;
import source.Database.SuggestionDaoImpl;
import source.Entity.Camp;
import source.Entity.Enquiry;
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

    //get camp committee suggestions
    public ArrayList<Suggestion> getCampCommitteeSuggestions(String name){
        return suggestionDao.readSuggestions(new DatabaseQuery(name, "created_by"));
    }

    public ArrayList<Suggestion> getCampSuggestions(Camp camp){
        return suggestionDao.readSuggestions(new DatabaseQuery(camp.getCampInfo().getName(), "camp_name"));
    }

    public void createSuggestion(String campName, String createdBy) {
        LocalDate createdDate = LocalDate.now();
        System.out.print("Enter Suggestion Content: ");
        String content = InputHandler.getString();
        Suggestion newSuggestion = new Suggestion(campName, createdBy, "", content, false, false, createdDate, null);
        suggestionDao.createSuggestion(newSuggestion);
    }

    public void deleteSuggestion(Suggestion suggestion){
        suggestionDao.deleteSuggestion(suggestion);
    }

    public void editSuggestion(Suggestion suggestion){
        suggestionDao.updateSuggestion(suggestion);
    }
}
