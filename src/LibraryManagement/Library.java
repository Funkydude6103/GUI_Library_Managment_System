package LibraryManagement;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Library
{
    public Library()
    {
        itemList=new ArrayList<>();
        borrowerList=new ArrayList<>();
    }
    private List<Item> itemList;
    private List<Borrower> borrowerList;
    public void displayAllItems()
    {
        int count=1;
        for(Item item:itemList)
        {
            System.out.println("LibraryManagement.Item "+count+"#");
            item.displayInfo();
            System.out.println();
            count++;
        }
        System.out.println("Enter 0 to return to Main LibraryManagement.Menu");
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            String choice=scanner.nextLine();
            if(choice.equals("0"))
            {
                break;
            }
        }
    }
    public List<Item> loadFromFileReturn()
    {
        Vector<String> dataFromFile=new Vector<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt")))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                dataFromFile.add(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for(String data:dataFromFile)
        {
            //LibraryManagement.Book Case
            if(data.charAt(0)=='1')
            {
                Book book=new Book();
                String [] arr=data.split(", ");
                if(arr.length>=6)
                {
                    book.setTittle(arr[1]);
                    book.setAuthor(arr[2]);
                    book.setBorrowed(false);
                    try {
                        book.setYear(Integer.parseInt(arr[3]));
                        book.setPopularityCount(Integer.parseInt(arr[4]));
                        book.setCost(Integer.parseInt(arr[5]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Cannot parse to an integer.");
                    }
                    book.setId(Item.getNextId());
                    book.incrementNextId();
                    itemList.add(book);
                }

            }
            //LibraryManagement.Magazine Case
            if(data.charAt(0)=='2')
            {
                Magazine magazine=new Magazine();
                String [] arr=data.split(", ");
                if(arr.length>=6)
                {
                    magazine.setTittle(arr[1]);
                    magazine.setBorrowed(false);
                    List<String> authors = new ArrayList<>(Arrays.asList(arr).subList(2, arr.length - 4));
                    authors.add(arr[arr.length-4].substring(0,arr[arr.length-4].length()-1));
                    magazine.setAuthorList(authors);
                    magazine.setPublisher(arr[arr.length - 3]);

                    try {
                        magazine.setPopularityCount(Integer.parseInt(arr[arr.length - 2]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Cannot parse to an integer.");
                    }
                    try {
                        magazine.setCost(Integer.parseInt(arr[arr.length - 1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Cannot parse to an integer.");
                    }
                    magazine.setId(Item.getNextId());
                    magazine.incrementNextId();
                    itemList.add(magazine);
                }
            }
            //LibraryManagement.Newspaper Case
            if(data.charAt(0)=='3')
            {
                Newspaper newspaper=new Newspaper();
                String [] arr=data.split(", ");
                if(arr.length>=4)
                {
                    newspaper.setTittle(arr[1]);
                    newspaper.setPublisher(arr[2]);
                    newspaper.setBorrowed(false);
                    try {
                        newspaper.setPopularityCount(Integer.parseInt(arr[3]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Cannot parse to an integer.");
                    }
                    String dateString = arr[4];
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date date = dateFormat.parse(dateString);
                        newspaper.setDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("Invalid date format.");
                    }
                    newspaper.setId(Item.getNextId());
                    newspaper.incrementNextId();
                    itemList.add(newspaper);
                }
            }
        }
        return itemList;
    }
    public void saveInFile(List<Item> itemList2)  {
        try {
            String dataFilePath = "data.txt";
            File data = new File(dataFilePath);
            data.delete();
            File dataTemp = new File(dataFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataTemp));
            for (Item i : itemList2) {
                if (i instanceof Book) {
                    String line = "1, " + i.getTittle() + ", " + ((Book) i).getAuthor() + ", " + ((Book) i).getYear() + ", " + i.getPopularityCount() + ", " + i.getCost();
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                if (i instanceof Magazine) {
                    String line = "2, " + i.getTittle() + ", ";
                    List<String> authors = ((Magazine) i).getAuthorList();
                    int k = 0;
                    for (String a : authors) {
                        if (k != authors.size() - 1) {
                            line = line + a + ", ";
                        } else {
                            line = line + a + "., ";
                        }
                        k++;
                    }
                    line = line + ((Magazine) i).getPublisher() + ", " + i.getPopularityCount() + ", " + i.getCost();
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                if (i instanceof Newspaper) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String formatDate=dateFormat.format(((Newspaper) i).getDate());
                    String line = "3, " + i.getTittle() + ", " + ((Newspaper) i).getPublisher() + ", " + i.getPopularityCount() + ", " + formatDate;
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void loadFromFile()
    {
        Vector<String> dataFromFile=new Vector<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Tayyab Anees\\OneDrive\\Desktop\\LibraryManagmentSystem\\src\\LibraryManagmentSystem\\data.txt")))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                dataFromFile.add(line);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for(String data:dataFromFile)
        {
            //LibraryManagement.Book Case
            if(data.charAt(0)=='1')
            {
                Book book=new Book();
                String [] arr=data.split(", ");
                if(arr.length>=6)
                {
                    book.setTittle(arr[1]);
                    book.setAuthor(arr[2]);
                    book.setBorrowed(false);
                    try {
                        book.setYear(Integer.parseInt(arr[3]));
                        book.setPopularityCount(Integer.parseInt(arr[4]));
                        book.setCost(Integer.parseInt(arr[5]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Cannot parse to an integer.");
                    }
                    book.setId(Item.getNextId());
                    book.incrementNextId();
                    itemList.add(book);
                }

            }
            //LibraryManagement.Magazine Case
            if(data.charAt(0)=='2')
            {
                Magazine magazine=new Magazine();
                String [] arr=data.split(", ");
                if(arr.length>=6)
                {
                    magazine.setTittle(arr[1]);
                    magazine.setBorrowed(false);
                    List<String> authors = new ArrayList<>(Arrays.asList(arr).subList(2, arr.length - 4));
                    authors.add(arr[arr.length-4].substring(0,arr[arr.length-4].length()-1));
                    magazine.setAuthorList(authors);
                    magazine.setPublisher(arr[arr.length - 3]);

                    try {
                        magazine.setPopularityCount(Integer.parseInt(arr[arr.length - 2]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Cannot parse to an integer.");
                    }
                    try {
                        magazine.setCost(Integer.parseInt(arr[arr.length - 1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Cannot parse to an integer.");
                    }
                    magazine.setId(Item.getNextId());
                    magazine.incrementNextId();
                    itemList.add(magazine);
                }
            }
            //LibraryManagement.Newspaper Case
            if(data.charAt(0)=='3')
            {
              Newspaper newspaper=new Newspaper();
                String [] arr=data.split(", ");
                if(arr.length>=4)
                {
                    newspaper.setTittle(arr[1]);
                    newspaper.setPublisher(arr[2]);
                    newspaper.setBorrowed(false);
                    try {
                        newspaper.setPopularityCount(Integer.parseInt(arr[3]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Cannot parse to an integer.");
                    }
                    String dateString = arr[4];
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date date = dateFormat.parse(dateString);
                        newspaper.setDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("Invalid date format.");
                    }
                    newspaper.setId(Item.getNextId());
                    newspaper.incrementNextId();
                    itemList.add(newspaper);
                }
            }
        }
    }
    public void displaySingleItem(Item i)
    {
       i.displayInfo();
    }
    public Item getItemById(int id)
    {
        for(Item item:itemList)
        {
            if(item.getId()==id)
                return item;
        }
        return null;
    }
    public void addItem()
    {
        while(true)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 1 for LibraryManagement.Book");
            System.out.println("Enter 2 for LibraryManagement.Magazine");
            System.out.println("Enter 3 for LibraryManagement.Newspaper");
            System.out.println("Enter 0 to return to Main LibraryManagement.Menu");
            System.out.println("Select the LibraryManagement.Item: ");
            String choice2 = scanner.nextLine();
            if(choice2.equals("1"))
            {
                Book book=new Book();
                book.setBorrowed(false);
                book.setId(Item.getNextId());
                book.incrementNextId();
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Enter Tittle of LibraryManagement.Book: ");
                String input = scanner.nextLine();
                book.setTittle(input);
                System.out.println("Enter the Author of LibraryManagement.Book: ");
                input = scanner.nextLine();
                book.setAuthor(input);
                System.out.println("Enter the Published year of LibraryManagement.Book: ");
                int input2=scanner2.nextInt();
                book.setYear(input2);
                System.out.println("Enter the Popularity Count of LibraryManagement.Book: ");
                input2=scanner2.nextInt();
                book.setPopularityCount(input2);
                System.out.println("Enter the Cost of LibraryManagement.Book: ");
                input2=scanner2.nextInt();
                book.setCost(input2);
                itemList.add(book);
                System.out.println("LibraryManagement.Item Added!!!!");
            }
            if(choice2.equals("2"))
            {
                Magazine magazine=new Magazine();
                magazine.setBorrowed(false);
                magazine.setId(Item.getNextId());
                magazine.incrementNextId();
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Enter Tittle of LibraryManagement.Magazine: ");
                String input = scanner.nextLine();
                magazine.setTittle(input);
                System.out.println("Enter the Authors of LibraryManagement.Book and Enter 0 to exit entering Authors: ");
                List<String> authors=new ArrayList<>();
                while(true)
                {
                    input = scanner.nextLine();
                    if (input.equals("0"))
                        break;
                    authors.add(input);
                }
                magazine.setAuthorList(authors);
                System.out.println("Enter the Publisher of the LibraryManagement.Magazine: ");
                input=scanner2.nextLine();
                magazine.setPublisher(input);
                System.out.println("Enter the Popularity Count of LibraryManagement.Magazine: ");
                int input2=scanner2.nextInt();
                magazine.setPopularityCount(input2);
                System.out.println("Enter the Cost of LibraryManagement.Magazine: ");
                input2=scanner2.nextInt();
                magazine.setCost(input2);
                itemList.add(magazine);
                System.out.println("LibraryManagement.Item Added!!!!");
            }
            if(choice2.equals("3"))
            {
                Newspaper newspaper=new Newspaper();
                newspaper.setBorrowed(false);
                newspaper.setId(Item.getNextId());
                newspaper.incrementNextId();
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Enter Tittle of LibraryManagement.Newspaper: ");
                String input = scanner.nextLine();
                newspaper.setTittle(input);
                System.out.println("Enter the Publisher of LibraryManagement.Newspaper: ");
                input = scanner.nextLine();
                newspaper.setPublisher(input);
                System.out.println("Enter the Popularity Count of LibraryManagement.Newspaper: ");
                int input2=scanner2.nextInt();
                newspaper.setPopularityCount(input2);
                System.out.println("Enter the Date of the LibraryManagement.Newspaper(dd-MM-yyyy): ");
                String userInput = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                dateFormat.setLenient(false);
                try {
                    Date date = dateFormat.parse(userInput);
                    newspaper.setDate(date);
                    itemList.add(newspaper);
                    System.out.println("LibraryManagement.Item Added!!!!");
                } catch (ParseException e) {
                    System.err.println("Invalid date format. Please enter a date in dd-mm-yyyy format.");
                }
            }
            if(choice2.equals("0"))
            {
             break;
            }

        }

    }
    public void deleteItem(int id)
    {
        Object o=getItemById(id);
        if(Objects.isNull(o))
        {
            System.out.println("LibraryManagement.Item does not Exists");
        }
        else {
            itemList.remove(o);
            System.out.println("LibraryManagement.Item deleted");
        }
    }
    public void displayHotPicks()
    {
        List<Item> i=new ArrayList<>(itemList);
        i.sort((item1, item2) -> Integer.compare(item2.getPopularityCount(), item1.getPopularityCount()));
        int count1=1;
        for(Item item:i)
        {
            System.out.println("LibraryManagement.Item "+count1+"#");
            item.displayInfo();
            System.out.println();
            count1++;
        }
        System.out.println("Enter 0 to return to Main LibraryManagement.Menu");
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            String choice=scanner.nextLine();
            if(choice.equals("0"))
            {
                break;
            }
        }

    }
    public void displayAvailableItems()
    {
        System.out.println("Available Books:");
        for(Item item:itemList)
        {

            if(!item.isBorrowed()) {
                item.displayInfo();
                System.out.println();
            }
        }
    }
    public boolean borrowItem()
    {
        boolean flag1=true;
        Scanner scanner3 = new Scanner(System.in);
        Borrower oldBorrower=null;
        System.out.println("Enter the Username of the LibraryManagement.Borrower:");
        String username=scanner3.nextLine();
        for (Borrower b:borrowerList)
        {
            if(b.getName().equals(username))
            {
                oldBorrower=b;
                flag1=false;
                break;
            }
        }
        if(flag1)
        {
            Borrower newBorrower=new Borrower(username);
            displayAvailableItems();
            System.out.println("Enter the Id of the LibraryManagement.Item to be Borrowed:");
            int id=scanner3.nextInt();
            Object o =getItemById(id);
            if (!Objects.isNull(o)) {
                Item item =getItemById(id);
                if(!item.isBorrowed()) {
                    item.setBorrowed(true);
                    item.setPopularityCount(item.getPopularityCount()+1);
                    newBorrower.currentAdder(item);
                    borrowerList.add(newBorrower);
                    item.displayInfo();
                    System.out.println("Borrowed by " + newBorrower.getName());
                }
                else {
                    System.out.println("LibraryManagement.Item does not Exists");
                }
            } else {
                System.out.println("LibraryManagement.Item does not Exists");
            }
        }
        else
        {
            List<Item> borrowed=oldBorrower.getBorrowed();
            displayAvailableItems();
            System.out.println("Enter the Id of the LibraryManagement.Item to be Borrowed:");
            int id=scanner3.nextInt();
            Object o =getItemById(id);
            if (!Objects.isNull(o)) {
                Item item =getItemById(id);
                for(Item i:borrowed)
                {
                    if(i.equals(item))
                    {
                        System.out.println("Cant Borrow");
                        return false;
                    }
                }
                if(!item.isBorrowed()) {
                    item.setBorrowed(true);
                    item.setPopularityCount(item.getPopularityCount()+1);
                    oldBorrower.currentAdder(item);
                    item.displayInfo();
                    System.out.println("Borrowed by " + oldBorrower.getName());
                }
                else
                {
                    System.out.println("Can't Borrow this LibraryManagement.Item");
                    return false;
                }
            } else {
                System.out.println("LibraryManagement.Item does not Exists");
            }


        }

        return false;
    }
    public void returnItem()
    {
        boolean flag1=true;
        Scanner scanner3 = new Scanner(System.in);
        Borrower oldBorrower=null;
        System.out.println("Enter the Username of the LibraryManagement.Borrower:");
        String username=scanner3.nextLine();
        for (Borrower b:borrowerList)
        {
            if(b.getName().equals(username))
            {
                oldBorrower=b;
                flag1=false;
                break;
            }
        }
        if(flag1)
        {
            System.out.println("New User");
        }
        else
        {
            List<Integer> books=new ArrayList<>();
            List<Item> currentItems=oldBorrower.getCurrent();
            System.out.println("Current Borrowed Items:");
            for(Item i:currentItems)
            {
                i.displayInfo();
            }
            System.out.print("Enter the Id LibraryManagement.Item you want to return: ");
            int choice = scanner3.nextInt();
            for(Item i:currentItems)
            {
                if(choice==i.getId())
                {
                    i.setBorrowed(false);
                    System.out.println("LibraryManagement.Item Returned");
                    oldBorrower.borrowedAdder(i);
                    oldBorrower.currentRemover(i);
                    return;
                }
            }
            System.out.println("User doesnt own the LibraryManagement.Item with this Id");
            return;
        }

    }
    public void viewBorrowersList()
    {
        System.out.println("Current Borrowers List: ");
        for (Borrower b:borrowerList)
        {
            System.out.println(b.getName()+" is Currently Borrowing the following Items: ");
            List<Item> items=b.getCurrent();
            for (Item i:items)
            {
                i.displayInfo();
            }
        }
    }

}
