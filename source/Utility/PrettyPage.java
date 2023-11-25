package source.Utility;

import source.Entity.Camp;
import source.Entity.CampInfo;
import source.Entity.Enquiry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    public static void printLineDivided(Option option, SubOptions[] descriptions) {
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

        /*
         *       SPACE and calculations
         */
        //Partition array for each description
        Integer[] partitions = new Integer[descriptions.length];
        Integer[] space = new Integer[descriptions.length];

        //Loop through all the descriptions and initialize partition and spaces array
        int runningSpace = 0;
        for (int i = 0; i < descriptions.length; i++) {
            //Total text space
            space[i] = (int) Math.floor(descriptions[i].getProportion() * descriptionBoxWidth) - (2 * ROW_PADDING) - 1;
            partitions[i] = (int) Math.ceil((double) descriptions[i].getDescription().length() / space[i]);
            runningSpace += space[i];
        }
        //Total available text space
        int availableTextSpace = descriptionBoxWidth - (descriptions.length * (2 * ROW_PADDING)) - descriptions.length;
        //Prioritize last element
        if (runningSpace < availableTextSpace) {
            space[space.length - 1] += (availableTextSpace - runningSpace);
        }
        //Calculate our partitions after updating space
        for (int i = 0; i < descriptions.length; i++) {
            partitions[i] = (int) Math.ceil((double) descriptions[i].getDescription().length() / space[i]);
        }
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + TOP_VERTICAL; // FIRST INITIAL BOX
        for (int i = 0; i < descriptions.length; i++) {

            line += HORIZONTAL_LINE.repeat(space[i] + (2 * ROW_PADDING));
            if (i != descriptions.length - 1) {
                line += TOP_VERTICAL;
            } else {
                line += HORIZONTAL_LINE + TOP_RIGHT;
            }
        }
        line += "\n";


        String middle = wrapTexts(optionBoxWidth, optionMessage, descriptions, partitions, space);

        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(optionBoxLength) + BOTTOM_VERTICAL; //FIRST INITIAL BOX
        for (int i = 0; i < descriptions.length; i++) {
            last += HORIZONTAL_LINE.repeat(space[i] + (2 * ROW_PADDING));
            if (i != descriptions.length - 1) {
                last += BOTTOM_VERTICAL;
            } else {
                last += HORIZONTAL_LINE + BOTTOM_RIGHT;
            }
        }
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
     * @param header  the header to use
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

    /**
     * A method to print a camp using pretty formatting
     *
     * @param camp the camp
     */
    public static void printCampDetails(Camp camp) {
        CampInfo campInfo = camp.getCampInfo();
        String attendees = "";
        String committeeMembers = "";
        //Populate strings into nice format
        for (int i = 0; i < camp.getAttendees().size(); i++) {
            attendees += camp.getAttendees().get(i).getUserID();
            if (i != camp.getAttendees().size() - 1)
                attendees += ", ";
        }
        for (int i = 0; i < camp.getCampCommitteeMembers().size(); i++) {
            committeeMembers += camp.getCampCommitteeMembers().get(i).getUserID();
            if (i != camp.getCampCommitteeMembers().size() - 1)
                committeeMembers += ", ";
        }

        if (attendees.isEmpty())
            attendees = "N/A";
        if (committeeMembers.isEmpty())
            committeeMembers = "N/A";

        printTitle("Information for: " + campInfo.getName(), 1);
        Option[] options = {
                new Option("Name", campInfo.getName()),
                new Option("Start Date", DateTimeFormatter.formatDateTimeToLocal(campInfo.getStartDate())),
                new Option("End Date", DateTimeFormatter.formatDateTimeToLocal(campInfo.getEndDate())),
                new Option("Registration Close Date", DateTimeFormatter.formatDateTimeToLocal(campInfo.getClosingDate())),
                new Option("User Group", campInfo.getFaculty().getClass().getSimpleName()),
                new Option("Attendees ", attendees),
                new Option("Camp Committee Members ", committeeMembers),
                new Option("Attendee Slots", campInfo.getCurrentSlots() + "/" + campInfo.getMaxSlots()),
                new Option("Camp Committee", campInfo.getCampCommitteeSlots() + "/" + campInfo.getMaxCampCommitteeSlots()),
                new Option("Description", campInfo.getDescription()),
                new Option("Staff in Charge", campInfo.getStaffInCharge())
        };
        printLines(options);
    }

    /**
     * A method to print an enquiry using pretty formatting
     *
     * @param enquiry the enquiry
     */
    public static void printEnquiry(Enquiry enquiry) {
        printTitle(enquiry.getTitle(), 1);

        String repliedBy = enquiry.getRepliedBy();
        if (repliedBy.isEmpty())
            repliedBy = "N/A";

        LocalDate date = enquiry.getRepliedDate();
        String repliedDate = (date == null) ? "N/A" : date.toString();
        Option[] options = {
                new Option("Camp Name", enquiry.getCampName()),
                new Option("Created by", enquiry.getCreatedBy()),
                new Option("Created on", DateTimeFormatter.formatDateTimeToLocal(enquiry.getCreatedDate())),
                new Option("Content", enquiry.getContent()),
                new Option("Processed", String.valueOf(enquiry.getProcessed())),
                new Option("Reply", enquiry.getReply()),
                new Option("Replied by", repliedBy),
                new Option("Replied on", repliedDate)
        };
        printLines(options);
    }

    /**
     * A method to print a list of enquiries using pretty formatting
     *
     * @param enquiries the list of camps
     */
    public static void printEnquiries(ArrayList<Enquiry> enquiries) {

        printTitle("Your Enquiries", 1);
        if (enquiries.isEmpty()) {
            printTitle("You have not sent any enquiries before!", 1);
            return;
        }
        printLineDivided(new Option("N", "test"),
                new SubOptions[]{
                        new SubOptions("Name", 0.325f),
                        new SubOptions("Created by", 0.15f),
                        new SubOptions("Created on", 0.175f),
                        new SubOptions("Replied by", 0.15f),
                        new SubOptions("Replied on", 0.175f),
                });
        for (int i = 0; i < enquiries.size(); i++) {
            Enquiry e = enquiries.get(i);
            String repliedBy = e.getRepliedBy();
            if (repliedBy.isEmpty())
                repliedBy = "N/A";

            LocalDate date = e.getRepliedDate();
            String repliedDate = (date == null) ? "N/A" : date.toString();

            printLineDivided(
                    new Option(String.valueOf(i + 1), ""),
                    new SubOptions[]{
                            new SubOptions(e.getTitle(), 0.325f),
                            new SubOptions(e.getCreatedBy(), 0.15f),
                            new SubOptions(e.getCreatedDate().toString(), 0.175f),
                            new SubOptions(repliedBy, 0.15f),
                            new SubOptions(repliedDate, 0.175f)
                    }
            );
        }
    }

    /**
     * A method to print a list of camps using pretty formatting
     *
     * @param camps the list of camps
     */
    public static void printCamps(ArrayList<Camp> camps) {
        printTitle("All Camps", 1);
        printLineDivided(new Option("N", "test"),
                new SubOptions[]{
                        new SubOptions("Name", 0.3f),
                        new SubOptions("Start Date", 0.175f),
                        new SubOptions("End Date", 0.175f),
                        new SubOptions("Faculty", 0.2f),
                        new SubOptions("Slot", 0.1f),
                });
        for (int i = 0; i < camps.size(); i++) {
            CampInfo campInfo = camps.get(i).getCampInfo();
            String description = campInfo.getName();
            printLineDivided(
                    new Option(String.valueOf(i + 1), description),
                    new SubOptions[]{
                            new SubOptions(description, 0.3f),
                            new SubOptions(campInfo.getStartDate().toString(), 0.175f),
                            new SubOptions(campInfo.getEndDate().toString(), 0.175f),
                            new SubOptions(campInfo.getFaculty().getClass().getSimpleName(), 0.2f),
                            new SubOptions(campInfo.getCurrentSlots() + "/" + campInfo.getMaxSlots(), 0.1f)
                    }
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

    /**
     * A helper method to wrap text around the box given
     *
     * @param optionBoxWidth the width of our option box
     * @param optionMessage  the initial option message for width testing
     * @param descriptions   the description of to be wrapped
     * @param partitions     the partition array
     * @param space          the space array
     */
    private static String wrapTexts(
            int optionBoxWidth,
            String optionMessage,
            SubOptions[] descriptions,
            Integer[] partitions,
            Integer[] space
    ) {
        //Start end iterators
        Integer[] start = new Integer[descriptions.length];
        Integer[] end = new Integer[descriptions.length];
        Arrays.fill(start, 0);
        //Set the end to be the appropriate space
        System.arraycopy(space, 0, end, 0, space.length);
        //Find the max partition
        int maxPartitions = Collections.max(Arrays.asList(partitions));
        //Initialize initialisation of option message
        String middle = "";
        //Our true effective size
        int effectiveSize = SIZE - descriptions.length;
        //Loop through all the descriptions and up to max partitions
        //Make the initial partition
        for (int j = 1; j <= maxPartitions; j++) {
            String option = "";
            //only print options for first line
            if (j == 1) {
                //Add the initial box
                option += VERTICAL + padText(optionMessage, ROW_PADDING); //ERROR TEXT BOX
            } else {
                option += VERTICAL + padText(SPACE.repeat(optionMessage.length()), ROW_PADDING); //EMPTY BOX
            }
            if (option.length() < optionBoxWidth) {
                int diff = optionBoxWidth - option.length() - 1;
                option += SPACE.repeat(diff);
            }
            //INCLUDE VERTICAL
            int runningLength = option.length() + 1;
            middle += option + VERTICAL;
            for (int i = 0; i < descriptions.length; i++) {
                //Calculate our text space
                int textSpace = space[i] + (2 * ROW_PADDING);
                //The current partitions
                int cp = partitions[i];
                //If current iterator > our max partitions then continue
                if (j > cp) {
                    String filledText = "";
                    //If last, fill to end,
                    if (i != descriptions.length - 1) {
                        filledText = SPACE.repeat(textSpace) + VERTICAL;
                    } else {
                        filledText = SPACE.repeat(effectiveSize - runningLength + 1) + VERTICAL;
                    }
                    //Fill to the end
                    middle += filledText;
                    runningLength += filledText.length();
                    continue;
                }
                //For single partitions, this is the code.
                if (cp == 1) {
                    String filledText;
                    String text = padText(descriptions[i].getDescription(), ROW_PADDING);
                    if (i != descriptions.length - 1) {
                        filledText = text + SPACE.repeat(textSpace - text.length());
                    } else {
                        //System.out.println(effectiveSize);
                        filledText = text + SPACE.repeat(effectiveSize - runningLength - text.length() + 1);
                    }
                    filledText += VERTICAL;
                    middle += filledText;
                    runningLength += filledText.length();
                } else {
                    //Check out of bounds for start
                    if (start[i] < descriptions[i].getDescription().length()) {
                        String add = "";
                        if (end[i] > descriptions[i].getDescription().length()) {
                            //substring to the end and add spaces
                            add = padText(descriptions[i].getDescription().substring(start[i]), ROW_PADDING);
                        } else {
                            add = padText(descriptions[i].getDescription().substring(start[i], end[i]), ROW_PADDING);
                        }
                        //Repeat space for those that did not end
                        String filledText = add + SPACE.repeat(textSpace - add.length());
                        //If at the last iterator, we add spaces
                        if (i == descriptions.length - 1) {
                            //FILL EMPTY SPACES AT THE END
                            filledText += SPACE.repeat(effectiveSize - runningLength - filledText.length() - 1);
                        }
                        //Add final separator
                        filledText += VERTICAL;
                        middle += filledText;
                        runningLength += filledText.length();
                    }
                }
                //Increment start and end
                start[i] = end[i];
                end[i] += space[i];
            }
            middle += "\n";
        }
        return middle;
    }
}
