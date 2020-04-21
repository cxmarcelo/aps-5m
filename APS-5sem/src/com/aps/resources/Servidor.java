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

import com.aps.dominio.enums.Comandos;

public class Servidor extends Thread{

	private static ArrayList<BufferedWriter>clientes;           
	private static ServerSocket server; 
	private String nome;
	private Socket con;
	private InputStream in;  
	private InputStreamReader inr;  
	private BufferedReader bfr;

	
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
		try {
		    String msg;
		    OutputStream ou =  this.con.getOutputStream();
		    Writer ouw = new OutputStreamWriter(ou);
		    BufferedWriter bfw = new BufferedWriter(ouw); 
		    clientes.add(bfw);
		    nome = msg = bfr.readLine();
		               
		    while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg) && msg != null)
		      {           
		       msg = bfr.readLine();
		       sendToAll(bfw, msg);
		       System.out.println(msg);                                              
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
	
	public void iniciarServidor(int porta) {
		try{
		    server = new ServerSocket(porta);
		    clientes = new ArrayList<BufferedWriter>();
		    System.out.println("Servidor aberto na porta: " + porta);
		     while(true){
		       System.out.println("Aguardando conexão...");
		       Socket con = server.accept();
		       System.out.println("Cliente conectado...");
		       Thread t = new Servidor(con);
		        t.start();   
		    }
		  }catch (Exception e) {
		    e.printStackTrace();
		  } 
	}
	
	public static void main(String[] args) {
		try{
			int porta = 12445;
		    server = new ServerSocket(porta);
		    clientes = new ArrayList<BufferedWriter>();
		    System.out.println("Servidor aberto na porta: " + porta);
		     while(true){
		       System.out.println("Aguardando conexão...");
		       Socket con = server.accept();
		       System.out.println("Cliente conectado...");
		       Thread t = new Servidor(con);
		        t.start();   
		    }
		  }catch (Exception e) {
		    e.printStackTrace();
		  } 
		
	
	}

}
