package source.Camp;

import source.Controllers.CampManager;
import source.Entity.Camp;

public class DeleteCamp implements CampService{
    private Camp camp;
    private CampManager campManager;

    public DeleteCamp(Camp camp, CampManager campManager){
        this.camp = camp;
        this.campManager = campManager;
    }

    @Override
    public boolean execute() {
        /**
         * Deletes a camp in the manager/database
         *
         * @return true if delete was successful, false if not.
         */

        return campManager.getCampDao().deleteCamp(camp);
    }
}
