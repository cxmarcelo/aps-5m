package com.aps.controler;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;

public class Decodificadores {
	
	public static Usuario toUsuario(String msg) {
		if(contemComando(msg)) {
		Usuario us = new Usuario();
		String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
		us.setLogin(dados[1]);
		us.setNome(dados[2]);
		us.setSenha(dados[3]);
		us.setTipo(dados[4]);
		return us;
		}else return null;
	}
	
	public static ArrayList<Usuario> todosUsuarios(String msg) {
		if(contemComando(msg)) {
			String[] aux = msg.split((Comandos.SEPARAR_DADOS.getCodigo()));
			String[] dados = new String[aux.length - 1];
			for (int i = 0; i < dados.length; i++) {
				dados[i] = aux[i+1]; 
			}
			ArrayList<Usuario> lista = new ArrayList<Usuario>();
			
			int qtdUsers = (int) (dados.length -1) / 4;
			for (int x = 0; x < qtdUsers; x++) {
				Usuario us = new Usuario();
				int y = 4 * x;
				us.setLogin(dados[y]);
				us.setNome(dados[y+1]);
				us.setSenha(dados[y+2]);
				us.setTipo(dados[y+3]);
				lista.add(us);
			}
			return lista;
		}
		else return null;
	}
	
	public static ArrayList<String> nomesUsuarios(String msg) {
		if(contemComando(msg)) {
			ArrayList<String> listaNomes = new ArrayList<String>();
			String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
			for (int x = 1; x < dados.length; x++) {
				listaNomes.add(dados[x]);
			}
			return listaNomes;
		}
		return null;
	}
	
	
	public static String usuarioToMsg(Usuario us) {
		String sp = Comandos.SEPARAR_DADOS.getCodigo();
		String msg = us.getLogin() + sp + us.getNome() + sp + us.getSenha() + sp + us.getTipo() + "\r\n";
		return msg;
	}
	
	public static String msgToString(String msg) {
		if(contemComando(msg)) {
			return msg.split(Comandos.SEPARAR_DADOS.getCodigo())[1];
		}
		else return null;
	}
	

	public static String nomesToString(ArrayList<String> listaNomes) {
		String msg = "";
		for (int x = 0; x < listaNomes.size(); x++) {
			msg = msg + listaNomes.get(x) + Comandos.SEPARAR_DADOS.getCodigo();
		}
		return msg;
	}
	
	// NÃO IMPLEMENTADO
	private static void fileToString(File f) {
		String msg = "";
		InputStream is;
		try {
			is = new FileInputStream( f );
			byte[] bytes = new byte[(int)f.length() ];
			int offset = 0;
			int numRead = 0;
			try {
				while (offset < bytes.length
						&& (numRead = is.read(bytes, offset, bytes.length-offset)) >= 0) {
					offset += numRead;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	        /*
	        ps.setString( 1, f.getName() );
	        ps.setBytes( 2, bytes );
	        ps.execute();
	        ps.close();
	        c.close();*/
	        
	}
	
	private static boolean contemComando(String msg) {
		for (Comandos comando : Comandos.values()) {
			if(msg.contains(comando.getCodigo())) {
				return true;
			}
		}
		return false;
	
	}
	
	public static String msgCriarUsuario(Usuario us) {
		String msg = "";
		//Usuario us = new Usuario(login, senha, nome, tipo);
		String sp = Comandos.SEPARAR_DADOS.getCodigo();
		msg = Comandos.CRIAR_USUARIO.getCodigo() + sp + us.getLogin() + sp + us.getSenha() + sp + us.getNome()  + sp + us.getTipo();
		return msg;
	}
	
	// Terminar de implementar essa funçao se considerar q pode mandar mais de um comando
	/*
	public static void decodificar(String msg) {
		String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
		ArrayList<String> listaComandos = new ArrayList<String>();
		for (String isComand : dados) {
			for (Comandos comando : Comandos.values()) {
				if(isComand.equals(comando.getCodigo())) {
				}
			}
		}
	}
	*/
	
}
