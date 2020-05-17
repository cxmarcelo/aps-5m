package JanelaLogin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.aps.controler.Login;
import com.aps.db.ArquivosDB;
import com.aps.dominio.Arquivo;
import com.aps.dominio.ArquivoDTO;
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
		ArquivosDB t = new ArquivosDB();
		Arquivo teste = t.buscarArquivo(1);
	 
		java.io.File file = new java.io.File("C:\\Users\\Marcelo\\Desktop\\LIVROS/teste7salvando.txt");
		try {
			FileOutputStream in = new FileOutputStream(file) ;  
			String dados = "";
			System.out.println("----" + teste.getArquivo().length);
			for (int x = 0; x < teste.getArquivo().length; x++) {
				dados += teste.getArquivo()[x];
			}
			for (byte b : teste.getArquivo()) {
				System.out.println(b);
			}
			in.write(teste.getArquivo());
			in.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		String p = "Palavra";
		
		/*
		
		JFileChooser escolherLocal = new JFileChooser();
		FileNameExtensionFilter filtroPDF = new FileNameExtensionFilter("Arquivos PDF, TXT, ODT", "pdf", "odt", "txt");  
		escolherLocal.setFileFilter(filtroPDF);
		escolherLocal.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		
		File arquivo = new File("testttteeee.pdf");
		System.out.println(arquivo.getName());
		

		escolherLocal.setSelectedFile(arquivo);
		if(escolherLocal.showOpenDialog(escolherLocal) == JFileChooser.APPROVE_OPTION){
			String tes =  escolherLocal.getSelectedFile().getAbsolutePath();
			System.out.println(tes);
		}
		
		//t.insertFile(arquivo);
		
		/*
		ArquivoDTO teste = new ArquivoDTO();
		teste.setData(new Date(System.currentTimeMillis()));
		System.out.println(teste.getDiaMes());
		
		/*
		long longteste = System.currentTimeMillis();
		Calendar teste2 = Calendar.getInstance();
		GregorianCalendar cal = new GregorianCalendar(TimeZone.getDefault());
		Date teste = new Date(longteste);
		String[] te = teste.toString().split(" ");
		System.out.println(teste.toString());
		*/
		/*String[] teste2 = teste.toString().split(" ");
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
