package source.Controllers.Sorting;

import source.Entity.Camp;

import java.util.ArrayList;

public class SortManager {

    public void sort(CampSortOperation operation, ArrayList<Camp> camps) {
        operation.sort(camps);
    }

    public void sort(CampSortOperation[] operations, ArrayList<Camp> camps) {
        //Go through all the various sorting functions if neededd
        for (CampSortOperation c : operations) {
            c.sort(camps);
        }
    }
}
