import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketChat implements Runnable {
	BufferedReader in;
	PrintWriter out;
	Socket echoSocket;
	

	public SocketChat (BufferedReader in, PrintWriter out, Socket echoSocket) {
		this.in = in;
		this.out  = out;
		this.echoSocket = echoSocket;
	}

	public void run() {
		String input = null;

		do {
			try {
				input = in.readLine();
				System.out.println(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} while (input != null);

	}


}
