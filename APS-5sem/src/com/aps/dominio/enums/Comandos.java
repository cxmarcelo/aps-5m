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
	UPAR_MENSAGENS("::__UPAR_MENSAGENS__::"),
	NULL("::__NULL__::"),
	TODOS_ARQUIVOS_NOMES("::__TODOS_ARQUIVOS_NOMES__::"),
	TODOS_ARQUIVOS_NOMES_RET("::__TODOS_ARQUIVOS_NOMES_RET__::"),
	RETORNAR_ARQUIVO("::__RETORNAR_ARQUIVO__::"),
	REQUISITAR_ARQUIVO("::__REQUISITAR_ARQUIVO__::");
	
	private String codigo;
	
	private Comandos(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

}
