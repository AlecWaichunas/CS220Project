package edu.siu.framework;

import edu.siu.datastructures.LinkedList;
import edu.siu.google.query.DomainDetails;
import edu.siu.sortingalgorithms.QuickSort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Alec on 4/22/2017.
 * Edited by Ryan and Robert
 */
public class SearchButtons extends JPanel {

    private FramePanel framePanel;

    private LinkedList<JButton> buttons = new LinkedList<JButton>();
    private LinkedList<DomainDetails> books;

    public LinkedList<DomainDetails> sortedBooks;
    private String keyWords = "";

    public SearchButtons(int width, final FramePanel framePanel){
        this.framePanel = framePanel;
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(width, 30));

        JButton topResult = new JButton("Top Results");
        topResult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortedBooks = books;
                updateBooks();
            }
        });

        /**
         * See @DomainDetails class for properties
         */

        JButton title = new JButton("Title");
        title.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DomainDetails[] books = sortedBooks.toArray(DomainDetails.class);
                QuickSort.Sort(books, 0, sortedBooks.getCurrentSize() - 1);
                sortedBooks = LinkedList.ArrayToLinkedList(books);
                updateBooks();
            }
        });
        JButton keyWords = new JButton("Description"); //most key words in description
        JButton fileFormat = new JButton("File Format");

        buttons.add(topResult);

        //adds every search term to it
        add(topResult);
        add(title);
        add(keyWords);
        add(fileFormat);

    }

    /**
     * Sets default for books and sorted books
     * @param books default books
     */
    public void setBooks(LinkedList<DomainDetails> books){
        this.books = books;
        this.sortedBooks = books;
    }

    /**
     * sets the default data
     * @param books the DomainDetails
     */
    public void setDefaultLinkedList(LinkedList<DomainDetails> books){
        this.books = books;
    }

    /**
     * Updates the UI books after they were sorted
     */
    public void updateBooks(){
        if(sortedBooks == null) return;
        framePanel.removePanels();

        framePanel.addBooks(sortedBooks);

    }

    /**
     * sets the search terms used
     * @param keyWords search terms used
     */
    public void setKeyWords(String keyWords){
        this.keyWords = keyWords;
    }


}
