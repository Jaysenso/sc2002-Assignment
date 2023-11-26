package source.Controllers;

import source.CampOperations.CampOperations;
import source.Database.CampDaoImpl;
import source.Database.Dao.CampDao;
import source.Utility.*;

/**
 * The CampManager class holds all logic for the creation and handling of camps and is the intermediary between
 * the dao and the app logic. Works through abstraction
 *
 * @author Isaac Chun
 * @version 1.4
 * @since 11/23/2023
 */
public final class CampManager {
    /**
     * The camp data access object that contains direct implementations to our database
     */
    private final CampDao campDao;

    /**
     * A default constructor.
     */
    public CampManager() {
        this.campDao = new CampDaoImpl(DirectoryUtility.CAMP_LIST_PATH);
    }

    /**
     * A function to use a particular camp service
     *
     * @param campOperations camp operation to use
     */
    public boolean operate(CampOperations campOperations) {
        return campOperations.execute();
    }

    /**
     * A function to acquire our data access object for camp
     *
     * @return camp dao
     */
    public CampDao getCampDao() {
        return campDao;
    }
}
