package JanelaSalas;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.border.LineBorder;

import com.aps.dominio.Usuario;
import com.aps.dominio.enums.Comandos;

import JanelaChat.JanelaChat;
import JanelaLogin.JanelaLogin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaSalas extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private ImageIcon imagemPoluidoresRio = new ImageIcon(getClass().getResource("../com/aps/imgs/Mundo.png"));
	private ImageIcon imagemEsgoto = new ImageIcon(getClass().getResource("../com/aps/imgs/Esgoto.png"));
	private ImageIcon imagemLimpeza = new ImageIcon(getClass().getResource("../com/aps/imgs/Limpeza.png"));
	private ImageIcon imagemUrbana = new ImageIcon(getClass().getResource("../com/aps/imgs/Urbana.png"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuario user = new Usuario("erickson199?", "Erickson123", "Erick", null);
					JanelaSalas frame = new JanelaSalas(user);
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
	public JanelaSalas(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(new Color(46,84,143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextArea txtrPoluidoresRio = new JTextArea();
		txtrPoluidoresRio.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
		txtrPoluidoresRio.setForeground(Color.WHITE);
		txtrPoluidoresRio.setBackground(new Color(46,84,143));
		txtrPoluidoresRio.setLineWrap(true);
		txtrPoluidoresRio.setWrapStyleWord(true);
		txtrPoluidoresRio.setEditable(false);
		txtrPoluidoresRio.setText("Ambiente para descutir quais os poluidores do rio tiet\u00EA.");
		txtrPoluidoresRio.setBounds(114, 161, 212, 63);
		contentPane.add(txtrPoluidoresRio);

		JTextArea txtrEstacaoLimpeza = new JTextArea();
		txtrEstacaoLimpeza.setText("Ambiente para organiza\u00E7\u00E3o de pontos para esta\u00E7\u00F5es de limpeza.");
		txtrEstacaoLimpeza.setForeground(Color.WHITE);
		txtrEstacaoLimpeza.setBackground(new Color(46,84,143));
		txtrEstacaoLimpeza.setLineWrap(true);
		txtrEstacaoLimpeza.setWrapStyleWord(true);
		txtrEstacaoLimpeza.setEditable(false);
		txtrEstacaoLimpeza.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
		txtrEstacaoLimpeza.setBounds(114, 394, 212, 63);
		contentPane.add(txtrEstacaoLimpeza);

		JTextArea txtrRemanejamentoEsgoto = new JTextArea();
		txtrRemanejamentoEsgoto.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
		txtrRemanejamentoEsgoto.setForeground(Color.WHITE);
		txtrRemanejamentoEsgoto.setBackground(new Color(46,84,143));
		txtrRemanejamentoEsgoto.setLineWrap(true);
		txtrRemanejamentoEsgoto.setWrapStyleWord(true);
		txtrRemanejamentoEsgoto.setEditable(false);
		txtrRemanejamentoEsgoto.setText("Ambiente para descutir formas de remanejar o esgoto.");
		txtrRemanejamentoEsgoto.setBounds(515, 161, 212, 63);
		contentPane.add(txtrRemanejamentoEsgoto);

		JTextArea txtrUrbana = new JTextArea();
		txtrUrbana.setText("Ambiente para elabora\u00E7\u00E3o de a\u00E7\u00F5es para concientiza\u00E7\u00E3o urbana.");
		txtrUrbana.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 13));
		txtrUrbana.setForeground(Color.WHITE);
		txtrUrbana.setBackground(new Color(46,84,143));
		txtrUrbana.setLineWrap(true);
		txtrUrbana.setWrapStyleWord(true);
		txtrUrbana.setEditable(false);
		txtrUrbana.setBounds(515, 394, 212, 63);
		contentPane.add(txtrUrbana);

		JLabel lblLogoPoluidoresRio = new JLabel(imagemPoluidoresRio);
		lblLogoPoluidoresRio.setBounds(34, 161, 68, 55);
		contentPane.add(lblLogoPoluidoresRio);

		JLabel lblLogoEstacoesLimpeza = new JLabel(imagemLimpeza);
		lblLogoEstacoesLimpeza.setBounds(34, 402, 68, 55);
		contentPane.add(lblLogoEstacoesLimpeza);

		JLabel lblLogoEsgoto = new JLabel(imagemEsgoto);
		lblLogoEsgoto.setBounds(434, 161, 68, 55);
		contentPane.add(lblLogoEsgoto);

		JLabel lblLogoUrbana = new JLabel(imagemUrbana);
		lblLogoUrbana.setBounds(434, 402, 68, 55);
		contentPane.add(lblLogoUrbana);

		JLabel lblTituloPoluidoresRio = new JLabel("Poluidores do Rio");
		lblTituloPoluidoresRio.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloPoluidoresRio.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTituloPoluidoresRio.setForeground(Color.WHITE);
		lblTituloPoluidoresRio.setBounds(46, 97, 280, 25);
		contentPane.add(lblTituloPoluidoresRio);

		JLabel lblTituloRemanejamentoEsgoto = new JLabel("Remanejamento do esgoto");
		lblTituloRemanejamentoEsgoto.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTituloRemanejamentoEsgoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloRemanejamentoEsgoto.setForeground(Color.WHITE);
		lblTituloRemanejamentoEsgoto.setBounds(446, 97, 281, 25);
		contentPane.add(lblTituloRemanejamentoEsgoto);

		JLabel lblTituloEstacoes = new JLabel("Mini-esta\u00E7\u00F5es de limpeza");
		lblTituloEstacoes.setForeground(Color.WHITE);
		lblTituloEstacoes.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTituloEstacoes.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloEstacoes.setBounds(46, 330, 280, 25);
		contentPane.add(lblTituloEstacoes);

		JLabel lblTituloUrbana = new JLabel("Concientiza\u00E7\u00E3o urbana");
		lblTituloUrbana.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTituloUrbana.setForeground(Color.WHITE);
		lblTituloUrbana.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloUrbana.setBounds(446, 330, 281, 25);
		contentPane.add(lblTituloUrbana);

		JLabel lblSalas = new JLabel("Salas");
		lblSalas.setBounds(215, 13, 361, 44);
		lblSalas.setForeground(Color.WHITE);
		lblSalas.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblSalas.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblSalas);

		JButton btnPoluidoresRio = new JButton("Entrar");
		btnPoluidoresRio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				entrarEmSala(1,  usuario);
			}
		});
		btnPoluidoresRio.setBounds(149, 271, 97, 25);
		contentPane.add(btnPoluidoresRio);

		JButton btnRemanejamentoEsgoto = new JButton("Entrar");
		btnRemanejamentoEsgoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrarEmSala(2,  usuario);
			}
		});
		btnRemanejamentoEsgoto.setBounds(544, 271, 97, 25);
		contentPane.add(btnRemanejamentoEsgoto);

		JButton btnUrbana = new JButton("Entrar");
		btnUrbana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrarEmSala(3,  usuario);
			}
		});
		btnUrbana.setBounds(544, 507, 97, 25);
		contentPane.add(btnUrbana);

		JButton btnEstacoes = new JButton("Entrar");
		btnEstacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrarEmSala(4,  usuario);
			}
		});
		btnEstacoes.setBounds(149, 507, 97, 25);
		contentPane.add(btnEstacoes);

		Box TextBoxPoluidores = Box.createHorizontalBox();
		TextBoxPoluidores.setBorder(new LineBorder(new Color(0, 0, 0)));
		TextBoxPoluidores.setBounds(34, 148, 304, 83);
		contentPane.add(TextBoxPoluidores);

		Box TextBoxRemanejamento = Box.createHorizontalBox();
		TextBoxRemanejamento.setBorder(new LineBorder(new Color(0, 0, 0)));
		TextBoxRemanejamento.setBounds(426, 148, 312, 83);
		contentPane.add(TextBoxRemanejamento);

		Box ContainerPoluidores = Box.createHorizontalBox();
		ContainerPoluidores.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContainerPoluidores.setBounds(12, 86, 376, 229);
		contentPane.add(ContainerPoluidores);

		Box ContainerRemanejamento = Box.createHorizontalBox();
		ContainerRemanejamento.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContainerRemanejamento.setBounds(400, 86, 367, 229);
		contentPane.add(ContainerRemanejamento);

		Box TextBoxEstacoes = Box.createHorizontalBox();
		TextBoxEstacoes.setBorder(new LineBorder(new Color(0, 0, 0)));
		TextBoxEstacoes.setBounds(34, 382, 304, 83);
		contentPane.add(TextBoxEstacoes);

		Box ContainerEstacoes = Box.createHorizontalBox();
		ContainerEstacoes.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		ContainerEstacoes.setBounds(12, 323, 376, 229);
		contentPane.add(ContainerEstacoes);

		Box TextBoxUrbana = Box.createHorizontalBox();
		TextBoxUrbana.setBorder(new LineBorder(new Color(0, 0, 0)));
		TextBoxUrbana.setBounds(434, 386, 312, 83);
		contentPane.add(TextBoxUrbana);

		Box ContainerUrbana = Box.createHorizontalBox();
		ContainerUrbana.setBorder(new LineBorder(new Color(0, 0, 0)));
		ContainerUrbana.setBounds(400, 323, 367, 229);
		contentPane.add(ContainerUrbana);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaLogin().setVisible(true);
				dispose();
			}
		});
		btnSair.setForeground(Color.RED);
		btnSair.setBounds(699, 29, 68, 25);
		contentPane.add(btnSair);

		JLabel lblNomeUsuario = new JLabel(usuario.getNome());
		lblNomeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNomeUsuario.setForeground(Color.WHITE);
		lblNomeUsuario.setBounds(12, 11, 90, 25);
		contentPane.add(lblNomeUsuario);

		//Funções Eventos Mouse Entered/Exited
		//Containers
		ContainerRemanejamento.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				TextBoxRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		ContainerPoluidores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				TextBoxPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		ContainerEstacoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				TextBoxEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		ContainerUrbana.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				TextBoxUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		//TXTBOX
		txtrRemanejamentoEsgoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				TextBoxRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		txtrPoluidoresRio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				TextBoxPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		txtrEstacaoLimpeza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				TextBoxEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		txtrUrbana.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
				TextBoxUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		//Button
		btnRemanejamentoEsgoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerRemanejamento.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
				TextBoxRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxRemanejamento.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		btnPoluidoresRio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerPoluidores.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
				TextBoxPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxPoluidores.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		btnEstacoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerEstacoes.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
				TextBoxEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxEstacoes.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});

		btnUrbana.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ContainerUrbana.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
				TextBoxUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ContainerUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
				TextBoxUrbana.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			}
		});
	}

	private void entrarEmSala(int sala, Usuario us) {
		switch (sala) {
		case 1:
			new JanelaChat(us, 12346).setVisible(true);
			dispose();
			break;

			
		case 2:
			new JanelaChat(us, 12347).setVisible(true);
			dispose();
			break;
			
		case 3:
			new JanelaChat(us, 12348).setVisible(true);
			dispose();
			break;
		case 4:
			new JanelaChat(us, 12349).setVisible(true);
			dispose();
			break;
			
		default:
			JOptionPane.showMessageDialog(null, "Erro ao entrar na sala");
			break;
		}
	}
}
