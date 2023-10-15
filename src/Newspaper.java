

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Newspaper extends Item
{
 private String publisher;
 private Date date;
 private  final int publisherCharges=5;

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public int calculateCost()
    {
        return 10+publisherCharges;
    }

    @Override
    public void displayInfo()
    {
        System.out.println("Newspaper ID: "+this.getId());
        System.out.println("Newspaper Tittle: "+this.getTittle());
        System.out.println("Newspaper Publisher: "+this.getPublisher());
        System.out.println("Newspaper Popularity Count: "+this.getPopularityCount());
        System.out.println("Newspaper Date: "+this.getDate());
        System.out.println("Newspaper Total Cost: "+this.calculateCost());
    }
    @Override
    public void edit()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the attributes to edit (enter the corresponding numbers without spaces):");
        System.out.println("1. Title");
        System.out.println("2. Publisher");
        System.out.println("3. Borrowed Status");
        System.out.println("4. Published Date");
        System.out.println("5. Popularity Count");
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
                    System.out.println("Enter the new Publisher:");
                    String publisher = scanner.nextLine();
                    this.setPublisher(publisher);
                }
                case 3 -> {
                    System.out.println("Enter the new borrowed status (true/false):");
                    boolean newBorrowedStatus = scanner.nextBoolean();
                    this.setBorrowed(newBorrowedStatus);
                }
                case 4 -> {
                    String userInput = scanner.nextLine();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    dateFormat.setLenient(false);
                    try {
                        Date date = dateFormat.parse(userInput);
                        this.setDate(date);
                        System.out.println("Date entered: " + dateFormat.format(date));
                    } catch (ParseException e) {
                        System.err.println("Invalid date format. Please enter a date in dd-mm-yyyy format.");
                    }
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
