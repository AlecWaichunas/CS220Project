package edu.siu.framework;

import edu.siu.datastructures.LinkedList;
import edu.siu.google.query.DomainDetails;
import edu.siu.google.query.DomainDetailsPageMiner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ryan
 * Edited by Alec on 4/21/2017
 */
public class EntryPanel extends JPanel{

    //All text boxes and text labels used for UI
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
    //Extra variables used to decorate
    private Font labelFont = new Font("Arial", Font.PLAIN, 15);
    private Font buttonFont = new Font("Arial", Font.PLAIN, 25);
    private Dimension labelDim = new Dimension(120, 35);
    private Dimension buttonDim = new Dimension(75, 50);

    //Program opertations
    private DomainDetailsPageMiner dpm;
    private FramePanel bookLists;
    private SearchButtons searchButtons;

    /**
     *
     * @param dpm used to mine for books
     * @param bookLists panel to add the books to
     * @param searchButtons panel to add info too
     */

    public EntryPanel(final DomainDetailsPageMiner dpm, final FramePanel bookLists, final SearchButtons searchButtons){
        //sets variables for this panel
         this.dpm = dpm;
         this.bookLists = bookLists;
         this.searchButtons = searchButtons;
         setPreferredSize(new Dimension(700, 100));
         //grid back layout
         GridBagLayout gbl = new GridBagLayout();
         GridBagConstraints gbc = new GridBagConstraints();
         setLayout(gbl);
         title.setLabelFor(titleBox);
         author.setLabelFor(authorBox);
         publisher.setLabelFor(publisherBox);
         year.setLabelFor(yearBox);
         genre.setLabelFor(genreBox);

         //adding functionality to the go button
         go.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 //put all the info together from the textboxes
                 String info = "";
                 for(int i = 0; i < textFieldArray.length; i++)
                     info += textFieldArray[i].getText() + " ";

                 //if info is nothing then stop operation
                 if(info.equals("")) return;
                //remove panels of booklists
                 bookLists.removePanels();
                 //search for the PDFs
                 LinkedList<DomainDetails> books = dpm.MineRequest(null, info, 1, 3);
                 //Sets info for other panels
                 searchButtons.setBooks(books);
                 searchButtons.setKeyWords(info);
                 bookLists.addBooks(books);

             }
         });

         //organinzing everything for the frame
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


    //paints to the go button
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //go button
        go.setBackground(Color.GREEN);
        go.setFont(buttonFont);

    }

}
