package source.ViewModels.Application.StaffViewModels;

import source.Controllers.SuggestionManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Staff;
import source.Entity.Suggestion;
import source.Entity.User;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.StaffSuggestionView;
import source.Views.Application.StudentView.CampCommitteeSuggestionView;

import java.util.ArrayList;

/**
 * The StaffSuggestionViewModel class handles the suggestions operations a staff can do in this viewmodel
 *
 * @author J'sen Ong
 * @version 1.0
 * @since 11/17/2023
 */
public class StaffSuggestionViewModel extends BaseViewModel implements IViewModel {
    /**
     * The suggestion view object that contains the UI for suggestions for staff
     *
     * @see StaffSuggestionView
     */
    private final StaffSuggestionView staffSuggestionView;
    /**
     * The suggestion manager reference
     *
     * @see Suggestion
     */
    private final SuggestionManager suggestionManager;
    private final User campCommittee = (Staff) App.getUser();
    /**
     * The selected camp to be stored in this view model
     */
    private final Camp selectedCamp;

    /**
     * A default constructor.
     *
     * @see CampCommitteeSuggestionView
     */
    public StaffSuggestionViewModel(Camp selectedCamp) {
        this.selectedCamp = selectedCamp;
        suggestionManager = App.getSuggestionManager();
        staffSuggestionView = new StaffSuggestionView();
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
        while (true) {
            ArrayList<Suggestion> suggestions = suggestionManager.getCampSuggestions(selectedCamp);
            if (suggestions.isEmpty()) {
                PrettyPage.printTitle("No Suggestions", 1);
            }
            //Print the suggestions if applicable
            PrettyPage.printSuggestions(suggestions);
            staffSuggestionView.display();
            //Get the input
            int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    //View suggestion
                    if (suggestions.isEmpty()) {
                        PrettyPage.printTitle("There are no suggestions!", 1);
                        break;
                    }
                    int index = InputHandler.tryGetInt(1, suggestions.size(), "Select Suggestion: ", "Invalid choice!");
                    Suggestion selectedSuggestion = suggestions.get(index - 1);
                    //Handle the selected suggestion
                    showSuggestionDetails(selectedSuggestion);
                    break;
                }
                case 2: {
                    viewManager.returnToPreviousView();
                    break;
                }
                default: {
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
     * Handles the sub logic of dealing with a suggestion
     *
     * @param suggestion the suggestion
     */
    public void showSuggestionDetails(Suggestion suggestion) {
        while (true) {
            //print details here
            PrettyPage.printSuggestion(suggestion);

            //do it easy way
            if (suggestion.getProcessed()) {
                PrettyPage.printLineWithHeader(new Option("1", "Back"), "Choose your option");
                InputHandler.tryGetInt(1, 1, "Choose option: ", "Invalid choice!");
                return;
            }

            Option[] options = {
                    new Option("1", "Approve Suggestion"),
                    new Option("2", "Back"),
            };
            PrettyPage.printLinesWithHeader(options, "Choose your option");

            int choice = InputHandler.tryGetInt(1, 2, "Choose option: ", "Invalid choice!");
            switch (choice) {
                case 1: {
                    //Get the user input
                    System.out.print("Approve this suggestion? (y/n): ");
                    String input = InputHandler.tryGetString(new String[]{
                            "y", "n"
                    });
                    suggestion.setApproved(input.equals("y"));
                    suggestion.setProcessed(true);
                    suggestionManager.editSuggestion(suggestion);
                    break;
                }
                case 2: {
                    return;
                }
            }
        }
    }
}
