package siu;

import javax.swing.*;

/**
 * Created by Alec on 3/3/2017.
 */

//extension of JFrame (A GUI implemented from the Java 1.8 Library)
public class MainFrame extends JFrame{

    private static final String APP_NAME = "My Application";

    public MainFrame(){
        //sets the name of the app from the constructor method inside JFrame.java
        super(APP_NAME);

        //sets the size of the jframe
        setSize(400, 400);
        //sets the close operation (Programs the exit button)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //makes the frame visible
        setVisible(true);
    }

}
