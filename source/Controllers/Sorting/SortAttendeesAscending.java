package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class that contains the implementation of sorting attendees in ascending order.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public class SortAttendeesAscending implements CampSortOperation {
    /**
     * A sort function that works through abstractions
     *
     * @param campList list of camps
     */
    @Override
    public void sort(ArrayList<Camp> campList) {
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getCurrentSlots()));
    }
}
