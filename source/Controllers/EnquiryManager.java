package source.Controllers;

import source.Database.Dao.EnquiryDao;
import source.Database.EnquiryDaoImpl;
import source.EnquiryOperations.EnquiryOperations;
import source.Utility.DirectoryUtility;

/**
 * The EnquiryManager class serves as a DB and abstracts the Data Access Object implementations and provides
 * what's necessary for others to be used for enquiries
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class EnquiryManager {

    /**
     * The enquiry data access object that contains direct implementations to our database..
     */
    private final EnquiryDao enquiryDao;

    /**
     * A default constructor.
     */
    public EnquiryManager() {

        this.enquiryDao = new EnquiryDaoImpl(DirectoryUtility.ENQUIRY_LIST_PATH);
    }

    public void operate(EnquiryOperations enquiryOperations){
        enquiryOperations.execute();
    }

    public EnquiryDao getEnquiryDao() {
        return enquiryDao;
    }

}
