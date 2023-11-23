package source.Controllers;

import source.Entity.Camp;
import source.Entity.Staff;
import source.Entity.User;
import source.Utility.InputHandler;

import java.util.ArrayList;

public final class CampManager{

    private final ArrayList<Camp> campList = new ArrayList<>();

    public void createCamp(){
        String name = InputHandler.getString();
        String description = InputHandler.getString();
    }

    public void deleteCamp(Camp camp) {
        try {
            // Check if the campList contains the specified camp
            if (this.campList.contains(camp)) {
                // If the camp is found, remove it from the list
                this.campList.remove(camp);
                System.out.println("Camp deleted successfully");
            } else {
                // If the camp is not found, throw an exception
                throw new IllegalArgumentException("Camp not found in the list");
            }
        } catch (Exception e) {
            // Handle the exception (e.g., print an error message)
            System.err.println("Error deleting camp: " + e.getMessage());
        }
    }
}
