package LibraryManagement;

import LibraryManagement.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Book extends Item
{
    private String author;
    private int year;
    private final int GST=200;

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Override
    public void displayInfo()
    {
        System.out.println("LibraryManagement.Book ID: "+this.getId());
        System.out.println("LibraryManagement.Book Tittle: "+this.getTittle());
        System.out.println("LibraryManagement.Book Author: "+this.getAuthor());
        System.out.println("LibraryManagement.Book Year: "+this.getYear());
        System.out.println("LibraryManagement.Book's Popularity Count: "+this.getPopularityCount());
        System.out.println("LibraryManagement.Book Price: "+this.getCost());
        System.out.println("LibraryManagement.Book Total Price: "+this.calculateCost());
    }

    @Override
    public int calculateCost()
    {
        return (int) (this.getCost()+(0.20*this.getCost())+GST);
    }
    @Override
    public void edit()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the attributes to edit (enter the corresponding numbers without spaces):");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Borrowed Status");
        System.out.println("4. Published Year");
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
                    System.out.println("Enter the new Author:");
                    String author = scanner.nextLine();
                    this.setAuthor(author);
                }
                case 3 -> {
                    System.out.println("Enter the new borrowed status (true/false):");
                    boolean newBorrowedStatus = scanner.nextBoolean();
                    this.setBorrowed(newBorrowedStatus);
                }
                case 4 -> {
                    System.out.println("Enter the new Published Year:");
                    int publishYear = scanner.nextInt();
                    this.setYear(publishYear);
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
}
