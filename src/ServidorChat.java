import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServidorChat {
	public static void main(String[] args) throws IOException {
		Socket echoSocket = null;
		ServerSocket server = null;
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			server = new ServerSocket(9090);
			System.out.println("Aguardando nova conexao");
			echoSocket = server.accept();
			System.out.println("Conexao com cliente iniciada");
			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			out.println("Ola, voce se conectou ao servidor");
			
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

		String input;
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			input = stdIn.readLine();
//		System.out.println(input);
			out.println(input);
			
		} while (input != null);
		
		out.close();
		in.close();
		echoSocket.close();
		server.close();
	}
}