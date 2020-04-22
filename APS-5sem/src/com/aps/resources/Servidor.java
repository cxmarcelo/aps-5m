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

import com.aps.controler.Login;
import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;


public class Servidor extends Thread{

	private static ArrayList<BufferedWriter>clientes;           
	private static ArrayList<BufferedWriter>clientesSala2;           
	private static ServerSocket server; 
	private static ServerSocket chat2; 
	private String nome;
	private Socket con;
	//private Socket sala1;
	//private Socket sala2;
	//private Socket sala3;
	//private Socket sala4;
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
			System.out.println(con.getLocalPort() + "porta -------------------------------");
			if(con.getPort() == 12345) {
				clientes.add(bfw);
				System.out.println("Clientes: " + clientes.size());
			}else {
				clientesSala2.add(bfw);
				System.out.println("Sala 2 : " + clientesSala2.size());
			}


			nome = msg = bfr.readLine();

			while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg) && msg != null)
			{           
				separarMsg(msg, bfw);
				System.out.println(msg + "  Teste");
				System.out.println("Entrei no escutar do servidor");
				msg = bfr.readLine();
				System.out.println(msg);                                              

				sendToAll(bfw, msg);
				sendToAll2(bfw, msg);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void sendToAll(BufferedWriter bwSaida, String msg) throws  IOException {
		BufferedWriter bwS;
		for(BufferedWriter bw : clientes){
			bwS = (BufferedWriter)bw;
			if(!(bwSaida == bwS)){
				bw.write(nome + " -> " + msg+"\r\n");
				bw.flush(); 
			}
		}          
	}

	public void sendToAll2(BufferedWriter bwSaida, String msg) throws  IOException {
		BufferedWriter bwS;
		for(BufferedWriter bw : clientesSala2){
			bwS = (BufferedWriter)bw;
			if(!(bwSaida == bwS)){
				bw.write(nome + " -> " + msg+"\r\n");
				bw.flush(); 
			}
		}          
	}

	public void retorno(BufferedWriter bwSaida, String msg) throws  IOException {
		BufferedWriter bwS;
		for(BufferedWriter bw : clientes){
			bwS = (BufferedWriter)bw;
			if((bwSaida == bwS)){
				bw.write(msg+"\r\n");
				bw.flush(); 
			}
		}          
	}



	private void separarMsg(String msg, BufferedWriter bfw) {
		System.out.println("ENTREI NO METODO QUE QUERIA SERVIDOR SEPARAR MSG");
		System.out.println(msg + "-");
		String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
		for (int x = 0; x < dados.length; x++) {
			executarComando(dados, x, bfw);
		}


	}

	private void executarComando(String[] dados, int atual, BufferedWriter bfw) {
		//System.out.println("dados:");
		//for (String teste : dados) {
		//System.out.println(teste);
		//}
		if(dados[atual].equals(Comandos.AUTENTITCAR.getCodigo())) {
			Usuario us = checarLogin.logar(dados[atual+1], dados[atual+2]);
			String msg;
			String sp = Comandos.SEPARAR_DADOS.getCodigo();
			if(us != null) {
				msg = Comandos.RETORNO_AUTENTICACAO.getCodigo() + sp + us.getLogin() + sp + us.getNome() + sp + us.getSenha() + sp + us.getTipo() + "\r\n"; 
			}else {
				msg = Comandos.RETORNO_NULL.getCodigo();
			}
			System.out.println("---------------------");
			System.out.println(msg);
			System.out.println("---------------------");
			try {
				retorno(bfw, msg);

			} catch (Exception e) {
				System.out.println("Erro para retornar a mensagem. Servidor/executarComando/retorno");
			}
		}
		else if(dados[atual].equals(Comandos.ENVIAR_MSG.getCodigo())) {
			try {
				sendToAll(bfw, dados[atual+1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(dados[atual].equals(Comandos.SAIR.getCodigo())) {

		}
		else if(dados[atual].equals(Comandos.ENVIAR_ARQUIVO.getCodigo())) {

		}
		else {
			try {
				sendToAll(bfw, dados.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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