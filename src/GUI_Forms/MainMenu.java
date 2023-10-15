package GUI_Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MainMenu
{
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new GridLayout(10, 1));

        String[] menuItems = {
                "Hot Picks!",
                "Borrow an item",
                "Add Item",
                "Edit Item",
                "Delete Item",
                "View All Items",
                "View Item by ID",
                "View Borrowers List",
                "Return A Book",
                "Exit"
        };

        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setOpaque(true);
            button.setContentAreaFilled(false);
            button.setBorderPainted(true);
            button.setBackground(Color.BLACK);
            button.setForeground(Color.white);
            button.setFont(new Font("Arial", Font.BOLD, 25));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle button click actions here
                    JOptionPane.showMessageDialog(frame, "You clicked " + item);
                }
            });
            menuPanel.add(button);
        }

        frame.setSize(673,503);
        frame.setResizable(false);
        frame.add(menuPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
