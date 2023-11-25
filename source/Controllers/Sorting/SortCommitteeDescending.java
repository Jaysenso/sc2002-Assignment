package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortCommitteeDescending implements CampSortOperation {

    @Override
    public void sort(ArrayList<Camp> campList) {
        campList.sort(Comparator.comparingInt(camp -> -camp.getCampInfo().getCampCommitteeSlots()));
    }
}
