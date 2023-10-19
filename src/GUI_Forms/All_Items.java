package GUI_Forms;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import LibraryManagement.*;

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
                                if (selectedRow >= 0) {
                                        Item selectedItem = list.get(selectedRow);

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


                JButton back = new JButton("Back");
                back.setOpaque(true);
                back.setContentAreaFilled(false);
                back.setBorderPainted(true);
                back.setBackground(Color.BLACK);
                back.setForeground(Color.white);
                back.setFont(new Font("Arial", Font.BOLD, 25));

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

