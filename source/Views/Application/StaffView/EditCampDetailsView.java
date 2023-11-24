package source.Views.Application.StaffView;
import source.Entity.Staff;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;


/**
 * The EditCampDetailsView class contains the necessary UI elements to display the necessary operations to edit camp details.
 *
 * @author J'sen Ong
 * @version 1.0
 * @since 11/17/2023
 */
public class EditCampDetailsView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Back"),
                new Option("2", "Edit Camp Name"),
                new Option("3", "Edit Start Date"),
                new Option("4", "Edit End Date"),
                new Option("5", "Edit Registration Close Date"),
                new Option("6", "Edit User Group"),
                new Option("7", "Edit Attendee Slots"),
                new Option("8", "Edit Description"),
                new Option("9", "Edit Visibility"),

        };
        //PrettyPage.printTitle("Edit Camp Details", 3);
        PrettyPage.printLinesWithHeader(options, "Choose your option.");
    }
}