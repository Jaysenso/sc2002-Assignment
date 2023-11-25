package source.Controllers.Filters;

import source.Entity.Camp;

import java.util.ArrayList;

public class FilterManager {

    public ArrayList<Camp> filter(CampFilterOperation operation, ArrayList<Camp> camps) {
        ArrayList<Camp> campList = (ArrayList) camps.clone();
        //Apply one filter
        campList = operation.filter(camps);
        return campList;
    }

    public ArrayList<Camp> filter(CampFilterOperation[] operations, ArrayList<Camp> camps) {
        ArrayList<Camp> campList = (ArrayList) camps.clone();
        //Apply all the filters available
        for (CampFilterOperation c : operations) {
            campList = c.filter(campList);
        }
        return campList;
    }
}
