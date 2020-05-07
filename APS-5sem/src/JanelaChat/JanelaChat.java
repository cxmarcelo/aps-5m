package JanelaChat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.aps.controler.Decodificadores;
import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;
import com.aps.resources.PrincipalCliente;
import com.aps.resources.Servidor;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.SwingConstants;

public class JanelaChat extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<String> conectados = new ArrayList<String>();
	private JTextArea textArea;
	private PrincipalCliente servConect;
	private JTextPane txtPanel;
	private Usuario user;
	private JLabel[] lblIntegrantes;
	private int paginas = 1;
	private int paginaAtual = 1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario user = new Usuario("erickson199?", "Erickson123", "Erick", null);
					JanelaChat frame = new JanelaChat(user, 12346);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaChat(Usuario usuario, int chat) {
		setTitle("Sala 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46,84,143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtPanel = new JTextPane();
		txtPanel.setEditable(false);
		txtPanel.setBounds(32, 45, 518, 332);
		contentPane.add(txtPanel);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviarMsg();
			}
		});
		btnEnviar.setBounds(453, 400, 97, 38);
		contentPane.add(btnEnviar);

		JButton btnAnexar = new JButton("Anexar");
		btnAnexar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anexar();
			}
		});
		btnAnexar.setBounds(453, 448, 97, 38);
		contentPane.add(btnAnexar);

		JButton btnVoltar = new JButton("Voltar as Salas");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Função Voltar
			}
		});
		btnVoltar.setBounds(291, 509, 150, 31);
		contentPane.add(btnVoltar);

		JLabel lblNomeDoInfeliz = new JLabel(usuario.getNome());
		lblNomeDoInfeliz.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNomeDoInfeliz.setForeground(Color.WHITE);
		lblNomeDoInfeliz.setBounds(32, 16, 518, 21);
		contentPane.add(lblNomeDoInfeliz);

		textArea = new JTextArea();	
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textArea.setForeground(Color.GRAY);
		textArea.setText("Escreva...");
		textArea.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if("Escreva...".equals(textArea.getText())) {
					textArea.setForeground(Color.BLACK);
					textArea.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if("".equals(textArea.getText())) {
					textArea.setForeground(Color.GRAY);
					textArea.setText("Escreva...");
				}
			}
		});
		textArea.setBounds(32, 402, 407, 83);
		contentPane.add(textArea);

		JLabel lblIntegrantesCabe = new JLabel("Integrantes");
		lblIntegrantesCabe.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblIntegrantesCabe.setForeground(Color.WHITE);
		lblIntegrantesCabe.setBounds(579, 11, 119, 31);
		contentPane.add(lblIntegrantesCabe);
		
		JLabel lblQtdConectados = new JLabel("x Online");
		lblQtdConectados.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQtdConectados.setForeground(Color.GREEN);
		lblQtdConectados.setHorizontalAlignment(SwingConstants.CENTER);
		lblQtdConectados.setBounds(579, 45, 119, 24);
		contentPane.add(lblQtdConectados);
		
		JButton btnConecPagAnt = new JButton("<");
		btnConecPagAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAnterior();
			}
		});
		btnConecPagAnt.setBounds(589, 341, 41, 23);
		contentPane.add(btnConecPagAnt);
		
		JButton btnConecPagSeg = new JButton(">");
		btnConecPagSeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaSeguinte();
			}
		});
		btnConecPagSeg.setBounds(657, 341, 41, 23);
		contentPane.add(btnConecPagSeg);
		
		lblIntegrantes = new JLabel[10];
		for (int x = 0; x < lblIntegrantes.length; x++) {
			lblIntegrantes[x] = new JLabel("");
			lblIntegrantes[x].setBounds(579, 80 + (x * 25), 120, 20);
			lblIntegrantes[x].setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblIntegrantes[x].setForeground(Color.WHITE);
			lblIntegrantes[x].setHorizontalAlignment(SwingConstants.CENTER);			
			contentPane.add(lblIntegrantes[x]);
		}
		
		
		servConect = new PrincipalCliente();
		conectar(chat, usuario);
		user = usuario;
	}

	
	//NAO IMPLEMENTADO
	private void anexar() {
		JFileChooser arquivo = new JFileChooser();
		FileNameExtensionFilter filtroPDF = new FileNameExtensionFilter("Arquivos PDF", "pdf");  
		arquivo.addChoosableFileFilter(filtroPDF);
		arquivo.setAcceptAllFileFilterUsed(false);
		if(arquivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			//.setText(arquivo.getSelectedFile().getAbsolutePath());
			//arquivo.getSelectedFile();
		
		}
	}
	
	private void paginaAnterior() {
		if(paginaAtual > 1) {
			paginaAtual--;
			for(int x =0; x <10; x++) {
				int y = x + 10 *( paginaAtual -1) ;
				lblIntegrantes[x].setText(conectados.get(y));
			}
		}
	}
	private void paginaSeguinte() {
		if(paginaAtual < paginas) {
			paginaAtual++;
			for(int x = 0 ; x < 10 ; x++) {
				int y = x + 10 *( paginaAtual -1) ;
				if(y >= conectados.size()) {
					lblIntegrantes[x] .setText("");
				}else {
					lblIntegrantes[x].setText(conectados.get(y));
				}
				repaint();
				revalidate();
			}
		}
		uparUsuariosConectados();
		System.out.println(conectados.size());
	}

	private void conectar(int chat, Usuario us) {
		new Thread() {
			public void run() {
				try {
					System.out.println("Antes de conectar");
					servConect.conectar(chat, us);
					System.out.println("Depois de conectar");
					escutar();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Erro ao conectar ao chat");
				}
			}
		}.start();
		//uparUsuariosConectados();
	}

	private void escutar() throws IOException{
		InputStream in = servConect.getSocket().getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";
		while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg))
			if(bfr.ready()){
				msg = bfr.readLine();
				
				if(msg.equals("Sair"))
					txtPanel.setText("Sai aqui meu");
				else if(msg.contains(Comandos.ENVIAR_MSG.getCodigo())) {
					System.out.println("RECEBI O LIXO DA MSG COM INDEX ERROR A SEGUIR" + msg);
					msg = Decodificadores.msgToString(msg);
					if(msg == null) return;
					System.out.println("RECEBI O LIXO DA MENSAGEM AQUI KRL SO NAO QUERO PRINTAR");
					txtPanel.setText(txtPanel.getText() + "\r\n" + (msg != null ? msg : ""));
					repaint();
					revalidate();
				}else if(msg.contains(Comandos.ENVIAR_ARQUIVO.getCodigo())) {
					//nao implementado
					
					
					//*****
				}else if(msg.contains(Comandos.TODOS_USUARIOS_SALA_RET.getCodigo())) {
					JOptionPane.showMessageDialog(null, "Recebi os usúarios");
					conectados = Decodificadores.nomesUsuarios(msg);
					atualizarConectados();
					usuariosConectados();
				}
			}
	}

	//Nao implementado
	private void uparMsgs() {
		/*
		 * Enviar msg ao servidor para trazer todas as mensagens
		 */
	}

	//
	private void uparUsuariosConectados() {
		try {
			servConect.enviarMensagem(Comandos.TODOS_USUARIOS_SALA.getCodigo());
		} catch (IOException e) {
			System.out.println("erro para pedir todos usuarios da sala");
			e.printStackTrace();
		}
	}
	
	private void usuariosConectados() {
		paginas = 1;
		paginaAtual = 1;
		int contador = conectados.size();
		
		if(conectados.size() > 10) {
			paginas =  (int) Math.ceil(((double) conectados.size() / 10));
			contador = 10;
		}

		for(int x =0; x < contador; x++) {
			if(conectados.get(x) == null) {
				break;
				//continue ?
			}
			lblIntegrantes[x].setText(conectados.get(x));
			validate();
			repaint();
		}
	}

	private void atualizarConectados() {
		if(conectados.size() == 0) {
			return;
		}
		for (int x = 0; x < lblIntegrantes.length; x++) {
			if(conectados.get(x) != null ) {
				lblIntegrantes[x].setText(conectados.get(x));
			}else {
				break;
			}
		}
	}
	
	
	private void enviarMsg() {
		String msg = Comandos.ENVIAR_MSG.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + textArea.getText();
		try {
			servConect.enviarMensagem(msg);
			txtPanel.setText(txtPanel.getText() + "/r/n" + user.getNome() + textArea.getText() );
		} catch (IOException e) {
			System.out.println("erro ao enviar msg janelaChat");
			e.printStackTrace();
		}
		textArea.setText("");
	}
}
