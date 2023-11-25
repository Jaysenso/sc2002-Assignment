package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;

import static java.util.Comparator.comparing;

/**
 * A class that contains the implementation of sorting the name of camps in asscending order.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public class SortNameAscending implements CampSortOperation {
    /**
     * A sort function that works through abstractions
     *
     * @param campList list of camps
     */
    @Override
    public void sort(ArrayList<Camp> campList) {
        campList.sort(comparing(camp -> camp.getCampInfo().getName().toLowerCase()));
    }
}
