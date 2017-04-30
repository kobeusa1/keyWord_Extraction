import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class AnalysisWeb {
	private final String USER_AGENT = "Mozilla";
	Map<String, Integer> map = new HashMap<>();
	protected Map<String, Integer> Analysis(String url) throws IOException {
		Connection connection;
		try {
			connection = Jsoup.connect(url).userAgent(USER_AGENT);	
		} catch (Exception e) {
			throw new IOException("Unable to connect requested URL");
		}
		Document doc = connection.get();
		int statuscode = connection.response().statusCode();
		if(statuscode >= 400 && statuscode < 500) throw new IOException("Client Side Error, Page Not Found Or Time Out");
		else if (statuscode >= 500) throw new IOException("Server Error");
		String text = doc.body().text();
		String title = doc.title();
		addMap(title, 10);
        BufferedReader bd = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))));
		String temp = "";
		System.out.println("Analysis on: " + url);
		while((temp= bd.readLine()) !=null){
			addMap(temp, 1);
		}
		
		bd.close();
		return map;
	}
	private void addMap(String temp, int weight) {
		String[] words = temp.split("[^a-zA-Z0-9-]+");
		for (String word : words) {
			word = word.toLowerCase();
			if (word.length() <= 1) continue;
			map.putIfAbsent(word, weight);
			map.put(word, map.get(word) + weight);
		}			
	}
}
