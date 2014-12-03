package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MorteMachoDAO;
import br.edu.unoesc.projetofinal.dao.MorteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Causa;
import br.edu.unoesc.projetofinal.model.Macho;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.MorteMacho;
import br.edu.unoesc.projetofinal.model.MorteMatriz;

public class Morte_Inclusao extends JFrame {
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbMacho = new JRadioButton("Macho");
	private JRadioButton jrbFemea = new JRadioButton("Fêmea");
	private JLabel jlbBrinco = new JLabel("Brinco");
	private JLabel jlbData = new JLabel("Data");
	private JLabel jlbCausa = new JLabel("Causa");
	private JCheckBox jcbAproveitavel = new JCheckBox("Morte Aproveitavel");
	private JTextField jtfBrinco = new JTextField();
	private JTextField jtfData = new JTextField();
	private JComboBox<String> jcbCausa = new JComboBox<String>();
	private JButton jbtCadastrar = new JButton("Cadastrar"), jbtSair = new JButton("Sair");
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private MorteMacho morteMacho = new MorteMacho();
	private MorteMatriz morteMatriz = new MorteMatriz();
	private MorteMatrizDAO morteMatrizDao = DaoFactory.get().morteMatrizDao();
	private MorteMachoDAO morteMachoDao = DaoFactory.get().morteMachoDao();
	private Matriz matriz1=new Matriz();
	private Macho macho1=new Macho();
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Morte_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		posicionaObjeto(jrbMacho, 60, 30, 180, 35);
		posicionaObjeto(jrbFemea, 240, 30, 160, 35);
		posicionaObjeto(jlbBrinco, 150, 90, 40, 25);
		posicionaObjeto(jtfBrinco, 200, 90, 200, 25);
		posicionaObjeto(jlbData, 160, 120, 130, 25);
		posicionaObjeto(jtfData, 200, 120, 200, 25);
		posicionaObjeto(jlbCausa, 150, 150, 140, 25);
		posicionaObjeto(jcbCausa, 200, 150, 200, 25);
		posicionaObjeto(jcbAproveitavel, 70, 200, 140, 25);

		posicionaObjeto(jbtCadastrar, 125, 260, 100, 30);
		posicionaObjeto(jbtSair, 285, 260, 80, 20);

		btgEscolha.add(jrbMacho);
		btgEscolha.add(jrbFemea);
		jrbMacho.setSelected(true);

		for (Causa causa : causaDao.listarTodos()) {
			jcbCausa.addItem(causa.getNome());
		}

		jtfBrinco.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int aux = 0;
					if (jtfBrinco.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o número do animal");
					} else {
						if (jrbMacho.isSelected()) {
							for (Macho macho : machoDao.listarTodos()) {
								if (macho.getStatus().equals("Ativo")) {
									if (macho.getBrinco().equals(Long.valueOf(jtfBrinco.getText()))) {
										jtfBrinco.setText(macho.getBrinco().toString());
										morteMacho.setMacho(macho);
										macho.setStatus("Morto");
										macho1=macho;
										aux = 1;
										break;
									}
								}
							}
						}
						if (jrbFemea.isSelected()) {
							for (Matriz matriz : matrizDao.listarTodos()) {
								if (!matriz.getStatus().equals("Morta") && !matriz.getStatus().equals("Descartada")
										&& !matriz.getStatus().equals("Vendida")) {
									if (matriz.getBrinco().equals(Long.valueOf(jtfBrinco.getText()))) {
										jtfBrinco.setText(matriz.getBrinco().toString());
										aux = 1;
										matriz1=new Matriz();
										matriz1=matriz;
										morteMatriz.setMatriz(matriz);
										break;
									}
								}
							}
						}
						if (aux == 0) {
							JOptionPane.showMessageDialog(null, "Nenhum Animal encontrado com esse brinco");
						}
						if (aux == 1) {
							jtfData.requestFocus();
						}
					}
				}
			}
		});

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!jrbFemea.isSelected() && !jrbMacho.isSelected()) {
					JOptionPane.showMessageDialog(null, "Escolha o sexo do animal que morreu");
				} else if (jtfData.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Escolha a data da morte do animal");
				} else {
					if (jrbFemea.isSelected()) {
						morteMatriz.setCausa(causaDao.listarTodos().get(jcbCausa.getSelectedIndex()));
						morteMatriz.setData(Date.valueOf(jtfData.getText()));
						morteMatrizDao.store(morteMatriz);
						matriz1.setStatus("Morta");
						matrizDao.alter(matriz1);
						int linha=1;
						dtmDados.setRowCount(1);
						for(MorteMacho morteMacho:morteMachoDao.listarTodos()){
							dtmDados.setRowCount(dtmDados.getRowCount()+1);
							dtmDados.setValueAt(morteMacho.getMacho().getBrinco(), linha, 0);
							dtmDados.setValueAt(morteMacho.getData(), linha, 1);
							dtmDados.setValueAt(morteMacho.getCausa().getNome(), linha, 2);
							linha++;
						}
						for(MorteMatriz morteMatriz:morteMatrizDao.listarTodos()){
							dtmDados.setRowCount(dtmDados.getRowCount()+1);
							dtmDados.setValueAt(morteMatriz.getMatriz().getBrinco(), linha, 0);
							dtmDados.setValueAt(morteMatriz.getData(), linha, 1);
							dtmDados.setValueAt(morteMatriz.getCausa().getNome(), linha, 2);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Morte Cadastrada com Sucesso");
						dispose();
					}
					if (jrbMacho.isSelected()) {
						morteMacho.setCausa(causaDao.listarTodos().get(jcbCausa.getSelectedIndex()));
						morteMacho.setData(Date.valueOf(jtfData.getText()));
						morteMachoDao.store(morteMacho);
						machoDao.alter(macho1);
						int linha=1;
						dtmDados.setRowCount(1);
						for(MorteMacho morteMacho:morteMachoDao.listarTodos()){
							dtmDados.setRowCount(dtmDados.getRowCount()+1);
							dtmDados.setValueAt(morteMacho.getMacho().getBrinco(), linha, 0);
							dtmDados.setValueAt(morteMacho.getData(), linha, 1);
							dtmDados.setValueAt(morteMacho.getCausa().getNome(), linha, 2);
							linha++;
						}
						for(MorteMatriz morteMatriz:morteMatrizDao.listarTodos()){
							dtmDados.setRowCount(dtmDados.getRowCount()+1);
							dtmDados.setValueAt(morteMatriz.getMatriz().getBrinco(), linha, 0);
							dtmDados.setValueAt(morteMatriz.getData(), linha, 1);
							dtmDados.setValueAt(morteMatriz.getCausa().getNome(), linha, 2);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Morte Cadastrada com Sucesso");
						dispose();
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setTitle("Morte Inclusao");
		setSize(500, 350);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}