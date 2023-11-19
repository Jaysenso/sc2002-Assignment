package source.ViewModels;

public interface IViewModel {
    public void init(ViewManager viewManager);
    public void handleInputs();
    public void cleanup();
}
