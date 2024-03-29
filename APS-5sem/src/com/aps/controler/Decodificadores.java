package com.aps.controler;


import java.util.ArrayList;
import java.util.Date;

import com.aps.dominio.Arquivo;
import com.aps.dominio.ArquivoDTO;
import com.aps.dominio.Mensagem;
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
		us.setEmail(dados[4]);
		return us;
		}else return null;
	}
	
	private static String sepB = "-.-";
	
	public static ArrayList<Usuario> todosUsuarios(String msg) {
		if(contemComando(msg)) {
			String[] aux = msg.split((Comandos.SEPARAR_DADOS.getCodigo()));
			String[] dados = new String[aux.length - 1];
			for (int i = 0; i < dados.length; i++) {
				dados[i] = aux[i+1]; 
			}
			ArrayList<Usuario> lista = new ArrayList<Usuario>();
			
			int qtdUsers = (int) (dados.length -1) / 4;
			for (int x = 0; x < qtdUsers; x++) {
				Usuario us = new Usuario();
				int y = 4 * x;
				us.setLogin(dados[y]);
				us.setNome(dados[y+1]);
				us.setSenha(dados[y+2]);
				us.setEmail(dados[y+3]);
				lista.add(us);
			}
			return lista;
		}
		else return null;
	}
	
	public static ArrayList<String> nomesUsuarios(String msg) {
		if(contemComando(msg)) {
			ArrayList<String> listaNomes = new ArrayList<String>();
			String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
			if(dados.length > 1) {
				for (int x = 1; x < dados.length; x++) {
					listaNomes.add(dados[x]);
				}
				return listaNomes;
			}
		}
		return null;
	}
	
	
	public static String usuarioToMsg(Usuario us) {
		String sp = Comandos.SEPARAR_DADOS.getCodigo();
		String msg = us.getLogin() + sp + us.getNome() + sp + us.getSenha() + sp + us.getEmail() + "\r\n";
		return msg;
	}
	
	public static String mensagemToString(Mensagem msg) {
		String ret = "";
		String sep = Comandos.SEPARAR_DADOS.getCodigo();
		ret =  msg.getData().getTime() + sep + msg.getNome() + sep +  msg.getMensagem();
		return ret;
	}
	

	public static Mensagem stringToMensagem(String msg) {
		if(contemComando(msg)) {
			String dados[] = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
			if(dados.length > 3) {
				Mensagem mensagem = new Mensagem();
				try {
					long aux = Long.parseLong(dados[1]);
					mensagem.setData(new Date(aux));
					
				} catch (Exception e) {
					System.out.println("Erro ao converter a data");
				}
				mensagem.setNome(dados[2]);
				mensagem.setMensagem(dados[3]);
				return mensagem;
			}
		}
		return null;
	}
	
	
	public static String msgToString(String msg) {
		if(contemComando(msg)) {
			String mensg[] = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
			if(mensg.length < 2) {
				return null;
			}else {
				return mensg[1];
			}
		}
		else return null;
	}
	

	public static String nomesToString(ArrayList<String> listaNomes) {
		String msg = "";
		for (int x = 0; x < listaNomes.size(); x++) {
			msg = msg + listaNomes.get(x) + Comandos.SEPARAR_DADOS.getCodigo();
		}
		return msg;
	}
	
	
	
	//lista para msg
	public static String listaArquivosToMsg(ArrayList<ArquivoDTO> lista) {
		String msg = "";
		String sep = Comandos.SEPARAR_DADOS.getCodigo();
		for (ArquivoDTO arquivo : lista) {
			msg += arquivo.getId() + sep + arquivo.getNomeArquivo() + sep + arquivo.getNomeRemetente() + sep + arquivo.getData().getTime() + sep;
		}
		return msg;
	}
	
	
	
	
	
	//msg to list<arquivosDTO>
	public static ArrayList<ArquivoDTO> msgToListaArquivosDTO(String msg) {
		if(contemComando(msg)) {
			String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
			ArrayList<ArquivoDTO> listaArquivos = new ArrayList<ArquivoDTO>();
			int contadorArquivos = ( dados.length - 1 ) /4;
			for (int x = 0; x < contadorArquivos; x++) {
				ArquivoDTO aux = new ArquivoDTO();
				int id= Integer.parseInt(dados[(x * 4) + 1]);
				aux.setId(id);
				aux.setNomeArquivo(dados[(x*4) + 2]);
				aux.setNomeRemetente(dados[(x*4) + 3]);
				Long dataLong = Long.parseLong(dados[(x * 4) +4]);
				aux.setData(new Date(dataLong));
				listaArquivos.add(aux);
			}
			return listaArquivos;
		}
		return null;
	}
	
	
	public static Arquivo msgToArquivo(String msg) {
		if(contemComando(msg)) {
			String[] dados = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
			Arquivo arq = new Arquivo();
			try {
				arq.setId(Integer.parseInt(dados[1]));
			} catch (Exception e) {
				arq.setId(null);
			}
			arq.setNomeArquivo(dados[2]);
			arq.setNomeRemetente(dados[3]);
			Long data = Long.parseLong(dados[4]);
			arq.setChat(Integer.parseInt(dados[5]));
			arq.setDataHora(new Date(data));
			try {
				String[] dadosAux = dados[6].split(sepB);
				byte[] aux = new byte[dadosAux.length];
				for (int x = 0; x < dadosAux.length; x++) {
						aux[x] = Byte.parseByte(dadosAux[x]);
				}
				arq.setArquivo(aux);
				return arq;
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro para converter a msg em arquivo");
			}
		
		
		
		
		}
		return null;
	}
	
	public static String arquivoToMsg(Arquivo arq) {
		//id, nomearq, nomeuser, data,chat, arq
		String ret = "";
		if(arq != null) {
			String sep = Comandos.SEPARAR_DADOS.getCodigo();
			String aux = "";
			for (int x = 0; x < arq.getArquivo().length; x++) {
				aux += arq.getArquivo()[x] + sepB;
			}
			ret = (arq.getId() == null ? "null" : arq.getId()) + sep + arq.getNomeArquivo() + sep + arq.getNomeRemetente() + sep + arq.getDataHora().getTime() + sep + arq.getChat() + sep + aux;
		}
		return ret;
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
		String sp = Comandos.SEPARAR_DADOS.getCodigo();
		msg = Comandos.CRIAR_USUARIO.getCodigo() + sp + us.getLogin() + sp + us.getSenha() + sp + us.getNome()  + sp + us.getEmail();
		return msg;
	}
	

	public static String listaMsgToString(ArrayList<Mensagem> lista) {
		String ret = "";
		String sep = Comandos.SEPARAR_DADOS.getCodigo();
		for (Mensagem mensagem : lista) {
			ret += mensagem.getData().getTime() + sep + mensagem.getNome() + sep + mensagem.getMensagem() + sep;
		}
		return ret;
	}
	
	public static ArrayList<Mensagem> msgToListaMsg(String msg){
		if(contemComando(msg)) {
			ArrayList<Mensagem> lista = new ArrayList<Mensagem>();
			String[] dadosAux = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
			String[] dados = new String[dadosAux.length-1];
			for (int x = 1; x < dadosAux.length; x++) {
				dados[x-1] = dadosAux[x];
			}
			int qtsMensagens = (dados.length) / 3;
			for (int x = 0;  x < qtsMensagens; x++) {
				int y = x * 3;
				Mensagem aux = new Mensagem();
				long longAux = Long.parseLong(dados[y]);
				aux.setData(new Date(longAux));
				aux.setNome(dados[y+1]);
				aux.setMensagem(dados[y+2]);
				lista.add(aux);
			}
			return lista;
		}
		return null;
	}
	
	// Terminar de implementar essa fun�ao se considerar q pode mandar mais de um comando
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
