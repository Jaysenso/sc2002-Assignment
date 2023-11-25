package source.Controllers.Filters;

import source.Entity.Camp;

import java.util.ArrayList;

public interface CampFilterOperation {

    ArrayList<Camp> filter(ArrayList<Camp> camps);
}
