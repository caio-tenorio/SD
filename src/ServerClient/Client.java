package ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private ConnectionToServer server;
	private Socket echoSocket;
	private String userName;

	public Client(String IPAddress, int port, String userName) throws IOException{
		echoSocket = new Socket (IPAddress, port);
		this.userName = userName;
		server = new ConnectionToServer(echoSocket, userName);
	}

	private class ConnectionToServer {
		PrintWriter out;
		BufferedReader in;
		Socket echoSocket;
		String userName;

		ConnectionToServer(Socket echoSocket, String userName) throws IOException {
			this.echoSocket = echoSocket;
			this.userName = userName;
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
							out.println(userName + ": " + clientInput);
						} catch(IOException e){
							e.printStackTrace();
						}
					} while (clientInput != null);
				}
			};

			read.start();
			write.start();
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Digite o nome do seu usuário:");
		Scanner userNameInput = new Scanner(System.in);
		String userName = userNameInput.nextLine();
		Client client = new Client ("127.0.0.1", 9090, userName);
		System.out.println("Você está conectado como " + userName + "!");
	}
}
