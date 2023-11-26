package source.Controllers;

import source.Database.Dao.EnquiryDao;
import source.Database.EnquiryDaoImpl;
import source.EnquiryOperations.EnquiryOperations;
import source.Entity.Enquiry;
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

    /**
     * A function to use a particular enquiry service
     *
     * @param enquiryOperations enquiry operation to use
     */
    public void operate(EnquiryOperations enquiryOperations) {
        enquiryOperations.execute();
    }

    /**
     * A function to acquire our data access object for enquiry
     *
     * @return enquiry dao
     */
    public EnquiryDao getEnquiryDao() {
        return enquiryDao;
    }

    /**
     * A function to acquire an enquiry given a name
     *
     * @return enquiry object
     */
    public Enquiry getEnquiryByName(String name) {
        for (Enquiry s : enquiryDao.getEnquiries()) {
            if (s.getCreatedBy().equals(name)) {
                return s;
            }
        }
        return null;
    }
}
