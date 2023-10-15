
import java.util.Objects;
import java.util.Scanner;

public class Menu
{
public void showMenu()
{
    Library library=new Library();
    library.loadFromFile();
    Scanner scanner = new Scanner(System.in);

    while (true)
    {
        System.out.println("*****************************************");
        System.out.println("*  Library Management System Menu:      *");
        System.out.println("*  1. Hot Picks!                        *");
        System.out.println("*  2. Borrow an item                    *");
        System.out.println("*  3. Add Item                          *");
        System.out.println("*  4. Edit Item                         *");
        System.out.println("*  5. Delete Item                       *");
        System.out.println("*  6. View All Items                    *");
        System.out.println("*  7. View Item by ID                   *");
        System.out.println("*  8. View Borrowers List               *");
        System.out.println("*  9. Return A Book                     *");
        System.out.println("*  10. Exit                             *");
        System.out.println("*****************************************");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> library.displayHotPicks();
            case "2" -> library.borrowItem();
            case "3" -> library.addItem();
            case "4" ->
            {
                Scanner scanner3 = new Scanner(System.in);
                System.out.println("Enter the Item ID you want to edit:");
                int itemId = scanner3.nextInt();
                Object o = library.getItemById(itemId);
                if (!Objects.isNull(o)) {
                    Item item = library.getItemById(itemId);
                    item.edit();
                } else {
                    System.out.println("Item does not Exists");
                }
            }
            case "5" ->
            {
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Enter the Item ID for Deletion: ");
                int id2 = scanner2.nextInt();
                library.deleteItem(id2);
            }
            case "6" -> library.displayAllItems();
            case "7" ->
            {
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter the Items ID: ");
                int id = scanner1.nextInt();
                Item i = library.getItemById(id);
                library.displaySingleItem(i);
                System.out.println("Enter 0 to return to Main Menu");
                while (true) {
                    String choice1 = scanner1.nextLine();
                    if (choice1.equals("0")) {
                        break;
                    }
                }
            }
            case "8" -> library.viewBorrowersList();
            case "10" ->
            {
                System.out.println("Exiting the program. Goodbye!");
                scanner.close();
                System.exit(0);
            }
            case "9" -> library.returnItem();
            default -> System.out.println("Invalid choice. Please enter a valid option (1-9).");
        }
    }
}

}
