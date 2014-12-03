package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import br.edu.unoesc.projetofinal.dao.CompraMedicamentoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.CompraMedicamento;

public class Farmacia extends JFrame {
	private JLabel jlbSair, jlbVoltar, jlbVacinaMedicamento;
	private JButton jbtSair, jbtVoltar, jbtVacinaMedicamento;
	private ScrollPane scpRolagem = new ScrollPane();
	private JTable jtbDados = new JTable();
	private DefaultTableModel dtmDados = new DefaultTableModel();
	private JLabel jlbEventos;
	private JTree jtrCompraVacinaMedic = new JTree(new DefaultMutableTreeNode("Compra Vacina Medic"));
	private int aux = 0, linha = 1;
	private CompraMedicamentoDAO compraMedicamentoDAO = DaoFactory.get().compraMedicamentoDao();
	private JButton jbtAdicionar, jbtEditar, jbtExcluir, jbtPesquisar;

	public Farmacia() {
		setLayout(null);

		jlbEventos = new JLabel("Eventos");
		jlbEventos.setBounds(40, 150, 150, 25);
		jlbEventos.setFont(new Font("Arial", Font.BOLD, 16));
		jlbEventos.setForeground(Color.darkGray);
		getContentPane().add(jlbEventos);

		jtrCompraVacinaMedic.setBounds(60, 190, 150, 25);
		getContentPane().add(jtrCompraVacinaMedic);

		jtrCompraVacinaMedic.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {

					jtrCompraVacinaMedic.setSelectionInterval(-1, -1);
					aux = 1;

					jbtAdicionar.setToolTipText("Clique para cadastrar uma nova nota de compra");
					jbtEditar.setToolTipText("Clique para editar uma nota de compra");
					jbtExcluir.setToolTipText("Clique para excluir uma nota de compra");
					jbtPesquisar.setToolTipText("Clique para pesquisar uma nota de compra");

					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(6);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Vacina", 0, 1);
					dtmDados.setValueAt("Data da Compra", 0, 2);
					dtmDados.setValueAt("Fornecedor", 0, 3);
					dtmDados.setValueAt("Nota", 0, 4);
					dtmDados.setValueAt("Quantidade", 0, 5);
					jtbDados.getColumnModel().getColumn(0).setMaxWidth(40);
					linha = 1;
					dtmDados.setRowCount(1);
					for (CompraMedicamento compra1 : compraMedicamentoDAO.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(compra1.getCodigo(), linha, 0);
						dtmDados.setValueAt(compra1.getVacina().getCodigo(), linha, 1);
						dtmDados.setValueAt(compra1.getData(), linha, 2);
						dtmDados.setValueAt(compra1.getFornecedor().getNome(), linha, 3);
						dtmDados.setValueAt(compra1.getNota().getCodigo(), linha, 4);
						dtmDados.setValueAt(compra1.getQuantidade(), linha, 5);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new CompraMedicamento_Inclusao(dtmDados);
				}

			}
		});

		jbtAdicionar = new JButton(new ImageIcon("Adicionar.png"));
		jbtAdicionar.setBounds(250, 140, 25, 25);
		getContentPane().add(jbtAdicionar);

		jbtEditar = new JButton(new ImageIcon("Editar.png"));
		jbtEditar.setBounds(280, 140, 25, 25);
		getContentPane().add(jbtEditar);

		jbtExcluir = new JButton(new ImageIcon("Excluir.jpg"));
		jbtExcluir.setBounds(310, 140, 25, 25);
		getContentPane().add(jbtExcluir);

		jbtPesquisar = new JButton(new ImageIcon("Pesquisar.jpg"));
		jbtPesquisar.setBounds(340, 140, 25, 25);
		getContentPane().add(jbtPesquisar);

		scpRolagem.setBounds(250, 170, 1100, 500);
		jtbDados.setModel(dtmDados);
		scpRolagem.add(jtbDados);
		getContentPane().add(scpRolagem);

		jbtVacinaMedicamento = new JButton(new ImageIcon("Medicamento.jpg"));
		jbtVacinaMedicamento.setBounds(100, 20, 75, 75);
		jbtVacinaMedicamento.setBorder(null);
		getContentPane().add(jbtVacinaMedicamento);

		jlbVacinaMedicamento = new JLabel("Vacina/Medic.");
		jlbVacinaMedicamento.setFont(new Font("Arial", Font.BOLD, 16));
		jlbVacinaMedicamento.setForeground(Color.darkGray);
		jlbVacinaMedicamento.setBounds(85, 80, 150, 60);
		getContentPane().add(jlbVacinaMedicamento);

		jbtVoltar = new JButton(new ImageIcon("Voltar.jpg"));
		jbtVoltar.setBounds(800, 20, 75, 75);
		jbtVoltar.setBackground(Color.white);
		jbtVoltar.setBorder(null);
		getContentPane().add(jbtVoltar);

		jlbVoltar = new JLabel("Voltar");
		jlbVoltar.setFont(new Font("Arial", Font.BOLD, 16));
		jlbVoltar.setForeground(Color.darkGray);
		jlbVoltar.setBounds(810, 80, 140, 60);
		getContentPane().add(jlbVoltar);

		jbtSair = new JButton(new ImageIcon("Sair.png"));
		jbtSair.setBounds(1115, 20, 75, 75);
		jbtSair.setBackground(Color.white);
		jbtSair.setBorder(null);
		getContentPane().add(jbtSair);

		jlbSair = new JLabel("Sair");
		jlbSair.setFont(new Font("Arial", Font.BOLD, 16));
		jlbSair.setForeground(Color.darkGray);
		jlbSair.setBounds(1135, 80, 140, 60);
		getContentPane().add(jlbSair);
		jbtVacinaMedicamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aux = 1;
				jbtAdicionar.setToolTipText("Clique para cadastrar uma nova nota de compra");
				jbtEditar.setToolTipText("Clique para editar uma nota de compra");
				jbtExcluir.setToolTipText("Clique para excluir uma nota de compra");
				jbtPesquisar.setToolTipText("Clique para pesquisar uma nota de compra");
				linha = 1;
				dtmDados.setRowCount(1);
				dtmDados.setColumnCount(6);
				dtmDados.setValueAt("Código", 0, 0);
				dtmDados.setValueAt("Vacina", 0, 1);
				dtmDados.setValueAt("Data da Compra", 0, 2);
				dtmDados.setValueAt("Fornecedor", 0, 3);
				dtmDados.setValueAt("Nota", 0, 4);
				dtmDados.setValueAt("Quantidade", 0, 5);

				linha = 1;
				dtmDados.setRowCount(1);
				for (CompraMedicamento compra1 : compraMedicamentoDAO.listarTodos()) {
					dtmDados.setRowCount(dtmDados.getRowCount() + 1);
					dtmDados.setValueAt(compra1.getCodigo(), linha, 0);
					dtmDados.setValueAt(compra1.getVacina().getCodigo(), linha, 1);
					dtmDados.setValueAt(compra1.getData(), linha, 2);
					dtmDados.setValueAt(compra1.getFornecedor().getNome(), linha, 3);
					dtmDados.setValueAt(compra1.getNota().getCodigo(), linha, 4);
					dtmDados.setValueAt(compra1.getQuantidade(), linha, 5);
					linha++;
				}
			}
		});
		jbtEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtbDados.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione a nota que deseja alterar");
				} else if (jtbDados.getSelectedRow() == 0) {
					JOptionPane.showMessageDialog(null, "Não pode editar esta linha!");
				} else {
					if (aux == 1) {
						CompraMedicamento_Edicao tela = new CompraMedicamento_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}

				}
			}
		});

		jbtExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtbDados.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o registro que deseja excluir");
				} else if (jtbDados.getSelectedRow() == 0) {
					JOptionPane.showMessageDialog(null, "Não pode excluir essa linha");
				} else {
					int opcao = JOptionPane.showConfirmDialog(null, "Deseja Realmente Excluir o registro?");
					if (opcao == 0) {

						compraMedicamentoDAO.delete(compraMedicamentoDAO.listarTodos().get(
								jtbDados.getSelectedRow() - 1));
						linha = 1;
						dtmDados.setRowCount(1);
						for (CompraMedicamento compra1 : compraMedicamentoDAO.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(compra1.getCodigo(), linha, 0);
							dtmDados.setValueAt(compra1.getVacina().getCodigo(), linha, 1);
							dtmDados.setValueAt(compra1.getData(), linha, 2);
							dtmDados.setValueAt(compra1.getFornecedor().getNome(), linha, 3);
							dtmDados.setValueAt(compra1.getNota().getCodigo(), linha, 4);
							dtmDados.setValueAt(compra1.getQuantidade(), linha, 5);
							linha++;

						}
						JOptionPane.showMessageDialog(null, "Medicamento deletada com Sucesso");
					}

				}
			}
		});

		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer aux = 0;
				if (jtrCompraVacinaMedic.isRowSelected(0)) {
					aux = 15;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
			}
		});

		jbtVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);

			}
		});

		jbtAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (jtrCompraVacinaMedic.isRowSelected(0)) {
					new CompraMedicamento_Inclusao(dtmDados);
				}

			}
		});

		setTitle("SUINOSOFT");
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}