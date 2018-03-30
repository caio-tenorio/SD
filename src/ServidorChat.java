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
		} catch (IOException e){ 
			e.printStackTrace();			
		}
		while (true) {
			try {

				System.out.println("Aguardando nova conexao");
				echoSocket = server.accept();
				
				out = new PrintWriter(echoSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				
				SocketChat socketChatServer = new SocketChat(in, out, echoSocket);
				Thread socketChatServerThread = new Thread (socketChatServer);
				socketChatServerThread.start();
				
				SocketChatWriting socketChatWriting = new SocketChatWriting(in, out, echoSocket, "server");
				Thread socketChatThreadWriting = new Thread (socketChatWriting);
				socketChatThreadWriting.start();

				
				
				
				//				System.out.println("Conexao com cliente iniciada");
				//				out = new PrintWriter(echoSocket.getOutputStream(), true);
				//				in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				//				out.println("Ola, voce se conectou ao servidor");
				//			Multiple multiple = new Multiple(server, echoSocket);
				//			Thread multipleThread = new Thread (multiple);
				//			multipleThread.start();

				//				SocketChatClient socketChat = new SocketChatClient(in, out);
				//				Thread socketChatThread = new Thread (socketChat);
				//				socketChatThread.start();
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		//		String input;
		//		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		//
		//		do {
		//			input = stdIn.readLine();
		//			//		System.out.println(input);
		//			out.println(input);
		//
		//		} while (input != null);
		//
		//		out.close();
		//		in.close();
		//		echoSocket.close();
		//		server.close();
	}
}