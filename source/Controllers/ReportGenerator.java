package source.Controllers;

import source.Entity.Camp;
import source.Utility.PrettyPage;

import java.io.PrintWriter;

/**
 * The ReportGenerator generates a report and writes it to a text file
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/20/2023
 */
public class ReportGenerator {
    /**
     * The file path to write in
     */

    private String filePath;

    /**
     * A default constructor.
     */
    public ReportGenerator(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Generates a performance report and writes it into the file path.
     *
     * @param camp the camp    * @param name
     */
    public void generatePerformanceReport(Camp camp, String name) {
        String data = PrettyPage.getPerformanceReport(camp) + "\n";
        data += PrettyPage.getFormattedTitle("Generated by " + name, 3) + "\n";
        try {
            // Creates a FileWriter
            PrintWriter output = new PrintWriter(filePath);
            output.print(data);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates an attendance report and writes it into the file path.
     *
     * @param camp the camp
     * @param name name of who generated the report
     */
    public void generateAttendanceReport(Camp camp, String name) {
        String data = PrettyPage.getCampReport(camp) + "\n";
        data += PrettyPage.getFormattedTitle("Generated by " + name, 3) + "\n";
        try {
            // Creates a FileWriter
            PrintWriter output = new PrintWriter(filePath);
            output.print(data);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a full report (attendance + performance) and writes it into the file path.
     *
     * @param camp the camp
     */
    public void generateFullReport(Camp camp, String name) {
        String data = PrettyPage.getCampReport(camp) + "\n";
        data += PrettyPage.getPerformanceReport(camp) + "\n";
        data += PrettyPage.getFormattedTitle("Generated by " + name, 3) + "\n";
        try {
            // Creates a FileWriter
            PrintWriter output = new PrintWriter(filePath);
            output.print(data);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
