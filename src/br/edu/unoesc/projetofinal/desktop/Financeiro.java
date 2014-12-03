package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.NotaCompra;
import br.edu.unoesc.projetofinal.model.NotaVenda;

public class Financeiro extends JFrame {

	private JLabel jlbSair, jlbVoltar, jlbNotaCompra, jlbNotaVenda, jlbNotas;
	private JButton jbtSair, jbtVoltar, jbtNotasCompra, jbtNotasVenda;
	private ScrollPane scpRolagem = new ScrollPane();
	private JTable jtbDados = new JTable();
	private DefaultTableModel dtmDados = new DefaultTableModel();
	private int aux = 0, linha = 1;
	private NotaCompraDAO notaCompraDao = DaoFactory.get().notaCompraDao();
	private JButton jbtAdicionar, jbtEditar, jbtExcluir, jbtPesquisar;
	private NotaVendaDAO notaVendaDao = DaoFactory.get().notaVendaDao();

	public Financeiro() {
		setLayout(null);

		jlbNotas = new JLabel("Notas");
		jlbNotas.setBounds(40, 150, 200, 25);
		jlbNotas.setFont(new Font("Arial", Font.BOLD, 16));
		jlbNotas.setForeground(Color.darkGray);
		getContentPane().add(jlbNotas);

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

		jbtNotasCompra = new JButton(new ImageIcon("NotaCompra.png"));
		jbtNotasCompra.setBounds(100, 20, 75, 75);
		jbtNotasCompra.setBorder(null);
		getContentPane().add(jbtNotasCompra);

		jbtNotasCompra.addActionListener(new ActionListener() {
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
				dtmDados.setValueAt("Número", 0, 1);
				dtmDados.setValueAt("Data da Compra", 0, 2);
				dtmDados.setValueAt("Fornecedor", 0, 3);
				dtmDados.setValueAt("Valor", 0, 4);
				dtmDados.setValueAt("Forma de Pagamento", 0, 5);
				for (NotaCompra nota : notaCompraDao.listarTodos()) {
					dtmDados.setRowCount(dtmDados.getRowCount() + 1);
					dtmDados.setValueAt(nota.getCodigo(), linha, 0);
					dtmDados.setValueAt(nota.getNumero(), linha, 1);
					dtmDados.setValueAt(nota.getData(), linha, 2);
					dtmDados.setValueAt(nota.getFornecedor().getNome(), linha, 3);
					dtmDados.setValueAt(nota.getValor(), linha, 4);
					dtmDados.setValueAt(nota.getFormaPagamento(), linha, 5);
					linha++;
				}
			}
		});

		jbtAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (aux == 1) {
					new NotaCompra_Inclusao(dtmDados);
				}
				if (aux == 0) {
					new NotaVenda_Inclusao(dtmDados);
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
						NotaCompra_Edicao tela = new NotaCompra_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (aux == 0) {
						NotaVenda_Edicao tela = new NotaVenda_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
			}
		});

		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (aux == 1) {
					int auxx = 9;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(auxx);
				}
				if (aux == 0) {
					int auxx = 10;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(auxx);
				}
			}
		});

		jlbNotaCompra = new JLabel("Nota Compra");
		jlbNotaCompra.setFont(new Font("Arial", Font.BOLD, 16));
		jlbNotaCompra.setForeground(Color.darkGray);
		jlbNotaCompra.setBounds(85, 80, 120, 60);
		getContentPane().add(jlbNotaCompra);

		jbtNotasVenda = new JButton(new ImageIcon("NotaVenda.png"));
		jbtNotasVenda.setBounds(355, 20, 75, 75);
		jbtNotasVenda.setBackground(Color.white);
		jbtNotasVenda.setBorder(null);
		getContentPane().add(jbtNotasVenda);

		jlbNotaVenda = new JLabel("Nota Venda");
		jlbNotaVenda.setFont(new Font("Arial", Font.BOLD, 16));
		jlbNotaVenda.setForeground(Color.darkGray);
		jlbNotaVenda.setBounds(350, 80, 190, 60);
		getContentPane().add(jlbNotaVenda);

		jbtVoltar = new JButton(new ImageIcon("Voltar.jpg"));
		jbtVoltar.setBounds(900, 20, 75, 75);
		jbtVoltar.setBackground(Color.white);
		jbtVoltar.setBorder(null);
		getContentPane().add(jbtVoltar);

		jlbVoltar = new JLabel("Voltar");
		jlbVoltar.setFont(new Font("Arial", Font.BOLD, 16));
		jlbVoltar.setForeground(Color.darkGray);
		jlbVoltar.setBounds(910, 80, 140, 60);
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

		jbtNotasVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				aux = 0;
				jbtAdicionar.setToolTipText("Clique para cadastrar uma nova nota de venda");
				jbtEditar.setToolTipText("Clique para editar uma nota de venda");
				jbtExcluir.setToolTipText("Clique para excluir uma nota de venda");
				jbtPesquisar.setToolTipText("Clique para pesquisar uma nota de venda");
				linha = 1;
				dtmDados.setRowCount(1);
				dtmDados.setColumnCount(6);
				dtmDados.setValueAt("Código", 0, 0);
				dtmDados.setValueAt("Número", 0, 1);
				dtmDados.setValueAt("Data da Venda", 0, 2);
				dtmDados.setValueAt("Comprador", 0, 3);
				dtmDados.setValueAt("Valor", 0, 4);
				dtmDados.setValueAt("Forma de Pagamento", 0, 5);
				for (NotaVenda nota : notaVendaDao.listarTodos()) {
					dtmDados.setRowCount(dtmDados.getRowCount() + 1);
					dtmDados.setValueAt(nota.getCodigo(), linha, 0);
					dtmDados.setValueAt(nota.getNumero(), linha, 1);
					dtmDados.setValueAt(nota.getData(), linha, 2);
					dtmDados.setValueAt(nota.getComprador().getNome(), linha, 3);
					dtmDados.setValueAt(nota.getValor(), linha, 4);
					dtmDados.setValueAt(nota.getFormaPagamento(), linha, 5);
					linha++;
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