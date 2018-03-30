import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteChat {
	public static void main(String[] args) {
		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		int port = 9090;
		
		try {
			echoSocket = new Socket("localhost", port);
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			
			SocketChat socketChat = new SocketChat(in, out);
			Thread socketChatThread = new Thread (socketChat);
			socketChatThread.start();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			String userInput;

			System.out.println(in.readLine());
			while ((userInput = stdIn.readLine()) != null) {

				out.println(userInput);
//				System.out.println(in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}