package edu.siu.framework;

import edu.siu.google.query.DomainDetailsPageMiner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alecwaichunas on 4/21/2017.
 */
public class ProgramFrame {

    //SETS the width and height of the program
    private static final int WIDTH = 750;
    private static final int HEIGHT = 550;

    public ProgramFrame(DomainDetailsPageMiner dpm){
        JFrame north = new JFrame("PDF-BOOK-MINER");

        //custom made frames
        FramePanel bookLists = new FramePanel(WIDTH - 50);
        SearchButtons searchButtons = new SearchButtons(WIDTH - 50, bookLists);
        JScrollPane jsp = new JScrollPane(bookLists, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setPreferredSize(new Dimension(WIDTH - 50, HEIGHT-  200));
        //adds panels to frame
        north.add(new EntryPanel(dpm, bookLists, searchButtons), BorderLayout.NORTH);
        north.add(searchButtons, BorderLayout.CENTER);
        north.add(jsp, BorderLayout.SOUTH);

        //setting frame properties
        north.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        north.setSize(WIDTH, HEIGHT);
        north.setResizable(false);
        north.setVisible(true);
    }


}
