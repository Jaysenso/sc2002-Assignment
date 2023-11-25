package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;

/**
 * The SortManager is a class that works through abstractions defined by sorting operations
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public class SortManager {
    /**
     * A sort function that works through abstractions
     *
     * @param operation the sorting operation to use
     * @param camps     list of camps
     */
    public void sort(CampSortOperation operation, ArrayList<Camp> camps) {
        operation.sort(camps);
    }

    /**
     * A sort function that works through abstractions
     *
     * @param operations a list of sorting operations to use
     * @param camps      list of camps
     */
    public void sort(CampSortOperation[] operations, ArrayList<Camp> camps) {
        //Go through all the various sorting functions if needed
        for (CampSortOperation c : operations) {
            c.sort(camps);
        }
    }
}
