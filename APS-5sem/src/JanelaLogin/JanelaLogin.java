package JanelaLogin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class JanelaLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passField;
	private ImageIcon imagem = new ImageIcon(getClass().getResource("logoaps.png"));
	private JLabel lblTitulo;
	private JLabel lblSenha;
	private JLabel lblLogin;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaLogin frame = new JanelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaLogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46,84,143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label Login
		lblLogin = new JLabel("Login");
		lblLogin.setLabelFor(textFieldLogin);
		lblLogin.setForeground(Color.GRAY);
		lblLogin.setBounds(210, 406, 56, 16);
		contentPane.add(lblLogin);
		
		//label senha
		lblSenha = new JLabel("Senha");
		lblSenha.setLabelFor(passField);
		lblSenha.setForeground(Color.GRAY);
		lblSenha.setBounds(210, 441, 56, 16);
		contentPane.add(lblSenha);
		
		//textFieldLogin
		textFieldLogin = new JTextField();
		textFieldLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if("Login".equals(lblLogin.getText())) {
					lblLogin.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(textFieldLogin.getText())) {
					lblLogin.setText("Login");
				}
			}
		});

		textFieldLogin.setBounds(200, 403, 193, 22);
		textFieldLogin.setColumns(10);
		contentPane.add(textFieldLogin);
		
		//textFieldSenha
		
		passField = new JPasswordField	();
		passField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if("Senha".equals(lblSenha.getText())) {
					lblSenha.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if("".equals(String.copyValueOf(passField.getPassword()))) {
					lblSenha.setText("Senha");
				}
			}
		});
		
		passField.setBounds(200, 438, 193, 22);
		passField.setColumns(10);
		contentPane.add(passField);

		
		//Logo
		JLabel lblLogo = new JLabel(imagem);
		lblLogo.setBounds(97, 150, 420, 229);
		contentPane.add(lblLogo);
		
		//Titulo
		lblTitulo = new JLabel("Tiet\u00EA Solutions");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblTitulo.setBounds(149, 85, 298, 35);
		contentPane.add(lblTitulo);
		
		//botão conectar
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(248, 482, 97, 25);
		contentPane.add(btnConectar);
	}
}
