package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;

/**
 * The CampSortOperation is an interface that allows filtering options for camps to easily extend from this class
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public interface CampSortOperation {
    /**
     * A sort function that works through abstractions
     *
     * @param camps list of camps
     */
    void sort(ArrayList<Camp> camps);
}
