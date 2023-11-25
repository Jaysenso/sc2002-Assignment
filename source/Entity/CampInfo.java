package source.Entity;

import source.Faculty.Faculty;

import java.time.LocalDate;

/**
 * The CampInfo class holds information about all the information a camp can have
 *
 * @author J'sen Ong
 * @version 1.0
 * @see Camp
 * @see LocalDate
 * @since 11/4/2023
 */
public class CampInfo {
    /**
     * The name of the camp
     */
    private String name;
    /**
     * The location of the camp
     */
    private String location;
    /**
     * The current available slots
     */
    private int currentSlots;
    /**
     * The max slots of this camp
     */
    private int maxSlots;
    /**
     * The number of camp committee slots
     */
    private int campCommitteeSlots;
    /**
     * The number of max camp committee slots
     */
    private int maxCampCommitteeSlots;
    /**
     * The description of the camp
     */
    private String description;
    /**
     * The name of the staff in charge of the camp
     */
    private String staffInCharge;
    /**
     * The start date of this camp
     */
    private LocalDate startDate;
    /**
     * The closing date of this camp
     */
    private LocalDate endDate;
    /**
     * The closing registration date of this camp
     */
    private LocalDate closingDate;
    /**
     * The faculty of the camp
     */
    private Faculty faculty;

    /**
     * An overloaded constructor for the camp info
     *
     * @param name                  The name of the camp
     * @param location              The location of the camp
     * @param currentSlots          The current available slots
     * @param maxSlots              The max slots of this camp
     * @param campCommitteeSlots    The number of camp committee slots
     * @param maxCampCommitteeSlots The number of camp committee slots
     * @param description           The description of the camp
     * @param staffInCharge         The name of the staff in charge of the camp
     * @param startDate             The start date of this camp
     * @param endDate               The closing date of this camp
     * @param closingDate           The closing registration date of this camp
     * @param faculty               The faculty of the camp
     */
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

    /**
     * Acquires the name of the camp
     *
     * @return the name of the camp
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the new name of the camp
     *
     * @param name the new name of the camp
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Acquires the current slots of the camp
     *
     * @return the current slots of the camp
     */
    public int getCurrentSlots() {
        return currentSlots;
    }

    /**
     * Sets the current slots of the camp
     *
     * @param currentSlots the new current slots of the camp
     */
    public void setCurrentSlots(int currentSlots) {
        this.currentSlots = currentSlots;
    }

    /**
     * Acquires the max slots of the camp
     *
     * @return the max slots of the camp
     */
    public int getMaxSlots() {
        return this.maxSlots;
    }

    /**
     * Sets the max slots of the camp
     *
     * @param maxSlots the new max slots of the camp
     */
    public void setMaxSlots(int maxSlots) {
        this.maxSlots = maxSlots;
    }

    /**
     * Acquires the camp committee slots of the camp
     *
     * @return the camp committee slots of the camp
     */
    public int getCampCommitteeSlots() {
        return campCommitteeSlots;
    }

    /**
     * Acquires the max camp committee slots of the camp
     *
     * @return the max camp committee slots of the camp
     */
    public int getMaxCampCommitteeSlots() {
        return maxCampCommitteeSlots;
    }

    /**
     * Sets the max camp committee slots of the camp
     *
     * @param maxCampCommitteeSlots the new max committee slots of the camp
     */
    public void setMaxCampCommitteeSlots(int maxCampCommitteeSlots) {
        this.maxCampCommitteeSlots = maxCampCommitteeSlots;
    }

    /**
     * Sets the camp committee slots of the camp
     *
     * @param campCommitteeSlots the new number of camp committee slots of the camp
     */
    public void setCampCommitteeSlots(int campCommitteeSlots) {
        this.campCommitteeSlots = campCommitteeSlots;
    }

    /**
     * Acquires the description of the camp
     *
     * @return the description of the camp
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the camp
     *
     * @param description the new description of the camp
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Acquires the location of the camp
     *
     * @return the location of the camp
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the camp
     *
     * @param location the new location of the camp
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Acquires the staff in charge of the camp
     *
     * @return the staff in charge of the camp
     */
    public String getStaffInCharge() {
        return staffInCharge;
    }

    /**
     * Sets the staff in charge of the camp
     *
     * @param staffInCharge the new staff in charge of the camp
     */
    public void setStaffInCharge(String staffInCharge) {
        this.staffInCharge = staffInCharge;
    }

    /**
     * Acquires the start date of the camp
     *
     * @return the start date of the camp
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the camp
     *
     * @param startDate the new start date of the camp
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Acquires the end date of the camp
     *
     * @return the end date of the camp
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date of the camp
     *
     * @param endDate the new end date of the camp
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Acquires the closing date of the camp
     *
     * @return the closing date of the camp
     */
    public LocalDate getClosingDate() {
        return closingDate;
    }

    /**
     * Sets the closing date of the camp
     *
     * @param closingDate the new closing date of the camp
     */
    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    /**
     * Acquires the faculty of the camp
     *
     * @return the faculty of the camp
     */
    public Faculty getFaculty() {
        return this.faculty;
    }

    /**
     * Sets the faculty of the camp
     *
     * @param faculty the new faculty of the camp
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
