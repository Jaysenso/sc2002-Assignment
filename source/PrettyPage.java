package source;

import java.util.ArrayList;

public class PrettyPage {
    //Store a list of constants to print
    private static final String topLeft = "┌";
    private static final String topRight = "┐";
    private static final String bottomLeft = "└";
    private static final String bottomRight = "┘";
    private static final String vertical = "│";
    private static final String horizontalLine = "─";
    private static final String leftVertical = "├";
    private static final String rightVertical = "┤";
    private static final String topVertical = "┬";
    private static final String bottomVertical = "┴";
    private static final String space = " ";

    public static void printLineWithDescription(String text, String description) {
        int count = 80;
        int rowPadding = 2;
        int textLength = text.length();
        int desLength = description.length() + rowPadding;
        int firstRow = (2 * rowPadding) + textLength;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = topLeft + horizontalLine.repeat(firstRow) + topVertical + horizontalLine.repeat(count - firstRow - 1) + topRight + "\n";
        String middle = vertical + padText(text, rowPadding) + vertical + padTextLeft(description, rowPadding) + space.repeat(count - firstRow - desLength - 1) + vertical + "\n";
        String last = bottomLeft + horizontalLine.repeat(firstRow) + bottomVertical + horizontalLine.repeat(count - firstRow - 1) + bottomRight;
        System.out.println(line + middle + last);
    }

    public static void printAppTitle(String text, int maxWidth, int maxHeight) {
        //Should be odd number for the best effect
        String line = topLeft + horizontalLine.repeat(maxWidth) + topRight + "\n";
        int divisor = maxHeight / 2;
        String middle = "";
        for (int i = 0; i < maxHeight; i++) {
            if (i == divisor) {
                //Have to calculate the center
                int bias = (int) Math.ceil((double) (maxWidth - text.length()) / 2);
                if ((maxWidth - text.length()) % 2 == 0) {
                    //If it's divisible by 2, we have to account bias
                    middle += vertical + space.repeat(bias) + text + space.repeat(bias) + vertical + "\n";
                } else {
                    //If it's divisible by 2, we have to account bias
                    middle += vertical + space.repeat(bias) + text + space.repeat(bias -1) + vertical + "\n";
                }

            } else {
                middle += vertical + space.repeat(maxWidth) + vertical + "\n";
            }
        }
        String last = bottomLeft + horizontalLine.repeat(maxWidth) + bottomRight;
        System.out.println(line + middle + last);
    }

    public static void printLinesWithDescription(String[] options, String[] description) {
        //Find the max length of the options
        int maxLength = 0;
        for (String s : options) {
            //Assign the max to the length
            maxLength = Integer.max(maxLength, s.length());
        }

        int maxSize = 80;
        int rowPadding = 2;
        int textLength = maxLength;

        int firstRow = (2 * rowPadding) + textLength;
        //┌────────────────────────────────────────────────────────────────────────────────┐
        String line = topLeft + horizontalLine.repeat(firstRow) + topVertical + horizontalLine.repeat(maxSize - firstRow - 1) + topRight + "\n";
        //Loop through the descriptions and print our result
        String middle = "";
        for (int i = 0; i < options.length; i++) {
            String desText = description[i];
            int desLength = desText.length() + rowPadding;
            int optionLength = options[i].length();
            //Dynamically resize our first row based on our highest option
            int bias = maxLength - optionLength;


            middle += vertical + padText(options[i], rowPadding) + space.repeat(bias) + vertical + padTextLeft(desText, rowPadding) + space.repeat(maxSize - firstRow - desLength - 1) + vertical + "\n";
        }
        String last = bottomLeft + horizontalLine.repeat(firstRow) + bottomVertical + horizontalLine.repeat(maxSize - firstRow - 1) + bottomRight;
        System.out.println(line + middle + last);
    }

    private static String padText(String text, int padding) {
        return space.repeat(padding) + text + space.repeat(padding);
    }

    private static String padTextLeft(String text, int padding) {
        return space.repeat(padding) + text;
    }

    private static String padTextRight(String text, int padding) {
        return text + space.repeat(padding);
    }

}
