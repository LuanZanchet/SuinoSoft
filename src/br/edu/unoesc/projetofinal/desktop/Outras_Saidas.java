package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Outras_Saidas extends JFrame {
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbAutoConsumo = new JRadioButton("Auto Consumo");
	private JRadioButton jrbDoacao = new JRadioButton("Doação");
	private JLabel jlbOutrasSaidas = new JLabel("Outras Saidas ");
	private JLabel jlbLote = new JLabel("Lote ");
	private JLabel jlbData = new JLabel("Data ");
	private JLabel jlbQuantidade = new JLabel("Quantidade");
	private JLabel jlbPesoTotal = new JLabel("Peso Total ");
	private JTextField jtfLote = new JTextField();
	private JTextField jtfData = new JTextField();
	private JTextField jtfQuantidade = new JTextField();
	private JTextField jtfPesoTotal = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Outras_Saidas() {
		setLayout(null);

		jlbOutrasSaidas.setFont(new Font("Arial", Font.BOLD, 18));
		jlbOutrasSaidas.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbOutrasSaidas, 110, 15, 500, 25);

		posicionaObjeto(jrbAutoConsumo, 55, 75, 120, 25);
		posicionaObjeto(jrbDoacao, 185, 75, 150, 25);
		posicionaObjeto(jlbLote, 95, 115, 110, 25);
		posicionaObjeto(jtfLote, 130, 115, 200, 25);
		posicionaObjeto(jlbData, 95, 145, 110, 25);
		posicionaObjeto(jtfData, 130, 145, 200, 25);
		posicionaObjeto(jlbQuantidade, 55, 175, 200, 25);
		posicionaObjeto(jtfQuantidade, 130, 175, 200, 25);
		posicionaObjeto(jlbPesoTotal, 60, 205, 200, 25);
		posicionaObjeto(jtfPesoTotal, 130, 205, 200, 25);
		posicionaObjeto(jbtCadastrar, 30, 265, 150, 35);
		posicionaObjeto(jbtSair, 200, 265, 120, 20);
		btgEscolha.add(jrbAutoConsumo);
		btgEscolha.add(jrbDoacao);

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Outras Saidas");
		setSize(420, 400);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Outras_Saidas();

	}

}
