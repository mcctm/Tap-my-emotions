package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalMain implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel title;

    public GraphicalMain() {
        frame = new JFrame();

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 30, 120, 30));
        panel.setLayout(new GridLayout(5, 5));
        panel.setBackground(Color.getHSBColor(60, 0, 50));

        title = new JLabel("♡ Tap my heart ♡");
        title.setBounds(150, 0, 1000, 100);
        title.setFont(new Font("Noto Sans", Font.PLAIN, 30));
        frame.add(title);

        ImageIcon imgIcon = new ImageIcon("./data/emotions.png");
        Image image = imgIcon.getImage();
        Image imgScale = image.getScaledInstance(500,200,Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        JLabel imageLabel = new JLabel(scaledIcon);
        panel.add(imageLabel);

        createTools();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Tap my heart");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicalMain();
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createTools() {

        JButton playGameButton = new JButton("Play game");
        playGameButton.addActionListener(this);
        panel.add(playGameButton);

        JButton designGameButton = new JButton("Design new questions");
        designGameButton.addActionListener(this);
        panel.add(designGameButton);

        JButton loadQuestionsButton = new JButton("Load question bank");
        loadQuestionsButton.addActionListener(this);
        panel.add(loadQuestionsButton);

        JButton saveQuestionsButton = new JButton("Save question bank");
        saveQuestionsButton.addActionListener(this);
        panel.add(saveQuestionsButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
