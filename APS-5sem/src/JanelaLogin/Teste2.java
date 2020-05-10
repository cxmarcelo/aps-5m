package JanelaLogin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.aps.controler.Login;
import com.aps.dominio.enums.Comandos;

public class Teste2 {

	

	private static void fileToString(File f) {
		InputStream is;
		try {
			is = new FileInputStream( f );
			byte[] bytes = new byte[(int)f.length() ];
			int offset = 0;
			int numRead = 0;
			try {
				while (offset < bytes.length
						&& (numRead = is.read(bytes, offset, bytes.length-offset)) >= 0) {
					offset += numRead;
				}
				String msg = "";
				for (byte b : bytes) {
					msg = msg + b;
				}
				System.out.println(msg.length());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
	

		Date teste = new Date(System.currentTimeMillis());
		String[] teste2 = teste.toString().split(" ");
		String[] teste3 = teste.toString().split(" ")[3].split(":");
		
		for (String string : teste3) {
			System.out.println(string);
		}
		
		System.out.println(teste.toString());
		
		
		/*
		Date dataAtual = new Date(System.currentTimeMillis());
		Date dataAtual2 = new Date(dataAtual.getTime());
		System.out.println(dataAtual2.toString());
		*/
		
		
		
		
		
		
		
		
		
		/*
		File arquivo = new File("c:/meusamigos/arquivo.txt");
		Teste2.fileToString(arquivo);
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		String msg = "";
		String sp = Comandos.SEPARAR_DADOS.getCodigo();
		msg = Comandos.AUTENTITCAR.getCodigo() + sp + "login" + sp + "senha"; 
		String dados[] =  msg.split(Comandos.SEPARAR_DADOS.getCodigo());
		for (String dado : dados) {
			System.out.println(dado);
		}
		 */
		/*
		Login teste = new Login();
		System.out.println(teste.logar("marcelo123", "marcelo2000"));

		 */
	}
}
