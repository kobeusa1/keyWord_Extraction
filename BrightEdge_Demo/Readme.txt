How to Run:
Type: java -jar demo.java

How to Operate:
Type any Url for Analysis or Type “SHUTDOWN” to close the program

What is Output:
Top 10 keywords that best describe the content of the URL.

Note: Please keep “WordFreq.txt” in same folder with demo.jar.
------------------------------------------------------------------------------------
Algorithm Used For Keyword Extraction: Tf-idf
Procedures Or Methods:
1. Developed java application(GenerateTermFreq.java) 
	a. get URL List by crawling URLs inside three website(www.cnn.com, www.amazon.com,blog.rei.com) 
	b. crawl lists and get content and then map terms and frequency
	c. generate the WordFreq.txt

2. Tf-idf Application will load the WordFreq.txt and restore the terms & Freq table when constructed (that is why the first request take longer time) and calculate the score based on the terms freq in the page and table.
	Note: Formula Used for Calculating score: score = tf * idf (tf = v / d, idf = log10(d / (1 + n)), where v = term freq in page, d = total terms in map, n = term freq in table, reference from “https://en.wikipedia.org/wiki/Tf%E2%80%93idf”);

3. Handling Exceptions:
	a. Connection Exception(Invalid URL, Network error);
	b. Client/web side error (timeout, not authorized etc);
	c. File IOException(wordFreq not found or no content, etc);

4. Clean: The system will clear the term & freq table for the webpage before next input url or when exceptions (like timeout, invalid urls) was arised.

--------------------------------------------------------------------------------------
Limitations Or Possible Improvements;

1. Crawling more websites to generate the dictionary will improve the accuracy
2. Appropriate words processing will be a great improvement (for example, able to treat 2-Slice Toaster as one word instead of 2)


 