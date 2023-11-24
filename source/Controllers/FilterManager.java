package source.Controllers;

import source.Database.App;
import source.Entity.Camp;
import source.Utility.PrettyPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FilterManager {

    private CampManager campManager = App.getCampManager();
    public void ascendAlphabetical(ArrayList<Camp> campList){
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getName()));
    }
    public void descendAlphabetical(ArrayList<Camp> campList){
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getName()));
        Collections.reverse(campList);
    }

    public void ascendAttendee(ArrayList<Camp> campList){
        campList.sort(Comparator.comparingInt(camp -> camp.getCampInfo().getCurrentSlots()));
    }
    public void descendAttendee(ArrayList<Camp> campList){
        campList.sort(Comparator.comparingInt(camp -> -camp.getCampInfo().getCurrentSlots()));
    }
    public void ascendCommittee(ArrayList<Camp> campList){
        campList.sort(Comparator.comparingInt(camp -> camp.getCampInfo().getCampCommitteeSlots()));
    }
    public void descendCommittee(ArrayList<Camp> campList){
        campList.sort(Comparator.comparingInt(camp -> -camp.getCampInfo().getCampCommitteeSlots()));
    }
    public void ascendDate(ArrayList<Camp> campList){
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getStartDate()));
    }
    public void descendDate(ArrayList<Camp> campList){
        campList.sort(Comparator.comparing(camp -> camp.getCampInfo().getStartDate()));
        Collections.reverse(campList);
    }

    public void viewAll(int filtertype){
        switch (filtertype) {
            case 1: {
                ascendAlphabetical(campManager.getCamps());
                PrettyPage.printCamps(campManager.getCamps());
                break;
            }
            case 2: {
                descendAlphabetical(campManager.getCamps());
                PrettyPage.printCamps(campManager.getCamps());
                break;
            }
            case 3: {
                ascendAttendee(campManager.getCamps());
                PrettyPage.printCamps(campManager.getCamps());
                break;
            }
            case 4: {
                descendAttendee(campManager.getCamps());
                PrettyPage.printCamps(campManager.getCamps());
            }
            case 5: {
                ascendCommittee(campManager.getCamps());
                PrettyPage.printCamps(campManager.getCamps());
                break;
            }
            case 6: {
                descendCommittee(campManager.getCamps());
                PrettyPage.printCamps(campManager.getCamps());
                break;
            }
            case 7: {
                ascendDate(campManager.getCamps());
                PrettyPage.printCamps(campManager.getCamps());
                break;
            }
            case 8: {
                descendDate(campManager.getCamps());
                PrettyPage.printCamps(campManager.getCamps());
                break;
            }
        }
    }







}
