package com.aps.dominio;

import java.util.Date;

public class Mensagem {

	private Date data;
	private String nome;
	private String mensagem;


	public Mensagem() {
	}
	
	
	
	public Mensagem(Date data, String nome, String mensagem) {
		super();
		this.data = data;
		this.nome = nome;
		this.mensagem = mensagem;
	}


	public String getHora() {
		if(data != null) {
			System.out.println(data.toString());
			String[] aux = data.toString().split(" ")[3].split(":");
			return (aux[0] + ":" + aux[1]);
		}
		return "";
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
