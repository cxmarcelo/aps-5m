package com.aps.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aps.dominio.Usuario;

public class UsuarioDB {

	public void criarUsario(Usuario user) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO usuario(login, senha, nome, tipo) VALUES (?, ?, ?, ?)");
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getSenha());
			stmt.setString(3, user.getNome());
			stmt.setString(4, user.getTipo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao gravar no banco de dados");
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	//???
	public ArrayList<Usuario> buscarTodos() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();

		try {
			stmt = con.prepareStatement("Select * from Tartarugas");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setSenha(rs.getString("nome"));
				user.setSenha(rs.getString("tipo"));
				lista.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return lista;
	}

	
	public ArrayList<Usuario> buscarPorTipo(String tipo) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			stmt = con.prepareStatement("Select * from tartarugas where tipo = %?%;");
			stmt.setString(1, tipo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setSenha(rs.getString("nome"));
				user.setSenha(rs.getString("tipo"));
				lista.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return lista;
	}

	
	
	public ArrayList<Usuario> buscarLogin(String login){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			stmt = con.prepareStatement("Select * from tartarugas where id = ?");
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setSenha(rs.getString("nome"));
				user.setSenha(rs.getString("tipo"));
				lista.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro");
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return lista;
	}

	public void alterarNome(Usuario user, String nome ) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update usuario set nome = ? where login = ? and senha = ?");
			stmt.setString(1, nome);
			stmt.setString(2, user.getLogin());
			stmt.setString(3, user.getSenha());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erro: " + e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}


	public boolean delete(String login, String senha) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from usuario where login = ? and senha = ?");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			if(stmt.executeUpdate() ==1) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
}
