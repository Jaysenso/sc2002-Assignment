package source.Views.Application.AppViews;

import source.Utility.Option;
import source.Utility.PrettyPage;
import source.Views.IView;

public class FilterView implements IView {
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "Name Ascending"),
                new Option("2", "Name Descending"),
                new Option("3", "No. of Attendees Ascending"),
                new Option("4", "No. of Attendees Descending"),
                new Option("5", "No. of CampCommittees Ascending"),
                new Option("6", "No. of CampCommittees Descending"),
                new Option("7", "Starting Date Ascending"),
                new Option("8", "Starting Date Descending"),
                new Option("9", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Filter By");
    }
}