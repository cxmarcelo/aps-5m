package JanelaSalas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.aps.controler.Decodificadores;
import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;
import com.aps.resources.PrincipalCliente;

import JanelaLogin.JanelaLogin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class JanelaUsuario extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textEmail;
	private JLabel textLogin;
	private JPasswordField passwordSenhaAtual;
	private JPasswordField passwordNova;
	private JPasswordField passwordDelete;
	private PrincipalCliente servConect;
	private Usuario user;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario a = new Usuario("testeLogin", "senha123", "NomeTeste", "teste@gmail.com");
					JanelaUsuario frame = new JanelaUsuario(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaUsuario(Usuario usuario) {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46,84,143));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitulo = new JLabel("Configura\u00E7\u00E3o do Usu\u00E1rio");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(160, 11, 274, 24);
		contentPane.add(lblTitulo);

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNome.setBounds(10, 131, 46, 14);
		contentPane.add(lblNome);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmail.setBounds(10, 156, 46, 14);
		contentPane.add(lblEmail);

		JLabel lblSenha = new JLabel("Senha Atual:");
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSenha.setBounds(10, 222, 86, 24);
		contentPane.add(lblSenha);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		btnSalvar.setBounds(352, 127, 121, 23);
		contentPane.add(btnSalvar);

		textNome = new JTextField();
		textNome.setBounds(82, 128, 246, 20);
		contentPane.add(textNome);
		textNome.setColumns(10);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(82, 156, 246, 20);
		contentPane.add(textEmail);

		JLabel lblSenhaNova = new JLabel("Senha nova:");
		lblSenhaNova.setForeground(Color.WHITE);
		lblSenhaNova.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSenhaNova.setBounds(10, 259, 86, 24);
		contentPane.add(lblSenhaNova);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLogin.setBounds(10, 104, 46, 14);
		contentPane.add(lblLogin);

		textLogin = new JLabel();
		textLogin.setForeground(Color.WHITE);
		textLogin.setBounds(82, 97, 220, 20);
		contentPane.add(textLogin);

		passwordSenhaAtual = new JPasswordField();
		passwordSenhaAtual.setBounds(108, 223, 220, 24);
		contentPane.add(passwordSenhaAtual);

		passwordNova = new JPasswordField();
		passwordNova.setBounds(108, 260, 220, 22);
		contentPane.add(passwordNova);

		JButton btnAlterarSenha = new JButton("Alterar Senha");
		btnAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarSenha();
			}
		});
		btnAlterarSenha.setBounds(352, 243, 121, 23);
		contentPane.add(btnAlterarSenha);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JanelaSalas(user).setVisible(true);
				sair();
				dispose();
			}
		});
		btnVoltar.setBounds(485, 14, 89, 23);
		contentPane.add(btnVoltar);

		JLabel lblAltDados = new JLabel("Alterar Dados");
		lblAltDados.setForeground(Color.WHITE);
		lblAltDados.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAltDados.setBounds(82, 61, 220, 14);
		contentPane.add(lblAltDados);

		JLabel lblAltSenha = new JLabel("Alterar Senha");
		lblAltSenha.setForeground(Color.WHITE);
		lblAltSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAltSenha.setBounds(82, 195, 220, 14);
		contentPane.add(lblAltSenha);

		JButton btnDelete = new JButton("Deletar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDelete.setBounds(352, 336, 89, 23);
		contentPane.add(btnDelete);

		JLabel lblExcluirConta = new JLabel("Excluir Conta");
		lblExcluirConta.setForeground(Color.WHITE);
		lblExcluirConta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblExcluirConta.setBounds(82, 308, 220, 14);
		contentPane.add(lblExcluirConta);

		JLabel lblSenhaDelete = new JLabel("Senha:");
		lblSenhaDelete.setForeground(Color.WHITE);
		lblSenhaDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSenhaDelete.setBounds(10, 343, 46, 14);
		contentPane.add(lblSenhaDelete);

		passwordDelete = new JPasswordField();
		passwordDelete.setBounds(82, 335, 246, 24);
		contentPane.add(passwordDelete);

		addWindowListener(new WindowAdapter() { 
			public void windowClosing(WindowEvent evt){ 
				try {
					sair();
					System.exit(0);
				} catch (Exception e) {
					System.exit(0);
				}
			} 
		});


		servConect = new PrincipalCliente();
		user = usuario;
		conectarServer();

		upDados();
	}

	private void upDados() {
		textNome.setText(user.getNome());
		textLogin.setText(user.getLogin());
		textEmail.setText(user.getEmail());
	}


	private void alterarSenha() {
		String senha = new String(passwordSenhaAtual.getPassword());
		if(senha.equals(user.getSenha())) {
			user.setSenha(new String(passwordNova.getPassword()));
			String msg = Comandos.ATUALIZAR_SENHA_USUARIO.getCodigo() +  Comandos.SEPARAR_DADOS.getCodigo() + Decodificadores.usuarioToMsg(user);
			try {
				servConect.enviarMensagem(msg);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Erro para enviar mensagem de alterar senha para o servidor");
			}
		}else {
			JOptionPane.showMessageDialog(null, "Senha incorreta");
		}
	}

	private void salvar() {
		String nome, email;
		nome = textNome.getText();
		email = textNome.getText();
		if(nome.equals("") || nome == null) {
			JOptionPane.showMessageDialog(null, "Nome inválido");
			return;
		}
		if(email.equals("") || email == null) {
			JOptionPane.showMessageDialog(null, "Nome inválido");
			return;
		}
		try {
			user.setNome(nome);
			user.setEmail(email);
			String msg = "";
			msg = Comandos.ATUALIZAR_DADOS_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + Decodificadores.usuarioToMsg(user);
			System.out.println(msg);
			servConect.enviarMensagem(msg);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro salvar dados");
			e.printStackTrace();
		}
	}

	public void deletar(){
		if(new String(passwordDelete.getPassword()).equals(user.getSenha())) {
			String msg = "";
			msg = Comandos.DELETAR_USUARIO.getCodigo() + Comandos.SEPARAR_DADOS.getCodigo() + user.getLogin() + Comandos.SEPARAR_DADOS.getCodigo() + new String(passwordDelete.getPassword());
			try {
				servConect.enviarMensagem(msg);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Erro ao enviar mensagem de deletar");
			}
		}else {
			JOptionPane.showMessageDialog(null,"Senha Incorreta" );
		}
	}

	private void sair() {
		String msg = Comandos.SAIR.getCodigo();
		try {
			servConect.enviarMensagem(msg);
			servConect.sair();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void conectarServer() {
		try {
			servConect.conectar(12345, user);
			new Thread() {
				public void run() {
					try {
						escutar();
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Erro para ouvir resposta JanelaUsuario");
					}
				}
			}.start();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Não foi possível conectar ao servidor");
			try {
				servConect.sair();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


	private void escutar() throws IOException{
		InputStream in = servConect.getSocket().getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";
		while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg))
			if(bfr.ready()){
				msg = bfr.readLine();
				if(msg.equals("Sair")){

				}else if(msg.contains(Comandos.ATUALIZAR_DADOS_USUARIO.getCodigo())) {
					String[] retorno = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
					if(retorno.length >= 2) {
						if(retorno[1].contains(Comandos.RETORNO_TRUE.getCodigo())) {
							JOptionPane.showMessageDialog(null, "Dados Atualizados");
						}else {
							JOptionPane.showMessageDialog(null, "Falha ao salvar os dados tente novamente");
						}
					}

				}else if(msg.contains(Comandos.ATUALIZAR_SENHA_USUARIO.getCodigo())) {
					String[] retorno = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
					if(retorno.length >= 2) {
						if(retorno[1].contains(Comandos.RETORNO_TRUE.getCodigo())) {
							JOptionPane.showMessageDialog(null, "Senha alterada");
						}else {
							JOptionPane.showMessageDialog(null, "Falha ao trocar a senha");
						}
					}
				}else if(msg.contains(Comandos.DELETAR_USUARIO.getCodigo())) {
					String[] retorno = msg.split(Comandos.SEPARAR_DADOS.getCodigo());
					if(retorno.length >= 2) {
						if(retorno[1].contains(Comandos.RETORNO_TRUE.getCodigo())) {
							new JanelaLogin().setVisible(true);;
							sair();
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Falha ao deletar a conta, solicitar ao ADM caso erro continue");
						}
					}
				}
			}
	}
}
