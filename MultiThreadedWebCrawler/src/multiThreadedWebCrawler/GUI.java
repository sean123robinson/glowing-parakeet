package multiThreadedWebCrawler;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUI implements ActionListener{
	
	public String Url1, Url2, Url3;
	
	JLabel url_list;
	JFrame frame;
	JPanel panel;
	JTextField url1,url2,url3;
	JButton enterUrlsButton, startButton, stopButton;
	ArrayList<Spider> bots = new ArrayList<>();
	
	public GUI() {
		frame = new JFrame();
		
		url1 = new JTextField("https://abcnews.go.com", 50);
		url2 = new JTextField("https://www.npr.org", 50);
		url3 = new JTextField("https://www.nytimes.com",50);
		
		enterUrlsButton = new JButton(new AbstractAction("Enter Urls") {
			@Override
			public void actionPerformed(ActionEvent e) {
				Url1 = url1.getText();
				Url2 = url2.getText();
				Url3 = url3.getText();
				String UrlList = "Urls: "+Url1.toString()+" , "+Url2.toString()+" , "+Url3.toString();
				url_list.setText(UrlList);
				
				panel.remove(enterUrlsButton);
				panel.add(startButton);
				panel.add(stopButton);
				
				panel.repaint();
			}
		});
		
		startButton = new JButton(new AbstractAction("Start Crawling") {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				bots.add(new Spider(Url1, 1));
				bots.add(new Spider(Url2, 2)); 
				bots.add(new Spider(Url3, 3));
				
				for(Spider s : bots) {
					try {
						s.getThread().join();
					}
					catch(InterruptedException er) {
						er.printStackTrace();
					}
				}
			}
		});
		
		stopButton = new JButton(new AbstractAction("Stop Crawling") {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bots.get(0).wait();
					bots.get(1).wait();
					bots.get(2).wait();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		url_list = new JLabel("This is where spider logs will go");
		
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 80, 100, 80));
		panel.setLayout(new GridLayout(0, 1));
		
		panel.add(url1);
		panel.add(url2);
		panel.add(url3);
		
		panel.add(enterUrlsButton);
		
		panel.add(url_list);
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Multi-Threaded Web Crawler");
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
