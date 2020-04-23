package com.aps.dominio.enums;

public enum Comandos {

	SAIR("::_SAIR_::"),
	AUTENTITCAR("::__AUTENTICAR__::"),
	LOGAR("::__LOGAR__:"),
	ENVIAR_MSG("::__MSGTEXT__::"),
	ENVIAR_ARQUIVO("::__MSGARQUIVO__::"),
	RETORNO_AUTENTICACAO("::__RET_AUT__::"),
	SEPARAR_DADOS("::__SEP__::"),
	RETORNO_NULL("::__RETORNO_NULL__"),
	NOME_USUARIO("::_NOME_USER__");
	
	
	
	private String codigo;
	
	private Comandos(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
