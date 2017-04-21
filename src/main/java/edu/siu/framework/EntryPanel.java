package edu.siu.framework;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alecwaichunas on 4/21/2017.
 */
public class EntryPanel extends JPanel{

    private JButton go = new JButton("GO");
    private JLabel title = new JLabel("Title");
    private JLabel author = new JLabel("Author(s)");
    private JLabel publisher = new JLabel("Publisher");
    private JLabel year = new JLabel("Year");
    private JLabel genre = new JLabel("Genre");
    private JLabel[] labelArray = {title, author, publisher, year, genre};
    private JTextField titleBox = new JTextField();
    private JTextField authorBox = new JTextField();
    private JTextField publisherBox = new JTextField();
    private JTextField yearBox = new JTextField();
    private JTextField genreBox = new JTextField();
    private JTextField[] textFieldArray = {titleBox, authorBox, publisherBox,yearBox,
                genreBox};
    private Font textFieldFont = new Font("Arial", Font.PLAIN, 20);
    private Font labelFont = new Font("Arial", Font.PLAIN, 25);
    private Font buttonFont = new Font("Arial", Font.PLAIN, 70);
    private Dimension labelDim = new Dimension(120, 100);
    private Dimension textFieldDim = new Dimension(190, 30);
    private Dimension buttonDim = new Dimension(250, 150);

    public EntryPanel(){
         setPreferredSize(new Dimension(1000, 1000));
         setLayout(new FlowLayout());
         add(title);
         add(titleBox);
         add(author);
         add(authorBox);
         add(publisher);
         add(publisherBox);
         add(year);
         add(yearBox);
         add(genre);
         add(genreBox);
         add(go);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        //go button
        go.setPreferredSize(buttonDim);
        go.setBackground(Color.GREEN);
        go.setFont(buttonFont);
        go.setVisible(true);
        //labels
        for (JLabel l : labelArray) {
            l.setVisible(true);
            l.setPreferredSize(labelDim);
            l.setFont(labelFont);
        }
        //text boxes
        for (JTextField t : textFieldArray) {
            t.setVisible(true);
            t.setPreferredSize(textFieldDim);
            t.setFont(textFieldFont);
        }

    }

}
