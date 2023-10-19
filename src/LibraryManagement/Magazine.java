package LibraryManagement;

import LibraryManagement.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Magazine extends Item
{
    private String publisher;
    private List<String> authorList;

    public String getPublisher()
    {
        return publisher;
    }

    @Override
    public void edit()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the attributes to edit (enter the corresponding numbers without spaces):");
        System.out.println("1. Title");
        System.out.println("2. Authors");
        System.out.println("3. Borrowed Status");
        System.out.println("4. Publisher");
        System.out.println("5. Popularity Count");
        System.out.println("6. Cost");
        String attributeChoices = scanner.next();
        List<Integer> choices = new ArrayList<>();
        for (char choiceChar : attributeChoices.toCharArray())
        {
            int choice = Character.getNumericValue(choiceChar);
            choices.add(choice);
        }
        for (int choice : choices) {
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the new title:");
                    scanner.nextLine();
                    String newTitle = scanner.nextLine();
                    this.setTittle(newTitle);
                }
                case 2 -> {
                    System.out.println("Enter the Authors of LibraryManagement.Book and Enter 0 to exit entering Authors: ");
                    List<String> authors=new ArrayList<>();
                    while(true)
                    {
                        String input = scanner.nextLine();
                        if (input.equals("0"))
                            break;
                        authors.add(input);
                    }
                    this.setAuthorList(authors);
                }
                case 3 -> {
                    System.out.println("Enter the new borrowed status (true/false):");
                    boolean newBorrowedStatus = scanner.nextBoolean();
                    this.setBorrowed(newBorrowedStatus);
                }
                case 4 -> {
                    System.out.println("Enter the new Published Year:");
                    String publisher = scanner.nextLine();
                    this.setPublisher(publisher);
                }
                case 5 -> {
                    System.out.println("Enter the new popularity count:");
                    int newPopularityCount = scanner.nextInt();
                    this.setPopularityCount(newPopularityCount);
                }
                case 6 -> {
                    System.out.println("Enter the new cost:");
                    int newCost = scanner.nextInt();
                    this.setCost(newCost);
                }
                default -> System.out.println("Invalid choice: " + choice);
            }
        }


    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public List<String> getAuthorList()
    {
        return authorList;
    }

    public void setAuthorList(List<String> authorList)
    {
        this.authorList = authorList;
    }

    @Override
    public int calculateCost()
    {
        return this.getCost()*this.getPopularityCount();
    }

    @Override
    public void displayInfo()
    {
        System.out.println("LibraryManagement.Magazine ID: "+this.getId());
        System.out.println("LibraryManagement.Magazine Tittle: "+this.getTittle());
        System.out.println("LibraryManagement.Magazine Authors: "+this.getAuthorList());
        System.out.println("LibraryManagement.Magazine Publisher Company: "+this.getPublisher());
        System.out.println("LibraryManagement.Magazine Popularity Count: "+this.getPopularityCount());
        System.out.println("LibraryManagement.Magazine Price: "+this.getCost());
        System.out.println("LibraryManagement.Magazine Total Price: "+this.calculateCost());
    }
}
