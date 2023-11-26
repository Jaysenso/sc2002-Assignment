package source.Camp;

import source.Controllers.CampManager;
import source.Entity.Camp;
import java.util.ArrayList;

public class GetCamps implements CampService{
    /**
     * Acquires the camps stored in this manager
     *
     * @return list of camps
     */
    private CampManager campManager;
    private ArrayList<Camp> camps;

    public GetCamps(CampManager campManager){
        this.campManager = campManager;
    }

    @Override
    public boolean execute() {
        this.camps = campManager.getCampDao().getCamps();
        return true;
    }

    public ArrayList<Camp> getCamps() {
        return camps;
    }

    public void setCamps(ArrayList<Camp> camps) {
        this.camps = camps;
    }
}
