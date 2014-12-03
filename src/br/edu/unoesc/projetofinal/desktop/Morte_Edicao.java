package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
import br.edu.unoesc.projetofinal.dao.MorteMachoDAO;
import br.edu.unoesc.projetofinal.dao.MorteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Causa;
import br.edu.unoesc.projetofinal.model.MorteMacho;
import br.edu.unoesc.projetofinal.model.MorteMatriz;

public class Morte_Edicao extends JFrame {
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
	private JButton jbtCadastrar = new JButton("Alterar"), jbtSair = new JButton("Sair");
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private MorteMacho morteMacho = new MorteMacho();
	private MorteMatriz morteMatriz = new MorteMatriz();
	private MorteMatrizDAO morteMatrizDao = DaoFactory.get().morteMatrizDao();
	private MorteMachoDAO morteMachoDao = DaoFactory.get().morteMachoDao();
	private List machoMatrizMorte=new ArrayList<>();
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}
	
	public void setValor(Integer posicao){
		machoMatrizMorte.addAll(morteMachoDao.listarTodos());
		machoMatrizMorte.addAll(morteMatrizDao.listarTodos());
		
		if(machoMatrizMorte.get(posicao) instanceof MorteMacho){
			morteMacho=(MorteMacho)machoMatrizMorte.get(posicao);
			jrbMacho.setSelected(true);
			jtfBrinco.setText(morteMacho.getMacho().getBrinco().toString());
			jtfData.setText(morteMacho.getData().toString());
			jcbCausa.setSelectedItem(morteMacho.getCausa().getNome());
		}
		
		if(machoMatrizMorte.get(posicao) instanceof MorteMatriz){
			morteMatriz = (MorteMatriz) machoMatrizMorte.get(posicao);
			jrbFemea.setSelected(true);
			jtfBrinco.setText(morteMatriz.getMatriz().getBrinco().toString());
			jtfData.setText(morteMatriz.getData().toString());
			jcbCausa.setSelectedItem(morteMatriz.getCausa().getNome());
		}
		
	}
	
	public Morte_Edicao(final DefaultTableModel dtmDados) {
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
		
		jtfBrinco.setEditable(false);
		
		for (Causa causa : causaDao.listarTodos()) {
			jcbCausa.addItem(causa.getNome());
		}

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
						morteMatrizDao.alter(morteMatriz);
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
						JOptionPane.showMessageDialog(null, "Morte Alterada com Sucesso");
						dispose();
					}
					if (jrbMacho.isSelected()) {
						morteMacho.setCausa(causaDao.listarTodos().get(jcbCausa.getSelectedIndex()));
						morteMacho.setData(Date.valueOf(jtfData.getText()));
						morteMachoDao.alter(morteMacho);
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
						JOptionPane.showMessageDialog(null, "Morte Alterada com Sucesso");
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

		setTitle("Morte Edição");
		setSize(500, 350);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}