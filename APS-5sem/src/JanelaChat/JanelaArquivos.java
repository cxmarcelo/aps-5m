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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import com.aps.controler.Decodificadores;
import com.aps.dominio.ArquivoDTO;
import com.aps.dominio.enums.Comandos;
import com.aps.resources.PrincipalCliente;
import java.awt.Font;

public class JanelaArquivos extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private PrincipalCliente cliConect;
	private ArrayList<ArquivoDTO> listaArquivos = new ArrayList<ArquivoDTO>();
	JLabel lblQuemEnviou[];
	JLabel[] lblArquivoNome;
	JLabel[] lblData;
	JButton[] btnBaixar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalCliente a = new PrincipalCliente();
					a.conectar(12346, null);
					JanelaArquivos frame = new JanelaArquivos(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param servConect 
	 */
	public JanelaArquivos(PrincipalCliente servConect) {
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
		
		lblQuemEnviou = new JLabel[8];
		lblArquivoNome = new JLabel[8];
		lblData = new JLabel[8];
		btnBaixar = new JButton[8];

		for (int x = 0; x < lblArquivoNome.length; x++) {
			lblQuemEnviou[x] = new JLabel("" + x);
			lblQuemEnviou[x].setBounds(5, 0 + (35 * x), 125, 30);
			contentPane.add(lblQuemEnviou[x]);
			
			lblArquivoNome[x] = new JLabel(""+ x + "");
			lblArquivoNome[x].setHorizontalAlignment(SwingConstants.CENTER);
			lblArquivoNome[x].setBounds(130, 0 + (35 * x), 100, 30);
			contentPane.add(lblArquivoNome[x]);

		
			lblData[x] = new JLabel("");
			lblData[x].setHorizontalAlignment(SwingConstants.CENTER);
			lblData[x].setBounds(250, 0 + (35 * x), 100 , 30);
			contentPane.add(lblData[x]);
		
		
			btnBaixar[x] = new JButton("Baixar");
			btnBaixar[x].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnBaixar[x].setVisible(false);
			btnBaixar[x].setBounds(360, 0 + (35 * x), 75, 30);
			contentPane.add(btnBaixar[x]);
		}
		
		
		cliConect = servConect; 
		ouvir();
	}

	
	private void ouvir() {
		new Thread() {
			public void run() {
				try {
					escutar();
				} catch (IOException e) {
					System.out.println("Erro para ouvir mensagens do servidor /JanelaArquivos");
					e.printStackTrace();
				}
			}
		}.start();
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
					//sair
					
				}else if(msg.contains(Comandos.RETORNAR_ARQUIVO.getCodigo())) {
					
					//Baixar o arquivo;
					//listaArquivos = Decodificadores.
					
					
				}else if(msg.contains(Comandos.TODOS_ARQUIVOS_NOMES.getCodigo())) {
					//arraylist nomes arquivos(dto)
					listaArquivos = Decodificadores.msgToListaArquivosDTO(msg);
					uparArquivos();
					
				}else if(msg.equals("") || msg != null || msg.equals(ultimaMsg)) {
					System.out.println("MENSAGEM RECEBIDA FOI: " + msg);
					ultimaMsg = msg;
				}
					
			}
	}

	
	private void uparArquivos() {
		for (int x = 0; x < btnBaixar.length; x++) {
			try {
				ArquivoDTO aux = listaArquivos.get(x);
				lblQuemEnviou[x].setText(aux.getNomeRemetente());
				lblArquivoNome[x].setText(aux.getNomeArquivo());
				lblData[x].setText(aux.getDiaMes());
				btnBaixar[x].setVisible(true);
			} catch (Exception e) {
				lblQuemEnviou[x].setText("");
				lblArquivoNome[x].setText("");
				lblData[x].setText("");
				btnBaixar[x].setVisible(false);
			}
		}
	}
	
}
