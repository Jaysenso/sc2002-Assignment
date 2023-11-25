package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortDateDescending implements CampSortOperation {

    @Override
    public void sort(ArrayList<Camp> campList) {
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getStartDate()));
        Collections.reverse(campList);
    }
}
