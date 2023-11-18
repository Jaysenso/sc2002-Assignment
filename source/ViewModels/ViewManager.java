package source.ViewModels;

import source.Views.IView;

public class ViewManager {
    IViewModel currentView;

    public ViewManager() {
        currentView = null;
    }

    public ViewManager(IViewModel viewModel) {
        this.currentView = viewModel;
    }

    public void changeView(IViewModel newView)
    {
        if(newView == null)
            return;

        //Cleanup current view
        currentView.cleanup();
        currentView = newView;
        currentView.init(this);
    }

    public void run()
    {
        if(currentView == null)
            return;
        System.out.println("Starting view: " + currentView.getClass().getSimpleName());
        currentView.init(this);
    }
}
