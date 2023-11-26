package source.ViewModels.Application.Apps;

import source.Controllers.EnquiryManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Enquiry;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.AppViews.ReplyEnquiryView;

import java.util.ArrayList;

/**
 * The ReplyEnquiryModel holds all the logic and necessary UI elements for replying enquiries
 *
 * @author Isaac Chun
 * @version 1.0
 * @since 11/12/2023
 */
public class ReplyEnquiryViewModel extends BaseViewModel implements IViewModel {

    /**
     * The ReplyEnquiryView object shows the UI when the campCommitteeMember/staff wants to reply to an enquiry
     *
     * @see ReplyEnquiryView
     */
    private final ReplyEnquiryView replyEnquiryView;
    /**
     * The enquiry manager reference
     */
    private final EnquiryManager enquiryManager;
    /**
     * The selected camp to be passed into this view model
     */
    private final Camp selectedCamp;

    /**
     * A default constructor.
     *
     * @see ReplyEnquiryView
     */
    public ReplyEnquiryViewModel(Camp selectedCamp) {
        this.selectedCamp = selectedCamp;
        enquiryManager = App.getEnquiryManager();
        replyEnquiryView = new ReplyEnquiryView();
    }

    /**
     * A function called by ViewManager to pass by reference and conduct any initial setup
     * of the view model.
     *
     * @param viewManager the view manager reference.
     * @see ViewManager
     */
    @Override
    public void init(ViewManager viewManager) {
        super.init(viewManager);
        handleInputs();
    }

    /**
     * A function to handle all inputs over here.
     */
    @Override
    public void handleInputs() {
        int choice;
        while (true) {
            ArrayList<Enquiry> enquiries = selectedCamp.getEnquiryList();
            PrettyPage.printEnquiries(enquiries);
            replyEnquiryView.display();
            choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    int index = InputHandler.tryGetInt(1, enquiries.size(), "Select Enquiry: ", "Invalid Enquiry");
                    Enquiry selectedEnquiry = enquiries.get(index - 1);
                    replyToEnquiry(selectedEnquiry);
                    break;
                }
                case 2: {
                    viewManager.returnToPreviousView();
                    break;
                }
            }
        }
    }

    /**
     * A function that is called when the ViewManager swaps view, any clean up code such as
     * flushing the console output or cleaning up any lists or data
     */
    @Override
    public void cleanup() {
        System.out.flush(); //NOTE: Does not work in IntelliJ IDEA as it is not a real terminal.
    }

    /**
     * Handles the logic of replying to the enquiry
     */
    public void replyToEnquiry(Enquiry selectedEnquiry) {
        //Print this enquiry
        PrettyPage.printEnquiry(selectedEnquiry);
        //Just do it easily
        if (selectedEnquiry.getProcessed()) {
            PrettyPage.printLineWithHeader(new Option("1", "Back"), "Select Option: ");
            InputHandler.tryGetInt(1, 1, "Input choice: ", "Invalid choice");
            return;
        }
        Option[] options = {
                new Option("1", "Reply to enquiry"),
                new Option("2", "Back"),
        };
        PrettyPage.printLinesWithHeader(options, "Select Option: ");
        int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
        switch (choice) {
            case 1: {
                enquiryManager.replyEnquiry(selectedEnquiry, App.getUser());
                PrettyPage.printLine(new Option("Success", "You have replied to the enquiry."));
                break;
            }
            case 2: {

                break;
            }
        }
    }
}
