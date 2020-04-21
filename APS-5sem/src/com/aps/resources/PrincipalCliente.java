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

import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.aps.dominio.enums.Comandos;

public class PrincipalCliente {

	private JTextArea texto;
	private JTextField txtMsg;
	private Socket socket;
	private OutputStream ou ;
	private Writer ouw; 
	private BufferedWriter bfw;


	private String iP = "127.0.0.1";
	private int porta = 8483;

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
			//bfw.write("NOME DO USUARIO" +"\r\n");
			//bfw.flush();
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
					texto.append("Servidor caiu! \r\n");
				else
					texto.append(msg+"\r\n");         
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

	//gambiarra fodaci
	public String validarMSG(String msg) {
		for (Comandos comand : Comandos.values()) {
			if(msg.equals(comand.getCodigo())) {
				msg = msg + " ";
			}
		}
		return msg;
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
			texto.append("Desconectado \r\n");
		}else{
			bfw.write(msg+"\r\n");
			texto.append("NOME DO USUARIO" + " diz -> " + "MENSAGEM" +"\r\n");
		}
		bfw.flush();
		txtMsg.setText("");        
	}

	public Socket getSocket() {
		return socket;
	}
	
	public static void main(String []args) throws IOException{

		Cliente app = new Cliente();
		app.conectar();
		app.escutar();
	}


}
