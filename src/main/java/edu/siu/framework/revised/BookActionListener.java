package edu.siu.framework.revised;

import edu.siu.datastructures.LinkedList;
import edu.siu.google.query.DomainDetails;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Akec on 4/22/2017.
 */
public abstract class BookActionListener implements ActionListener {

    private LinkedList<DomainDetails> books = null;

    public abstract void actionPerformed(ActionEvent e);

    public void updateBooks(LinkedList<DomainDetails> books){
        this.books = books;
    }

}
