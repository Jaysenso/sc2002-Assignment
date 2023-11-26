package source.CampOperations;

import source.Controllers.CampManager;
import source.Entity.Camp;

import java.util.ArrayList;

/**
 * The GetCamps class holds the logic for acquiring the camp list
 *
 * @author Edwin Lim
 * @version 1.4
 * @since 11/23/2023
 */
public class GetCamps implements CampOperations {
    /**
     * The camp manager reference
     */
    private final CampManager campManager;

    /**
     * The list of camps
     */
    private ArrayList<Camp> camps;

    /**
     * Overloaded constructor to initialize a camp and manager
     *
     * @param campManager the camp manager reference
     */
    public GetCamps(CampManager campManager) {
        this.campManager = campManager;
    }

    /**
     * The execution function
     */
    @Override
    public boolean execute() {
        this.camps = campManager.getCampDao().getCamps();
        return true;
    }

    /**
     * A function to acquire the list of camps
     *
     * @return a list of camps
     */
    public ArrayList<Camp> getCamps() {
        return camps;
    }

    /**
     * A function to update the camp list
     *
     * @param camps the new camp list
     */
    public void setCamps(ArrayList<Camp> camps) {
        this.camps = camps;
    }
}
