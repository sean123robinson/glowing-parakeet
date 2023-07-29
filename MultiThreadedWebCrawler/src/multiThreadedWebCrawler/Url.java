package multiThreadedWebCrawler;

public class Url extends Main{
	private String urlString;
	
	public Url(String s) {
		this.urlString = s;
	}
	
	public String toString() {
		return this.urlString;
	}
}
