package com.aps.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.aps.controler.Decodificadores;
import com.aps.controler.Login;
import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;


public class Servidor extends Thread{

	private static ArrayList<BufferedWriter>clientes;           
	private static ArrayList<BufferedWriter>clientesSala1;           
	private static ArrayList<BufferedWriter>clientesSala2;           
	private static ArrayList<BufferedWriter>clientesSala3;           
	private static ArrayList<BufferedWriter>clientesSala4;           
	private static ServerSocket server; 
	private static ServerSocket chat2; 
	private String nome;
	private Socket con;
	private InputStream in;  
	private InputStreamReader inr;  
	private BufferedReader bfr;
	private Login checarLogin = new Login();

	public Servidor(Socket con) {
		this.con = con;
		try {
			in = con.getInputStream();
			inr = new InputStreamReader(in);
			bfr = new BufferedReader(inr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		iniciarCon(this.con);
	}

	private void iniciarCon(Socket con) {
		try {
			String msg;
			OutputStream ou =  con.getOutputStream();
			Writer ouw = new OutputStreamWriter(ou);
			BufferedWriter bfw = new BufferedWriter(ouw); 
			
			switch (con.getLocalPort()) {
			case 12345:
				clientes.add(bfw);
				System.out.println("Entrei no clientes");
				break;

			case 12346:
				clientesSala1.add(bfw);
				break;

			case 12347:
				clientesSala2.add(bfw);
				System.out.println("entrei no clientesSala2");
				break;

			case 12348:
				clientesSala3.add(bfw);
				break;

			case 12349:
				clientesSala4.add(bfw);
				break;
				
			default:
				clientes.add(bfw);
				System.out.println("CAI NO DEFAULT");
				break;
			}
			msg = "";
			while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg) && msg != null)
			{           
				System.out.println("recebimsg");
				System.out.println(msg);
				msg = bfr.readLine();
				decodificarMsg(msg, bfw);
				System.out.println(msg + "  Teste---------------");
				//System.out.println("Entrei no escutar do servidor");
				//System.out.println(msg);                                              

				//sendToAll(bfw, msg);
				//sendToAllChat(bfw, msg);
				//sendToAll2(bfw, msg);
				msg = "";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void sendToAllChat(BufferedWriter bwSaida, String msg) throws  IOException {
		BufferedWriter bwS;
		
		switch (con.getLocalPort()) {
		case 12345:
			for(BufferedWriter bw : clientes){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(nome + " -> " + msg+"\r\n");
					bw.flush(); 
				}
			}   
			break;

		case 12346:
			for(BufferedWriter bw : clientesSala1){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(nome + " -> " + msg+"\r\n");
					bw.flush(); 
				}
			}   
			break;

		case 12347:
			for(BufferedWriter bw : clientesSala2){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(nome + " -> " + msg+"\r\n");
					bw.flush(); 
				}
			} 
			break;

		case 12348:
			for(BufferedWriter bw : clientesSala3){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(nome + " -> " + msg+"\r\n");
					bw.flush(); 
				}
			} 
			break;

		case 12349:
			for(BufferedWriter bw : clientesSala4){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(nome + " -> " + msg+"\r\n");
					bw.flush(); 
				}
			} 
			break;
			
		default:
			System.out.println("CAI NO DEFAULT");
			break;
		}
	}
	

	public void retorno(BufferedWriter bwSaida, String msg) throws  IOException {
		BufferedWriter bwS;
		for(BufferedWriter bw : clientes){
			bwS = (BufferedWriter)bw;
			if((bwSaida == bwS)){
				bw.write(msg+"\r\n");
				bw.flush(); 
				return;
			}
		}          
	}



	private void decodificarMsg(String msg, BufferedWriter bfw) {
		System.out.println("Cheguei ate decodificar");
		String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
		for (int x = 0; x < dados.length; x++) {
			//ideia if executou?qual?
			executarComando(dados, x, bfw);
		}
	}

	
	private void executarComando(String[] dados, int atual, BufferedWriter bfw) {
		if(dados[atual].equals(Comandos.AUTENTITCAR.getCodigo())) {
			Usuario us = checarLogin.logar(dados[atual+1], dados[atual+2]);
			String msg;
			if(us != null) {
				System.out.println("Entrei pra enviar a msg de retorno");
				msg = Comandos.RETORNO_AUTENTICACAO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Decodificadores.usuarioToMsg(us); 
			}else {
				msg = Comandos.RETORNO_NULL.getCodigo();
			}
			try {
				retorno(bfw, msg);

			} catch (Exception e) {
				System.out.println("Erro para retornar a mensagem. Servidor/executarComando/retorno");
			}
		}
		else if(dados[atual].equals(Comandos.ENVIAR_MSG.getCodigo())) {
			try {
				sendToAllChat(bfw, dados[atual+1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(dados[atual].equals(Comandos.SAIR.getCodigo())) {

		}
		else if(dados[atual].equals(Comandos.ENVIAR_ARQUIVO.getCodigo())) {

		}else if(dados[atual].equals(Comandos.NOME_USUARIO.getCodigo())) {
			this.nome = dados[atual+1];
		}
		else {
			return;
		}
	}



	public static void main(String[] args) {
		try{
			int porta = 12345;
			int porta2 = 12347;
			server = new ServerSocket(porta);
			chat2 = new ServerSocket(porta2);
			clientes = new ArrayList<BufferedWriter>();
			clientesSala2 = new ArrayList<BufferedWriter>();
			System.out.println("Servidor aberto na porta: " + porta);
			new Thread() {
				public void run() {
					try {
						while(true) {
							System.out.println("Aguardando conexão...");
							Socket con;
							con = server.accept();
							System.out.println("Cliente conectado...");
							Thread t = new Servidor(con);
							t.start();   
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();

			new Thread() {
				public void run() {
					try {
						while(true) {
							System.out.println("Aguardando conexão...");
							Socket con2;
							con2 = chat2.accept();
							System.out.println("Cliente conectado...");
							Thread t = new Servidor(con2);
							t.start();   
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}catch (Exception e) {
			e.printStackTrace();
		} 
	}
}