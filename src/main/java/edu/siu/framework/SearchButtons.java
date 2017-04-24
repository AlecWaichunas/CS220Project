package edu.siu.framework;

import edu.siu.datastructures.LinkedList;
import edu.siu.google.query.DomainDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Akec on 4/22/2017.
 */
public class SearchButtons extends JPanel {

    private LinkedList<JButton> buttons = new LinkedList<JButton>();
    private LinkedList<DomainDetails> books;

    public LinkedList<DomainDetails> sortedBooks;

    public SearchButtons(int width){
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(width, 30));

        JButton topResult = new JButton("Top Result");
        topResult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortedBooks = books;
            }
        });
        buttons.add(topResult);

        add(topResult);

    }

    public void setDefaultLinkedList(LinkedList<DomainDetails> book){
        this.books = books;
    }



}
