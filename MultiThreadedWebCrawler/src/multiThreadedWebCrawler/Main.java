package multiThreadedWebCrawler;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Spider> bots = new ArrayList<>();
		bots.add(new Spider("https://abcnews.go.com", 1));
		bots.add(new Spider("https://www.npr.org", 2));
		bots.add(new Spider("https://www.nytimes.org", 3));
		
		for(Spider s : bots) {
			try {
				s.getThread().join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
