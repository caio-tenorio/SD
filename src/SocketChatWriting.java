import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketChatWriting implements Runnable {
	BufferedReader in;
	PrintWriter out;
	Socket echoSocket;
	String serverOrClient;
	

	public SocketChatWriting (BufferedReader in, PrintWriter out, Socket echoSocket,String serverOrClient ) {
		this.in = in;
		this.out  = out;
		this.echoSocket = echoSocket;
		this.serverOrClient = serverOrClient;
	}

	public void run() {

		try {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			String userInput;
			if (serverOrClient == "server") {
				out.println("Você está conectado");
			}
			while ((userInput = stdIn.readLine()) != null) {

				out.println(userInput);
//				System.out.println(in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
