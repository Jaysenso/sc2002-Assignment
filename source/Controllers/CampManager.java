package source.Controllers;

import source.Camp.CampService;
import source.Database.CampDaoImpl;
import source.Database.Dao.CampDao;
import source.Database.DatabaseQuery;
import source.Entity.Camp;
import source.Entity.Student;
import source.Utility.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The CampManager class holds all logic for the creation and handling of camps and is the intermediary between
 * the dao and the app logic.
 *
 * @author Isaac Chun
 * @version 1.4
 * @since 11/23/2023
 */
public final class CampManager {
    public CampDao getCampDao() {
        return campDao;
    }

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

    public boolean useCampService(CampService campService){
        return campService.execute();
    }
    public void refresh() {
        campDao.refresh();
    }

    public void loadContext() {
        campDao.loadContext();
    }

}
