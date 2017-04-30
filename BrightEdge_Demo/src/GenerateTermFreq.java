import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
class GenerateTermFreq {
	static List<String> getUrl = new ArrayList<>();
	static Map<String, Integer> map = new HashMap<>();
	public static void main(String[] agrs) {
		List<String> listUrl = new ArrayList<>();
		listUrl.add("http://www.amazon.com");
		listUrl.add("http://www.cnn.com/");
		listUrl.add("http://blog.rei.com/");
		for (int i = 0; i < listUrl.size(); i++) {	
			try {
				Document doc = Jsoup.connect(listUrl.get(i)).userAgent("Mozilla").get();
		        Elements imports = doc.select("a[href]");
		        for (Element link : imports) {
		        	getUrl.add(link.attr("abs:href"));
		        }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		generateMap();
	}
	private static void generateMap() {
		AnalysisWeb aw = new AnalysisWeb();
		for (String url : getUrl) {
			try {
				Map<String, Integer> temp = aw.Analysis(url);
				mergeTable(temp);
			} catch (IOException e) {
				continue;
			}
		}
		generateTermTable();
	}
	private static void mergeTable(Map<String, Integer> temp) {
		for (String key : temp.keySet()) {
			map.putIfAbsent(key, 0);
			map.put(key, temp.get(key) + temp.get(key));
		}
	}
	private static void generateTermTable(){
		File file = new File("WordFreq.txt");
		try {
			FileWriter fileWriter = new FileWriter(file);
			for (String key : map.keySet()) {
				fileWriter.write(key + ":" + map.get(key) + ";");
				fileWriter.flush();
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
