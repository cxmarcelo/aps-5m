package com.aps.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.aps.dominio.Arquivo;
import com.aps.dominio.ArquivoDTO;

public class ArquivosDB {

	public boolean salvarAquivoBD(Arquivo arq ){
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO tabelaArquivos( id, nomeArquivo, nomeRemetente, dataHora, chat, arquivo ) VALUES (null, ?, ?, ?, ?, ?)");
			
			File f = File.createTempFile("temporario", ".tmp");
			InputStream is = new FileInputStream(f);
			try {
				FileOutputStream in = new FileOutputStream(f);
				in.write(arq.getArquivo());
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ps.setBinaryStream(5, (InputStream)is,(int)f.length());
			ps.setString( 1, arq.getNomeArquivo());
			ps.setString(2, arq.getNomeRemetente());
			ps.setLong(3, arq.getDataHora().getTime());
			ps.setInt(4, arq.getChat());
			ps.execute();
			ps.close();
			con.close();
			is.close();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
	
	public Arquivo buscarArquivo( int id ){
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM tabelaArquivos WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			Arquivo arq = null;
			if ( rs.next() ){
				arq = new Arquivo();
				arq.setId(rs.getInt("id"));
				arq.setNomeArquivo(rs.getString("nomeArquivo"));
				arq.setNomeRemetente(rs.getString("nomeRemetente"));
				arq.setDataHora(new Date(rs.getLong("dataHora")));
				arq.setChat(rs.getInt("chat"));
				java.sql.Blob blob = rs.getBlob("Arquivo");
				arq.setArquivo(blob.getBytes(1, (int) blob.length()));
				
			}
			rs.close();
			ps.close();
			con.close();
			return arq;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<ArquivoDTO> buscarTodosArquivos(int chat) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<ArquivoDTO> lista = new ArrayList<ArquivoDTO>();
		try {
			stmt = con.prepareStatement("SELECT * FROM tabelaArquivos WHERE chat = ? order by id desc");
			stmt.setInt(1, chat);
			rs = stmt.executeQuery();
			while(rs.next()) {
				ArquivoDTO arq = new ArquivoDTO();
				arq.setId(rs.getInt("id"));
				arq.setNomeArquivo(rs.getString("nomeArquivo"));
				arq.setNomeRemetente(rs.getString("nomeRemetente"));
				arq.setData(new Date(rs.getLong("dataHora")));
				lista.add(arq);
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
