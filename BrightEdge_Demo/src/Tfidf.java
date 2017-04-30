import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class Tfidf {
	Map<String, Integer> map = new HashMap<>();
	public Tfidf() throws IOException {
		generateMap();
	}
	private void generateMap() throws IOException {
		InputStream in = getClass().getResourceAsStream("/WordFreq.txt"); 
		if (in == null) throw new IOException();
		//try (BufferedReader br = new BufferedReader(new FileReader(new File("WordFreq.txt")))) {
		try (BufferedReader br = new BufferedReader (new InputStreamReader(in))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] keyPairs = line.split(";");
		    	for (String keyPair : keyPairs) {
		    		String[] str = keyPair.split(":");
		    		if (str.length != 2) throw new IOException("Invalid WordFreq format");
		    		map.put(str[0], Integer.valueOf(str[1]));
		    	}
		    }
		}
		if (map.size() == 0) throw new IOException("Empty Dict Expction"); //prevent divided by 0

	}
	protected double calculateWeight(double value, String term) {
		 double tf = value / map.size(); //calculate tf;
		 int num = 0;
		 if (map.containsKey(term)) num = map.get(term);
		 double idf = Math.log10(map.size() / (1 + num));
		 return tf * idf;
	}
}

