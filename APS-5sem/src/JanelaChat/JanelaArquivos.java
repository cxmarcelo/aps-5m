package JanelaChat;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	private JLabel lblQuemEnviou[];
	private JLabel[] lblArquivoNome;
	private JLabel[] lblData;
	private JButton[] btnBaixar;
	private JLabel lblPags;
	private int paginas = 1;
	private int chat;
	private int paginaAtual = 1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaArquivos frame = new JanelaArquivos(12346);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaArquivos(int chat) {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 384);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46,84,143));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnPagAnt = new JButton("<<<");
		btnPagAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAnterior();
			}
		});
		btnPagAnt.setBounds(50, 285, 90, 23);
		contentPane.add(btnPagAnt);

		lblPags = new JLabel(paginaAtual +"/" + paginas);
		lblPags.setForeground(Color.WHITE);
		lblPags.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPags.setHorizontalAlignment(SwingConstants.CENTER);
		lblPags.setBounds(150, 285, 50, 23);
		contentPane.add(lblPags);

		JButton btnPagSeg = new JButton(">>>");
		btnPagSeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaSeguinte();
			}
		});
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
			lblQuemEnviou[x].setForeground(Color.WHITE);
			contentPane.add(lblQuemEnviou[x]);

			lblArquivoNome[x] = new JLabel(""+ x + "");
			lblArquivoNome[x].setHorizontalAlignment(SwingConstants.CENTER);
			lblArquivoNome[x].setBounds(130, 0 + (35 * x), 100, 30);
			lblArquivoNome[x].setForeground(Color.WHITE);
			
			contentPane.add(lblArquivoNome[x]);


			lblData[x] = new JLabel("");
			lblData[x].setHorizontalAlignment(SwingConstants.CENTER);
			lblData[x].setBounds(250, 0 + (35 * x), 100 , 30);
			lblData[x].setForeground(Color.WHITE);
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
		this.chat = chat;
		cliConect = new PrincipalCliente();
		conectar();
		ouvir();
		enviarRequisicao(Comandos.TODOS_ARQUIVOS_NOMES.getCodigo());
		
		
		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent evt){ 
				sair();
				System.exit(0);
			} 
		});
	}

	private void sair() {
		String msg = Comandos.SAIR.getCodigo();
		try {
			cliConect.enviarMensagem(msg);
			cliConect.sair();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void conectar() {
		try {
			cliConect.conectar(chat, null);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro para conectar");
		}
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
		while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg))
			if(bfr.ready()){
				msg = bfr.readLine();
				if(msg.equals("Sair")){
					//sair

				}else if(msg.contains(Comandos.RETORNAR_ARQUIVO.getCodigo())) {
					Arquivo arq = Decodificadores.msgToArquivo(msg);
					baixar(arq);
				}else if(msg.contains(Comandos.TODOS_ARQUIVOS_NOMES_RET.getCodigo())) {
					listaArquivos = Decodificadores.msgToListaArquivosDTO(msg);
					uparArquivos();
				}
			}
	}


	private void uparArquivos() {
		paginas = 1;
		paginaAtual = 1;
		int contador = listaArquivos.size();

		if(listaArquivos.size() > 8) {
			paginas =  (int) Math.ceil(((double) listaArquivos.size() / 10));
			contador = 8;
		}		
		lblPags.setText(paginaAtual +"/" + paginas);
		for (int x = 0; x < contador; x++) {
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

	private void paginaAnterior() {
		if(paginaAtual > 1) {
			paginaAtual--;
			for(int x =0; x <8; x++) {
				int y = x + 8 *( paginaAtual -1) ;
				lblQuemEnviou[x].setText(listaArquivos.get(y).getNomeRemetente());
				lblArquivoNome[x].setText(listaArquivos.get(y).getNomeArquivo());
				lblData[x].setText(listaArquivos.get(y).getDiaMes());
				btnBaixar[x].setVisible(true);
			}
		}
	}
	
	private void paginaSeguinte() {
		if(paginaAtual < paginas) {
			paginaAtual++;
			for(int x = 0 ; x < 8 ; x++) {
				int y = x + 8 *( paginaAtual -1) ;
				if(y >= listaArquivos.size()) {
					lblQuemEnviou[x].setText("");
					lblArquivoNome[x].setText("");
					lblData[x].setText("");
					btnBaixar[x].setVisible(false);
				}else {
					lblQuemEnviou[x].setText(listaArquivos.get(y).getNomeRemetente());
					lblArquivoNome[x].setText(listaArquivos.get(y).getNomeArquivo());
					lblData[x].setText(listaArquivos.get(y).getDiaMes());
					btnBaixar[x].setVisible(true);
				}
				repaint();
				revalidate();
			}
		}
	}


}
