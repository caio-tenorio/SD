package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	private ConnectionToServer server;
	private Socket echoSocket;

	public Client(String IPAddress, int port) throws IOException{
		echoSocket = new Socket (IPAddress, port);
		server = new ConnectionToServer(echoSocket);

	}

	private class ConnectionToServer {
		PrintWriter out;
		BufferedReader in;
		Socket echoSocket;

		ConnectionToServer(Socket echoSocket) throws IOException {
			this.echoSocket = echoSocket;
			in = new BufferedReader(new InputStreamReader(this.echoSocket.getInputStream()));
			out = new PrintWriter(this.echoSocket.getOutputStream(), true);

			Thread read = new Thread(){
				public void run(){
					String input = null;
					do {
						try{
							input = in.readLine();
							System.out.println(input);
						} catch(IOException e){ 
							e.printStackTrace(); 
						}
					} while (input != null);
				}
			};

			Thread write = new Thread(){
				public void run(){
					BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
					String clientInput = null;
					do {
						try{
							clientInput = stdIn.readLine();
							out.println(clientInput);
						} catch(IOException e){
							e.printStackTrace();
						}
					} while (clientInput != null);
				}
			};

			//			read.setDaemon(true);
			read.start();
			//			write.setDaemon(true);
			write.start();
		}
	}

	public static void main(String[] args) throws IOException {
		Client client = new Client ("127.0.0.1", 9090);

	}
}
