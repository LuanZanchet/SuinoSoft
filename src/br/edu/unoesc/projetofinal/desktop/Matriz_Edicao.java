package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

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

import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Fornecedor;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.NotaCompra;
import br.edu.unoesc.projetofinal.model.Raca;

public class Matriz_Edicao extends JFrame {
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbLeitoa = new JRadioButton("Leitoa");
	private JRadioButton jrbMatriz = new JRadioButton("Matriz");
	private JRadioButton jrbMatrizGestante = new JRadioButton("Matriz Gestante");
	private JLabel jlbMossa = new JLabel("Mossa");
	private JLabel jlbBrinco = new JLabel("Brinco");
	private JLabel jlbNumeroNota = new JLabel("Numero da nota");
	private JLabel jlbCiclo = new JLabel("Ciclo(Partos)");
	private JLabel jlbDataEntrada = new JLabel("Data entrada granja");
	private JLabel jlbDataNascimento = new JLabel("Data de nascimento");
	private JLabel jlbPesoEntrada = new JLabel("Peso de entrada");
	private JLabel jlbValordeCompra = new JLabel("Valor de Compra");
	private JLabel jlbFornecedor = new JLabel("Fornecedor");
	private JLabel jlbTetaEsquerda = new JLabel("Esquerdas");
	private JLabel jlbTetaDireita = new JLabel("Tetas direitas");
	private JLabel jlbRaca = new JLabel("Raça");
	private JTextField jtfMossa = new JTextField();
	private JTextField jtfBrinco = new JTextField();
	private JComboBox<String> jcbNotas = new JComboBox<String>();
	private JTextField jtfCiclo = new JTextField();
	private JTextField jtfDataEntrada = new JTextField();
	private JTextField jtfDataNascimento = new JTextField();
	private JTextField jtfPesoEntrada = new JTextField();
	private JTextField jtfValordeCompra = new JTextField();
	private JComboBox<String> jcbRaca = new JComboBox<>();
	private JComboBox<String> jcbFornecedor = new JComboBox<>();
	private JTextField jtfTetaEsquerda = new JTextField();
	private JTextField jtfTetaDireita = new JTextField();
	private JButton jbtCadastrar = new JButton("Alterar"), jbtSair = new JButton("Sair");
	private RacaDAO racaDao = DaoFactory.get().racaDao();
	private FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();
	private NotaCompraDAO notaDao = DaoFactory.get().notaCompraDao();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private JTextField jtfObservacao = new JTextField();
	private JLabel jlbObservacao = new JLabel("Observação");
	private JTextField jtfGuardaValor = new JTextField();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor(Integer posicao) {
		jtfGuardaValor.setText(posicao.toString());
		if (matrizDao.listarTodos().get(posicao).getNumeroCiclos() == 0) {
			jrbLeitoa.setSelected(true);
		} else if (matrizDao.listarTodos().get(posicao).getNumeroCiclos() > 0
				&& matrizDao.listarTodos().get(posicao).getStatus().equals("Gestante")) {
			jrbMatrizGestante.setSelected(true);
		} else {
			jrbMatriz.setSelected(true);
		}
		jtfMossa.setText(matrizDao.listarTodos().get(posicao).getMossa().toString());
		jtfBrinco.setText(matrizDao.listarTodos().get(posicao).getBrinco().toString());
		jcbNotas.setSelectedItem(matrizDao.listarTodos().get(posicao).getNota().getNumero().toString());
		jtfCiclo.setText(matrizDao.listarTodos().get(posicao).getNumeroCiclos().toString());
		jtfDataEntrada.setText(matrizDao.listarTodos().get(posicao).getDataEntrada().toString());
		jtfDataNascimento.setText(matrizDao.listarTodos().get(posicao).getDataNascimento().toString());
		jtfPesoEntrada.setText(matrizDao.listarTodos().get(posicao).getPeso().toString());
		jtfValordeCompra.setText(matrizDao.listarTodos().get(posicao).getValor().toString());
		jcbRaca.setSelectedItem(matrizDao.listarTodos().get(posicao).getRaca().getNome());
		jcbNotas.setSelectedItem(matrizDao.listarTodos().get(posicao).getNota().getNumero().toString());
		jtfTetaDireita.setText(matrizDao.listarTodos().get(posicao).getTetasDireitas().toString());
		jtfTetaEsquerda.setText(matrizDao.listarTodos().get(posicao).getTetasEsquerdas().toString());
		jtfObservacao.setText(matrizDao.listarTodos().get(posicao).getObservacao());
	}

	public Matriz_Edicao(final DefaultTableModel dtmDados) {
		setLayout(null);

		posicionaObjeto(jrbLeitoa, 60, 30, 130, 35);
		posicionaObjeto(jrbMatriz, 190, 30, 100, 35);
		posicionaObjeto(jrbMatrizGestante, 290, 30, 130, 35);
		posicionaObjeto(jlbMossa, 150, 90, 40, 25);
		posicionaObjeto(jtfMossa, 200, 90, 200, 25);
		posicionaObjeto(jlbBrinco, 150, 120, 40, 25);
		posicionaObjeto(jtfBrinco, 200, 120, 200, 25);
		posicionaObjeto(jlbNumeroNota, 95, 150, 100, 25);
		posicionaObjeto(jcbNotas, 200, 150, 200, 25);
		posicionaObjeto(jlbCiclo, 110, 180, 80, 25);
		posicionaObjeto(jtfCiclo, 200, 180, 200, 25);
		posicionaObjeto(jlbDataEntrada, 70, 210, 130, 25);
		posicionaObjeto(jtfDataEntrada, 200, 210, 200, 25);
		posicionaObjeto(jlbDataNascimento, 70, 240, 140, 25);
		posicionaObjeto(jtfDataNascimento, 200, 240, 200, 25);
		posicionaObjeto(jlbPesoEntrada, 90, 270, 100, 25);
		posicionaObjeto(jtfPesoEntrada, 200, 270, 200, 25);
		posicionaObjeto(jlbValordeCompra, 90, 300, 100, 25);
		posicionaObjeto(jtfValordeCompra, 200, 300, 200, 25);
		posicionaObjeto(jlbRaca, 155, 330, 40, 25);
		posicionaObjeto(jcbRaca, 200, 330, 200, 25);
		posicionaObjeto(jlbFornecedor, 120, 360, 100, 25);
		posicionaObjeto(jcbFornecedor, 200, 360, 200, 25);
		posicionaObjeto(jlbTetaDireita, 105, 390, 120, 25);
		posicionaObjeto(jtfTetaDireita, 200, 390, 60, 25);
		posicionaObjeto(jlbTetaEsquerda, 270, 390, 70, 25);
		posicionaObjeto(jtfTetaEsquerda, 340, 390, 60, 25);
		posicionaObjeto(jlbObservacao, 115, 420, 100, 25);
		posicionaObjeto(jtfObservacao, 200, 420, 200, 25);
		posicionaObjeto(jbtCadastrar, 125, 470, 100, 30);
		posicionaObjeto(jbtSair, 285, 470, 80, 20);
		btgEscolha.add(jrbLeitoa);
		btgEscolha.add(jrbMatriz);
		btgEscolha.add(jrbMatrizGestante);
		btgEscolha.clearSelection();

		for (NotaCompra nota : notaDao.listarTodos()) {
			jcbNotas.addItem(nota.getNumero().toString());
		}

		jrbLeitoa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtfCiclo.setEditable(false);
					jtfCiclo.setText("0");
				}
			}
		});

		jrbMatriz.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtfCiclo.setEditable(true);
					jtfCiclo.setText(null);
				}
			}
		});

		jrbMatrizGestante.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtfCiclo.setEditable(true);
					jtfCiclo.setText(null);
				}
			}
		});

		for (Raca raca : racaDao.listarTodos()) {
			jcbRaca.addItem(raca.getNome());
		}

		for (Fornecedor fornecedor : fornecedorDao.listarTodos()) {
			jcbFornecedor.addItem(fornecedor.getNome());
		}

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfBrinco.getText().isEmpty() || jtfCiclo.getText().isEmpty() || jtfDataEntrada.getText().isEmpty()
						|| jtfObservacao.getText().isEmpty() || jtfDataNascimento.getText().isEmpty()
						|| jtfMossa.getText().isEmpty() || jtfPesoEntrada.getText().isEmpty()
						|| jtfTetaDireita.getText().isEmpty() || jtfTetaEsquerda.getText().isEmpty()
						|| jtfValordeCompra.getText().isEmpty()
						|| (!jrbLeitoa.isSelected() && !jrbMatriz.isSelected() && !jrbMatrizGestante.isSelected())) {
					JOptionPane.showMessageDialog(null, "Existem campos do formulário não preenchidos");
				} else {
					int aux = 0;
					for (Matriz matriz : matrizDao.listarTodos()) {
						if (matriz.getBrinco().equals(Long.valueOf(jtfBrinco.getText()))) {
							aux = 1;
							if (matriz.getBrinco().equals(
									matrizDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText())).getBrinco())) {
								aux = 0;
							}
							break;
						}
					}
					if (aux == 0) {
						NotaCompra nota = new NotaCompra();
						nota = notaDao.listarTodos().get(jcbNotas.getSelectedIndex());
						if (nota.getValor() >= Double.valueOf(jtfValordeCompra.getText())) {
							Matriz matriz = new Matriz();
							matriz = matrizDao.listarTodos().get(Integer.valueOf(jtfGuardaValor.getText()));
							matriz.setBrinco(Long.valueOf(jtfBrinco.getText()));
							matriz.setMossa(Long.valueOf(jtfMossa.getText()));
							matriz.setNota(notaDao.listarTodos().get(jcbNotas.getSelectedIndex()));
							matriz.setDataEntrada(Date.valueOf(jtfDataEntrada.getText()));
							matriz.setDataNascimento(Date.valueOf(jtfDataNascimento.getText()));
							matriz.setPeso(Double.valueOf(jtfPesoEntrada.getText()));
							matriz.setValor(Double.valueOf(jtfValordeCompra.getText()));
							matriz.setRaca(racaDao.listarTodos().get(jcbRaca.getSelectedIndex()));
							matriz.setFornecedor(fornecedorDao.listarTodos().get(jcbFornecedor.getSelectedIndex()));
							matriz.setObservacao(jtfObservacao.getText());
							if (jrbMatrizGestante.isSelected()) {
								matriz.setStatus("Gestante");
							} else {
								matriz.setStatus("Vazia");
							}
							matriz.setIdade(100);// ajeitar aki
							matriz.setTetasDireitas(Integer.valueOf(jtfTetaDireita.getText()));
							matriz.setTetasEsquerdas(Integer.valueOf(jtfTetaEsquerda.getText()));
							matriz.setNumeroCiclos(Integer.valueOf(jtfCiclo.getText()));
							matrizDao.alter(matriz);
							JOptionPane.showMessageDialog(null, "Matriz alterada com Sucesso");
							int linha = 1;
							dtmDados.setRowCount(1);
							for (Matriz matriz1 : matrizDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(matriz1.getMossa(), linha, 0);
								dtmDados.setValueAt(matriz1.getBrinco(), linha, 1);
								dtmDados.setValueAt(matriz1.getStatus(), linha, 2);
								dtmDados.setValueAt(matriz1.getNumeroCiclos(), linha, 3);
								dtmDados.setValueAt(matriz1.getDataEntrada(), linha, 4);
								dtmDados.setValueAt(matriz1.getDataNascimento(), linha, 5);
								dtmDados.setValueAt(matriz1.getPeso(), linha, 6);
								dtmDados.setValueAt(matriz1.getValor(), linha, 7);
								dtmDados.setValueAt(matriz1.getRaca().getNome(), linha, 8);
								dtmDados.setValueAt(matriz1.getFornecedor().getNome(), linha, 9);
								dtmDados.setValueAt(matriz1.getIdade(), linha, 10);
								dtmDados.setValueAt(matriz1.getObservacao(), linha, 11);
								linha++;
							}
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"O valor de compra da matriz é maior que o valor máximo da nota");
						}
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Já existe uma matriz cadastrada com esse número!");
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setTitle("Matriz Edição");
		setSize(500, 570);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
