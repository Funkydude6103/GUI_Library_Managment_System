package GUI_Forms;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import LibraryManagement.*;
class AddItemForm {
        static JFrame frame;
        public static void createAndShowGUI()
        {
                frame = new JFrame("AddItem Form");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400, 200);
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(5, 2));
                JTextField titleField = new JTextField(20);
                JTextField authorField = new JTextField(20);
                JTextField yearField = new JTextField(4);
                JTextField priceField = new JTextField(10);
                JLabel titleLabel = new JLabel("Title:");
                JLabel authorLabel = new JLabel("Author:");
                JLabel yearLabel = new JLabel("Published Year:");
                JLabel priceLabel = new JLabel("Price:");
                JButton saveButton = new JButton("Add");
                Library library=new Library();
                List<Item> itemList=library.loadFromFileReturn();
                saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String title = titleField.getText();
                                String author = authorField.getText();
                                String yearText = yearField.getText();
                                String priceText = priceField.getText();
                                int year = -1;
                                try {
                                        year = Integer.parseInt(yearText);
                                } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(frame, "Invalid year input. Please enter a valid year.");
                                        return;
                                }
                                int price = -1;
                                try {
                                        price = Integer.parseInt(priceText);
                                } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(frame, "Invalid price input. Please enter a valid price.");
                                        return;
                                }
                                if(!title.equals("") && !author.equals("")) {
                                        System.out.println("Title: " + title);
                                        System.out.println("Author: " + author);
                                        System.out.println("Published Year: " + year);
                                        System.out.println("Price: " + price);
                                        int response = JOptionPane.showConfirmDialog(
                                                null,
                                                "Are you sure?",
                                                "Confirmation",
                                                JOptionPane.YES_NO_OPTION
                                        );

                                        if (response == JOptionPane.YES_OPTION) {
                                               Book book=new Book();
                                               book.setTittle(title);
                                               book.setAuthor(author);
                                               book.setYear(year);
                                               book.setCost(price);
                                               itemList.add(book);
                                                library.saveInFile(itemList);
                                                JOptionPane.showMessageDialog(frame, "Item Addition Complete");
                                                frame.dispose();

                                        } else if (response == JOptionPane.NO_OPTION) {

                                        } else if (response == JOptionPane.CLOSED_OPTION) {

                                        }
                                }
                                else
                                {
                                        JOptionPane.showMessageDialog(frame, "Fill all Fields");
                                        return;
                                }
                        }
                });
                panel.add(titleLabel);
                panel.add(titleField);
                panel.add(authorLabel);
                panel.add(authorField);
                panel.add(yearLabel);
                panel.add(yearField);
                panel.add(priceLabel);
                panel.add(priceField);
                panel.add(new JLabel());
                panel.add(saveButton);

                frame.add(panel);
                frame.setVisible(true);
        }

        public static JFrame getFrame() {
                return frame;
        }
}

class EditItemForm {
        static JFrame frame;
        public static void createAndShowGUI(String tittle_)
        {
                frame = new JFrame("EditItem Form");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400, 200);
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(5, 2));
                JTextField titleField = new JTextField(20);
                JTextField authorField = new JTextField(20);
                JTextField yearField = new JTextField(4);
                JTextField priceField = new JTextField(10);
                JLabel titleLabel = new JLabel("Title:");
                JLabel authorLabel = new JLabel("Author:");
                JLabel yearLabel = new JLabel("Published Year:");
                JLabel priceLabel = new JLabel("Price:");
                JButton saveButton = new JButton("Save");
                Library library=new Library();
                List<Item> itemList=library.loadFromFileReturn();
                for (Item item:itemList)
                {
                        if(item.getTittle().equals(tittle_))
                        {
                                if(item instanceof Book) {
                                        titleField.setText(item.getTittle());
                                        authorField.setText(((Book) item).getAuthor());
                                        yearField.setText(String.valueOf(((Book) item).getYear()));
                                        priceField.setText(String.valueOf(((Book) item).getCost()));
                                        break;
                                }
                        }
                }
                saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                String title = titleField.getText();
                                String author = authorField.getText();
                                String yearText = yearField.getText();
                                String priceText = priceField.getText();
                                int year = -1;
                                try {
                                        year = Integer.parseInt(yearText);
                                } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(frame, "Invalid year input. Please enter a valid year.");
                                        return;
                                }
                                int price = -1;
                                try {
                                        price = Integer.parseInt(priceText);
                                } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(frame, "Invalid price input. Please enter a valid price.");
                                        return;
                                }
                                if(!title.equals("") && !author.equals("")) {
                                        System.out.println("Title: " + title);
                                        System.out.println("Author: " + author);
                                        System.out.println("Published Year: " + year);
                                        System.out.println("Price: " + price);
                                        int response = JOptionPane.showConfirmDialog(
                                                null,
                                                "Are you sure you?",
                                                "Confirmation",
                                                JOptionPane.YES_NO_OPTION
                                        );

                                        if (response == JOptionPane.YES_OPTION) {
                                                for (Item item:itemList)
                                                {
                                                        if(item.getTittle().equals(tittle_))
                                                        {
                                                                if(item instanceof Book) {
                                                                        item.setTittle(title);
                                                                        ((Book) item).setAuthor(author);
                                                                        ((Book) item).setYear(year);
                                                                        item.setCost(price);
                                                                        break;
                                                                }
                                                        }
                                                }
                                                library.saveInFile(itemList);
                                                JOptionPane.showMessageDialog(frame, "Item Edit Complete");
                                                frame.dispose();

                                        } else if (response == JOptionPane.NO_OPTION) {

                                        } else if (response == JOptionPane.CLOSED_OPTION) {

                                        }
                                }
                                else
                                {
                                        JOptionPane.showMessageDialog(frame, "Fill all Fields");
                                        return;
                                }
                        }
                });
                panel.add(titleLabel);
                panel.add(titleField);
                panel.add(authorLabel);
                panel.add(authorField);
                panel.add(yearLabel);
                panel.add(yearField);
                panel.add(priceLabel);
                panel.add(priceField);
                panel.add(new JLabel());
                panel.add(saveButton);

                frame.add(panel);
                frame.setVisible(true);
//                frame.addWindowListener(new WindowListener() {
//                });
        }

        public static JFrame getFrame() {
                return frame;
        }
}
public class All_Items {
        public static void createAndShowGUI() {
                JFrame frame = new JFrame("Items Management");
                frame.setSize(800, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                ImageIcon backgroundImage = new ImageIcon("background.jpg");
                JLabel backgroundLabel = new JLabel(backgroundImage);
                backgroundLabel.setLayout(new BorderLayout());
                frame.setContentPane(backgroundLabel);

                JLabel head = new JLabel("Items Management");
                head.setFont(new Font("Arial", Font.BOLD, 25));
                head.setHorizontalAlignment(SwingConstants.CENTER);
                JPanel topPanel = new JPanel();
                topPanel.setOpaque(false);
                topPanel.add(head);
                frame.add(topPanel, BorderLayout.NORTH);
                DefaultTableModel model = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                                return column != 0 && column != 1 && column!= 2 && column!=3;
                        }
                };

                model.addColumn("Item Type");
                model.addColumn("Title");
                model.addColumn("Author");
                model.addColumn("Publication Year");
                model.addColumn("Read Item");

                JTable jTable = new JTable(model);
                jTable.setEditingColumn(1);
                jTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
                jTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));

                Library library = new Library();
                List<Item> list = library.loadFromFileReturn();

                for (Item i : list) {
                        if (i instanceof Book)
                        {
                                model.addRow(new Object[]{"Book", i.getTittle(), ((Book) i).getAuthor(), ((Book) i).getYear(),"Read"});
                        }
                }

                jTable.setDragEnabled(false);
                jTable.setOpaque(false);
                jTable.addMouseMotionListener(new MouseInputAdapter() {
                        private int highlightedRow = -1;

                        @Override
                        public void mouseMoved(MouseEvent e) {
                                int row = jTable.rowAtPoint(e.getPoint());
                                if (row != highlightedRow) {
                                        if (highlightedRow >= 0) {
                                                jTable.removeRowSelectionInterval(highlightedRow, highlightedRow);
                                        }
                                        highlightedRow = row;
                                        jTable.addRowSelectionInterval(row, row);
                                }
                        }
                });


                JScrollPane scrollPane = new JScrollPane(jTable);
                scrollPane.setOpaque(false);
                frame.add(scrollPane, BorderLayout.CENTER);

                JButton add = new JButton("Add Item");
                add.setOpaque(true);
                add.setContentAreaFilled(false);
                add.setBorderPainted(true);
                add.setBackground(Color.BLACK);
                add.setForeground(Color.white);
                add.setFont(new Font("Arial", Font.BOLD, 25));
                add.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                AddItemForm.createAndShowGUI();
                                JFrame addFrame = AddItemForm.getFrame();
                                addFrame.addWindowListener(new WindowAdapter() {
                                        @Override
                                        public void windowClosed(WindowEvent e) {
                                                frame.dispose();
                                                SwingUtilities.invokeLater(All_Items::createAndShowGUI);
                                        }
                                });

                        }
                });

                JButton edit = new JButton("Edit Item");
                edit.setOpaque(true);
                edit.setContentAreaFilled(false);
                edit.setBorderPainted(true);
                edit.setBackground(Color.BLACK);
                edit.setForeground(Color.white);
                edit.setFont(new Font("Arial", Font.BOLD, 25));
                edit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int selectedRow = jTable.getSelectedRow();
                                if (selectedRow >= 0)
                                {
                                        System.out.println(jTable.getValueAt(selectedRow,1));
                                        EditItemForm.createAndShowGUI((String)jTable.getValueAt(selectedRow,1));
                                        JFrame editFrame = EditItemForm.getFrame();
                                        editFrame.addWindowListener(new WindowAdapter() {
                                                @Override
                                                public void windowClosed(WindowEvent e) {
                                                        frame.dispose();
                                                        SwingUtilities.invokeLater(All_Items::createAndShowGUI);
                                                }
                                        });
                                }
                                else {
                                        JOptionPane.showMessageDialog(frame, "Please Select a Item form the Table");
                                }
                        }
                });


                JButton delete = new JButton("Delete Item");
                delete.setOpaque(true);
                delete.setContentAreaFilled(false);
                delete.setBorderPainted(true);
                delete.setBackground(Color.BLACK);
                delete.setForeground(Color.white);
                delete.setFont(new Font("Arial", Font.BOLD, 25));

                delete.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int selectedRow = jTable.getSelectedRow();
                                if (selectedRow >= 0)
                                {
                                        System.out.println(jTable.getValueAt(selectedRow,1));
                                        String name=(String)jTable.getValueAt(selectedRow,1);

                                        int response = JOptionPane.showConfirmDialog(
                                                null,
                                                "Are you sure you?",
                                                "Confirmation",
                                                JOptionPane.YES_NO_OPTION
                                        );

                                        if (response == JOptionPane.YES_OPTION) {
                                                for(Item i:list)
                                                {
                                                        if(i.getTittle().equals(name))
                                                        {
                                                                list.remove(i);
                                                                library.saveInFile(list);
                                                                break;
                                                        }
                                                }
                                                frame.dispose();
                                                SwingUtilities.invokeLater(All_Items::createAndShowGUI);

                                        } else if (response == JOptionPane.NO_OPTION) {

                                        } else if (response == JOptionPane.CLOSED_OPTION) {

                                        }
                                }

                                else
                                {
                                        JOptionPane.showMessageDialog(frame, "Please Select a Item form the Table");
                                }
                        }
                });


                JButton back = new JButton("Back");
                back.setOpaque(true);
                back.setContentAreaFilled(false);
                back.setBorderPainted(true);
                back.setBackground(Color.BLACK);
                back.setForeground(Color.white);
                back.setFont(new Font("Arial", Font.BOLD, 25));
                back.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                frame.dispose();
                                MainMenu.createAndShowGUI();
                        }
                });

                JPanel buttons=new JPanel();
                buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
                buttons.add(back);
                buttons.add(add);
                buttons.add(edit);
                buttons.add(delete);
                buttons.setOpaque(false);


                frame.add(buttons, BorderLayout.SOUTH);

                frame.setSize(673, 503);
                frame.setResizable(false);
                frame.setVisible(true);
        }
}


class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
                setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setText((value == null) ? "" : value.toString());
                return this;
        }
}

class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private String label;
        private boolean isPushed;
        private String tittle;

        public ButtonEditor(JCheckBox checkBox) {
                super(checkBox);
                button = new JButton();
                button.setOpaque(true);
                button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                fireEditingStopped();
                                System.out.println("Button Pressed: " + tittle);
                                Library library=new Library();
                                List<Item> itemList=library.loadFromFileReturn();
                                for (Item item:itemList)
                                {
                                        if(item.getTittle().equals(tittle))
                                        {
                                                item.setPopularityCount(item.getPopularityCount()+1);
                                        }
                                }
                                        library.saveInFile(itemList);
                                File file = new File(tittle+".txt");
                                if(file.exists() && file.isFile())
                                {
                                      JFrame bookview=new JFrame(tittle);
                                      bookview.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                                      bookview.setSize(700,503);
                                      bookview.setLayout(new BorderLayout());
                                      JTextArea textArea = new JTextArea();
                                      JScrollPane scrollPane = new JScrollPane(textArea);
                                        try {
                                                String content= Files.readString(file.toPath());
                                                textArea.setText(content);
                                        } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                        }
                                        textArea.setEditable(false);
                                        bookview.add(scrollPane);
                                        bookview.setVisible(true);
                                        bookview.addWindowListener(new WindowAdapter() {
                                                @Override
                                                public void windowClosing(WindowEvent e) {
                                                        int option = JOptionPane.showConfirmDialog(bookview, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                                                        if (option == JOptionPane.YES_OPTION) {
                                                                bookview.dispose();
                                                        }
                                                        else {
                                                        }
                                                }
                                        });

                                }
                                else
                                {
                                        JOptionPane.showMessageDialog(null,"File does not exist");
                                }
                        }
                });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                label = (value == null) ? "" : value.toString();
                button.setText(label);
                isPushed = true;
                tittle=(String) table.getValueAt(row, 1);
                return button;
        }

        @Override
        public Object getCellEditorValue() {
                isPushed = false;
                return label;
        }

        @Override
        public boolean stopCellEditing() {
                isPushed = false;
                return super.stopCellEditing();
        }
}

