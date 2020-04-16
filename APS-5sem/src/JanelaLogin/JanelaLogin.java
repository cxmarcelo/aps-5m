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
import javax.swing.JTextArea;

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
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);


		//textFieldSenha

		passField = new JPasswordField	();
		passField.setText("Senha");
		passField.setForeground(Color.GRAY);
		passField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if("Senha".equals(String.copyValueOf(passField.getPassword()))) {
					passField.setForeground(Color.BLACK);
					passField.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(String.copyValueOf(passField.getPassword()))) {
					passField.setForeground(Color.GRAY);
					passField.setText("Senha");
				}
			}
		});

		passField.setBounds(200, 438, 193, 22);
		passField.setColumns(10);	
		contentPane.add(passField);




		//textFieldLogin
		textFieldLogin = new JTextField();
		textFieldLogin.setText("Login");
		textFieldLogin.setForeground(Color.GRAY);
		textFieldLogin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if("Login".equals(textFieldLogin.getText())) {
					textFieldLogin.setForeground(Color.BLACK);
					textFieldLogin.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(textFieldLogin.getText())) {
					textFieldLogin.setForeground(Color.GRAY);
					textFieldLogin.setText("Login");
				}
			}
		});
		textFieldLogin.setBounds(200, 403, 193, 22);
		textFieldLogin.setColumns(10);
		contentPane.add(textFieldLogin);


		//Titulo
		lblTitulo = new JLabel("Tiet\u00EA Solutions");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblTitulo.setBounds(149, 85, 298, 35);
		contentPane.add(lblTitulo);

		
		//Logo
		JLabel lblLogo = new JLabel(imagem);
		lblLogo.setBounds(97, 150, 420, 229);
		contentPane.add(lblLogo);

		
		//TextAreaGambi
		JTextArea textAreaGambi = new JTextArea();
		textAreaGambi.setBackground(new Color(46,84,143));
		textAreaGambi.setEditable(false);
		textAreaGambi.setBounds(69, 13, 4, 22);
		contentPane.add(textAreaGambi);

		
		//botão conectar
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(248, 482, 97, 25);
		contentPane.add(btnConectar);



	}
}
