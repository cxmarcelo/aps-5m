package com.aps.dominio.enums;

public enum Comandos {

	SAIR("::_SAIR_::"),
	AUTENTITCAR("::__AUTENTICAR__::"),
	LOGAR("::__LOGAR__:"),
	ENVIAR_MSG("::__MSGTEXT__::"),
	ENVIAR_ARQUIVO("::__MSGARQUIVO__::");
	
	private String codigo;
	
	private Comandos(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
