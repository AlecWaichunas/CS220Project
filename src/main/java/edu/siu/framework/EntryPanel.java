package edu.siu.framework;

import edu.siu.datastructures.LinkedList;
import edu.siu.google.query.DomainDetails;
import edu.siu.google.query.DomainDetailsPageMiner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alecwaichunas on 4/21/2017.
 */
public class EntryPanel extends JPanel{

    private JButton go = new JButton("Search");
    private JLabel title = new JLabel("Title:");
    private JLabel author = new JLabel("Author(s):");
    private JLabel publisher = new JLabel("Publisher:");
    private JLabel year = new JLabel("Year:");
    private JLabel genre = new JLabel("Genre:");
    private JLabel[] labelArray = {title, author, publisher, year, genre};
    private JTextField titleBox = new JTextField();
    private JTextField authorBox = new JTextField();
    private JTextField publisherBox = new JTextField();
    private JTextField yearBox = new JTextField();
    private JTextField genreBox = new JTextField();
    private JTextField[] textFieldArray = {titleBox, authorBox, publisherBox,yearBox,
                genreBox};
    private Font labelFont = new Font("Arial", Font.PLAIN, 15);
    private Font buttonFont = new Font("Arial", Font.PLAIN, 25);
    private Dimension labelDim = new Dimension(120, 35);
    private Dimension buttonDim = new Dimension(75, 50);

    private DomainDetailsPageMiner dpm;
    private FramePanel bookLists;
    private SearchButtons searchButtons;

    public EntryPanel(final DomainDetailsPageMiner dpm, final FramePanel bookLists, final SearchButtons searchButtons){
         this.dpm = dpm;
         this.bookLists = bookLists;
         setPreferredSize(new Dimension(700, 100));
         GridBagLayout gbl = new GridBagLayout();
         GridBagConstraints gbc = new GridBagConstraints();

         setLayout(gbl);
         title.setLabelFor(titleBox);
         author.setLabelFor(authorBox);
         publisher.setLabelFor(publisherBox);
         year.setLabelFor(yearBox);
         genre.setLabelFor(genreBox);

         go.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 String info = "";
                 for(int i = 0; i < textFieldArray.length; i++)
                     info += textFieldArray[i].getText() + " ";

                 if(info.equals("")) return;
                 LinkedList<DomainDetails> books = dpm.MineRequest(null, info, 1, 3);
                 searchButtons.setBooks(books);
                 searchButtons.setKeyWords(info);
                 bookLists.addBooks(books);

             }
         });

         gbc.fill = GridBagConstraints.BOTH;
         gbc.weightx = 1.0;
         gbc.gridx = 1;
         gbc.gridy = 1;
         gbl.setConstraints(title, gbc);
         add(title);
         gbc.gridx = 2;
         gbl.setConstraints(titleBox, gbc);
         add(titleBox);
         gbc.gridx = 3;
         gbl.setConstraints(author, gbc);
         add(author);
         gbc.gridx = 4;
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         gbl.setConstraints(authorBox, gbc);
         add(authorBox);

         gbc.gridy = 2;
         gbc.gridx = 1;
         gbc.gridwidth = 1;
         gbc.weightx = 1.0;
         gbl.setConstraints(publisher, gbc);
         add(publisher);
         gbc.gridx = 2;
         gbl.setConstraints(publisherBox, gbc);
         add(publisherBox);
         gbc.gridx = 3;
         gbl.setConstraints(year, gbc);
         add(year);
         gbc.gridx = 4;
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         gbl.setConstraints(yearBox, gbc);
         add(yearBox);

         gbc.gridwidth = 1;
         gbc.gridy = 3;
         gbc.gridx = 1;
         gbl.setConstraints(genre, gbc);
         add(genre);
         gbc.gridx = 2;
         gbl.setConstraints(genreBox, gbc);
         add(genreBox);
         gbc.gridy = 4;
         gbc.gridx = 1;
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         gbl.setConstraints(go, gbc);
         add(go);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //go button
        go.setBackground(Color.GREEN);
        go.setFont(buttonFont);
        //labels
        for (JLabel l : labelArray) {

        }
        //text boxes
        for (JTextField t : textFieldArray) {

        }

    }

}
