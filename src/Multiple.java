import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Multiple implements Runnable {
	ServerSocket server = null;
	Socket echoSocket = null;
	
	
	public Multiple (ServerSocket server, Socket echoSocket) {
		this.echoSocket = echoSocket;
		this.server = server;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (server != null) {
			try {
				System.out.println("chegou até aqui");
				echoSocket = server.accept();
				System.out.println("Foi conectado");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
