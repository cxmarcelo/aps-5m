package com.aps.controler;

import com.aps.db.UsuarioDB;
import com.aps.dominio.Usuario;

public class Login {

	private UsuarioDB userDB = new UsuarioDB();
	
	public Usuario logar(String login, String senha) {
		Usuario us = userDB.buscarLogin(login).get(0);
		if (us != null) {
			if(us.getLogin() == login && us.getSenha() == senha) {
				return us;
			}
		}
		return null;
	}
	
	
}
