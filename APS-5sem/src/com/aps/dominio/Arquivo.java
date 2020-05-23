package com.aps.dominio;

import java.util.Date;

public class Arquivo {

	private Integer id;
	private String nomeArquivo;
	private String nomeRemetente;
	private Date dataHora;
	private int chat;
	private byte[] arquivo;

	
	
	public Arquivo() {
	}
	
	
	
	public Arquivo(int id, String nomeArquivo, String nomeRemetente, Date dataHora, int chat, byte[] arquivo) {
		super();
		this.id = id;
		this.nomeArquivo = nomeArquivo;
		this.nomeRemetente = nomeRemetente;
		this.dataHora = dataHora;
		this.chat = chat;
		this.arquivo = arquivo;
	}
	
	
	
	//getFile();
	/*
	public File getFile() {
		java.io.File file = new java.io.File("");
		FileOutputStream in = new FileOutputStream(file) ;  
		in.write(arquivo);
		in.close();
		return file;
	}
	
*/
	// Programação orientada a gambiarra
	public String getDiaMes() {
		//Fri May 15 20:23:10 BRT 2020
		if(dataHora != null ) {
			String[] dados = dataHora.toString().split(" ");
			switch (dados[1]) {

			case "Jan":
				return dados[2] + "/01/" + dados[5];
				
			case "Feb":
				return dados[2] + "/02/" + dados[5];
				
			case "Mar":
				return dados[2] + "/03/" + dados[5];				
			
			case "Apr":
				return dados[2] + "/04/" + dados[5];				
			
			case "May":
				return dados[2] + "/05/" + dados[5];				

			case "Jun":
				return dados[2] + "/06/" + dados[5];
			
			case "Jul":
				return dados[2] + "/07/" + dados[5];
			
			case "Aug":
				return dados[2] + "/08/" + dados[5];
				
			case "Sep":
				return dados[2] + "/09/" + dados[5];
				
			case "Oct":
				return dados[2] + "/10/" + dados[5];
			
			case "Nov":
				return dados[2] + "/11/" + dados[5];
				
			case "Dec":
				return dados[2] + "/12/" + dados[5];
				
			default:
				return "";
			}
			
		}
		return "";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getNomeRemetente() {
		return nomeRemetente;
	}
	public void setNomeRemetente(String nomeRemetente) {
		this.nomeRemetente = nomeRemetente;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public int getChat() {
		return chat;
	}
	public void setChat(int chat) {
		this.chat = chat;
	}
	public byte[] getArquivo() {
		return arquivo;
	}
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	
	
}
