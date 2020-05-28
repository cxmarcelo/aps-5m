package com.aps.resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;


import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;

public class PrincipalCliente {

	private Socket socket;
	private OutputStream ou ;
	private Writer ouw; 
	private BufferedWriter bfw;


	private String iP = "127.0.0.1";

	public PrincipalCliente() {                  
	}

	public void conectar(int port, Usuario us) throws UnknownHostException, IOException {
			socket = new Socket(iP,port);
			ou = socket.getOutputStream();
			ouw = new OutputStreamWriter(ou);
			bfw = new BufferedWriter(ouw);
			if(us != null) {
				enviarMensagem(Comandos.NOME_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + us.getNome());
			}
			else {
				enviarMensagem(Comandos.NOME_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Comandos.NULL.getCodigo());
			}
	}

	public void escutar() throws IOException{

		InputStream in = socket.getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";

		while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg))

			if(bfr.ready()){
				msg = bfr.readLine();
				if(msg.equals("Sair"))
					System.out.println("Servidor caiu PrincipalCliente");
				else
					System.out.println("recebi uma msg PrincipalCliente" + msg);
			}
	}

	public void sair() throws IOException{
		enviarMensagem(Comandos.SAIR.getCodigo());
		bfw.close();
		ouw.close();
		ou.close();
		socket.close();
	}

	public void enviarMensagem(String msg) throws IOException{
		if(msg.equals(Comandos.SAIR.getCodigo())){
			bfw.write("Desconectado \r\n");
		}else{
			System.out.println(msg + "metodo enviarMensagem PrincipalCliente");
			bfw.write(msg+"\r\n");
			System.out.println(msg + "PrincipalCliente/enviarmsg");
			System.out.println("Mensagem enviada");
		}
		bfw.flush();
		System.out.println("Dei flush PrincipalCliente");
	}

	   public void enviarMensagem2(String msg) throws IOException{
           
		     if(msg.equals("Sair")){
		       bfw.write("Desconectado \r\n");
		     }else{
		       bfw.write(msg+"\r\n");
		     }
		      bfw.flush();
		 }
	   
	public Socket getSocket() {
		return socket;
	}
}
