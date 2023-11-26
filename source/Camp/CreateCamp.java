package source.Camp;

import source.Controllers.CampManager;
import source.Entity.Camp;

public class CreateCamp implements CampService{
    /**
     * A function to take to create a camp and store into our database
     *
     * @param camp the camp to create and store
     */
    private Camp camp;
    private CampManager campManager;

    public CreateCamp(Camp camp, CampManager campManager){
        this.camp = camp;
        this.campManager = campManager;
    }

    @Override
    public boolean execute() {
        campManager.getCampDao().createCamp(camp);
        return true;
    }
}
