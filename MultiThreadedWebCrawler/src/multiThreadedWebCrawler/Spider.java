package multiThreadedWebCrawler;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Spider implements Runnable{
	private static final int MAX_DEPTH = 3;
	private Thread thread;
	private String first_link;
	private ArrayList<String> visitedLinks = new ArrayList<String>();
	private int ID;
	
//initiates the spider and starts the thread, accepts a string for the starting link and a num to identify the working thread
	public Spider(String link, int num) {
		System.out.print("Spider created");
		first_link = link;
		ID = num;
		
		thread = new Thread(this);
		thread.start();
	}
	
	
	@Override
	public void run() {
		crawl(1, first_link);
	}
	
	
//this is where the crawl happens. Makes sure the level does not go past the MAX_DEPTH
	//and ensures that the next link found has not already been visited
	private void crawl(int level, String url) {
		if (level <= MAX_DEPTH) {
			Document doc = request(url);
		
			if(doc != null) {
				for(Element link : doc.select("a[href]")) {
					String next_link = link.absUrl("href");
					if(visitedLinks.contains(next_link) == false) {
						crawl(level++, next_link);
					}
				}
			}
		}
	}
	 
	
//this is where the html is grabbed. Prints the ID of the bot that found it, the url, and the title of the doc found.
	//also adds the url to a visited links arrayList
	private Document request(String url) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			
			if(con.response().statusCode() == 200) {
				System.out.println("\n**Bot ID:" + ID + " Received Webpage at " + url);
				
				String title = doc.title();
				System.out.println(title);
				visitedLinks.add(url);
				
				return doc;
			}
			return null;
		}
		catch(IOException e) {
			return null;
		}
	}
	
	
	public Thread getThread() {
		return thread;
	}
}
