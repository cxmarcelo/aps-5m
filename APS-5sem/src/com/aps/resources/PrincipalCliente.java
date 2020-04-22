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
	private int porta = 12345;

	public PrincipalCliente() {                  
	}
	/***
	 * Método usado para conectar no server socket, retorna IO Exception caso dê algum erro.
	 * @throws UnknownHostException 
	 * @throws IOException
	 */
	public void conectar() throws UnknownHostException, IOException {
			socket = new Socket(iP,porta);
			ou = socket.getOutputStream();
			ouw = new OutputStreamWriter(ou);
			bfw = new BufferedWriter(ouw);
			bfw.flush();
	}

	/**
	 * Método usado para receber mensagem do servidor
	 * @throws IOException retorna IO Exception caso dê algum erro.
	 */
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

	/***
	 * Método usado quando o usuário clica em sair
	 * @throws IOException retorna IO Exception caso dê algum erro.
	 */
	public void sair() throws IOException{
		enviarMensagem(Comandos.SAIR.getCodigo());
		bfw.close();
		ouw.close();
		ou.close();
		socket.close();
	}

	
	
	public Usuario toUsuario(String msg) {
		Usuario us = new Usuario();
		String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
		us.setLogin(dados[1]);
		us.setNome(dados[2]);
		us.setSenha(dados[3]);
		us.setTipo(dados[4]);
		return us;
	}
	
	
	/*
	  public void actionPerformed(ActionEvent e) {

	    try {
	       if(e.getActionCommand().equals(btnSend.getActionCommand()))
	          enviarMensagem(txtMsg.getText());
	       else
	          if(e.getActionCommand().equals(btnSair.getActionCommand()))
	          sair();
	       } catch (IOException e1) {
	            e1.printStackTrace();
	       }                       
	  }
	 */

	/*
	  public void keyPressed(KeyEvent e) {

	      if(e.getKeyCode() == KeyEvent.VK_ENTER){
	         try {
	            enviarMensagem(txtMsg.getText());
	         } catch (IOException e1) {
	             e1.printStackTrace();
	         }                                                          
	     }                       
	  }
	 */      
	/***
	 * Método usado para enviar mensagem para o server socket
	 * @param msg do tipo String
	 * @throws IOException retorna IO Exception caso dê algum erro.
	 */
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
	
	public static void main(String []args) throws IOException{

		PrincipalCliente app = new PrincipalCliente();
		app.conectar();
		app.enviarMensagem2(Comandos.ENVIAR_MSG.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + "Não esta funcionando essa bosta do krl");
		app.escutar();
	}


}
