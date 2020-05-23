package com.aps.controler;

import java.util.ArrayList;

import com.aps.db.ArquivosDB;
import com.aps.db.MensagemDB;
import com.aps.db.UsuarioDB;
import com.aps.dominio.Arquivo;
import com.aps.dominio.ArquivoDTO;
import com.aps.dominio.Mensagem;
import com.aps.dominio.Usuario;

public class Login {

	private UsuarioDB userDB = new UsuarioDB();
	private MensagemDB msgDB = new MensagemDB();
	private ArquivosDB arqDB = new ArquivosDB();

	public Usuario logar(String login, String senha) {
		if(userDB.buscarLogin(login).size() != 0) {
			Usuario us = userDB.buscarLogin(login).get(0);
			if (us != null) {
				if(us.getLogin().equals(login) && us.getSenha().equals(senha)) {
					return us;
				}
			}
		}
		return null;
	}
	
	public boolean criarUsuario(Usuario user) {
		if(user != null) {
			return userDB.criarUsario(user);
		}
		return false;
	}
	
	public boolean deletarUsuario(String login, String senha) {
		return userDB.delete(login, senha);
	}
	
	public boolean alterarDadosUsuario(Usuario us) {
		return userDB.alterarDados(us);
	}
	
	public boolean alterarSenhaUsuario(Usuario us) {
		return userDB.alterarSenha(us);
	}

	
	public boolean criarMensgaem(Mensagem msg, int porta) {
		switch (porta) {
		case 12346:
			return msgDB.criarMensagem(msg, 1);

		case 12347:
			return msgDB.criarMensagem(msg, 2);

		case 12348:
			return msgDB.criarMensagem(msg, 3);
			
		case 12349:
			return msgDB.criarMensagem(msg, 4);

		default:
			return false;
		}
	}

	
	
	// apenas testes/criar condições
	public ArrayList<Mensagem> todasMensagens(int chat,int pagina) {
		switch (chat) {
		case 12346:
			return msgDB.msgsChat(1, 0);

		case 12347:
			return msgDB.msgsChat(2, 0);

		case 12348:
			return msgDB.msgsChat(3, 0);

		case 12349:
			return msgDB.msgsChat(4, 0);

		default:
			return new ArrayList<Mensagem>();
		}
	}
	
	
	public Arquivo buscarArquivo(int id) {
		return arqDB.buscarArquivo(id);
		
	}
	
	public ArrayList<ArquivoDTO> buscarArquivosChat(int chat){
		return arqDB.buscarTodosArquivos(chat);
	}

	public boolean salvarArquivo(Arquivo arq) {
		return arqDB.salvarAquivoBD(arq);
	}
	
}
