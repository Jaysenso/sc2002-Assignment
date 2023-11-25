package source.ViewModels.Application.StaffViewModels;

import source.Controllers.SuggestionManager;
import source.Database.App;
import source.Entity.*;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StaffView.StaffSuggestionView;
import source.Views.Application.StudentView.CampCommitteeSuggestionView;

import java.util.ArrayList;

public class StaffSuggestionViewModel extends BaseViewModel implements IViewModel {
    /**
     * The suggestion view object that contains the UI for suggestions.
     *
     * @see StaffSuggestionView
     */
    StaffSuggestionView staffSuggestionView;
    SuggestionManager suggestionManager;
    User campCommittee = (Staff) App.getUser();
    Camp selectedCamp;

    /**
     * A default constructor.
     *
     * @see CampCommitteeSuggestionView
     */
    public StaffSuggestionViewModel(Camp selectedCamp) {
        this.selectedCamp = selectedCamp;
        suggestionManager = new SuggestionManager();
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

        while(true){
            ArrayList<Suggestion> suggestions = suggestionManager.getCampSuggestions(selectedCamp);
            if(suggestions.isEmpty()){
                PrettyPage.printTitle("No Suggestions",1);
            }
            //print suggestions
            for(int i =0;i<suggestions.size();i++){
                System.out.print(i + ": ");
                System.out.println(suggestions.get(i).getCreatedBy());
            }

            staffSuggestionView.display();

            int choice = InputHandler.tryGetInt(1, 2, "Input choice: ", "Invalid choice!");

            switch (choice) {
                case 1: {
                    //view suggestion
                    if(suggestions.isEmpty()){
                        System.out.println("No suggestions");
                        break;
                    }
                    int index = InputHandler.tryGetInt(1,suggestions.size(),"Select Suggestion","Invalid Suggestion");
                    Suggestion selectedSuggestion = suggestions.get(index-1);
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

    public void showSuggestionDetails(Suggestion suggestion){
        boolean isLooping = true;
        while (isLooping){

            //print details here
            System.out.println(suggestion.getCreatedDate());
            System.out.println(suggestion.getContent());
            System.out.println(suggestion.getApproved());
            System.out.println(suggestion.getProcessed());

            Option[] options ={
                    new Option("1", "Approve suggestion"),
                    new Option("2", "Back"),
            };
            PrettyPage.printLinesWithHeader(options, "Choose your option");

            int choice = InputHandler.tryGetInt(1,2,"Choose option: ","Invalid Option");
            switch (choice){
                case 1:{
                    String input = "";
                    do {
                        System.out.print("Approve? y/n:");
                        try {
                            input = InputHandler.getString();
                        } catch (Exception e) {
                            PrettyPage.printError("Invalid Input");
                        }
                    } while (!input.equals("y") && !input.equals("n"));

                    suggestion.setApproved(input.equals("y"));
                    suggestion.setProcessed(true);
                    suggestionManager.editSuggestion(suggestion);
                    break;
                }
                case 2:{
                    isLooping = false;
                    break;
                }
            }
        }
    }
}
