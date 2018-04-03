package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private ServerSocket server;
	private ArrayList<ConnectionToClient> clients;

	public Server(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("Server na porta " + port);
			clients = new ArrayList<ConnectionToClient>();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread accept = new Thread() {
			public void run () {
				Socket echoSocket = null;
				while (true) {
					try {
						echoSocket = server.accept();
						clients.add(new ConnectionToClient(echoSocket));
						System.out.println("Conexão estabelecida...");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};

		accept.start();	
		
		Thread write = new Thread(){
			public void run(){
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				String serverInput = null;
				do {
					try{
						serverInput = stdIn.readLine();
						for(ConnectionToClient client : clients) {
							client.out.println(serverInput);
						}
					} catch(IOException e){ 
						e.printStackTrace(); 
					}
				} while (serverInput != null);
			}
		};
		
		write.start();
	}

	private class ConnectionToClient {
		PrintWriter out;
		BufferedReader in;
		Socket echoSocket;

		public ConnectionToClient (Socket echoSocket) throws IOException {
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
							for(ConnectionToClient client : clients) {
								client.out.println(input);
							}
						} catch(IOException e){
							e.printStackTrace(); 
						}
					}while (input != null);

				}
			};

			read.start();
		}

	}

	public static void main(String[] args) throws IOException {
		Server server = new Server(9090);		

	}
}
