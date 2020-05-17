package JanelaChat;

import java.awt.Color;
import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.aps.controler.Decodificadores;
import com.aps.dominio.Arquivo;
import com.aps.dominio.ArquivoDTO;
import com.aps.dominio.enums.Comandos;
import com.aps.resources.PrincipalCliente;

import java.awt.Font;

public class JanelaArquivos extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private PrincipalCliente cliConect;
	private ArrayList<ArquivoDTO> listaArquivos = new ArrayList<ArquivoDTO>();
	JLabel lblQuemEnviou[];
	JLabel[] lblArquivoNome;
	JLabel[] lblData;
	JButton[] btnBaixar;
	private int paginas = 1;
	private int paginaAtual = 1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalCliente a = new PrincipalCliente();
					a.conectar(12346, null);
					JanelaArquivos frame = new JanelaArquivos(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param servConect 
	 */
	public JanelaArquivos(PrincipalCliente servConect) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 384);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46,84,143));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnPagAnt = new JButton("<<<");
		btnPagAnt.setBounds(50, 285, 90, 23);
		contentPane.add(btnPagAnt);

		JLabel lblNewLabel = new JLabel("0/0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 285, 50, 23);
		contentPane.add(lblNewLabel);

		JButton btnPagSeg = new JButton(">>>");
		btnPagSeg.setBounds(210, 285, 90, 23);
		contentPane.add(btnPagSeg);

		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(350, 285, 90, 23);
		contentPane.add(btnSair);

		lblQuemEnviou = new JLabel[8];
		lblArquivoNome = new JLabel[8];
		lblData = new JLabel[8];
		btnBaixar = new JButton[8];

		for (int x = 0; x < lblArquivoNome.length; x++) {
			lblQuemEnviou[x] = new JLabel("" + x);
			lblQuemEnviou[x].setBounds(5, 0 + (35 * x), 125, 30);
			contentPane.add(lblQuemEnviou[x]);

			lblArquivoNome[x] = new JLabel(""+ x + "");
			lblArquivoNome[x].setHorizontalAlignment(SwingConstants.CENTER);
			lblArquivoNome[x].setBounds(130, 0 + (35 * x), 100, 30);
			contentPane.add(lblArquivoNome[x]);


			lblData[x] = new JLabel("");
			lblData[x].setHorizontalAlignment(SwingConstants.CENTER);
			lblData[x].setBounds(250, 0 + (35 * x), 100 , 30);
			contentPane.add(lblData[x]);


			btnBaixar[x] = new JButton("Baixar");
			btnBaixar[x].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnsBaixar(e);
				}
			});
			btnBaixar[x].setVisible(false);
			btnBaixar[x].setBounds(360, 0 + (35 * x), 75, 30);
			contentPane.add(btnBaixar[x]);
		}
		cliConect = servConect; 
		ouvir();
		enviarRequisicao(Comandos.TODOS_ARQUIVOS_NOMES.getCodigo());
	}


	private void ouvir() {
		new Thread() {
			public void run() {
				try {
					escutar();
				} catch (IOException e) {
					System.out.println("Erro para ouvir mensagens do servidor /JanelaArquivos");
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void btnsBaixar(ActionEvent e) {
		int id = -1;
		if (e.getSource()==btnBaixar[0]){
			id = listaArquivos.get(((paginaAtual -1) * 8)).getId();
		}
		else if(e.getSource()==btnBaixar[1]){
			id = listaArquivos.get(((paginaAtual -1) * 8) +1).getId();
		}
		else if (e.getSource()==btnBaixar[2]){
			id = listaArquivos.get(((paginaAtual -1) * 8) +2).getId();
		} 
		else if (e.getSource()==btnBaixar[3]){
			id = listaArquivos.get(((paginaAtual -1) * 8) +3).getId();
		} 
		else if (e.getSource()==btnBaixar[4]){
			id = listaArquivos.get(((paginaAtual -1) * 8) +4).getId();
		}
		else if (e.getSource()==btnBaixar[5]){
			id = listaArquivos.get(((paginaAtual -1) * 8) +5).getId();
		}
		else if (e.getSource()==btnBaixar[6]){
			id = listaArquivos.get(((paginaAtual -1) * 8) +6).getId();
		}		
		else if (e.getSource()==btnBaixar[7]){
			id = listaArquivos.get(((paginaAtual -1) * 7 ) + 7).getId();
		
		}	      
		String sep = Comandos.SEPARAR_DADOS.getCodigo();
		String msg = Comandos.REQUISITAR_ARQUIVO.getCodigo() + sep + id;
		enviarRequisicao(msg);
	}


	public void baixar(Arquivo arquivo) {
		JFileChooser escolherLocal = new JFileChooser();
		FileNameExtensionFilter filtroPDF = new FileNameExtensionFilter("Arquivos PDF, TXT, ODT", "pdf", "odt", "txt");  
		escolherLocal.setFileFilter(filtroPDF);
		escolherLocal.setFileSelectionMode(JFileChooser.FILES_ONLY);

		
		File file = new File(arquivo.getNomeArquivo());
		escolherLocal.setSelectedFile(file);
		if(escolherLocal.showOpenDialog(escolherLocal) == JFileChooser.APPROVE_OPTION){
			String path =  escolherLocal.getSelectedFile().getAbsolutePath();
			File arquivoSalvar = new File(path);

			if(arquivoSalvar.exists()) {
				if(JOptionPane.showConfirmDialog(null, "Arquivo existe deseja substituilo") == 0) {
					salvar(arquivo, arquivoSalvar);
				}else return;

			}else {
				salvar(arquivo, arquivoSalvar);
			}
		}
	}

	private void salvar(Arquivo arquivo, File file) {
		try {
			System.out.println(arquivo.getArquivo().length);
			FileOutputStream in = new FileOutputStream(file);
			in.write(arquivo.getArquivo());
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void escutar() throws IOException{
		InputStream in = cliConect.getSocket().getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";
		String ultimaMsg = "-1";
		while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg))
			if(bfr.ready()){
				msg = bfr.readLine();
				if(msg.equals("Sair")){
					//sair

				}else if(msg.contains(Comandos.RETORNAR_ARQUIVO.getCodigo())) {
					Arquivo arq = Decodificadores.msgToArquivo(msg);
					baixar(arq);
				}else if(msg.contains(Comandos.TODOS_ARQUIVOS_NOMES_RET.getCodigo())) {
					//arraylist nomes arquivos(dto)
					listaArquivos = Decodificadores.msgToListaArquivosDTO(msg);
					uparArquivos();

				}else if(msg.equals("") || msg != null || msg.equals(ultimaMsg)) {
					System.out.println("MENSAGEM RECEBIDA FOI: " + msg);
					ultimaMsg = msg;
				}

			}
	}


	private void uparArquivos() {
		for (int x = 0; x < btnBaixar.length; x++) {
			try {
				ArquivoDTO aux = listaArquivos.get(x);
				lblQuemEnviou[x].setText(aux.getNomeRemetente());
				lblArquivoNome[x].setText(aux.getNomeArquivo());
				lblData[x].setText(aux.getDiaMes());
				btnBaixar[x].setVisible(true);
			} catch (Exception e) {
				lblQuemEnviou[x].setText("");
				lblArquivoNome[x].setText("");
				lblData[x].setText("");
				btnBaixar[x].setVisible(false);
			}
		}
	}

	private void enviarRequisicao(String msg) {
		try {
			cliConect.enviarMensagem(msg);
		} catch (IOException e) {
			System.out.println("ERRO ao enviar msg JanelaArquivo");
			e.printStackTrace();
		}
	}
}
