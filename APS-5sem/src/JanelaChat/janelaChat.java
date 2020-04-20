package JanelaChat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.aps.dominio.Usuario;

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
import java.util.ArrayList;

public class janelaChat extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Usuario> conectados = new ArrayList<Usuario>();
	private JTextArea textArea;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario user = new Usuario("erickson199?", "Erickson123", "Erick", null);
					janelaChat frame = new janelaChat(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public janelaChat(Usuario usuario) {
		setTitle("Sala 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46,84,143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtPanel = new JTextPane();
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
				//Fun�ao para enviar arquivo
				
			}
		});
		btnAnexar.setBounds(453, 448, 97, 38);
		contentPane.add(btnAnexar);
		
		JButton btnVoltar = new JButton("Voltar as Salas");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Fun��o Voltar
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
	}
	
	//n�o implementada
	private void enviarMsg() {
		textArea.getText();
		//func�o para enviar ao servidor
		textArea.setText("");
	}
	
	
}
