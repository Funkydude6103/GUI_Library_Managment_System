
public abstract class Item implements Configuration
{

    private String tittle;
    private boolean isBorrowed;

    public static int getNextId()
    {
        return nextId;
    }

    public void setNextId(int nextId)
    {
        Item.nextId = nextId;
    }
    public void incrementNextId()
    {
        Item.nextId++;
    }


    private int popularityCount;
    private int id;
    private int cost;
    private static int nextId;
    static{
        nextId=1;
    }

    public int getCost()
    {
        return cost;
    }

    public String getTittle()
    {
        return tittle;
    }

    public void setTittle(String tittle)
    {
        this.tittle = tittle;
    }

    public boolean isBorrowed()
    {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed)
    {
        isBorrowed = borrowed;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getPopularityCount()
    {
        return popularityCount;
    }

    public void setPopularityCount(int popularityCount)
    {
        this.popularityCount = popularityCount;
    }

    public void setCost(int cost)
    {
        this.cost = cost;
    }

    public abstract void displayInfo();


    public abstract int calculateCost();
    public abstract void edit();
}
