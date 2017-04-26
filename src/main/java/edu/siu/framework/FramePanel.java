package edu.siu.framework;

import edu.siu.datastructures.LinkedList;
import edu.siu.google.query.DomainDetails;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FramePanel extends JPanel{

	private int width;

	public FramePanel(int width) {
		this.width = width;
		this.setMinimumSize(new Dimension(width, 600));
	}

	public void addBooks(LinkedList<DomainDetails> book){
		Object[] books = book.toArray();
		this.setPreferredSize(new Dimension(width, books.length * 35));
		List<JPanel> panels = new ArrayList<JPanel>();
		for (int i = 1; i < books.length; i++) {
		    final DomainDetails myBook = (DomainDetails) books[i];
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(width, 30));
			p.setName("panel " +String.valueOf(i));
			JLabel l = new JLabel();
			l.setText("Web Site:  " + myBook.formattedUrl + "\n");
			JButton b = new JButton();
			b.setPreferredSize(new Dimension(30, 30));
			String imgIcon = myBook.og_image;
			if(imgIcon == null) imgIcon = myBook.displayLink;
			if(imgIcon == null) imgIcon = myBook.thumbnail;
			if(imgIcon == null) imgIcon = "src/download1.png";
			ImageIcon download = new ImageIcon(imgIcon);
			b.setIcon(download);

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

			if(i % 2 == 0){
				p.setBackground(Color.LIGHT_GRAY);
			}
			else{
				p.setBackground(Color.WHITE);
			}
			p.add(l);
			p.add(b);
			panels.add(p);
		}

		for (JPanel p : panels) {
			this.add(p);
		}

		this.updateUI();
	}

	public void removePanels(){
		this.removeAll();
		this.updateUI();
	}

}
