package JanelaChat;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.aps.dominio.enums.Comandos;
import com.aps.resources.PrincipalCliente;
import java.awt.Font;

public class JanelaArquivos extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private PrincipalCliente cliConect;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaArquivos frame = new JanelaArquivos(12346);
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
	public JanelaArquivos(int porta) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 384);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46,84,143));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPagAnt = new JButton("<<<");
		btnPagAnt.setBounds(50, 285, 90, 23);
		contentPane.add(btnPagAnt);
		
		JLabel lblNewLabel = new JLabel("0/0");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 285, 50, 23);
		contentPane.add(lblNewLabel);

		JButton btnPagSeg = new JButton(">>>");
		btnPagSeg.setBounds(210, 285, 90, 23);
		contentPane.add(btnPagSeg);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(350, 285, 90, 23);
		contentPane.add(btnSair);
		
		JLabel lblQuemEnviou[] = new JLabel[8];
		JLabel[] lblArquivoNome = new JLabel[8];
		JLabel[] lblData = new JLabel[8];
		JButton[] btnBaixar = new JButton[8];

		for (int x = 0; x < lblArquivoNome.length; x++) {
			lblQuemEnviou[x] = new JLabel("por: Nome" + x);
			lblQuemEnviou[x].setBounds(5, 0 + (35 * x), 125, 30);
			contentPane.add(lblQuemEnviou[x]);
			
			lblArquivoNome[x] = new JLabel("Nome"+ x + ".pdf");
			lblArquivoNome[x].setHorizontalAlignment(SwingConstants.CENTER);
			lblArquivoNome[x].setBounds(130, 0 + (35 * x), 100, 30);
			contentPane.add(lblArquivoNome[x]);

		
			lblData[x] = new JLabel("01/01/0000");
			lblData[x].setHorizontalAlignment(SwingConstants.CENTER);
			lblData[x].setBounds(250, 0 + (35 * x), 100 , 30);
			contentPane.add(lblData[x]);
		
		
			btnBaixar[x] = new JButton("Baixar");
			btnBaixar[x].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnBaixar[x].setBounds(360, 0 + (35 * x), 75, 30);
			contentPane.add(btnBaixar[x]);
		
			cliConect = new PrincipalCliente();
			conectar();
		}
		
	}
	private void conectar() {
		try {
			cliConect.conectar(12346, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void escutar() throws IOException{
		InputStream in = cliConect.getSocket().getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bfr = new BufferedReader(inr);
		String msg = "";
		String ultimaMsg = "-1";
		while(!Comandos.SAIR.getCodigo().equalsIgnoreCase(msg))
			if(bfr.ready()){
				msg = bfr.readLine();
				if(msg.equals("Sair")){
				}else if(msg.contains(Comandos.ENVIAR_ARQUIVO.getCodigo())) {
				}else if(msg.contains(Comandos.UPAR_MENSAGENS.getCodigo())) {
				}else if(msg.equals("") || msg != null || msg.equals(ultimaMsg)) {
					System.out.println("MENSAGEM RECEBIDA FOI: " + msg);
					ultimaMsg = msg;
				}
					
			}
	}

}
