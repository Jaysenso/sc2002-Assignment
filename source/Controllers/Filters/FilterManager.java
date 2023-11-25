package source.Controllers.Filters;

import source.Entity.Camp;

import java.util.ArrayList;

/**
 * The FilterManager is a class that works through abstractions defined by camp filter operations
 *
 * @author Isaac Chun
 * @version 1.0
 * @see CampFilterOperation
 * @since 11/22/2023
 */
public class FilterManager {
    /**
     * A generic filter function
     *
     * @param operation the camp filter operation to perform
     * @param camps     list of camps
     */
    public ArrayList<Camp> filter(CampFilterOperation operation, ArrayList<Camp> camps) {
        ArrayList<Camp> campList = (ArrayList) camps.clone();
        //Apply one filter
        campList = operation.filter(camps);
        return campList;
    }

    /**
     * A generic filter function
     *
     * @param operations the camp filter operations to perform (an array list)
     * @param camps      list of camps
     */
    public ArrayList<Camp> filter(CampFilterOperation[] operations, ArrayList<Camp> camps) {
        ArrayList<Camp> campList = (ArrayList) camps.clone();
        //Apply all the filters available
        for (CampFilterOperation c : operations) {
            campList = c.filter(campList);
        }
        return campList;
    }
}
