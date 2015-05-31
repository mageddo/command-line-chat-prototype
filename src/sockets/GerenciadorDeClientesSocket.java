package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeClientesSocket extends Thread {
	private static final List<Socket> clientes = new ArrayList<Socket>();
	private Socket cliente;

	
	public GerenciadorDeClientesSocket(Socket cliente) {
		clientes.add(cliente);
		this.cliente = cliente;
		start();
	}


	/**
	 * Recebe e envia mensagens
	 */
	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter pw = new PrintWriter(cliente.getOutputStream(), true);
			pw.println("seja bem vindo");
			String mensagem;
			while((mensagem =  bf.readLine()) != null){
				// mandando a mensagem de volta para o cliente
				if(mensagem.equals("close")){
					pw.println("você fechou a conexao\n");
					cliente.close();
				}
				else if(mensagem.equals("close-server")){
					pw.println("você a conexao do servidor\n");
					for(Socket cliente: clientes){
						cliente.close();
					}
					SocketServer.server.close();
				}else{
					pw.println("você disse: " + mensagem);
				}
			}
		} catch (IOException e) {
			System.err.println("o cliente " + cliente + " teve sua conexao comprometida " + e.getMessage());
		}
	}
}
