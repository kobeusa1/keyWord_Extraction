import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Launcher {
	private static WebContent wc;
	private static int MaxRelevent = 10;
	public static void main(String[] args) {
		Launch();
	}
	private static void Launch(){
		Scanner sc;
		try {
			wc = new WebContent();
		} catch (IOException e1) {
			System.out.println("Word Count File Not found, System Quit");
			return;
		}
		while (true) {
			wc.offSet(); 
			sc = new Scanner(System.in);
			String inputUrl = sc.next();
			if (inputUrl.compareTo("SHUTDOWN") == 0) break;
				try {
					Iterator<NodeValue> iterator = wc.addRequest(inputUrl);
					for (int i = 0; i < MaxRelevent && iterator.hasNext(); i++) {
						System.out.println(iterator.next().name);
					}
				} catch(IOException e) {
					System.out.println(e.getMessage());
				} 
		}
		System.out.println("System Shut Down......");
		sc.close();
	}
}




