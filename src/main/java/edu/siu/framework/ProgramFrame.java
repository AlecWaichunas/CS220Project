package edu.siu.framework;

import javax.swing.*;
import java.awt.*;

/**
 * Created by alecwaichunas on 4/21/2017.
 */
public class ProgramFrame {

    public ProgramFrame(){
        JFrame north = new JFrame("North");

        north.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //north.setContentPane(np);
        north.add(new EntryPanel(), BorderLayout.NORTH);
        north.add(new FramePanel(), BorderLayout.SOUTH);
        
        north.setSize(1000, 550);
        north.setVisible(true);
        north.setLayout(new FlowLayout());
    }

    public static void main(String  args[]){
        new ProgramFrame();
    }


}
