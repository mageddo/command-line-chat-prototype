package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static ServerSocket server = null;

	public static void main(String[] args) {
		try {
			server = new ServerSocket(4444);
			Socket s;
			while((s = server.accept()) != null){
				new GerenciadorDeClientesSocket(s);
			}
		} catch (IOException e) {
			System.err.println("a porta estah indisponivel");
			try {
				if(server != null)
					server.close();
			} catch (IOException e1) {
				System.err.println("nao conseguiu fechar a conexao ");
				e1.printStackTrace();
			}
			
			System.exit(0);
		}
	}
}
