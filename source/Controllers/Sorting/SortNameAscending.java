package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.comparing;

public class SortNameAscending implements CampSortOperation {

    @Override
    public void sort(ArrayList<Camp> campList) {
        campList.sort(comparing(camp -> camp.getCampInfo().getName().toLowerCase()));
    }
}
