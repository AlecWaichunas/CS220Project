package edu.siu.framework;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FramePanel extends JPanel implements ActionListener{

	public FramePanel() {
		super( new GridLayout(0, 1));
		setSize(600, 600);

		JScrollPane scroll = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		List<JPanel> panels = new ArrayList<JPanel>();
		for (int i = 1; i <= 200; i++) {
		    JPanel p = new JPanel();
		    p.setName("panel " +String.valueOf(i));
		    JLabel l = new JLabel();
		    l.setText("Book " + String.valueOf(i));
		    JButton b = new JButton();
		    b.setSize(30, 30);
		    ImageIcon download = new ImageIcon("src/download1.png");
		    Image img = download.getImage();
		    Image img2 = img.getScaledInstance(b.getWidth(), b.getHeight(), Image.SCALE_DEFAULT);
		    b.setIcon(new ImageIcon(img2));
		    
		    if(i % 2 == 0){
		    	p.setBackground(Color.BLACK);
		    }
		    else{
		    	p.setBackground(Color.YELLOW);
		    }
		    panels.add(p);
		    p.add(l);
		    p.add(b);
		}
		for (JPanel p : panels) {
			this.add(p);
		}
		
	}

	public void actionPerformed(ActionEvent e) {

	}
}
