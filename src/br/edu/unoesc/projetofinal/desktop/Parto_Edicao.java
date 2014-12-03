package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

import java.sql.Date;

import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.LactacaoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.PartoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Funcionario;
import br.edu.unoesc.projetofinal.model.Lactacao;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.Parto;

public class Parto_Edicao extends JFrame {
	private JLabel jlbAborto = new JLabel("Cadastro de Parto ");
	private JLabel jlbMatriz = new JLabel("Matriz");
	private JLabel jlbData = new JLabel("Data ");
	private JLabel jlbTipoDeParto = new JLabel("Tipo de Parto");
	private JRadioButton jrbNormal = new JRadioButton("Normal");
	private JRadioButton jrbInduzido = new JRadioButton("Induzido");
	private JRadioButton jrbDistocico = new JRadioButton("Distocico");
	private JRadioButton jrbPrematuro = new JRadioButton("Prématuro");
	private JRadioButton jrbPrematuroDistocico = new JRadioButton("Prématuro e Distocico");
	private JRadioButton jrbInduzidoDistocico = new JRadioButton("Induzido e Distocico");
	private JLabel jlbQuantVivos = new JLabel("Quantidade Vivos");
	private JLabel jlbQuantMortos = new JLabel("Quantidade Mortos");
	private JLabel jlbQuantMumificados = new JLabel("Quantidade Mumificados");
	private JLabel jlbQuantNatiMortos = new JLabel("Quantidade NatiMortos");
	private JLabel jlbPesoTotal = new JLabel("Peso Total");
	private JLabel jlbPesoMedio = new JLabel("Peso Medio");
	private JLabel jlbFuncionario = new JLabel("Funcionário");
	private JTextField jtfMatriz = new JTextField();
	private JTextField jtfData = new JTextField();
	private JTextField jtfQuantVivos = new JTextField();
	private JTextField jtfQuantMortos = new JTextField();
	private JTextField jtfMumificados = new JTextField();
	private JTextField jtfNatiMortos = new JTextField();
	private JTextField jtfPesoTotal = new JTextField();
	private JTextField jtfPesoMedio = new JTextField();
	private JComboBox<String> jcbFuncionario = new JComboBox<String>();
	private FuncionarioDAO funcionarioDao = DaoFactory.get().funcionarioDao();
	private JButton jbtProxima = new JButton("Proxima >>");
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");
	private Matriz matriz1 = new Matriz();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private Parto parto = new Parto();
	private ButtonGroup btgEscolha = new ButtonGroup();
	private LactacaoDAO lactacaoDao = DaoFactory.get().lactacaoDao();
	private PartoDAO partoDao = DaoFactory.get().partoDao();
	private JTextField jtfGuardaValor=new JTextField();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}
	
	public void setValor(Integer posicao){
		jtfGuardaValor.setText(posicao.toString());
		jtfMatriz.setText(partoDao.listarTodos().get(posicao).getMatriz().getBrinco().toString());
		jtfData.setText(partoDao.listarTodos().get(posicao).getData().toString());
		if(partoDao.listarTodos().get(posicao).getTipoParto().equals("Normal")){
			jrbNormal.setSelected(true);
		}
		if(partoDao.listarTodos().get(posicao).getTipoParto().equals("Induzido")){
			jrbInduzido.setSelected(true);;
		}
		if(partoDao.listarTodos().get(posicao).getTipoParto().equals("Induzido e Distocico")){
			jrbInduzidoDistocico.setSelected(true);
		}
		if(partoDao.listarTodos().get(posicao).getTipoParto().equals("Distocico")){
			jrbDistocico.setSelected(true);
		}
		if(partoDao.listarTodos().get(posicao).getTipoParto().equals("Prematuro")){
			jrbPrematuro.setSelected(true);
		}
		if(partoDao.listarTodos().get(posicao).getTipoParto().equals("Prematuro e Distocico")){
			jrbPrematuroDistocico.setSelected(true);
		}
		jcbFuncionario.setSelectedItem(partoDao.listarTodos().get(posicao).getFuncionario().getNome());
		jtfQuantMortos.setText(partoDao.listarTodos().get(posicao).getQuantMortos().toString());
		jtfMumificados.setText(partoDao.listarTodos().get(posicao).getQuantMumificados().toString());
		jtfNatiMortos.setText(partoDao.listarTodos().get(posicao).getQuantNatimortos().toString());
		jtfQuantVivos.setText(partoDao.listarTodos().get(posicao).getQuantVivos().toString());
		jtfPesoTotal.setText(partoDao.listarTodos().get(posicao).getPesoTotal().toString());
		jtfPesoMedio.setText(partoDao.listarTodos().get(posicao).getPesoMedio().toString());
	}
	
	public Parto_Edicao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbAborto.setFont(new Font("Arial", Font.BOLD, 18));
		jlbAborto.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbAborto, 90, 15, 500, 25);
		jlbTipoDeParto.setFont(new Font("Arial", Font.BOLD, 14));
		jlbTipoDeParto.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbTipoDeParto, 90, 15, 500, 25);
		jtfMatriz.setEditable(false);
		
		posicionaObjeto(jlbMatriz, 90, 75, 150, 25);
		posicionaObjeto(jtfMatriz, 130, 75, 200, 25);
		posicionaObjeto(jlbData, 95, 105, 110, 25);
		posicionaObjeto(jtfData, 130, 105, 200, 25);
		posicionaObjeto(jlbTipoDeParto, 90, 145, 200, 25);
		posicionaObjeto(jrbNormal, 90, 185, 240, 25);
		posicionaObjeto(jrbPrematuro, 90, 215, 240, 25);
		posicionaObjeto(jrbInduzido, 90, 245, 240, 25);
		posicionaObjeto(jrbDistocico, 90, 275, 240, 25);
		posicionaObjeto(jrbInduzidoDistocico, 90, 305, 240, 25);
		posicionaObjeto(jrbPrematuroDistocico, 90, 335, 240, 25);
		posicionaObjeto(jbtProxima, 30, 395, 150, 35);
		posicionaObjeto(jbtSair, 200, 395, 120, 20);

		for (Funcionario funcionario : funcionarioDao.listarTodos()) {
			jcbFuncionario.addItem(funcionario.getNome());
		}

		btgEscolha.add(jrbDistocico);
		btgEscolha.add(jrbInduzido);
		btgEscolha.add(jrbInduzidoDistocico);
		btgEscolha.add(jrbNormal);
		btgEscolha.add(jrbPrematuro);
		btgEscolha.add(jrbPrematuroDistocico);

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfQuantMortos.getText().isEmpty() || jtfMumificados.getText().isEmpty()
						|| jtfNatiMortos.getText().isEmpty() || jtfQuantVivos.getText().isEmpty()
						|| jtfPesoMedio.getText().isEmpty() || jtfPesoTotal.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existem informações do formulário não preenchidas");
				} else {
					parto.setFuncionario(funcionarioDao.listarTodos().get(jcbFuncionario.getSelectedIndex()));
					parto.setQuantMortos(Integer.valueOf(jtfQuantMortos.getText()));
					parto.setQuantMumificados(Integer.valueOf(jtfMumificados.getText()));
					parto.setQuantNatimortos(Integer.valueOf(jtfNatiMortos.getText()));
					parto.setQuantVivos(Integer.valueOf(jtfQuantVivos.getText()));
					parto.setPesoMedio(Double.valueOf(jtfPesoMedio.getText()));
					parto.setPesoTotal(Double.valueOf(jtfPesoTotal.getText()));
					Lactacao lactacao = new Lactacao();
					lactacao=lactacaoDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText()));
					lactacao.setQuantAtual(parto.getQuantVivos());
					partoDao.alter(parto);
					lactacaoDao.alter(lactacao);
					JOptionPane.showMessageDialog(null, "Parto Alterado Com Sucesso");
					dtmDados.setRowCount(1);
					int linha = 1;
					for (Parto parto : partoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(parto.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(parto.getData(), linha, 1);
						dtmDados.setValueAt(parto.getTipoParto(), linha, 2);
						dtmDados.setValueAt(parto.getFuncionario().getNome(), linha, 3);
						dtmDados.setValueAt(parto.getQuantVivos(), linha, 4);
						dtmDados.setValueAt(parto.getQuantMortos(), linha, 5);
						dtmDados.setValueAt(parto.getQuantNatimortos(), linha, 6);
						dtmDados.setValueAt(parto.getQuantMumificados(), linha, 7);
						dtmDados.setValueAt(parto.getPesoTotal(), linha, 8);
						linha++;
					}

					dispose();
				}
			}

		});

		jbtProxima.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty()
						|| jtfMatriz.getText().isEmpty()
						|| (!jrbDistocico.isSelected() && !jrbInduzido.isSelected()
								&& !jrbInduzidoDistocico.isSelected() && !jrbNormal.isSelected()
								&& !jrbPrematuro.isSelected() && !jrbPrematuroDistocico.isSelected())) {

					JOptionPane.showMessageDialog(null, "Existem informações em branco no formulário");

				} else {
					parto=partoDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText()));
					parto.setData(Date.valueOf(jtfData.getText()));
					if (jrbDistocico.isSelected()) {
						parto.setTipoParto("Distocico");
					}
					if (jrbInduzido.isSelected()) {
						parto.setTipoParto("Induzido");
					}
					if (jrbInduzidoDistocico.isSelected()) {
						parto.setTipoParto("Induzido e Distocico");
					}
					if (jrbNormal.isSelected()) {
						parto.setTipoParto("Normal");
					}
					if (jrbPrematuro.isSelected()) {
						parto.setTipoParto("Prematuro");
					}
					if (jrbPrematuroDistocico.isSelected()) {
						parto.setTipoParto("Prematuro e Distocico");
					}

					jlbMatriz.setVisible(false);
					jtfMatriz.setVisible(false);
					jlbData.setVisible(false);
					jtfData.setVisible(false);
					jlbTipoDeParto.setVisible(false);
					jrbNormal.setVisible(false);
					jrbPrematuro.setVisible(false);
					jrbInduzido.setVisible(false);
					jrbDistocico.setVisible(false);
					jrbInduzidoDistocico.setVisible(false);
					jrbPrematuroDistocico.setVisible(false);
					jbtProxima.setVisible(false);

					posicionaObjeto(jlbFuncionario, 125, 75, 150, 25);
					posicionaObjeto(jcbFuncionario, 200, 75, 135, 25);
					posicionaObjeto(jlbQuantMortos, 80, 105, 110, 25);
					posicionaObjeto(jtfQuantMortos, 200, 105, 135, 25);
					posicionaObjeto(jlbQuantMumificados, 50, 135, 165, 25);
					posicionaObjeto(jtfMumificados, 200, 135, 135, 25);
					posicionaObjeto(jlbQuantNatiMortos, 60, 165, 240, 25);
					posicionaObjeto(jtfNatiMortos, 200, 165, 135, 25);
					posicionaObjeto(jlbQuantVivos, 90, 195, 240, 25);
					posicionaObjeto(jtfQuantVivos, 200, 195, 135, 25);
					posicionaObjeto(jlbPesoMedio, 120, 225, 150, 25);
					posicionaObjeto(jtfPesoMedio, 200, 225, 135, 25);
					posicionaObjeto(jlbPesoTotal, 130, 255, 150, 25);
					posicionaObjeto(jtfPesoTotal, 200, 255, 135, 25);
					posicionaObjeto(jbtCadastrar, 30, 305, 150, 35);
					posicionaObjeto(jbtSair, 200, 305, 120, 20);
					setSize(420, 400);
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Parto Inclusao");
		setSize(420, 500);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
