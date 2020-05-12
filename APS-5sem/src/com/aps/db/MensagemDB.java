package com.aps.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aps.dominio.Mensagem;

public class MensagemDB {
	
	
	public boolean criarMensagem(Mensagem msg, int chat) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO chat?(nome, dataMsg, mensagem) VALUES (?, ?, ?)");			
			stmt.setInt(1, chat);
			stmt.setString(2, msg.getNome());
			stmt.setLong(3, msg.getData().getTime());
			stmt.setString(4, msg.getMensagem());
			int resul = stmt.executeUpdate();
			return resul == 1 ? true : false ;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao gravar mensagem no banco de dados");
			return false;
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	
	//Criar alguns ajustes para diferenciar o chat
	public ArrayList<Mensagem> msgsChat(int chat,int pagina) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Mensagem> lista = new ArrayList<Mensagem>();

		try {
			stmt = con.prepareStatement("Select * from chat? order by id asc limit 100 offset ?");
			stmt.setInt(1, chat);
			stmt.setInt(2, pagina);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Mensagem msg = new Mensagem();
				msg.setNome(rs.getString("nome"));
				msg.setData(new Date(rs.getLong("dataMsg")));
				msg.setMensagem(rs.getString("mensagem"));
				lista.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return lista;
	}


	public int quantasMsgs() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT count(*) as total FROM chat");
			rs = stmt.executeQuery();
			return rs.getInt("total");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return -1;
	}
	
	
	//?
	public ArrayList<Mensagem> buscarPorNome(String tipo) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Mensagem> lista = new ArrayList<Mensagem>();
		try {
			stmt = con.prepareStatement("Select nome, dataMsg, mensagem from chat where nome = %?%;");
			stmt.setString(1, tipo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Mensagem msg = new Mensagem();
				msg.setNome(rs.getString("nome"));
				msg.setData(new Date(rs.getLong("dataMsg")));
				msg.setMensagem(rs.getString("mensagem"));
				lista.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return lista;
	}
	
}
