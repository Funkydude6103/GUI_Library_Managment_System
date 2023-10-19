package GUI_Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainMenu
{
    public static void createAndShowGUI() {
        JFrame frame = new JFrame("LibraryManagement.Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        ImageIcon backgroundImage = new ImageIcon("background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new GridLayout(10, 1));

        String[] menuItems = {
                "Hot Picks!",
                "Manage Items",
                "Exit"
        };
        JLabel head = new JLabel("Library Management System");
        head.setFont(new Font("Arial", Font.BOLD, 25));
        head.setForeground(Color.white);
        JPanel headPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        headPanel.setBackground(Color.BLACK);
        headPanel.add(head);
        menuPanel.add(headPanel);

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
                    if(item.equals("Exit"))
                    {
                        frame.dispose();
                    }
                    if(item.equals("Manage Items"))
                    {
                        frame.dispose();
                        SwingUtilities.invokeLater(All_Items::createAndShowGUI);
                    }
                    if (item.equals("Hot Picks!"))
                    {
                        frame.dispose();
                        SwingUtilities.invokeLater(HotPicks::createAndShowGUI);
                    }
                }
            });
            menuPanel.add(button);
        }
        frame.setSize(537,421);
        frame.setResizable(false);
        frame.add(menuPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = frame.getSize();
                System.out.println("Frame Size: " + size.width + "x" + size.height);
            }
        });
    }
}
