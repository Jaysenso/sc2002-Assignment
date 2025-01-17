package source.CampOperations;

import source.Controllers.CampManager;
import source.Entity.Camp;
import source.Utility.PrettyPage;

/**
 * The DeleteCamp class holds the logic for deleting a camp
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class DeleteCamp implements CampOperations {
    /**
     * The camp to delete
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
    public DeleteCamp(Camp camp, CampManager campManager) {
        this.camp = camp;
        this.campManager = campManager;
    }

    /**
     * The execution function
     */
    @Override
    public boolean execute() {
        //Check if there are already students
        if (camp.getCampInfoCurrentSlots() > 0) {
            PrettyPage.printError("A camp cannot be deleted as students have already registered for it!");
            return false;
        }
        return campManager.getCampDao().deleteCamp(camp);
    }
}
