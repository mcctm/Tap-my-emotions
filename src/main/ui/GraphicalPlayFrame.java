package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GraphicalPlayFrame extends GraphicalHomeFrame implements ActionListener {

    public GraphicalPlayFrame() {
        init();
        frame = new JFrame();
        panel = new JPanel();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        panel.setBackground(Color.getHSBColor(60, 0, 50));

        frame.setVisible(true);

    }
}
