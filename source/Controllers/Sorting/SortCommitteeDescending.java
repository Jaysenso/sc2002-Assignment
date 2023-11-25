package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A class that contains the implementation of sorting the number of committee members in descending order.
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/22/2023
 */
public class SortCommitteeDescending implements CampSortOperation {

    @Override
    public void sort(ArrayList<Camp> campList) {
        campList.sort(Comparator.comparingInt(camp -> -camp.getCampInfo().getCampCommitteeSlots()));
    }
}
