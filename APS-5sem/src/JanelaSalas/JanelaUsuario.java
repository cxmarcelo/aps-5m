package JanelaSalas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class JanelaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private JTextField textLogin;
	private JPasswordField passwordSenhaAtual;
	private JPasswordField passwordField_1;
	private JPasswordField passwordDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaUsuario frame = new JanelaUsuario();
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
	public JanelaUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Configura\u00E7\u00E3o do Usu\u00E1rio");
		lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setBounds(162, 11, 220, 24);
		contentPane.add(lblTitulo);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10, 131, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 156, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha Atual");
		lblSenha.setBounds(10, 206, 69, 24);
		contentPane.add(lblSenha);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(329, 122, 121, 23);
		contentPane.add(btnSalvar);
		
		textNome = new JTextField();
		textNome.setBounds(82, 128, 220, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(82, 156, 220, 20);
		contentPane.add(textEmail);
		
		JLabel lblSenhaNova = new JLabel("Senha nova");
		lblSenhaNova.setBounds(10, 241, 69, 24);
		contentPane.add(lblSenhaNova);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(10, 104, 46, 14);
		contentPane.add(lblLogin);
		
		textLogin = new JTextField();
		textLogin.setColumns(10);
		textLogin.setBounds(82, 97, 220, 20);
		contentPane.add(textLogin);
		
		passwordSenhaAtual = new JPasswordField();
		passwordSenhaAtual.setBounds(82, 206, 220, 24);
		contentPane.add(passwordSenhaAtual);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(82, 241, 220, 22);
		contentPane.add(passwordField_1);
		
		JButton btnAlterarSenha = new JButton("Alterar Senha");
		btnAlterarSenha.setBounds(329, 222, 121, 23);
		contentPane.add(btnAlterarSenha);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(485, 14, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblAltDados = new JLabel("Alterar Dados");
		lblAltDados.setBounds(82, 61, 220, 14);
		contentPane.add(lblAltDados);
		
		JLabel lblAltSenha = new JLabel("Alterar Senha");
		lblAltSenha.setBounds(82, 181, 220, 14);
		contentPane.add(lblAltSenha);
		
		JButton btnDelete = new JButton("Deletar");
		btnDelete.setBounds(329, 334, 89, 23);
		contentPane.add(btnDelete);
		
		JLabel lblExcluirConta = new JLabel("Excluir Conta");
		lblExcluirConta.setBounds(82, 296, 220, 14);
		contentPane.add(lblExcluirConta);
		
		JLabel lblSenhaDelete = new JLabel("Senha");
		lblSenhaDelete.setBounds(10, 343, 46, 14);
		contentPane.add(lblSenhaDelete);
		
		passwordDelete = new JPasswordField();
		passwordDelete.setBounds(82, 335, 220, 24);
		contentPane.add(passwordDelete);
	}
}
