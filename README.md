# glowing-parakeet
OS Project

Our project is a multi threaded web crawler that runs from a gui based one url's entered by the user from the GUI. The crawlers have a max depth of 3, so the threads don't dig too far into the site. For the sake of practicality in the GUI we limited the url inputs to 3 so there will only be 3 crawler threads created in this code, though the multi threaded crawlers themselves could easily handle more.

The inputs are a few strings given by the user in the GUI and a couple GUI button presses. The urls entered in the textboxes just need to include http:// or https:// as well as the www. or whatever other subdomain the site uses in its url.

The output of the code is given by the Spider class and is displayed in the terminal. Each Spider will print it's ID as well as the URL where each new Webpage is found. On the second line it includes the title of the html document found.

To run our code:

1. Move the folder "MultiThreadedWebCrawler" from the zip file into the eclipse-workspace or whatever similar directory for your ide. 
2. Open the IDE and run the code from the GUI class.
3. Enter new urls in the textfields or leave the defaults alone.
4. Click the Enter Urls button
5. Click the Start Crawling button.
6. Output logs will appear in the terminal
7. Click the Stop Crawling button when done and execution will stop and the GUI will close.
