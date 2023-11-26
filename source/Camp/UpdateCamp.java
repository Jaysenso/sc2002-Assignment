package source.Camp;

import source.Controllers.CampManager;
import source.Entity.Camp;

public class UpdateCamp implements CampService{
    private CampManager campManager;
    private Camp camp;

    public UpdateCamp(Camp camp, CampManager campManager){
        this.camp = camp;
        this.campManager = campManager;
    }

    @Override
    public boolean execute() {
        return campManager.getCampDao().updateCamp(camp);
    }
}
