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
	NOME_USUARIO("::_NOME_USER__"),
	CRIAR_USUARIO("::__CRIAR_USUARIO__::"),
	RETORNO_TRUE("::__TRUE__::"),
	RETORNO_FALSE("::__FALSE__::"),
	TODOS_USUARIOS_SALA("::__TODOS_USUARIOS_SALA__::"),
	TODOS_USUARIOS_SALA_RET("::__TODOS_USUARIOS_SALA_RET__::"),
	LISTA_DE_MENSAGENS("::__LISTA_DE_MENSAGENS__::"),
	UPAR_MENSAGENS("::__UPAR_MENSAGENS__::");
	
	private String codigo;
	
	private Comandos(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
