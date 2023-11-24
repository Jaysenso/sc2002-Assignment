package source.Utility;

import source.FileIO.IWritable;

import java.time.LocalDate;

/**
 * The DataRangeValidator provides functions to check whether a given date is in between two dates
 *
 * @author Isaac Chun
 * @version 1.0
 * @see IWritable
 * @since 11/4/2023
 */
public final class DateRangeValidator {
    /**
     * The start date of the range
     */
    private final LocalDate startDate;
    /**
     * The end date of the range
     */
    private final LocalDate endDate;

    /**
     * An overloaded constructor that defines the bounds of the date ranges
     *
     * @param startDate start date;
     * @param endDate   end date
     */
    public DateRangeValidator(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Checks if the date is within range of the start and end date defined in this class.
     *
     * @return true if within range, false if not.
     */
    public boolean isWithinRange(LocalDate testDate) {
        // exclusive startDate and endDate
        //return testDate.isBefore(endDate) && testDate.isAfter(startDate);

        // inclusive startDate and endDate
        return (testDate.isEqual(startDate) || testDate.isEqual(endDate))
                || (testDate.isBefore(endDate) && testDate.isAfter(startDate));
    }

}

