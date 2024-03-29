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
import com.aps.controler.Bancos;
import com.aps.dominio.Arquivo;
import com.aps.dominio.ArquivoDTO;
import com.aps.dominio.Mensagem;
import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;


public class Servidor extends Thread{

	private static ArrayList<BufferedWriter>clientes;           
	private static ArrayList<BufferedWriter>clientesSala1;           
	private static ArrayList<BufferedWriter>clientesSala2;           
	private static ArrayList<BufferedWriter>clientesSala3;           
	private static ArrayList<BufferedWriter>clientesSala4;           

	private static ArrayList<String>conectadosSala1 = new ArrayList<String>();     
	private static ArrayList<String>conectadosSala2 = new ArrayList<String>();     
	private static ArrayList<String>conectadosSala3 = new ArrayList<String>();     
	private static ArrayList<String>conectadosSala4 = new ArrayList<String>();     

	private static ServerSocket server; 
	private static ServerSocket chat1; 
	private static ServerSocket chat2; 
	private static ServerSocket chat3; 
	private static ServerSocket chat4; 

	private String nome;
	private Socket con;
	private InputStream in;  
	private InputStreamReader inr;  
	private BufferedReader bfr;
	private Bancos bancos = new Bancos();

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
				break;

			case 12346:
				clientesSala1.add(bfw);
				break;

			case 12347:
				clientesSala2.add(bfw);
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
				msg = bfr.readLine();
				decodificarMsg(msg, bfw);
				msg = "";
			}
		} catch (Exception e) {
			try {
				con.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


	public void sendToAllChat(BufferedWriter bwSaida, String msg) throws  IOException {
		BufferedWriter bwS;
		switch (con.getLocalPort()) {
		case 12345:
			for(BufferedWriter bw : clientes){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(msg+"\r\n");
					bw.flush(); 
				}
			}   
			break;

		case 12346:
			System.out.println("ENTREI NO CASE 12346");
			for(BufferedWriter bw : clientesSala1){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(msg+"\r\n");
					bw.flush(); 
				}
			}   
			break;

		case 12347:
			for(BufferedWriter bw : clientesSala2){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(msg+"\r\n");
					bw.flush(); 
				}
			} 
			break;

		case 12348:
			for(BufferedWriter bw : clientesSala3){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(msg+"\r\n");
					bw.flush(); 
				}
			} 
			break;

		case 12349:
			for(BufferedWriter bw : clientesSala4){
				bwS = (BufferedWriter)bw;
				if(!(bwSaida == bwS)){
					bw.write(msg+"\r\n");
					bw.flush(); 
				}
			} 
			break;

		default:
			System.out.println("CAI NO DEFAULT");
			break;
		}
	}


	public void retorno(BufferedWriter bwSaida, String msg) {
		BufferedWriter bwS;
		switch (con.getLocalPort()) {

		case 12345:
			for(BufferedWriter bw : clientes){
				bwS = (BufferedWriter)bw;
				if((bwSaida == bwS)){
					try {
						bw.write(msg+"\n\r");
						bw.flush(); 
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Erro: não foi possível enviar retorno. Servidor/retorno");
					}
				}
			}         			
			break;

		case 12346:
			for(BufferedWriter bw : clientesSala1){
				bwS = (BufferedWriter)bw;
				if((bwSaida == bwS)){
					try {
						bw.write(msg+"\n\r");
						bw.flush(); 
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Erro: não foi possível enviar retorno. Servidor/retorno");
					}
				}
			}         			
			break;


		case 12347:
			for(BufferedWriter bw : clientesSala2){
				bwS = (BufferedWriter)bw;
				if((bwSaida == bwS)){
					try {
						bw.write(msg+"\n\r");
						bw.flush(); 
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Erro: não foi possível enviar retorno. Servidor/retorno");
					}
				}
			}         			
			break;

		case 12348:
			for(BufferedWriter bw : clientesSala3){
				bwS = (BufferedWriter)bw;
				if((bwSaida == bwS)){
					try {
						bw.write(msg+"\n\r");
						bw.flush(); 
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Erro: não foi possível enviar retorno. Servidor/retorno");
					}
				}
			}         			
			break;

		case 12349:
			for(BufferedWriter bw : clientesSala4){
				bwS = (BufferedWriter)bw;
				if((bwSaida == bwS)){
					try {
						bw.write(msg+"\n\r");
						bw.flush(); 
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Erro: não foi possível enviar retorno. Servidor/retorno");
					}
				}
			}         			
			break;

		default:
			break;
		}

	}



	private void decodificarMsg(String msg, BufferedWriter bfw) {
		System.out.println("Cheguei ate decodificar");
		String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
		for (int x = 0; x < dados.length; x++) {
			executarComando(dados, x, bfw);
		}
	}

	// Metodo Principal ---------------------------------------------------------------------------------
	private void executarComando(String[] dados, int atual, BufferedWriter bfw) {
		if(dados[atual].equals(Comandos.AUTENTITCAR.getCodigo())) {
			System.out.println("AUTENTICAR");
			Usuario us = bancos.logar(dados[atual+1], dados[atual+2]);
			String msg;
			if(us != null) {
				System.out.println("Entrei pra enviar a msg de retorno");
				msg = Comandos.RETORNO_AUTENTICACAO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Decodificadores.usuarioToMsg(us); 
			}
			else {
				msg = Comandos.RETORNO_NULL.getCodigo();
			}
			retorno(bfw, msg);
		}


		else if(dados[atual].equals(Comandos.ENVIAR_MSG.getCodigo())) {
			try {
				String aux = "";
				for (String msg : dados) {
					aux += msg + Comandos.SEPARAR_DADOS.getCodigo();
				}
				Mensagem mensagemRecebida = Decodificadores.stringToMensagem(aux);
				System.out.println(bancos.criarMensgaem(mensagemRecebida, con.getLocalPort()) ? "Mensagem Criada" : "Mensagem nao foi criada caraleo");

				String msgRetorno = Comandos.ENVIAR_MSG.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() +  Decodificadores.mensagemToString(mensagemRecebida);
				sendToAllChat(bfw, msgRetorno);


			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		else if(dados[atual].contains(Comandos.SAIR.getCodigo())) {
			System.out.println("Eu deveria ter saido");
			switch (con.getLocalPort()) {

			case 12345:
				clientes.remove(bfw);
				sair();
				break;

			case 12346:
				clientesSala1.remove(bfw);
				sair();
				break;

			case 12347:
				clientesSala2.remove(bfw);
				sair();
				break;

			case 12348:
				clientesSala3.remove(bfw);
				sair();
				break;


			case 12349:
				clientesSala4.remove(bfw);
				sair();
				break;

			default:
				sair();
				break;
			}
		}


		else if(dados[atual].equals(Comandos.ENVIAR_ARQUIVO.getCodigo())) {
			System.out.println("Entrei no enviar arquivo ----------------------");
			String aux = "";
			for (String msg : dados) {
				aux += msg + Comandos.SEPARAR_DADOS.getCodigo();
			}
			System.out.println(aux);
			Arquivo arq = Decodificadores.msgToArquivo(aux);
			System.out.println("Sai desse loop");

			System.out.println(bancos.salvarArquivo(arq) ? "Salvou" : "Erro");
			System.out.println("Mas não desse");


		}else if(dados[atual].equals(Comandos.NOME_USUARIO.getCodigo())) {
			if(dados[atual +1].equals(Comandos.NULL.getCodigo())) {
				return;
			}else {
				this.nome = dados[atual+1];
				uparMensagens(con.getLocalPort(), bfw);
				adicionarUsuarioASala();
			}


		}else if(dados[atual].equals(Comandos.CRIAR_USUARIO.getCodigo())) {
			Usuario user = new Usuario();
			user.setLogin(dados[atual+1]);
			user.setSenha(dados[atual+2]);
			user.setNome(dados[atual+3]);
			user.setEmail(dados[atual+4]);
			String msg = "";
			if(bancos.criarUsuario(user)) {
				msg = Comandos.RETORNO_TRUE.getCodigo();
				retorno(bfw, msg);
			}else {
				msg = Comandos.RETORNO_FALSE.getCodigo();
				retorno(bfw, msg);
			}


		}else if(dados[atual].contains(Comandos.REQUISITAR_ARQUIVO.getCodigo())) {
			int id= -1;
			try {
				id= Integer.parseInt(dados[atual +1]);
			} catch (Exception e) {
				System.out.println("Erro para converter o id para int");
			}
			Arquivo arq = bancos.buscarArquivo(id);
			String msg = Comandos.RETORNAR_ARQUIVO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Decodificadores.arquivoToMsg(arq);
			System.out.println("**************\n" + msg);
			retorno(bfw, msg);


		}else if(dados[atual].contains(Comandos.TODOS_ARQUIVOS_NOMES.getCodigo())) {
			ArrayList<ArquivoDTO> lista = bancos.buscarArquivosChat(con.getLocalPort());
			String msg = Comandos.TODOS_ARQUIVOS_NOMES_RET.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Decodificadores.listaArquivosToMsg(lista);
			System.out.println(msg);
			retorno(bfw, msg);
		
		
		}else if(dados[atual].equals(Comandos.ATUALIZAR_DADOS_USUARIO.getCodigo())) {
			String aux = "";
			for (String msg : dados) {
				aux += msg + Comandos.SEPARAR_DADOS.getCodigo();
			}
			Usuario us = Decodificadores.toUsuario(aux);
			String msg = "";
			if(bancos.alterarDadosUsuario(us)) {
				msg = Comandos.ATUALIZAR_DADOS_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() +Comandos.RETORNO_TRUE.getCodigo();
			}else {
				msg = Comandos.ATUALIZAR_DADOS_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() +Comandos.RETORNO_FALSE.getCodigo();
			}
			System.out.println(msg);
			retorno(bfw, msg);
			
			
		}else if(dados[atual].equals(Comandos.ATUALIZAR_SENHA_USUARIO.getCodigo())) {
			String aux = "";
			for (String msg : dados) {
				aux += msg + Comandos.SEPARAR_DADOS.getCodigo();
			}
			Usuario us = Decodificadores.toUsuario(aux);
			String msg = "";
			if(bancos.alterarSenhaUsuario(us)) {
				msg = Comandos.ATUALIZAR_SENHA_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Comandos.RETORNO_TRUE.getCodigo();
			}else {
				msg = Comandos.ATUALIZAR_SENHA_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() +Comandos.RETORNO_FALSE.getCodigo();
			}
			retorno(bfw, msg);
			
			
		}else if(dados[atual].equals(Comandos.DELETAR_USUARIO.getCodigo())) {
			String login = dados[atual+1];
			String senha = dados[atual+2];
			String msg = "";
			if(bancos.deletarUsuario(login, senha)) {
				msg = Comandos.DELETAR_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() +Comandos.RETORNO_TRUE.getCodigo();
			}else {
				msg = Comandos.DELETAR_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() +Comandos.RETORNO_FALSE.getCodigo();
			}
			retorno(bfw, msg);
		}
	}


	private void uparMensagens(int chat, BufferedWriter bfw) {
		ArrayList<Mensagem> listaMsg = bancos.todasMensagens(chat, 0);
		String msg = Comandos.UPAR_MENSAGENS.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Decodificadores.listaMsgToString(listaMsg);
		retorno(bfw, msg);

	}

	private void adicionarUsuarioASala() {
		switch (con.getLocalPort()) {
		case 12346:
			conectadosSala1.add(nome);
			try {
				sendToAllChat(null, retNomeUsuarios());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 12347:
			conectadosSala2.add(nome);
			try {
				sendToAllChat(null, retNomeUsuarios());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 12348:
			conectadosSala3.add(nome);
			try {
				sendToAllChat(null, retNomeUsuarios());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 12349:
			conectadosSala4.add(nome);
			try {
				sendToAllChat(null, retNomeUsuarios());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

	private String retNomeUsuarios() {
		switch (con.getLocalPort()) {
		case 12346:
			return listaNomes(conectadosSala1);

		case 12347:
			return listaNomes(conectadosSala2);

		case 12348:
			return listaNomes(conectadosSala3);

		case 12349:
			return listaNomes(conectadosSala4);

		default:
			return listaNomes(null);
		}
	}

	private String listaNomes(ArrayList<String> listaNomes) {
		if(listaNomes != null ) {
			String msg = Comandos.TODOS_USUARIOS_SALA_RET.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Decodificadores.nomesToString(listaNomes);
			return msg;
		}
		return Comandos.TODOS_USUARIOS_SALA_RET.getCodigo();
	}


	private void sair() {
		try {
			notificarTodos();
			con.close();
			System.out.println("saiu");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro ao fechar a conexão");
		}

	}


	private void notificarTodos() {
		System.out.println("ENTREI NO NOTIFICAR TODOS");
		switch (con.getLocalPort()) {
		case 12346:
			try {
				sendToAllChat(null, retNomeUsuarios());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 12347:
			try {
				sendToAllChat(null, retNomeUsuarios());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 12348:
			try {
				sendToAllChat(null, retNomeUsuarios());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case 12349:
			conectadosSala4.add(nome);
			try {
				sendToAllChat(null, retNomeUsuarios());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default: 
			break;
		}
	}

	public static void main(String[] args) {
		try{
			int porta = 12345;
			int porta1 = 12346;
			int porta2 = 12347;
			int porta3 = 12348;
			int porta4 = 12349;
			server = new ServerSocket(porta);
			chat1 = new ServerSocket(porta1);
			chat2 = new ServerSocket(porta2);
			chat3 = new ServerSocket(porta3);
			chat4 = new ServerSocket(porta4);
			clientes = new ArrayList<BufferedWriter>();
			clientesSala1 = new ArrayList<BufferedWriter>();
			clientesSala2 = new ArrayList<BufferedWriter>();
			clientesSala3 = new ArrayList<BufferedWriter>();
			clientesSala4 = new ArrayList<BufferedWriter>();
			System.out.println("Servidor Iniciado");

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
						System.out.println("Cai no e.prirjie sei la erro");
						e.printStackTrace();
					}
				}
			}.start();

			new Thread() {
				public void run() {
					try {
						while(true) {
							System.out.println("Aguardando conexão...");
							Socket con1;
							con1 = chat1.accept();
							System.out.println("Cliente conectado...");
							Thread t = new Servidor(con1);
							t.start();   
						}
					} catch (IOException e) {
						System.out.println("Cai no e.prirjie sei la erro");
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

			new Thread() {
				public void run() {
					try {
						while(true) {
							System.out.println("Aguardando conexão...");
							Socket con3;
							con3 = chat3.accept();
							System.out.println("Cliente conectado...");
							Thread t = new Servidor(con3);
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
							Socket con4;
							con4 = chat4.accept();
							System.out.println("Cliente conectado...");
							Thread t = new Servidor(con4);
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