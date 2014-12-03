package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.CompraMedicamentoDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.CompraMedicamento;
import br.edu.unoesc.projetofinal.model.Fornecedor;
import br.edu.unoesc.projetofinal.model.NotaCompra;
import br.edu.unoesc.projetofinal.model.Vacina;

public class CompraMedicamento_Inclusao extends JFrame {

	private JLabel jlbCompraMedicamento = new JLabel("Compra de Medicamento");
	private JLabel jlbVacina = new JLabel("Vacina");
	private JLabel jlbDataCompra = new JLabel("Data Compra");
	private JLabel jlbNota = new JLabel("Nota");
	private JLabel jlbFornecedor = new JLabel("Fornecedor");
	private JLabel jlbQuantidade = new JLabel("Quantidade");

	private JComboBox<String> jcbVacina = new JComboBox<>();
	private JComboBox<String> jcbNotaMedicamento = new JComboBox<>();
	private JComboBox<String> jcbFornecedorMedicamento = new JComboBox<>();
	private JTextField jtfDataMedicamento = new JTextField();
	private JTextField jtfQuantidadeMedicamento = new JTextField();

	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"), jbtSair = new JButton("Sair");
	private CompraMedicamentoDAO compraMedicamentoDAO = DaoFactory.get().compraMedicamentoDao();
	private NotaCompraDAO notaDAO = DaoFactory.get().notaCompraDao();
	private FornecedorDAO fornecedorDAO = DaoFactory.get().fornecedorDao();
	private VacinaDAO vacinaDAO = DaoFactory.get().vacinaDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public CompraMedicamento_Inclusao(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbCompraMedicamento.setFont(new Font("Arial", Font.BOLD, 24));
		jlbCompraMedicamento.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbCompraMedicamento, 55, 45, 500, 25);

		posicionaObjeto(jlbVacina, 90, 105, 100, 25);
		posicionaObjeto(jlbDataCompra, 50, 145, 100, 25);
		posicionaObjeto(jlbNota, 95, 225, 100, 25);
		posicionaObjeto(jlbFornecedor, 60, 185, 100, 25);
		posicionaObjeto(jlbQuantidade, 60, 265, 100, 25);

		posicionaObjeto(jcbVacina, 140, 105, 150, 25);
		posicionaObjeto(jtfDataMedicamento, 140, 145, 150, 25);
		posicionaObjeto(jcbNotaMedicamento, 140, 225, 150, 25);
		posicionaObjeto(jcbFornecedorMedicamento, 140, 185, 150, 25);
		posicionaObjeto(jtfQuantidadeMedicamento, 140, 265, 150, 25);

		posicionaObjeto(jbtCadastrarFornCli, 90, 325, 100, 30);
		posicionaObjeto(jbtSair, 230, 325, 80, 20);

		for (Fornecedor fornecedor : fornecedorDAO.listarTodos()) {
			jcbFornecedorMedicamento.addItem(fornecedor.getNome());
		}
		for (NotaCompra nota : notaDAO.listarTodos()) {
			jcbNotaMedicamento.addItem(nota.getCodigo().toString());
		}

		for (Vacina vacina : vacinaDAO.listarTodos()) {
			jcbVacina.addItem(vacina.getNome());
		}

		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int aux = 0;
				if (jtfDataMedicamento.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Entre com a data do Medicamento");
				}

				else if (jtfQuantidadeMedicamento.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Entre com a quantidade");
				}

				else {
					for (CompraMedicamento compra : compraMedicamentoDAO.listarTodos()) {
						if (compra.getVacina().equals(jcbVacina.getSelectedItem())) {
							aux = 1;
							break;
						}
					}

					if (aux == 0) {
						CompraMedicamento compra = new CompraMedicamento();
						compra.setVacina(vacinaDAO.listarTodos().get(jcbVacina.getSelectedIndex()));
						compra.setData(Date.valueOf(jtfDataMedicamento.getText()));
						compra.setFornecedor(fornecedorDAO.listarTodos().get(
								jcbFornecedorMedicamento.getSelectedIndex()));
						compra.setNota((notaDAO.listarTodos().get(jcbNotaMedicamento.getSelectedIndex())));
						compra.setQuantidade(Integer.valueOf(jtfQuantidadeMedicamento.getText()));
						compraMedicamentoDAO.store(compra);
						JOptionPane.showMessageDialog(null, "COmpra Cadastrada com Sucesso");
						dtmDados.setRowCount(1);
						int linha = 1;
						for (CompraMedicamento compra1 : compraMedicamentoDAO.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(compra1.getCodigo(), linha, 0);
							dtmDados.setValueAt(compra1.getData(), linha, 1);
							dtmDados.setValueAt(compra1.getFornecedor().getNome(), linha, 2);
							dtmDados.setValueAt(compra1.getNota().getCodigo(), linha, 3);
							dtmDados.setValueAt(compra1.getQuantidade(), linha, 4);
							linha++;
						}
						dispose();
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Já existe um medicamento com esse número");
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Compra de Medicamento");
		setSize(400, 415);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}
