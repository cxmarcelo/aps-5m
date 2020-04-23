package JanelaCadastro;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.aps.controler.Decodificadores;
import com.aps.dominio.Usuario;
import com.aps.resources.PrincipalCliente;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaCadastro extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private ImageIcon imagem = new ImageIcon(getClass().getResource("../com/aps/imgs/logoaps.png"));
	private JTextField textFieldNome;
	private JTextField textFieldLogin;
	private JPasswordField passFieldSenha;
	private JTextField textFieldEmail;
	private PrincipalCliente servConnect;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaCadastro frame = new JanelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaCadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46,84,143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel JLabelLogo = new JLabel(imagem);
		JLabelLogo.setBounds(136, 35, 322, 218);
		contentPane.add(JLabelLogo);
		
		textFieldNome = new JTextField();
		textFieldNome.setText("Nome");
		textFieldNome.setForeground(Color.GRAY);
		textFieldNome.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if("Nome".equals(textFieldNome.getText())) {
					textFieldNome.setForeground(Color.BLACK);
					textFieldNome.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if("".equals(textFieldNome.getText())) {
					textFieldNome.setForeground(Color.GRAY);
					textFieldNome.setText("Nome");
				}
			}
		});
		textFieldNome.setBounds(129, 335, 335, 22);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setText("Login");
		textFieldLogin.setForeground(Color.GRAY);
		textFieldLogin.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if("Login".equals(textFieldLogin.getText())) {
					textFieldLogin.setForeground(Color.BLACK);
					textFieldLogin.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if("".equals(textFieldLogin.getText())) {
					textFieldLogin.setForeground(Color.GRAY);
					textFieldLogin.setText("Login");
				}
			}
		});
		textFieldLogin.setBounds(129, 370, 159, 22);
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		passFieldSenha = new JPasswordField	();
		passFieldSenha.setForeground(Color.GRAY);
		passFieldSenha.setText("Senha");
		passFieldSenha.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if("Senha".equals(new String(passFieldSenha.getPassword()))) {
					passFieldSenha.setForeground(Color.BLACK);
					passFieldSenha.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if("".equals(new String(passFieldSenha.getPassword()))) {
					passFieldSenha.setForeground(Color.GRAY);
					passFieldSenha.setText("Senha");
				}
			}
		});
		passFieldSenha.setBounds(310, 370, 154, 22);
		contentPane.add(passFieldSenha);
		passFieldSenha.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setText("E-mail");
		textFieldEmail.setForeground(Color.GRAY);
		textFieldEmail.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if("E-mail".equals(textFieldEmail.getText())) {
					textFieldEmail.setForeground(Color.BLACK);
					textFieldEmail.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if("".equals(textFieldEmail.getText())) {
					textFieldEmail.setForeground(Color.GRAY);
					textFieldEmail.setText("E-mail");
				}
			}
		});
		textFieldEmail.setBounds(129, 405, 335, 22);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criarUsuario();
			}
		});
		btnCadastrar.setBounds(310, 463, 97, 25);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnVoltar.setBounds(191, 463, 97, 25);
		contentPane.add(btnVoltar);
		
		JTextArea textAreaGambi = new JTextArea();
		textAreaGambi.setBounds(93, 100, 4, 22);
		textAreaGambi.setEditable(false);
		textAreaGambi.setBackground(new Color(46,84,143));
		contentPane.add(textAreaGambi);
		servConnect = new PrincipalCliente();
		conectar();
	}
	
	private void conectar() {
		try {
			servConnect.conectar(12345, null);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao conectar ao servidor");
		}
	}
	
	// NAO IMPLEMENTADO
	private void criarUsuario() {
		String msg = "";
		Usuario us = new Usuario(textFieldLogin.getText(), new String(passFieldSenha.getPassword()), textFieldNome.getText(), textFieldEmail.getText());
		msg = Decodificadores.msgCriarUsuario(us);
		try {
			servConnect.enviarMensagem(msg);
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro para criar usuário");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
