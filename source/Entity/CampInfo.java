package source.Entity;

import source.Faculty.Faculty;

import java.time.LocalDate;

public class CampInfo {
    private String name;
    private String location;
    private int currentSlots;
    private int maxSlots;
    private int campCommitteeSlots;
    private int maxCampCommitteeSlots;
    private String description;
    private String staffInCharge;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate closingDate;
    private Faculty faculty;

    //basic constructor all fields must be present
    public CampInfo(String name,
                    String location,
                    int currentSlots,
                    int maxSlots,
                    int campCommitteeSlots,
                    int maxCampCommitteeSlots,
                    String description,
                    String staffInCharge,
                    LocalDate startDate,
                    LocalDate endDate,
                    LocalDate closingDate,
                    Faculty faculty) {
        this.name = name;
        this.location = location;
        this.currentSlots = currentSlots;
        this.maxSlots = maxSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.maxCampCommitteeSlots = maxCampCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closingDate = closingDate;
        this.faculty = faculty;
    }

    public void updateCurrentSlot(int currentSlots){
        this.currentSlots = currentSlots;
    }

    public void updateCampCommitteeSlots(int currentSlots) {this.campCommitteeSlots = currentSlots;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentSlots() {
        return currentSlots;
    }

    public void setCurrentSlots(int currentSlots) {
        this.currentSlots = currentSlots;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    public int getMaxCampCommitteeSlots() {return maxCampCommitteeSlots; }

    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStaffInCharge() {
        return staffInCharge;
    }

    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    public void setMaxCampCommitteeSlots(int maxCampCommitteeSlots) {
        this.maxCampCommitteeSlots = maxCampCommitteeSlots;
    }

}
