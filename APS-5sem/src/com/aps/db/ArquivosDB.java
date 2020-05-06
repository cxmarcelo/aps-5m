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

//NAO IMPLEMENTADO
public class ArquivosDB {

	public boolean insertFile(File f ){
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO arquivo( id, nome, arquivo ) VALUES ( nextval('seq_arquivo'), ?, ? )");
			InputStream is = new FileInputStream(f);
			byte[] bytes = new byte[(int)f.length() ];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length
					&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
				offset += numRead;
			}

			ps.setString( 1, f.getName() );
			ps.setBytes( 2, bytes );
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

	public File getFile( int id ){
		Connection con = ConnectionFactory.getConnection();
		File f = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT id, nome, arquivo FROM arquivo WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if ( rs.next() ){
				byte [] bytes = rs.getBytes("arquivo");
				String nome = rs.getString("nome");

				//converte o array de bytes em file
				f = new File( "/local_a_ser_salvo/" + nome );
				FileOutputStream fos = new FileOutputStream( f);
				fos.write( bytes );
				fos.close();
			}
			rs.close();
			ps.close();
			con.close();
			return f;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}




}
