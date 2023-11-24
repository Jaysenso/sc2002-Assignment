package source.Camp;

public class Camp {
    private String name;

    public Camp(String name){

        this.name = name;
    }

    @Override
    public String toString() {

        return name;
    }

    public void setName(String name){

        this.name = name;
    }

    public String getName(){

        return name;
    }
}