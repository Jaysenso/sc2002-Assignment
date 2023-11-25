package source.Controllers.Filters;

import source.Entity.Camp;

import java.util.ArrayList;

/**
 * The CampFilterOperation is an interface that allows filtering options for camps to easily extend from this class
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public interface CampFilterOperation {
    /**
     * A generic filter function
     *
     * @param camps list of camps
     */
    ArrayList<Camp> filter(ArrayList<Camp> camps);
}
