package JanelaLogin;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;
import com.aps.resources.Cliente;
import com.aps.resources.PrincipalCliente;

import JanelaSalas.JanelaSalas;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaLogin extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passField;
	private ImageIcon imagem = new ImageIcon(getClass().getResource("../com/aps/imgs/logoaps.png"));
	private JLabel lblTitulo;
	private JLabel lblMensagem;
	private PrincipalCliente cliConect;

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
		contentPane.setLayout(null);
		setContentPane(contentPane);

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
		textFieldLogin.setBounds(200, 403, 193, 22);
		textFieldLogin.setColumns(10);
		contentPane.add(textFieldLogin);


		//Titulo
		lblTitulo = new JLabel("Tiet\u00EA Solutions");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblTitulo.setBounds(149, 25, 298, 35);
		contentPane.add(lblTitulo);


		//Logo
		JLabel lblLogo = new JLabel(imagem);
		lblLogo.setBounds(97, 120, 420, 229);
		contentPane.add(lblLogo);


		//TextAreaGambi
		JTextArea textAreaGambi = new JTextArea();
		textAreaGambi.setBackground(new Color(46,84,143));
		textAreaGambi.setEditable(false);
		textAreaGambi.setBounds(69, 13, 4, 22);
		contentPane.add(textAreaGambi);

		//bot�o conectar
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnConectar.setBounds(248, 482, 97, 25);
		contentPane.add(btnConectar);


		lblMensagem = new JLabel("");
		lblMensagem.setForeground(Color.RED);
		lblMensagem.setBounds(200, 360, 193, 22);
		contentPane.add(lblMensagem);
		//cliConect = new PrincipalCliente();
		//conectarServer();
	}


	private void conectarServer() {
		try {
			cliConect.conectar();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "N�o foi poss�vel conectar ao servidor");
			try {
				cliConect.sair();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void escutar() throws IOException{

		InputStream in = cliConect.getSocket().getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";

		while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg))

			if(bfr.ready()){
				msg = bfr.readLine();
				//dividir msg
				if(msg.equals(Comandos.RETORNO_AUTENTICACAO.getCodigo())) {
					Usuario user = new Usuario();
					//pegar informa��es do servidor que estar� no retorno
					
					new JanelaSalas(user).setVisible(true);
					dispose();
				}
				else {
					//erro para logar
				}
			}
	}


	/*
	private void conectar() {

		if(xxx.autenticar(textFieldLogin.getText(), passField.getPassword().toString())) {
			//chamar outra fun��o no servidor que retornar um usuario
			//telalogado(usuario)
			//dispose();
		}else {
			lblMensagem.setText("Us�ario ou senha inv�lido");
			passField.setText("");
		}
	}
	 */
}
