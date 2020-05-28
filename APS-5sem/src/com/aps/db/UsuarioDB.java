package com.aps.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.aps.dominio.Usuario;

public class UsuarioDB {

	public boolean criarUsario(Usuario user) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO usuario(login, senha, nome, email) VALUES (?, ?, ?, ?)");
			stmt.setString(1, user.getLogin());
			stmt.setString(2, user.getSenha());
			stmt.setString(3, user.getNome());
			stmt.setString(4, user.getEmail());
			int resul = stmt.executeUpdate();
			return resul == 1 ? true : false ;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao gravar no banco de dados");
			return false;
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
			stmt = con.prepareStatement("Select * from usuario");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setSenha(rs.getString("nome"));
				user.setSenha(rs.getString("email"));
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

	
	public ArrayList<Usuario> buscarPorTipo(String email) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {
			stmt = con.prepareStatement("Select * from usuario where email = %?%;");
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setSenha(rs.getString("nome"));
				user.setSenha(rs.getString("email"));
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
			stmt = con.prepareStatement("Select * from usuario where login = ?");
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Usuario user = new Usuario();
				user.setLogin(rs.getString("login"));
				user.setSenha(rs.getString("senha"));
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
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

	public boolean alterarDados(Usuario user) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update usuario set nome = ?, email = ? where login = ?");
			stmt.setString(1, user.getNome());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getLogin());
			if(stmt.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public boolean alterarSenha(Usuario user) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update usuario set senha = ? where login = ?");
			stmt.setString(1, user.getSenha());
			stmt.setString(2, user.getLogin());
			
			if(stmt.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
