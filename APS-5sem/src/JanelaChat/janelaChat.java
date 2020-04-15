package JanelaChat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DropMode;
import java.awt.Window.Type;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class janelaChat extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					janelaChat frame = new janelaChat();
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
	public janelaChat() {
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
		btnEnviar.setBounds(453, 400, 97, 38);
		contentPane.add(btnEnviar);
		
		JButton btnNewButton = new JButton("Anexar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(453, 448, 97, 38);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar as Salas");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(291, 509, 150, 31);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNomeDoInfeliz = new JLabel("Nome do infeliz");
		lblNomeDoInfeliz.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNomeDoInfeliz.setForeground(Color.WHITE);
		lblNomeDoInfeliz.setBounds(32, 16, 518, 21);
		contentPane.add(lblNomeDoInfeliz);
		
		JTextArea textArea = new JTextArea();	
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textArea.setForeground(Color.GRAY);
		textArea.setText("Escreva...");
		textArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if("Escreva...".equals(textArea.getText())) {
					textArea.setForeground(Color.BLACK);
					textArea.setText("");
				}
			}
			@Override
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
}
