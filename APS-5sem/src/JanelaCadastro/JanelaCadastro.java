package JanelaCadastro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextArea;

public class JanelaCadastro extends JFrame {

	private JPanel contentPane;
	private ImageIcon imagem = new ImageIcon(getClass().getResource("logoaps.png"));
	private JTextField textFieldNome;
	private JTextField textFieldLogin;
	private JTextField passFieldSenha;
	private JTextField textFieldEmail;


	/**
	 * Launch the application.
	 */
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
			@Override
			public void focusGained(FocusEvent e) {
				if("Senha".equals(passFieldSenha.getText())) {
					passFieldSenha.setForeground(Color.BLACK);
					passFieldSenha.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(passFieldSenha.getText())) {
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
		btnCadastrar.setBounds(310, 463, 97, 25);
		contentPane.add(btnCadastrar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(191, 463, 97, 25);
		contentPane.add(btnVoltar);
		
		JTextArea textAreaGambi = new JTextArea();
		textAreaGambi.setBounds(93, 100, 4, 22);
		textAreaGambi.setEditable(false);
		textAreaGambi.setBackground(new Color(46,84,143));
		contentPane.add(textAreaGambi);
		
		
	}
}
