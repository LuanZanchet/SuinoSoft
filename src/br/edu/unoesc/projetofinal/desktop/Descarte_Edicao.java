package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMachoDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Causa;
import br.edu.unoesc.projetofinal.model.DescarteMacho;
import br.edu.unoesc.projetofinal.model.DescarteMatriz;

public class Descarte_Edicao extends JFrame {

	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbMacho = new JRadioButton("Macho");
	private JRadioButton jrbFemea = new JRadioButton("Fêmea");
	private JLabel jlbBrinco = new JLabel("Brinco");
	private JLabel jlbData = new JLabel("Data");
	private JLabel jlbCausa = new JLabel("Causa");
	private JTextField jtfBrinco = new JTextField();
	private JTextField jtfData = new JTextField();
	private JComboBox<String> jcbCausas = new JComboBox<String>();
	private JButton jbtCadastrar = new JButton("Alterar"), jbtSair = new JButton("Sair");
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private DescarteMachoDAO descarteMachoDao = DaoFactory.get().descartaMachoDao();
	private DescarteMatrizDAO descarteMatrizDao = DaoFactory.get().descarteMatrizDao();
	private DescarteMatriz descarteMatriz = new DescarteMatriz();
	private DescarteMacho descarteMacho = new DescarteMacho();
	private List descarteMachoDescarteMatriz=new ArrayList();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}
	
	public void setValor(Integer posicao){
		descarteMachoDescarteMatriz.addAll(descarteMachoDao.listarTodos());
		descarteMachoDescarteMatriz.addAll(descarteMatrizDao.listarTodos());
		
		if(descarteMachoDescarteMatriz.get(posicao) instanceof DescarteMacho){
			descarteMacho = (DescarteMacho) descarteMachoDescarteMatriz.get(posicao);
			jrbMacho.setSelected(true);
			jtfBrinco.setText(descarteMacho.getMacho().getBrinco().toString());
			jtfData.setText(descarteMacho.getData().toString());
			jcbCausas.setSelectedItem(descarteMacho.getCausa().getNome());
		}
		if(descarteMachoDescarteMatriz.get(posicao) instanceof DescarteMatriz){
			descarteMatriz = (DescarteMatriz) descarteMachoDescarteMatriz.get(posicao);
			jrbFemea.setSelected(true);
			jtfBrinco.setText(descarteMatriz.getMatriz().getBrinco().toString());
			jtfData.setText(descarteMatriz.getData().toString());
			jcbCausas.setSelectedItem(descarteMatriz.getCausa().getNome());
		}
	}
	
	public Descarte_Edicao(final DefaultTableModel dtmDados) {
		setLayout(null);

		posicionaObjeto(jrbMacho, 60, 30, 180, 35);
		posicionaObjeto(jrbFemea, 240, 30, 160, 35);
		posicionaObjeto(jlbBrinco, 150, 90, 40, 25);
		posicionaObjeto(jtfBrinco, 200, 90, 200, 25);
		posicionaObjeto(jlbData, 160, 120, 130, 25);
		posicionaObjeto(jtfData, 200, 120, 200, 25);
		posicionaObjeto(jlbCausa, 150, 150, 140, 25);
		posicionaObjeto(jcbCausas, 200, 150, 200, 25);

		posicionaObjeto(jbtCadastrar, 125, 230, 100, 30);
		posicionaObjeto(jbtSair, 285, 230, 80, 20);

		btgEscolha.add(jrbMacho);
		btgEscolha.add(jrbFemea);
		jrbMacho.setSelected(true);

		for (Causa causa : causaDao.listarTodos()) {
			jcbCausas.addItem(causa.getNome());
		}

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Escolha a data para o descarte");
				} else {
					if (jrbMacho.isSelected()) {
						descarteMacho.setData(Date.valueOf(jtfData.getText()));
						descarteMacho.setCausa(causaDao.listarTodos().get(jcbCausas.getSelectedIndex()));
						descarteMachoDao.alter(descarteMacho);
						dtmDados.setRowCount(1);
						int linha = 1;
						for (DescarteMacho descarteMacho : descarteMachoDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(descarteMacho.getMacho().getBrinco(), linha, 0);
							dtmDados.setValueAt(descarteMacho.getData(), linha, 1);
							dtmDados.setValueAt(descarteMacho.getCausa().getNome(), linha, 2);
							linha++;
						}
						for (DescarteMatriz descarteMatriz : descarteMatrizDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(descarteMatriz.getMatriz().getBrinco(), linha, 0);
							dtmDados.setValueAt(descarteMatriz.getData(), linha, 1);
							dtmDados.setValueAt(descarteMatriz.getCausa().getNome(), linha, 2);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Descarte alterado com Sucesso");
						dispose();
					}
					if (jrbFemea.isSelected()) {
						descarteMatriz.setData(Date.valueOf(jtfData.getText()));
						descarteMatriz.setCausa(causaDao.listarTodos().get(jcbCausas.getSelectedIndex()));
						descarteMatrizDao.alter(descarteMatriz);
						dtmDados.setRowCount(1);
						int linha = 1;
						for (DescarteMacho descarteMacho : descarteMachoDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(descarteMacho.getMacho().getBrinco(), linha, 0);
							dtmDados.setValueAt(descarteMacho.getData(), linha, 1);
							dtmDados.setValueAt(descarteMacho.getCausa().getNome(), linha, 2);
							linha++;
						}
						for (DescarteMatriz descarteMatriz : descarteMatrizDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(descarteMatriz.getMatriz().getBrinco(), linha, 0);
							dtmDados.setValueAt(descarteMatriz.getData(), linha, 1);
							dtmDados.setValueAt(descarteMatriz.getCausa().getNome(), linha, 2);
							linha++;
						}
						JOptionPane.showMessageDialog(null, "Descarte Alterado com Sucesso");
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
		
		jtfBrinco.setEditable(false);
		
		setTitle("Descarte Inclusao");
		setSize(500, 300);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}