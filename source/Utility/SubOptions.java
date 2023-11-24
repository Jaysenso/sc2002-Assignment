package source.Utility;

public class SubOptions {
    private final String description;
    private final double proportion;

    public SubOptions(String description, double proportion) {
        this.description = description;
        this.proportion = proportion;
    }

    public String getDescription() {
        return description;
    }

    public double getProportion() {
        return proportion;
    }
}
