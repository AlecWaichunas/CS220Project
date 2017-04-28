package edu.siu.framework;

import edu.siu.datastructures.LinkedList;
import edu.siu.google.query.DomainDetails;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by Robert
 * Edited by Alec on 4/21/2017
 */

public class FramePanel extends JPanel{

	private int width;

	/**
	 *
	 * @param width width of the panel
	 */
	public FramePanel(int width) {
		this.width = width;
		this.setMinimumSize(new Dimension(width, 600));
	}

	/**
	 * add books to be showed off
	 *
	 * @param book books that are being shown
	 */
	public void addBooks(LinkedList<DomainDetails> book){
		//puts the books into an array
		DomainDetails[] books = book.toArray(DomainDetails.class);
		//updates size of the panel for JScrollpane
		this.setPreferredSize(new Dimension(width, books.length * 35));

		//creates the panels and shows them
		List<JPanel> panels = new ArrayList<JPanel>();
		for (int i = 1; i < books.length; i++) {
			//current book that is creating a panel
		    final DomainDetails myBook = books[i];
		    if(myBook == null) continue;
		    //create and set properties of panel
			JPanel p = new JPanel();
			p.setLayout(new BorderLayout());
			p.setPreferredSize(new Dimension(width, 30));
			p.setName("panel " +String.valueOf(i));
			//inner data of jpanel
			JLabel l = new JLabel();
			l.setText("   " + myBook.title + "\n");
			//creation of button that goes to the domain
			JButton b = new JButton();
			b.setPreferredSize(new Dimension(30, 30));
			//sets the img TODO get this to work
			String imgIcon = myBook.og_image;
			if(imgIcon == null) imgIcon = myBook.displayLink;
			if(imgIcon == null) imgIcon = myBook.thumbnail;
			if(imgIcon == null) imgIcon = "src/download1.png";
			ImageIcon download = new ImageIcon(imgIcon);
			b.setIcon(download);

			//opens the browser when button is pressed
			b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Desktop.getDesktop().browse(new URI(myBook.link));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (URISyntaxException e1) {
                        e1.printStackTrace();
                    }
                }
            });

			//color of the panels
			if(i % 2 == 0){
				p.setBackground(Color.LIGHT_GRAY);
			}
			else{
				p.setBackground(Color.WHITE);
			}

			//adds everything to the correct parent
			p.add(l, BorderLayout.WEST);
			p.add(b, BorderLayout.EAST);
			this.add(p);
		}

		//updateUI so it shows
		this.updateUI();
	}

	/**
	 * removes all panels and updates the UI
	 */
	public void removePanels(){
		this.removeAll();
		this.updateUI();
	}

}
