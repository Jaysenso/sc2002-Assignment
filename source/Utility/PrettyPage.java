package source.Utility;

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
    private static final int SIZE = 80;
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
        int textLength = "Error".length();
        int desLength = description.length() + ROW_PADDING;
        int firstRow = (2 * ROW_PADDING) + textLength;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(firstRow) + TOP_VERTICAL + HORIZONTAL_LINE.repeat(SIZE - firstRow - 1) + TOP_RIGHT + "\n";
        String middle = VERTICAL + padText("Error", ROW_PADDING) + VERTICAL + padTextLeft(description, ROW_PADDING) + SPACE.repeat(SIZE - firstRow - desLength - 1) + VERTICAL + "\n";
        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(firstRow) + BOTTOM_VERTICAL + HORIZONTAL_LINE.repeat(SIZE - firstRow - 1) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    /**
     * A method to print a line enclosed in a box, with an input description and text.
     *
     * @param option option object
     */
    public static void printLine(Option option) {
        int textLength = option.getOption().length();
        int desLength = option.getDescription().length() + ROW_PADDING;
        int firstRow = (2 * ROW_PADDING) + textLength;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(firstRow) + TOP_VERTICAL + HORIZONTAL_LINE.repeat(SIZE - firstRow - 1) + TOP_RIGHT + "\n";
        String middle = VERTICAL + padText(option.getOption(), ROW_PADDING) + VERTICAL + padTextLeft(option.getDescription(), ROW_PADDING) + SPACE.repeat(SIZE - firstRow - desLength - 1) + VERTICAL + "\n";
        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(firstRow) + BOTTOM_VERTICAL + HORIZONTAL_LINE.repeat(SIZE - firstRow - 1) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    /**
     * A method to print a text in the center of the box, editable by height.
     *
     * @param text      the text to print
     * @param maxHeight the height of our box
     */
    public static void printTitle(String text, int maxHeight) {
        //Should be odd number for the best effect
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(SIZE) + TOP_RIGHT + "\n";
        int divisor = maxHeight / 2;
        String middle = "";
        for (int i = 0; i < maxHeight; i++) {
            if (i == divisor) {
                //Have to calculate the center
                int bias = (int) Math.ceil((double) (SIZE - text.length()) / 2);
                if ((SIZE - text.length()) % 2 == 0) {
                    //If it's divisible by 2, we have to account bias
                    middle += VERTICAL + SPACE.repeat(bias) + text + SPACE.repeat(bias) + VERTICAL + "\n";
                } else {
                    //If it's divisible by 2, we have to account bias
                    middle += VERTICAL + SPACE.repeat(bias) + text + SPACE.repeat(bias - 1) + VERTICAL + "\n";
                }

            } else {
                middle += VERTICAL + SPACE.repeat(SIZE) + VERTICAL + "\n";
            }
        }
        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(SIZE) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }

    /**
     * A method to print multiple lines and descriptions all enclosed in a box.
     *
     * @param options     Option array
     */
    public static void printLines(Option[] options) {
        //Find the max length of the options
        int maxLength = 0;
        for (Option s : options) {
            //Assign the max to the length
            maxLength = Integer.max(maxLength, s.getOption().length());
        }
        int textLength = maxLength;

        int firstRow = (2 * ROW_PADDING) + textLength;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = TOP_LEFT + HORIZONTAL_LINE.repeat(firstRow) + TOP_VERTICAL + HORIZONTAL_LINE.repeat(SIZE - firstRow - 1) + TOP_RIGHT + "\n";
        //Loop through the descriptions and print our result
        String middle = "";
        for (int i = 0; i < options.length; i++) {
            String desText = options[i].getDescription();
            int desLength = desText.length() + ROW_PADDING;
            int optionLength = options[i].getOption().length();
            //Dynamically resize our first row based on our highest option
            int bias = maxLength - optionLength;


            middle += VERTICAL + padText(options[i].getOption(), ROW_PADDING) + SPACE.repeat(bias) + VERTICAL + padTextLeft(desText, ROW_PADDING) + SPACE.repeat(SIZE - firstRow - desLength - 1) + VERTICAL + "\n";
        }
        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(firstRow) + BOTTOM_VERTICAL + HORIZONTAL_LINE.repeat(SIZE - firstRow - 1) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
    }
    /**
     * A method to print multiple lines and descriptions all enclosed in a box with a header.
     *
     * @param options     Option array
     */
    public static void printLinesWithHeader(Option[] options, String header){

        //Header portion
        int headerLength = header.length();
        String headerFirstLine = TOP_LEFT + HORIZONTAL_LINE.repeat(headerLength + 2* (ROW_PADDING)) + TOP_RIGHT + "\n";
        String headerSecondLine = VERTICAL + padText(header, ROW_PADDING) + VERTICAL;
        System.out.println(headerFirstLine + headerSecondLine );

        //Normal box portion.
        //Find the max length of the options
        int maxLength = 0;
        for (Option s : options) {
            //Assign the max to the length
            maxLength = Integer.max(maxLength, s.getOption().length());
        }
        int textLength = maxLength;
        int firstRow = (2 * ROW_PADDING) + textLength;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = LEFT_VERTICAL + HORIZONTAL_LINE.repeat(firstRow) + TOP_VERTICAL;

        //Calculate the length to put our horizontal
        int l = headerFirstLine.length() - line.length() - 2;
        line += HORIZONTAL_LINE.repeat(l) + BOTTOM_VERTICAL;
        l = SIZE - line.length() + 1;
        line += HORIZONTAL_LINE.repeat(l) + TOP_RIGHT + "\n";

        //Loop through the descriptions and print our result
        String middle = "";
        for (int i = 0; i < options.length; i++) {
            String desText = options[i].getDescription();
            int desLength = desText.length() + ROW_PADDING;
            int optionLength = options[i].getOption().length();
            //Dynamically resize our first row based on our highest option
            int bias = maxLength - optionLength;
            middle += VERTICAL + padText(options[i].getOption(), ROW_PADDING) + SPACE.repeat(bias) + VERTICAL + padTextLeft(desText, ROW_PADDING) + SPACE.repeat(SIZE - firstRow - desLength - 1) + VERTICAL + "\n";
        }
        String last = BOTTOM_LEFT + HORIZONTAL_LINE.repeat(firstRow) + BOTTOM_VERTICAL + HORIZONTAL_LINE.repeat(SIZE - firstRow - 1) + BOTTOM_RIGHT;
        System.out.println(line + middle + last);
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
     * A helper method to return a text padded from ther right
     *
     * @param text    the text to print
     * @param padding the desired padding of our text
     */
    private static String padTextRight(String text, int padding) {
        return text + SPACE.repeat(padding);
    }

}
