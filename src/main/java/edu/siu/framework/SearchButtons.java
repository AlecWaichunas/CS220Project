package edu.siu.framework;

import edu.siu.datastructures.LinkedList;
import edu.siu.datastructures.PriorityQueue;
import edu.siu.google.query.DomainDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Akec on 4/22/2017.
 */
public class SearchButtons extends JPanel {

    private FramePanel framePanel;

    private LinkedList<JButton> buttons = new LinkedList<JButton>();
    private LinkedList<DomainDetails> books;

    public LinkedList<DomainDetails> sortedBooks;

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
        JButton keyWords = new JButton("Description"); //most key words in description
        JButton fileFormat = new JButton("File Format"); //use priority queue
        fileFormat.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
        		PriorityQueue<DomainDetails> queue = new PriorityQueue<DomainDetails>();
        		for(DomainDetails d : books.toArray()){
        			queue.add(d);
        		}
        		for(DomainDetails d : queue.toArray()){
        			sortedBooks.add(queue.remove());
        		}
        		/*int size = books.size;
        		for(int i = 0; i < size; i++){
        			queue.add(books.remove());
        		}
        		sortedBooks.add(queue.remove());*/
        		updateBooks();
        	}
        });

        buttons.add(topResult);

        add(topResult);
        add(title);
        add(keyWords);
        add(fileFormat);

    }

    public void setBooks(LinkedList<DomainDetails> books){
        this.books = books;
        this.sortedBooks = books;
    }

    public void setDefaultLinkedList(LinkedList<DomainDetails> books){
        this.books = books;
    }

    public void updateBooks(){
        if(sortedBooks == null) return;
        framePanel.removePanels();

        framePanel.addBooks(sortedBooks);

    }



}
