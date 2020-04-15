package JanelaLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class JanelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JTextField textFieldSenha;
	private ImageIcon imagem = new ImageIcon(getClass().getResource("logoaps.png"));
	private JLabel lblNewLabelTitulo;
	

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
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
		
		textFieldLogin = new JTextField();
		textFieldLogin.setForeground(Color.GRAY);
		textFieldLogin.setText("Login");
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
		contentPane.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		textFieldSenha = new JTextField();
		textFieldSenha.setForeground(Color.GRAY);
		textFieldSenha.setText("Senha");
		textFieldSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if("Senha".equals(textFieldSenha.getText())) {
					textFieldSenha.setForeground(Color.BLACK);
					textFieldSenha.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if("".equals(textFieldSenha.getText())) {
					textFieldSenha.setForeground(Color.GRAY);
					textFieldSenha.setText("Senha");
				}
			}
		});
		textFieldSenha.setBounds(200, 438, 193, 22);
		contentPane.add(textFieldSenha);
		textFieldSenha.setColumns(10);
		
		JLabel lblNewLabelLogo = new JLabel(imagem);
		lblNewLabelLogo.setBounds(97, 150, 420, 229);
		contentPane.add(lblNewLabelLogo);
		
		lblNewLabelTitulo = new JLabel("Tiet\u00EA Solutions");
		lblNewLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelTitulo.setForeground(Color.WHITE);
		lblNewLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabelTitulo.setBounds(149, 85, 298, 35);
		contentPane.add(lblNewLabelTitulo);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(248, 482, 97, 25);
		contentPane.add(btnConectar);
	}
}
