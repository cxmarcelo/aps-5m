package com.aps.controler;

import com.aps.db.UsuarioDB;
import com.aps.dominio.Usuario;

public class Login {

	private UsuarioDB userDB = new UsuarioDB();
	
	//if false msg falando q nao foi possivel logar
	//if true chama o obj do  login
	public boolean logar(String login, String senha) {
		Usuario us = userDB.buscarLogin(login).get(0);
		if (us != null) {
			if(us.getLogin() == login && us.getSenha() == senha) {
				return true;
			}
		}
		return false;
	}
	
	
}
