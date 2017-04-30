import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class WebContent {
	private Queue<String> messageQueue;
	private Map<String, Integer> map;
	Tfidf  td;
	public WebContent() throws IOException {
		messageQueue = new LinkedList<>();
		map = new HashMap<>();
		td = new Tfidf();
	}
	protected Iterator<NodeValue>  addRequest(String url) throws IOException {
		messageQueue.add(url);
		AnalysisWeb aw = new AnalysisWeb();
		map = aw.Analysis(url);
		return calculateFreq();
	}
	private Iterator<NodeValue> calculateFreq() {
        PriorityQueue<NodeValue> pq = new PriorityQueue<>(new myComparator());
		for (String key : map.keySet()) {
			double weight = td.calculateWeight(map.get(key), key);
			pq.add(new NodeValue(weight, key));
		}
		Iterator<NodeValue> iterator = pq.iterator();
		return iterator;
		
	}
	protected void offSet() {
		map.clear();
	}
}
