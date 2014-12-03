package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Compromissos extends JFrame {
	
	private JLabel jlbCompromissos = new JLabel("Compromissos");
	private JLabel jlbCompromisso = new JLabel("Compromisso");
	private JLabel jlbAgenda = new JLabel("Agenda para");
	private JTextField jtfCompromisso = new JTextField();
	private JTextField jtfData = new JTextField();
	private JTextField jtfDiaMes = new JTextField();
	private JRadioButton jrbData = new JRadioButton("Data");
	private JRadioButton jrbDiaMes = new JRadioButton("Dia do mês");
	private JRadioButton jrbDiaSemana = new JRadioButton("Dia da Semana");
	private JComboBox<String> jcbDiaSemana = new JComboBox<>();
	
	private JButton jbtCadastrarCompromisso = new JButton("Cadastrar"),
			jbtSair = new JButton("Sair");
	
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}
	 public Compromissos() {
		setLayout(null);
		
		jlbCompromissos.setFont(new Font("Arial", Font.BOLD, 24));
		jlbCompromissos.setForeground(Color.DARK_GRAY);		
		posicionaObjeto(jlbCompromissos , 100, 15, 500, 25);
		
		
		
		posicionaObjeto(jlbCompromisso, 70, 75, 200, 25);		
		posicionaObjeto(jlbAgenda, 140, 120, 200, 25);
		posicionaObjeto(jtfCompromisso, 160, 75,150,25);
		posicionaObjeto(jrbData, 75, 160, 55, 25);
		posicionaObjeto(jtfData, 160, 160, 150, 25);
		
		ButtonGroup btgEscolha=new ButtonGroup();
		btgEscolha.add(jrbData);
		btgEscolha.add(jrbDiaMes);
		btgEscolha.add(jrbDiaSemana);
		
		
		posicionaObjeto(jrbDiaMes, 75, 190, 90, 25);
		posicionaObjeto(jrbDiaSemana, 75, 220, 110, 25);
		posicionaObjeto(jtfDiaMes, 190, 190, 120, 25);
		posicionaObjeto(jcbDiaSemana, 200,  220, 110, 25);
		jcbDiaSemana.addItem("Domingo");
		jcbDiaSemana.addItem("Segunda-Feira");
		jcbDiaSemana.addItem("Terça-Feira");
		jcbDiaSemana.addItem("Quarta-Feira");
		jcbDiaSemana.addItem("Quinta-Feira");
		jcbDiaSemana.addItem("Sexta-Feira");
		jcbDiaSemana.addItem("Sabado");
		
		
		
		
		

		
		
		posicionaObjeto(jbtCadastrarCompromisso, 90, 280, 100, 30);
		posicionaObjeto(jbtSair, 230, 280, 80, 20);
		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		
		
		
	
		setTitle("Compromissos");
		setSize(400, 370);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Compromissos();

	}

}
