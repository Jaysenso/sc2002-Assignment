package source.Utility;

import source.Entity.Camp;
import source.Entity.CampInfo;

import java.util.ArrayList;

/**
 * A class that contains static functions to handle pretty printing of the UI
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/6/2023
 */
public class PrettyPage {
    /**
     * The string used for the top left
     */
    private static final String TOP_LEFT = "┌";
    /**
     * The string used for the top right
     */
    private static final String TOP_RIGHT = "┐";
    /**
     * The string used for the bottom left
     */
    private static final String BOTTOM_LEFT = "└";
    /**
     * The string used for the bottom right
     */
    private static final String BOTTOM_RIGHT = "┘";
    /**
     * The string used for a vertical line
     */
    private static final String VERTICAL = "│";
    /**
     * The string used for a horizontal line
     */
    private static final String HORIZONTAL_LINE = "─";
    /**
     * The string used for the left vertical line
     */
    private static final String LEFT_VERTICAL = "├";
    /**
     * The string used for the right vertical line
     */
    private static final String RIGHT_VERTICAL = "┤";
    /**
     * The string used for the top vertical
     */
    private static final String TOP_VERTICAL = "┬";
    /**
     * The string used for the bottom vertical
     */
    private static final String BOTTOM_VERTICAL = "┴";
    /**
     * The string used for a space
     */
    private static final String SPACE = " ";
    /**
     * The string used for an indent
     */
    private static final String INDENT = "  ";

    /**
     * The size of our box
     */
    private static final int SIZE = 100;
    /**
     * The effective size of our box (excluding top left and top right and middle separation)
     */
    private static final int EFFECTIVE_SIZE = SIZE - 3;
    /**
     * The padding between our text
     */
    private static final int ROW_PADDING = 2;

    /**
     * A method to print an error message enclosed in a box, with an input description
     *
     * @param description the description for our option
     */
    public static void printError(String description) {
        String errorMessage = "Error";
        /*
         * OPTION BOX WIDTH AND DETAILS
         */
        int optionBoxLength = padText(errorMessage, ROW_PADDING).length();
        int optionBoxWidth = optionBoxLength + 2;
        /*
         * DESCRIPTION BOX WIDTH AND DETAILS
         */
        int descriptionBoxLength = EFFECTIVE_SIZE - optionBoxWidth;
        int descriptionBoxWidth = descriptionBoxLength - 1;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + TOP_VERTICAL // FIRST INITIAL BOX
                + HORIZONTAL_LINE.repeat(descriptionBoxWidth) + TOP_RIGHT + "\n";

        String middle = wrapText(optionBoxWidth, descriptionBoxWidth, errorMessage, description);

        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + BOTTOM_VERTICAL //FIRST INITIAL BOX
                + HORIZONTAL_LINE.repeat(descriptionBoxWidth) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    /**
     * A method to print a line enclosed in a box, with an input description and text.
     *
     * @param option option object
     */
    public static void printLine(Option option) {
        String optionMessage = option.getOption();
        String description = option.getDescription();
        /*
         * OPTION BOX WIDTH AND DETAILS
         */
        int optionBoxLength = padText(optionMessage, ROW_PADDING).length();
        int optionBoxWidth = optionBoxLength + 2;
        /*
         * DESCRIPTION BOX WIDTH AND DETAILS
         */
        int descriptionBoxLength = EFFECTIVE_SIZE - optionBoxWidth;
        int descriptionBoxWidth = descriptionBoxLength - 1;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + TOP_VERTICAL // FIRST INITIAL BOX
                + HORIZONTAL_LINE.repeat(descriptionBoxWidth) + TOP_RIGHT + "\n";

        String middle = wrapText(optionBoxWidth, descriptionBoxWidth, optionMessage, description);

        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + BOTTOM_VERTICAL //FIRST INITIAL BOX
                + HORIZONTAL_LINE.repeat(descriptionBoxWidth) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    /**
     * A method to print a text in the center of the box, editable by height.
     *
     * @param text      the text to print
     * @param maxHeight the height of our box
     */
    public static void printTitle(String text, int maxHeight) {
        //Add bias
        int effectiveSize = EFFECTIVE_SIZE - 2;
        //Should be odd number for the best effect
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(effectiveSize) + TOP_RIGHT + "\n";
        int divisor = maxHeight / 2;
        String middle = "";
        for (int i = 0; i < maxHeight; i++) {
            if (i == divisor) {
                //Have to calculate the center
                int bias = (int) Math.ceil((double) (effectiveSize - text.length()) / 2);
                if ((effectiveSize - text.length()) % 2 == 0) {
                    //If it's divisible by 2, we have to account bias
                    middle += VERTICAL + SPACE.repeat(bias) + text + SPACE.repeat(bias) + VERTICAL + "\n";
                } else {
                    //If it's divisible by 2, we have to account bias
                    middle += VERTICAL + SPACE.repeat(bias) + text + SPACE.repeat(bias - 1) + VERTICAL + "\n";
                }

            } else {
                middle += VERTICAL + SPACE.repeat(effectiveSize) + VERTICAL + "\n";
            }
        }
        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(effectiveSize) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    /**
     * A method to print multiple lines and descriptions all enclosed in a box.
     *
     * @param options Option array
     */
    public static void printLines(Option[] options) {
        //Find the max length of the options
        int maxLength = 0;
        for (Option s : options) {
            //Assign the max to the length
            maxLength = Integer.max(maxLength, s.getOption().length());
        }
        /*
         * OPTION BOX WIDTH AND DETAILS
         */
        int optionBoxLength = padText(SPACE.repeat(maxLength), ROW_PADDING).length();
        int optionBoxWidth = optionBoxLength + 2;
        /*
         * DESCRIPTION BOX WIDTH AND DETAILS
         */
        int descriptionBoxLength = EFFECTIVE_SIZE - optionBoxWidth;
        int descriptionBoxWidth = descriptionBoxLength - 1;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + TOP_VERTICAL // FIRST INITIAL BOX
                + HORIZONTAL_LINE.repeat(descriptionBoxWidth) + TOP_RIGHT + "\n";

        //Loop through the descriptions and print our result
        String middle = "";
        for (Option option : options) {
            middle += wrapText(optionBoxWidth, descriptionBoxWidth, option.getOption(), option.getDescription());
        }

        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + BOTTOM_VERTICAL //FIRST INITIAL BOX
                + HORIZONTAL_LINE.repeat(descriptionBoxWidth) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    /**
     * A method to print a single line and description all enclosed in a box with a header.
     *
     * @param option Option array
     * @param header header text
     */
    public static void printLineWithHeader(Option option, String header) {

        //Header portion
        int headerLength = header.length();
        String headerFirstLine = TOP_LEFT + HORIZONTAL_LINE.repeat(headerLength + 2 * (ROW_PADDING)) + TOP_RIGHT + "\n";
        String headerSecondLine = VERTICAL + padText(header, ROW_PADDING) + VERTICAL;
        System.out.println(headerFirstLine + headerSecondLine);

        //Normal box portion.
        //Find the max length of the options
        int maxLength = option.getOption().length();
        /*
         * OPTION BOX WIDTH AND DETAILS
         */
        int optionBoxLength = padText("a".repeat(maxLength), ROW_PADDING).length();
        int optionBoxWidth = optionBoxLength + 2;
        /*
         * DESCRIPTION BOX WIDTH AND DETAILS
         */
        int descriptionBoxLength = EFFECTIVE_SIZE - optionBoxWidth;
        int descriptionBoxWidth = descriptionBoxLength - 1;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = LEFT_VERTICAL + HORIZONTAL_LINE.repeat(optionBoxLength) + TOP_VERTICAL; // FIRST INITIAL BOX

        //Calculate the length to put our horizontal
        int l = headerFirstLine.length() - line.length() - 2;
        line += HORIZONTAL_LINE.repeat(l) + BOTTOM_VERTICAL;
        l = EFFECTIVE_SIZE - line.length() - 1;
        line += HORIZONTAL_LINE.repeat(l) + TOP_RIGHT + "\n";

        String middle = wrapText(optionBoxWidth, descriptionBoxWidth, option.getOption(), option.getDescription());


        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + BOTTOM_VERTICAL //FIRST INITIAL BOX
                + HORIZONTAL_LINE.repeat(descriptionBoxWidth) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    /**
     * A method to print multiple lines and descriptions all enclosed in a box with a header.
     *
     * @param options Option array
     */
    public static void printLinesWithHeader(Option[] options, String header) {

        //Header portion
        int headerLength = header.length();
        String headerFirstLine = TOP_LEFT + HORIZONTAL_LINE.repeat(headerLength + 2 * (ROW_PADDING)) + TOP_RIGHT + "\n";
        String headerSecondLine = VERTICAL + padText(header, ROW_PADDING) + VERTICAL;
        System.out.println(headerFirstLine + headerSecondLine);

        //Normal box portion.
        //Find the max length of the options
        int maxLength = 0;
        for (Option s : options) {
            //Assign the max to the length
            maxLength = Integer.max(maxLength, s.getOption().length());
        }
        /*
         * OPTION BOX WIDTH AND DETAILS
         */
        int optionBoxLength = padText("a".repeat(maxLength), ROW_PADDING).length();
        int optionBoxWidth = optionBoxLength + 2;
        /*
         * DESCRIPTION BOX WIDTH AND DETAILS
         */
        int descriptionBoxLength = EFFECTIVE_SIZE - optionBoxWidth;
        int descriptionBoxWidth = descriptionBoxLength - 1;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = LEFT_VERTICAL + HORIZONTAL_LINE.repeat(optionBoxLength) + TOP_VERTICAL; // FIRST INITIAL BOX

        //Calculate the length to put our horizontal
        int l = headerFirstLine.length() - line.length() - 2;
        line += HORIZONTAL_LINE.repeat(l) + BOTTOM_VERTICAL;
        l = EFFECTIVE_SIZE - line.length() - 1;
        line += HORIZONTAL_LINE.repeat(l) + TOP_RIGHT + "\n";

        //Loop through the descriptions and print our result
        String middle = "";
        for (Option option : options) {
            middle += wrapText(optionBoxWidth, descriptionBoxWidth, option.getOption(), option.getDescription());
        }

        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + BOTTOM_VERTICAL //FIRST INITIAL BOX
                + HORIZONTAL_LINE.repeat(descriptionBoxWidth) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    public static void printCampDetails(Camp camp) {
        CampInfo campInfo = camp.getCampInfo();
        printTitle("Information for: " + campInfo.getName(), 1);
        Option[] options = {
                new Option("Name", campInfo.getName()),
                new Option("Start Date", DateTimeFormatter.formatDateTimeToLocal(campInfo.getStartDate())),
                new Option("End Date", DateTimeFormatter.formatDateTimeToLocal(campInfo.getEndDate())),
                new Option("Registration Close Date", DateTimeFormatter.formatDateTimeToLocal(campInfo.getClosingDate())),
                new Option("User Group", campInfo.getFaculty().getClass().getSimpleName()),
                new Option("Attendee Slots", campInfo.getCurrentSlots() + "/" + campInfo.getMaxSlots()),
                new Option("Camp Committee", campInfo.getCampCommitteeSlots() + "/" + campInfo.getMaxCampCommitteeSlots()),
                new Option("Description", campInfo.getDescription()),
                new Option("Staff in Charge", campInfo.getStaffInCharge())
        };
        printLines(options);
    }

    public static void printCamps(ArrayList<Camp> camps) {
        printTitle("All camps", 1);
        for (int i = 0; i < camps.size(); i++) {
            CampInfo campInfo = camps.get(i).getCampInfo();
            printLineWithHeader(
                    new Option(String.valueOf(i + 1), campInfo.getName()),
                    campInfo.getFaculty().getClass().getSimpleName()
            );
        }
    }

    /**
     * A helper method to return a text padded from left and right.
     *
     * @param text    the text to print
     * @param padding the desired padding of our text
     */
    private static String padText(String text, int padding) {
        return SPACE.repeat(padding) + text + SPACE.repeat(padding);
    }

    /**
     * A helper method to return a text padded from the left
     *
     * @param text    the text to print
     * @param padding the desired padding of our text
     */
    private static String padTextLeft(String text, int padding) {
        return SPACE.repeat(padding) + text;
    }

    /**
     * A helper method to return a text padded from the right
     *
     * @param text    the text to print
     * @param padding the desired padding of our text
     */
    private static String padTextRight(String text, int padding) {
        return text + SPACE.repeat(padding);
    }

    /**
     * A helper method to wrap text around the box given
     *
     * @param optionBoxWidth      the width of our option box
     * @param descriptionBoxWidth the width of our description box
     * @param optionMessage       the initial option message for width testing
     * @param description         the description of to be wrapped
     */
    private static String wrapText(
            int optionBoxWidth,
            int descriptionBoxWidth,
            String optionMessage,
            String description
    ) {
        //Effective text space
        int textSpace = descriptionBoxWidth - (2 * ROW_PADDING);
        //A partition of 1 means the description is able to fit into one row
        int partitions = (int) Math.ceil((double) description.length() / textSpace);
        String middle = VERTICAL + padText(optionMessage, ROW_PADDING); //ERROR TEXT BOX
        if (middle.length() < optionBoxWidth) {
            int diff = optionBoxWidth - middle.length() - 1;
            middle += SPACE.repeat(diff);
        }
        middle += VERTICAL;
        //The text is fully able to fit into the first line
        if (partitions == 1) {
            //Remaining characters after padded text (only for partition 1)
            int charactersLeft = EFFECTIVE_SIZE - (padText(description, ROW_PADDING).length() + optionBoxWidth) - 1;
            middle += padText(description, ROW_PADDING) + SPACE.repeat(charactersLeft) + VERTICAL + "\n";
        } else {
            //Make the initial partition
            int start = 0;
            int end = textSpace;
            middle += padText(description.substring(start, end), ROW_PADDING) + VERTICAL + "\n";
            //Start to for loop and wrap text
            for (int i = 1; i < partitions; i++) {

                //Handle the next partition
                middle += VERTICAL + SPACE.repeat(optionBoxWidth - 2) + VERTICAL; //FIRST INITIAL BOX

                //Go into the next portion
                start = end;
                end += textSpace;
                String currDescription;

                //If we have reached the max, then we have to append the vertical
                if (end > description.length()) {
                    currDescription = padText(description.substring(start), ROW_PADDING);
                    int charactersLeft = EFFECTIVE_SIZE - currDescription.length() - optionBoxWidth - 1;
                    middle += currDescription + SPACE.repeat(charactersLeft) + VERTICAL + "\n";
                    break;
                }
                currDescription = padText(description.substring(start, end), ROW_PADDING);
                middle += currDescription + VERTICAL + "\n";
            }
        }
        return middle;
    }
}
