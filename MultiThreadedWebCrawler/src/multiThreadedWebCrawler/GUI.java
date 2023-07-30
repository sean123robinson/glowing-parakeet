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
import javax.swing.SwingWorker;


public class GUI implements ActionListener{
	
	
//variable initialization
	public String Url1, Url2, Url3;
	
	JLabel url_list, label;
	JFrame frame;
	JPanel panel;
	JTextField url1,url2,url3;
	JButton enterUrlsButton, startButton, stopButton;
	
	ArrayList<Spider> bots = new ArrayList<>();
	
	public GUI() {
		
		frame = new JFrame();
		label = new JLabel("Urls must match http:// or https:// format.");
		
//These are TextFields to grab user input and display default values for urls to crawl
		url1 = new JTextField("https://abcnews.go.com", 50);
		url2 = new JTextField("https://www.npr.org", 50);
		url3 = new JTextField("https://www.nytimes.com",50);
		
		
//This runs the button to fetch Strings from textFields containing the urls to be crawled
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
		
		
//This creates a background thread to start spider threads without freezing GUI
		class StartSpiders extends SwingWorker<Void, Void> {
			@Override
			public Void doInBackground() {
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
				return null;
			}
		}
		StartSpiders startSpiders = new StartSpiders();
		
		
//This is the start button that will kick off the StartSpiders thread, creating a background thread to run spiders
		startButton = new JButton(new AbstractAction("Start Crawling") {
			@Override
			public void actionPerformed(ActionEvent e) {
				startSpiders.execute();
			}
		});
		
		
//This is a stop button that kills the execution of the code
		stopButton = new JButton(new AbstractAction("Stop Crawling") {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		
		
//This is where the GUI is initiated
		
		url_list = new JLabel("");
		
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 80, 100, 80));
		panel.setLayout(new GridLayout(0, 1));
		
		panel.add(label);
		
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
	
	
//main... duh
	public static void main(String[] args) {
		new GUI();
	}

	
//not used required action method for swing
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
