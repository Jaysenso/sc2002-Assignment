package source.Entity;

import java.time.LocalDate;

public class CampInfo {
    private String name;
    private int currentSlots;
    private int maxSlots;
    private int campCommitteeSlots;
    private String description;
    private Staff staffInCharge;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate closingDate;

    //basic constructor all fields must be present
    public CampInfo
            (
            String name,
            int currentSlots,
            int maxSlots,
            int campCommitteeSlots,
            String description,
            Staff staffInCharge,
            LocalDate startDate,
            LocalDate endDate,
            LocalDate closingDate
            )
    {
        this.name = name;
        this.currentSlots = currentSlots;
        this.maxSlots = maxSlots;
        this.campCommitteeSlots = campCommitteeSlots;
        this.description = description;
        this.staffInCharge = staffInCharge;
        this.startDate = startDate;
        this.endDate = endDate;
        this.closingDate = closingDate;
    }


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

    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Staff getStaffInCharge() {
        return staffInCharge;
    }

    public void setStaffInCharge(Staff staffInCharge) {
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
}
