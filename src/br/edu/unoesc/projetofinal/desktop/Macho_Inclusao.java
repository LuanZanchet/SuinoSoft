package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
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
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.SemenDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Fornecedor;
import br.edu.unoesc.projetofinal.model.Macho;
import br.edu.unoesc.projetofinal.model.NotaCompra;
import br.edu.unoesc.projetofinal.model.Raca;
import br.edu.unoesc.projetofinal.model.Semen;

public class Macho_Inclusao extends JFrame {
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbMacho = new JRadioButton("Macho");
	private JRadioButton jrbSemen = new JRadioButton("Semen");
	private JLabel jlbMossa = new JLabel("Mossa");
	private JLabel jlbBrinco = new JLabel("Brinco");
	private JLabel jlbNumeroNota = new JLabel("Numero da nota");
	private JLabel jlbDataEntrada = new JLabel("Data entrada granja");
	private JLabel jlbDataNascimento = new JLabel("Data de nascimento");
	private JLabel jlbPesoEntrada = new JLabel("Peso de entrada");
	private JLabel jlbValordeCompra = new JLabel("Valor de Compra");
	private JLabel jlbRaca = new JLabel("Raça");
	private JLabel jlbFornecedor = new JLabel("Fornecedor");
	private JTextField jtfMossa = new JTextField();
	private JTextField jtfBrinco = new JTextField();
	private JComboBox<String> jcbNotas = new JComboBox<String>();
	private JTextField jtfDataEntrada = new JTextField();
	private JTextField jtfDataNascimento = new JTextField();
	private JTextField jtfPesoEntrada = new JTextField();
	private JTextField jtfValordeCompra = new JTextField();
	private JComboBox<String> jcbRaca = new JComboBox<>();
	private JComboBox<String> jcbFornecedor = new JComboBox<>();
	private ButtonGroup btgEscolha2 = new ButtonGroup();
	private JRadioButton jrbInseminacao = new JRadioButton("Utilizado em inseminação artificial");
	private JRadioButton jrbMontaNarutal = new JRadioButton("Utilizado em monta natural");
	private JButton jbtCadastrar = new JButton("Cadastrar"), jbtSair = new JButton("Sair");
	private JLabel jlbTipo = new JLabel("Tipo"), jlbInformacoes = new JLabel("Informações");
	private FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();
	private RacaDAO racaDao = DaoFactory.get().racaDao();
	private NotaCompraDAO notaDao = DaoFactory.get().notaCompraDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private JLabel jlbTetasDireitas = new JLabel("Tetas Direitas"), jlbTetasEsquerdas = new JLabel("Tetas Esquerdas"),
			jlbObservacao = new JLabel("Observação");;
	private JTextField jtfTetasDireitas = new JTextField(), jtfTetasEsquerdas = new JTextField(),
			jtfObservacao = new JTextField();
	private SemenDAO semenDao = DaoFactory.get().semenDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Macho_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);
		jlbInformacoes.setFont(new Font("Arial", Font.BOLD, 24));
		jlbInformacoes.setForeground(Color.DARK_GRAY);

		posicionaObjeto(jlbTipo, 30, 0, 100, 25);
		posicionaObjeto(jrbMacho, 60, 20, 180, 35);
		posicionaObjeto(jrbSemen, 240, 20, 160, 35);
		posicionaObjeto(jlbInformacoes, 140, 60, 300, 25);
		posicionaObjeto(jlbMossa, 150, 90, 40, 25);
		posicionaObjeto(jtfMossa, 200, 90, 200, 25);
		posicionaObjeto(jlbBrinco, 150, 120, 40, 25);
		posicionaObjeto(jtfBrinco, 200, 120, 200, 25);
		posicionaObjeto(jlbNumeroNota, 95, 150, 100, 25);
		posicionaObjeto(jcbNotas, 200, 150, 200, 25);
		posicionaObjeto(jlbDataEntrada, 70, 180, 130, 25);
		posicionaObjeto(jtfDataEntrada, 200, 180, 200, 25);
		posicionaObjeto(jlbDataNascimento, 70, 210, 140, 25);
		posicionaObjeto(jtfDataNascimento, 200, 210, 200, 25);
		posicionaObjeto(jlbPesoEntrada, 90, 240, 100, 25);
		posicionaObjeto(jtfPesoEntrada, 200, 240, 200, 25);
		posicionaObjeto(jlbValordeCompra, 90, 270, 100, 25);
		posicionaObjeto(jtfValordeCompra, 200, 270, 200, 25);
		posicionaObjeto(jlbRaca, 155, 300, 40, 25);
		posicionaObjeto(jcbRaca, 200, 300, 200, 25);
		posicionaObjeto(jlbFornecedor, 120, 330, 100, 25);
		posicionaObjeto(jcbFornecedor, 200, 330, 200, 25);
		posicionaObjeto(jlbObservacao, 115, 360, 100, 25);
		posicionaObjeto(jtfObservacao, 200, 360, 200, 25);
		posicionaObjeto(jlbTetasDireitas, 105, 390, 100, 25);
		posicionaObjeto(jtfTetasDireitas, 200, 390, 40, 25);
		posicionaObjeto(jlbTetasEsquerdas, 250, 390, 100, 25);
		posicionaObjeto(jtfTetasEsquerdas, 350, 390, 50, 25);
		posicionaObjeto(jrbInseminacao, 60, 430, 340, 30);
		posicionaObjeto(jrbMontaNarutal, 60, 460, 340, 30);
		posicionaObjeto(jbtCadastrar, 125, 510, 100, 30);
		posicionaObjeto(jbtSair, 285, 510, 80, 20);

		btgEscolha.add(jrbMacho);
		btgEscolha.add(jrbSemen);
		jrbMacho.setSelected(true);
		btgEscolha2.add(jrbMontaNarutal);
		btgEscolha2.add(jrbInseminacao);

		for (Fornecedor fornecedor : fornecedorDao.listarTodos()) {
			jcbFornecedor.addItem(fornecedor.getNome());
		}

		for (Raca raca : racaDao.listarTodos()) {
			jcbRaca.addItem(raca.getNome());
		}

		for (NotaCompra nota : notaDao.listarTodos()) {
			jcbNotas.addItem(nota.getNumero().toString());
		}

		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jrbMacho.isSelected()) {
					if (jtfMossa.getText().isEmpty() || jtfBrinco.getText().isEmpty()
							|| jtfDataEntrada.getText().isEmpty() || jtfDataNascimento.getText().isEmpty()
							|| jtfPesoEntrada.getText().isEmpty() || jtfValordeCompra.getText().isEmpty()
							|| (!jrbInseminacao.isSelected() && !jrbMontaNarutal.isSelected())) {
						JOptionPane.showMessageDialog(null, "Existem informações pendentes no formulário de cadastro");
					} else {
						int aux = 0;
						for (Macho macho : machoDao.listarTodos()) {
							if (macho.getBrinco().equals(Long.valueOf(jtfBrinco.getText()))) {
								aux = 1;
								break;
							}
						}
						if (aux == 0) {
							Macho macho = new Macho();
							macho.setMossa(Long.valueOf(jtfMossa.getText()));
							macho.setBrinco(Long.valueOf(jtfBrinco.getText()));
							macho.setNota(notaDao.listarTodos().get(jcbNotas.getSelectedIndex()));
							macho.setDataEntrada(Date.valueOf(jtfDataEntrada.getText()));
							macho.setDataNascimento(Date.valueOf(jtfDataNascimento.getText()));
							macho.setPeso(Double.valueOf(jtfPesoEntrada.getText()));
							macho.setValor(Double.valueOf(jtfValordeCompra.getText()));
							macho.setRaca(racaDao.listarTodos().get(jcbRaca.getSelectedIndex()));
							macho.setFornecedor(fornecedorDao.listarTodos().get(jcbFornecedor.getSelectedIndex()));
							macho.setStatus("Ativo");
							macho.setTetasDireitas(Integer.valueOf(jtfTetasDireitas.getText()));
							macho.setTetasEsquerdas(Integer.valueOf(jtfTetasEsquerdas.getText()));
							macho.setObservacao(jtfObservacao.getText());
							if (jrbInseminacao.isSelected()) {
								macho.setTipoUtilizacao("Inseminação Artificial");
							}
							if (jrbMontaNarutal.isSelected()) {
								macho.setTipoUtilizacao("Monta Natural");
							}
							macho.setIdade(100);
							NotaCompra nota = new NotaCompra();
							nota = notaDao.listarTodos().get(jcbNotas.getSelectedIndex());
							if (nota.getValor() >= Double.valueOf(jtfValordeCompra.getText())) {
								machoDao.store(macho);
								dtmDados.setRowCount(1);
								int linha = 1;
								for (Macho macho1 : machoDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(macho1.getMossa(), linha, 0);
									dtmDados.setValueAt(macho1.getBrinco(), linha, 1);
									dtmDados.setValueAt(macho1.getStatus(), linha, 2);
									dtmDados.setValueAt(macho1.getTipoUtilizacao(), linha, 3);
									dtmDados.setValueAt(macho1.getDataEntrada(), linha, 4);
									dtmDados.setValueAt(macho1.getDataNascimento(), linha, 5);
									dtmDados.setValueAt(macho1.getPeso(), linha, 6);
									dtmDados.setValueAt(macho1.getValor(), linha, 7);
									dtmDados.setValueAt(macho1.getRaca().getNome(), linha, 8);
									dtmDados.setValueAt(macho1.getFornecedor().getNome(), linha, 9);
									dtmDados.setValueAt(macho1.getIdade(), linha, 10);
									dtmDados.setValueAt(macho1.getObservacao(), linha, 11);
									linha++;
								}
								for (Semen semen : semenDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt("nulo", linha, 0);
									dtmDados.setValueAt(semen.getBrinco(), linha, 1);
									dtmDados.setValueAt("nulo", linha, 2);
									dtmDados.setValueAt("nulo", linha, 3);
									dtmDados.setValueAt(semen.getDataEntrada(), linha, 4);
									dtmDados.setValueAt(semen.getDataNascimento(), linha, 5);
									dtmDados.setValueAt("nulo", linha, 6);
									dtmDados.setValueAt("nulo", linha, 7);
									dtmDados.setValueAt(semen.getRaca().getNome(), linha, 8);
									dtmDados.setValueAt(semen.getFornecedor().getNome(), linha, 9);
									dtmDados.setValueAt("nulo", linha, 10);
									dtmDados.setValueAt("nulo", linha, 11);
									linha++;
								}
								JOptionPane.showMessageDialog(null, "Macho cadastrado com sucesso");
								dispose();
							} else {
								JOptionPane.showMessageDialog(null,
										"O valor de compra do macho não pode ser maior que o valor total da nota");
							}
						}
						if (aux == 1) {
							JOptionPane.showMessageDialog(null, "Já existe um macho cadastrado com esse número");
						}
					}
				}
				if (jrbSemen.isSelected()) {
					if (jtfBrinco.getText().isEmpty() || jtfDataEntrada.getText().isEmpty()
							|| jtfDataNascimento.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Existem informações pendentes no formulário de cadastro");
					} else {
						int aux = 0;
						for (Semen semen : semenDao.listarTodos()) {
							if (semen.getBrinco().equals(Long.valueOf(jtfBrinco.getText()))) {
								aux = 1;
								break;
							}
						}
						if (aux == 0) {
							Semen semen = new Semen();
							semen.setBrinco(Long.valueOf(jtfBrinco.getText()));
							semen.setDataEntrada(Date.valueOf(jtfDataEntrada.getText()));
							semen.setDataNascimento(Date.valueOf(jtfDataNascimento.getText()));
							semen.setFornecedor(fornecedorDao.listarTodos().get(jcbFornecedor.getSelectedIndex()));
							semen.setNota(notaDao.listarTodos().get(jcbNotas.getSelectedIndex()));
							semen.setRaca(racaDao.listarTodos().get(jcbRaca.getSelectedIndex()));
							NotaCompra nota = new NotaCompra();
							nota = notaDao.listarTodos().get(jcbNotas.getSelectedIndex());
							semenDao.store(semen);
							int linha = 1;
							dtmDados.setRowCount(1);
							for (Macho macho1 : machoDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(macho1.getMossa(), linha, 0);
								dtmDados.setValueAt(macho1.getBrinco(), linha, 1);
								dtmDados.setValueAt(macho1.getStatus(), linha, 2);
								dtmDados.setValueAt(macho1.getTipoUtilizacao(), linha, 3);
								dtmDados.setValueAt(macho1.getDataEntrada(), linha, 4);
								dtmDados.setValueAt(macho1.getDataNascimento(), linha, 5);
								dtmDados.setValueAt(macho1.getPeso(), linha, 6);
								dtmDados.setValueAt(macho1.getValor(), linha, 7);
								dtmDados.setValueAt(macho1.getRaca().getNome(), linha, 8);
								dtmDados.setValueAt(macho1.getFornecedor().getNome(), linha, 9);
								dtmDados.setValueAt(macho1.getIdade(), linha, 10);
								dtmDados.setValueAt(macho1.getObservacao(), linha, 11);
								linha++;
							}
							for (Semen semen1 : semenDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt("nulo", linha, 0);
								dtmDados.setValueAt(semen1.getBrinco(), linha, 1);
								dtmDados.setValueAt("nulo", linha, 2);
								dtmDados.setValueAt("nulo", linha, 3);
								dtmDados.setValueAt(semen1.getDataEntrada(), linha, 4);
								dtmDados.setValueAt(semen1.getDataNascimento(), linha, 5);
								dtmDados.setValueAt("nulo", linha, 6);
								dtmDados.setValueAt("nulo", linha, 7);
								dtmDados.setValueAt(semen1.getRaca().getNome(), linha, 8);
								dtmDados.setValueAt(semen1.getFornecedor().getNome(), linha, 9);
								dtmDados.setValueAt("nulo", linha, 10);
								dtmDados.setValueAt("nulo", linha, 11);
								linha++;
							}

							JOptionPane.showMessageDialog(null, "Sêmen cadastrado com sucesso");
							dispose();
						}
						if (aux == 1) {
							JOptionPane.showMessageDialog(null, "Já existe um semen cadastrado com esse número");
						}
					}
				}
			}
		});

		jrbMacho.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					dispose();
					new Macho_Inclusao(null);

				}
			}
		});

		jrbSemen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jlbObservacao.setVisible(false);
					jtfObservacao.setVisible(false);
					jlbTetasDireitas.setVisible(false);
					jlbTetasEsquerdas.setVisible(false);
					jtfTetasDireitas.setVisible(false);
					jtfTetasEsquerdas.setVisible(false);
					jlbMossa.setVisible(false);
					jtfMossa.setVisible(false);
					jlbPesoEntrada.setVisible(false);
					jtfPesoEntrada.setVisible(false);
					jlbValordeCompra.setVisible(false);
					jtfValordeCompra.setVisible(false);
					jrbInseminacao.setVisible(false);
					jrbMontaNarutal.setVisible(false);
					posicionaObjeto(jrbMacho, 60, 20, 180, 35);
					posicionaObjeto(jlbRaca, 155, 240, 100, 25);
					posicionaObjeto(jcbRaca, 200, 240, 200, 25);
					posicionaObjeto(jlbFornecedor, 120, 270, 100, 25);
					posicionaObjeto(jcbFornecedor, 200, 270, 200, 25);
					posicionaObjeto(jbtCadastrar, 120, 380, 100, 30);
					posicionaObjeto(jbtSair, 285, 380, 80, 20);
					setSize(500, 440);
				}
			}
		});

		setTitle("Macho Inclusao");
		setSize(500, 600);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}