import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
public class Servidor {
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
			in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			out.println("Ola, voce se conectou ao servidor");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

		String input;
		do {
			input = in.readLine();
			System.out.println("recebido: " + input);
			out.println("O servidor devolve a mesma mensagem: " + input.toUpperCase());

		} while (input != null);
		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
		server.close();
	}
}