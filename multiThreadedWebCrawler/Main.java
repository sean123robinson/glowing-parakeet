package multiThreadedWebCrawler;

import java.util.*;



public class Main {

	public int num_threads;
	
	public static void main(String[] args) {
		
		/*
		Scanner input = new Scanner(System.in);
		System.out.print("How many sites do you want to launch the crawler on? Enter a number: ");
		int num_threads = input.nextInt();
		num_threads = Math.abs(num_threads);
		
		String[] urlList = new String[num_threads];
		
		for(int i=0; i<num_threads; i++) {
			System.out.print("Enter a URL ("+(i+1)+" of "+num_threads+"):");
			urlList[i] = input.next();
		}
		
		System.out.println(Arrays.toString(urlList));
		
		input.close();
		*/
		ArrayList<Spider> bots = new ArrayList<>();
		/*
		for(int i=0; i<num_threads; i++) {
			bots.add(new Spider(urlList[i], i+1));
		}
		*/
		bots.add(new Spider("https://abcnews.go.com", 1));
		bots.add(new Spider("https://www.npr.org", 2)); 
		bots.add(new Spider("https://www.nytimes.com", 3));
		
		for(Spider s : bots) {
			try {
				s.getThread().join();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void CreateSpider(String url) {
		
	}
}
