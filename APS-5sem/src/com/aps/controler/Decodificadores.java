package com.aps.controler;


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
	
	public static String usuarioToMsg(Usuario us) {
		String sp = Comandos.SEPARAR_DADOS.getCodigo();
		String msg = us.getLogin() + sp + us.getNome() + sp + us.getSenha() + sp + us.getTipo() + "\r\n";
		return msg;
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
