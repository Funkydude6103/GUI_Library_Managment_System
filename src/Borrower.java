

import java.util.ArrayList;
import java.util.List;

public class Borrower
{
    private String name;
    private List<Item> borrowed;
    private List<Item> current;

    public List<Item> getCurrent() {
        return current;
    }
    public void currentAdder(Item i) {
        current.add(i);
        return;
    }
    public void currentRemover(Item i) {
        current.remove(i);
        return;
    }

    public List<Item> getBorrowed() {
        return borrowed;
    }
    public void borrowedAdder(Item i) {
        borrowed.add(i);
        return;
    }


    public  Borrower(String name)
    {
        this.name=name;
        borrowed=new ArrayList<>();
        current=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
