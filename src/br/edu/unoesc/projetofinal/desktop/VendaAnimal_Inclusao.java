package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.VendaMachoDAO;
import br.edu.unoesc.projetofinal.dao.VendaMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Comprador;
import br.edu.unoesc.projetofinal.model.Macho;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.NotaVenda;
import br.edu.unoesc.projetofinal.model.VendaMacho;
import br.edu.unoesc.projetofinal.model.VendaMatriz;

public class VendaAnimal_Inclusao extends JFrame {
	private JLabel jlbVendaAnimal = new JLabel("Venda de Animal");
	private JLabel jlbComprador = new JLabel("Comprador ");
	private JLabel jlbValor = new JLabel("Valor");
	private JLabel jlbData = new JLabel("Data ");
	private JLabel jlbNota = new JLabel("Nota ");
	private JLabel jlbGta = new JLabel("Gta ");
	private JComboBox<String> jcbComprador = new JComboBox<String>();
	private JTextField jtfValor = new JTextField();
	private JComboBox<String> jcbNotas = new JComboBox<String>();
	private JTextField jtfGta = new JTextField();
	private JTextField jtfData = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtSair = new JButton("Sair");
	private JRadioButton jrbMacho = new JRadioButton("Macho"), jrbFemea = new JRadioButton("Fêmea");
	private ButtonGroup btgGrupo = new ButtonGroup();
	private JLabel jlbPeso = new JLabel("Peso");
	private JTextField jtfPeso = new JTextField();
	private JLabel jlbBrinco = new JLabel("Brinco");
	private JTextField jtfBrinco = new JTextField();
	private CompradorDAO compradorDao = DaoFactory.get().compradorDao();
	private NotaVendaDAO notaDao = DaoFactory.get().notaVendaDao();
	private VendaMacho vendaMacho = new VendaMacho();
	private VendaMatriz vendaMatriz = new VendaMatriz();
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private Macho macho1 = new Macho();
	private Matriz matriz1 = new Matriz();
	private VendaMachoDAO vendaMachoDao = DaoFactory.get().vendaMachoDao();
	private VendaMatrizDAO vendaMatrizDao = DaoFactory.get().vendaMatrizDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public VendaAnimal_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbVendaAnimal.setFont(new Font("Arial", Font.BOLD, 18));
		jlbVendaAnimal.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbVendaAnimal, 110, 15, 500, 25);
		posicionaObjeto(jrbMacho, 70, 60, 170, 35);
		posicionaObjeto(jrbFemea, 240, 60, 110, 35);
		posicionaObjeto(jlbBrinco, 85, 115, 100, 25);
		posicionaObjeto(jtfBrinco, 130, 115, 200, 25);
		posicionaObjeto(jlbPeso, 90, 145, 100, 25);
		posicionaObjeto(jtfPeso, 130, 145, 200, 25);
		posicionaObjeto(jlbComprador, 55, 175, 120, 25);
		posicionaObjeto(jcbComprador, 130, 175, 200, 25);
		posicionaObjeto(jlbValor, 90, 205, 110, 25);
		posicionaObjeto(jtfValor, 130, 205, 200, 25);
		posicionaObjeto(jlbData, 95, 235, 110, 25);
		posicionaObjeto(jtfData, 130, 235, 200, 25);
		posicionaObjeto(jlbGta, 100, 265, 200, 25);
		posicionaObjeto(jtfGta, 130, 265, 200, 25);
		posicionaObjeto(jlbNota, 95, 295, 200, 25);
		posicionaObjeto(jcbNotas, 130, 295, 200, 25);
		posicionaObjeto(jbtCadastrar, 50, 365, 150, 35);
		posicionaObjeto(jbtSair, 250, 365, 120, 20);

		jrbMacho.setSelected(true);

		btgGrupo.add(jrbFemea);
		btgGrupo.add(jrbMacho);

		for (Comprador comprador : compradorDao.listarTodos()) {
			jcbComprador.addItem(comprador.getNome());
		}

		for (NotaVenda nota : notaDao.listarTodos()) {
			jcbNotas.addItem(nota.getNumero().toString());
		}

		jtfBrinco.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				int auxiliar = 0;
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if (jtfBrinco.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o brinco do animal");
					} else {
						if (jrbMacho.isSelected()) {
							for (Macho macho : machoDao.listarTodos()) {
								if (macho.getBrinco().equals(Long.valueOf(jtfBrinco.getText()))) {
									auxiliar = 2;
									if (macho.getStatus().equals("Descartado")) {
										vendaMacho.setMacho(macho);
										macho1 = macho;
										auxiliar = 1;
										break;
									}
								}
							}
							if (auxiliar == 0) {
								JOptionPane.showMessageDialog(null, "Nenhum macho encontrado");
								jtfBrinco.setText(null);
							}
							if (auxiliar == 2) {
								JOptionPane.showMessageDialog(null, "Descarte o macho antes de vende-lo");
								jtfBrinco.setText(null);
							}
							if (auxiliar == 1) {
								jtfPeso.requestFocus();
							}
						}
						if (jrbFemea.isSelected()) {
							auxiliar = 0;
							for (Matriz matriz : matrizDao.listarTodos()) {
								if (matriz.getBrinco().equals(Long.valueOf(jtfBrinco.getText()))) {
									auxiliar = 2;
									if (matriz.getStatus().equals("Descartada")) {
										auxiliar = 1;
										vendaMatriz.setMatriz(matriz);
										matriz1 = matriz;
										break;
									}
								}
							}
							if (auxiliar == 0) {
								JOptionPane.showMessageDialog(null, "Nenhuma matriz encontrada");
								jtfBrinco.setText(null);
							}
							if (auxiliar == 2) {
								JOptionPane.showMessageDialog(null, "Descarte a matriz antes de vendê-la");
								jtfBrinco.setText(null);
							}
							if (auxiliar == 1) {
								jtfPeso.requestFocus();
							}
						}
					}
				}
			}
		});

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfData.getText().isEmpty() || jtfGta.getText().isEmpty() || jtfPeso.getText().isEmpty()
						|| jtfValor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existem campos no formulário deixados em branco");
				} else {
					if (jrbFemea.isSelected()) {
						vendaMatriz.setComprador(compradorDao.listarTodos().get(jcbComprador.getSelectedIndex()));
						vendaMatriz.setData(Date.valueOf(jtfData.getText()));
						vendaMatriz.setGta(Long.valueOf(jtfGta.getText()));
						vendaMatriz.setNota(notaDao.listarTodos().get(jcbNotas.getSelectedIndex()));
						vendaMatriz.setPesoMedio(Double.valueOf(jtfPeso.getText()));
						vendaMatriz.setPesoTotal(Double.valueOf(jtfPeso.getText()));
						vendaMatriz.setValor(Double.valueOf(jtfValor.getText()));
						NotaVenda nota = new NotaVenda();
						nota = notaDao.listarTodos().get(jcbNotas.getSelectedIndex());
						if (nota.getValor() >= Double.valueOf(jtfValor.getText())) {
							matriz1.setStatus("Vendida");
							matrizDao.alter(matriz1);
							vendaMatrizDao.store(vendaMatriz);
							JOptionPane.showMessageDialog(null, "Venda Realizada com Sucesso");
							dtmDados.setRowCount(1);
							int linha = 1;
							for (VendaMacho vendaMacho : vendaMachoDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(vendaMacho.getMacho().getBrinco(), linha, 0);
								dtmDados.setValueAt(vendaMacho.getPesoTotal(), linha, 1);
								dtmDados.setValueAt(vendaMacho.getComprador().getNome(), linha, 2);
								dtmDados.setValueAt(vendaMacho.getValor(), linha, 3);
								dtmDados.setValueAt(vendaMacho.getData(), linha, 4);
								dtmDados.setValueAt(vendaMacho.getGta(), linha, 5);
								dtmDados.setValueAt(vendaMacho.getNota().getNumero(), linha, 6);
								linha++;
							}
							for (VendaMatriz vendaMatriz : vendaMatrizDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(vendaMatriz.getMatriz().getBrinco(), linha, 0);
								dtmDados.setValueAt(vendaMatriz.getPesoTotal(), linha, 1);
								dtmDados.setValueAt(vendaMatriz.getComprador().getNome(), linha, 2);
								dtmDados.setValueAt(vendaMatriz.getValor(), linha, 3);
								dtmDados.setValueAt(vendaMatriz.getData(), linha, 4);
								dtmDados.setValueAt(vendaMatriz.getGta(), linha, 5);
								dtmDados.setValueAt(vendaMatriz.getNota().getNumero(), linha, 6);
								linha++;
							}
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"O valor da venda não pode ser maior que o valor da nota");
						}
					}
					if (jrbMacho.isSelected()) {
						vendaMacho.setData(Date.valueOf(jtfData.getText()));
						vendaMacho.setPesoMedio(Double.valueOf(jtfPeso.getText()));
						vendaMacho.setPesoTotal(Double.valueOf(jtfPeso.getText()));
						vendaMacho.setValor(Double.valueOf(jtfValor.getText()));
						vendaMacho.setComprador(compradorDao.listarTodos().get(jcbComprador.getSelectedIndex()));
						vendaMacho.setGta(Long.valueOf(jtfGta.getText()));
						vendaMacho.setNota(notaDao.listarTodos().get(jcbNotas.getSelectedIndex()));
						NotaVenda nota = new NotaVenda();
						nota = notaDao.listarTodos().get(jcbComprador.getSelectedIndex());
						if (nota.getValor() >= Double.valueOf(jtfValor.getText())) {
							vendaMachoDao.store(vendaMacho);
							dtmDados.setRowCount(1);
							int linha = 1;
							for (VendaMacho vendaMacho : vendaMachoDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(vendaMacho.getMacho().getBrinco(), linha, 0);
								dtmDados.setValueAt(vendaMacho.getPesoTotal(), linha, 1);
								dtmDados.setValueAt(vendaMacho.getComprador().getNome(), linha, 2);
								dtmDados.setValueAt(vendaMacho.getValor(), linha, 3);
								dtmDados.setValueAt(vendaMacho.getData(), linha, 4);
								dtmDados.setValueAt(vendaMacho.getGta(), linha, 5);
								dtmDados.setValueAt(vendaMacho.getNota().getNumero(), linha, 6);
								linha++;
							}
							for (VendaMatriz vendaMatriz : vendaMatrizDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(vendaMatriz.getMatriz().getBrinco(), linha, 0);
								dtmDados.setValueAt(vendaMatriz.getPesoTotal(), linha, 1);
								dtmDados.setValueAt(vendaMatriz.getComprador().getNome(), linha, 2);
								dtmDados.setValueAt(vendaMatriz.getValor(), linha, 3);
								dtmDados.setValueAt(vendaMatriz.getData(), linha, 4);
								dtmDados.setValueAt(vendaMatriz.getGta(), linha, 5);
								dtmDados.setValueAt(vendaMatriz.getNota().getNumero(), linha, 6);
								linha++;
							}
							JOptionPane.showMessageDialog(null, "Venda Cadastrada com Sucesso");
							macho1.setStatus("Vendido");
							machoDao.alter(macho1);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"O valor da venda não pode ser maior que o valor da nota");
						}
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}

		});

		setTitle("Venda Animal");
		setSize(420, 500);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}