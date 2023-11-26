package source.CampOperations;

import source.Controllers.CampManager;
import source.Entity.Camp;

/**
 * The UpdateCamp class holds the logic to update a camp in our database
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class UpdateCamp implements CampOperations {
    /**
     * The camp to create
     */
    private final Camp camp;
    /**
     * The camp manager reference
     */
    private final CampManager campManager;

    /**
     * Overloaded constructor to initialize a camp and manager
     *
     * @param camp        the camp
     * @param campManager the camp manager reference
     */
    public UpdateCamp(Camp camp, CampManager campManager) {
        this.camp = camp;
        this.campManager = campManager;
    }

    /**
     * The execution function
     */
    @Override
    public boolean execute() {
        return campManager.getCampDao().updateCamp(camp);
    }
}
