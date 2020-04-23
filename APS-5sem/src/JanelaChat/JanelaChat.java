package JanelaChat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;
import com.aps.resources.PrincipalCliente;
import com.aps.resources.Servidor;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JLabel;
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

public class JanelaChat extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private ArrayList<Usuario> conectados = new ArrayList<Usuario>();
	private JTextArea textArea;
	private PrincipalCliente servConect;
	private JTextPane txtPanel;
	private Usuario user;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario user = new Usuario("erickson199?", "Erickson123", "Erick", null);
					JanelaChat frame = new JanelaChat(user, 1);
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
				//Funçao para enviar arquivo

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

		JLabel lblIntegrantes = new JLabel("Integrantes");
		lblIntegrantes.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblIntegrantes.setForeground(Color.WHITE);
		lblIntegrantes.setBounds(579, 11, 119, 31);
		contentPane.add(lblIntegrantes);

		JTextArea txtIntegrantes = new JTextArea();
		txtIntegrantes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		txtIntegrantes.setForeground(Color.WHITE);
		txtIntegrantes.setEditable(false);
		txtIntegrantes.setBackground(new Color(46,84,143));
		txtIntegrantes.setBounds(579, 45, 119, 441);
		contentPane.add(txtIntegrantes);
		servConect = new PrincipalCliente();
		conectar(1, usuario);
		user = usuario;
	}


	private void conectar(int chat, Usuario us) {
		new Thread() {
			public void run() {
				try {
					servConect.conectar(1, us);
					escutar();
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Erro ao conectar ao chat");
				}
			}
		}.start();
	}

	private void escutar() throws IOException{
		InputStream in = servConect.getSocket().getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";
		while(!"Sair".equalsIgnoreCase(msg))
			if(bfr.ready()){
				msg = bfr.readLine();
				System.out.println("Entrei aqui");
				if(msg.equals("Sair"))
					txtPanel.setText("Sai aqui meu");
				else {
					txtPanel.setText(txtPanel.getText() + "\r\n" + msg);
					repaint();
					revalidate();
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
