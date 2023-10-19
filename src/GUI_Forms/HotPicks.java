package GUI_Forms;
import LibraryManagement.Item;
import LibraryManagement.Library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import  java.util.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

    public class HotPicks {
        public static void createAndShowGUI() {
            JFrame frame = new JFrame("Hot Picks!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 400);

            Library library = new Library();
            List<Item> items = library.returnHotPicks(library.loadFromFileReturn());
            int[] data = new int[items.size()];
            String[] labels = new String[items.size()];
            for (int i = 0; i < items.size(); i++) {
                data[i] = items.get(i).getPopularityCount();
                labels[i] = items.get(i).getTittle();
            }

            EnhancedBarChart chart = new EnhancedBarChart(data, labels);

            JPanel chartPanel = new JPanel(new BorderLayout());
            chartPanel.add(chart, BorderLayout.CENTER);

            JScrollPane scrollPane = new JScrollPane(chartPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            JButton backButton = new JButton("Back");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    MainMenu.createAndShowGUI();
                }
            });
            ImageIcon backgroundImage = new ImageIcon("background.jpg");
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setLayout(new BorderLayout());
            frame.setContentPane(backgroundLabel);
            JLabel head=new JLabel("Hot Picks!\n\n\n",SwingConstants.CENTER);
            head.setFont(new Font("Arial", Font.BOLD, 25));
            head.setOpaque(false);
            scrollPane.setOpaque(false);
            frame.add(head, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);
            backButton.setSize(30,30);
            frame.add(backButton, BorderLayout.SOUTH);
            frame.setSize(700,503);
            frame.setResizable(false);
            frame.setVisible(true);
        }
    }



class EnhancedBarChart extends JPanel {
    private int[] data;
    private String[] labels;
    private Color[] colors;
    private final int barWidth = 50;
    private final int barSpacing = 150;
    private String hoverText = "";


    public EnhancedBarChart(int[] data, String[] labels) {
        add(Box.createRigidArea(new Dimension(0, 50)));
        this.data = data;
        this.labels = labels;
        this.colors = generateRandomColors(data.length);
      addMouseMotionListener(new MouseAdapter() {
          @Override
          public void mouseMoved(MouseEvent e) {
              super.mouseMoved(e);
              int x = e.getX();
              int y = e.getY();
              int barIndex = (x - 70) / (barWidth + barSpacing);
              System.out.println(barIndex);
              if (barIndex >= 0 && barIndex < data.length) {
                  hoverText = labels[barIndex] + ": " + data[barIndex];
                  System.out.println(hoverText);
              } else {
                  hoverText = "";
              }
              repaint();

          }
      });
        this.setSize(data.length * (barWidth + barSpacing) + 70,getMaxValue() * (getHeight() - 30));
        int preferredWidth = data.length * (barWidth + barSpacing) + 70;
        int preferredHeight = getMaxValue() * (getHeight() - 30);
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int maxValue = getMaxValue();

        // Draw Y-axis
        g.setColor(Color.BLACK);
        g.drawLine(55, 10, 55, height - 20);
        String yAxisLabel = "Popularity";
        int labelWidth = g.getFontMetrics().stringWidth(yAxisLabel);
        int labelX = 50 - labelWidth +3; // Adjust the X-coordinate
        g.drawString(yAxisLabel, labelX, height / 2);


        // Draw X-axis
        g.drawLine(55, height - 20, width - 10, height - 20);
        g.drawString("Items", width - 50, height - 5);

        for (int i = 0; i < data.length; i++) {
            int barHeight = data[i] * (height - 30) / maxValue;
            int x = i * (barWidth + barSpacing) + 70;
            int y = height - 20 - barHeight;

            // Draw bar
            g.setColor(colors[i]);
            g.fillRect(x, y, barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);

            // Draw label
            g.drawString(labels[i], x, height - 5);

            if (hoverText != null && hoverText.trim().equals(labels[i] + ": " + data[i])) {
                g.setColor(Color.BLACK);
                g.drawString(hoverText, x, y - 5);
            }
        }
    }

    private int getMaxValue() {
        int max = data[0];
        for (int value : data) {
            if (value > max) {
                max = value;
            }
        }
        return max+3;
    }

    private Color[] generateRandomColors(int count) {
        Color[] colors = new Color[count];
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            colors[i] = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
        }
        return colors;
    }
}
