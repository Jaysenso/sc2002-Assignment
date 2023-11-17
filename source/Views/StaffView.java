package source.Views;

import source.Utility.Option;

public class StaffView implements IView{
    @Override
    public void display() {
        Option[] options = {
                new Option("1", "View Camps"), //will call another view for all camps or created camp
                new Option("2", "View Enquiries"),
                new Option("3", "Create Camp"),
                new Option("4", "Edit Camp"),
                new Option("5", "Delete Camp"),
                new Option("6", "Logout"),
        };
    }
}
