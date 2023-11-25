package source.ViewModels.Application.StudentViewModels;

import source.Controllers.SuggestionManager;
import source.Database.App;
import source.Entity.Camp;
import source.Entity.Student;
import source.Entity.Suggestion;
import source.Entity.User;
import source.Utility.InputHandler;
import source.Utility.Option;
import source.Utility.PrettyPage;
import source.ViewModels.BaseViewModel;
import source.ViewModels.IViewModel;
import source.ViewModels.ViewManager;
import source.Views.Application.StudentView.CampCommitteeSuggestionView;

import java.util.ArrayList;

/**
 * The SuggestionViewModel holds all the logic and necessary Ui elements for suggestions
 *
 * @author Marcus
 * @version 1.0
 * @since 11/12/2023
 */
public class CampCommitteeSuggestionViewModel extends BaseViewModel implements IViewModel {
    /**
     * The suggestion view object that contains the UI for suggestions.
     *
     * @see CampCommitteeSuggestionView
     */
    CampCommitteeSuggestionView campCommitteeSuggestionView;
    SuggestionManager suggestionManager;
    User campCommittee = (Student) App.getUser();
    Camp selectedCamp;

    /**
     * A default constructor.
     *
     * @see CampCommitteeSuggestionView
     */
    public CampCommitteeSuggestionViewModel(Camp selectedCamp) {
        this.selectedCamp = selectedCamp;
        suggestionManager = new SuggestionManager();
        campCommitteeSuggestionView = new CampCommitteeSuggestionView();
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
            ArrayList<Suggestion> suggestions = suggestionManager.getCampCommitteeSuggestions(campCommittee.getUserID());
            if(suggestions.isEmpty()){
                PrettyPage.printTitle("Make a suggestion",1);
            }
            //print suggestions
            for(int i =0;i<suggestions.size();i++){
                System.out.print(i + ": ");
                System.out.println(suggestions.get(i).getCreatedBy());
            }

            campCommitteeSuggestionView.display();

            int choice = InputHandler.tryGetInt(1, 3, "Input choice: ", "Invalid choice!");

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
                    //create
                    suggestionManager.createSuggestion(selectedCamp.getCampInfo().getName(), campCommittee.getUserID());
                    break;
                }
                case 3: {
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
                    new Option("1", "Edit Suggestion"),
                    new Option("2", "Delete Suggestion"),
                    new Option("3", "Back"),
            };
            PrettyPage.printLinesWithHeader(options, "Choose your option");

            int choice = InputHandler.tryGetInt(1,3,"Choose option: ","Invalid Option");
            switch (choice){
                case 1:{
                    System.out.println("Enter new Content");
                    String newContent = InputHandler.getString();
                    suggestion.setContent(newContent);
                    suggestionManager.editSuggestion(suggestion);
                    break;
                }
                case 2:{
                    suggestionManager.deleteSuggestion(suggestion);
                    isLooping = false;
                    break;
                }
                case 3:{
                    isLooping = false;
                    break;
                }
            }
        }
    }
}
