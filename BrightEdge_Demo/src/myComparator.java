import java.util.Comparator;

class myComparator implements Comparator <NodeValue>{
	public int compare(NodeValue a, NodeValue b) {
		if (b.val > a.val) return 1;
		return -1;
	}
}
