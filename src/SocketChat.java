import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SocketChat implements Runnable {
	BufferedReader in;
	PrintWriter out;

	public SocketChat (BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out  = out;
	}

	public void run() {
		String input = null;

		do {
			try {
				input = in.readLine();
				System.out.println(input);
//				out.println(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} while (input != null);

	}


}
