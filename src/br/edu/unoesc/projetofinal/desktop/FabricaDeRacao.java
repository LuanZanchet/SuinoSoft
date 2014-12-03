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

import br.edu.unoesc.projetofinal.dao.CompraRacaoDAO;
import br.edu.unoesc.projetofinal.dao.RacaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.CompraRacao;
import br.edu.unoesc.projetofinal.model.Racao;

public class FabricaDeRacao extends JFrame {
	private JLabel jlbSair, jlbVoltar, jlbOperacao, jlbCadastros, jlbCompras, jlbPrincipais;
	private JButton jbtSair, jbtVoltar, jbtOperacao, jbtCadastros;
	private ScrollPane scpRolagem = new ScrollPane();
	private JTable jtbDados = new JTable();
	private DefaultTableModel dtmDados = new DefaultTableModel();
	private JTree jtrRacaoCompra = new JTree(
			new DefaultMutableTreeNode("Ração"));
	private JTree jtrIngrediente = new JTree(new DefaultMutableTreeNode(
			"Ingredientes"));
	private JTree jtrRacaoCad = new JTree(new DefaultMutableTreeNode("Ração"));
	private JTree jtrIngredienteCad = new JTree(new DefaultMutableTreeNode(
			"Ingredientes"));
	private JButton jbtAdicionar, jbtEditar, jbtExcluir, jbtPesquisar;

	private CompraRacaoDAO compraRacaoDao = DaoFactory.get().compraRacaoDao();
	private RacaoDAO racaoDao = DaoFactory.get().racaoDao();
	private int linha = 1;
	public FabricaDeRacao() {
		setLayout(null);

		jlbCompras = new JLabel("Compras");
		jlbCompras.setBounds(40, 150, 150, 25);
		jlbCompras.setFont(new Font("Arial", Font.BOLD, 16));
		jlbCompras.setForeground(Color.darkGray);
		getContentPane().add(jlbCompras);

		jtrRacaoCompra.setBounds(60, 190, 190, 25);
		getContentPane().add(jtrRacaoCompra);

		jtrRacaoCompra.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrIngrediente.setSelectionInterval(-1, -1);
				}
				if (arg0.getClickCount() == 2) {
					new CompraRacao_Inclusao(dtmDados);
				}
			}
		});

		jlbPrincipais = new JLabel("Principais Cadastros");
		jlbPrincipais.setBounds(40, 150, 200, 25);
		jlbPrincipais.setFont(new Font("Arial", Font.BOLD, 16));
		jlbPrincipais.setForeground(Color.darkGray);
		getContentPane().add(jlbPrincipais);

	

		jtrRacaoCad.setBounds(60, 190, 190, 25);
		getContentPane().add(jtrRacaoCad);

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

		jbtOperacao = new JButton(new ImageIcon("Operacao1.png"));
		jbtOperacao.setBounds(100, 20, 75, 75);
		jbtOperacao.setBorder(null);
		getContentPane().add(jbtOperacao);

		jlbOperacao = new JLabel("Operação");
		jlbOperacao.setFont(new Font("Arial", Font.BOLD, 16));
		jlbOperacao.setForeground(Color.darkGray);
		jlbOperacao.setBounds(105, 80, 90, 60);
		getContentPane().add(jlbOperacao);

		jbtCadastros = new JButton(new ImageIcon("Cadastros.jpg"));
		jbtCadastros.setBounds(355, 20, 75, 75);
		jbtCadastros.setBackground(Color.white);
		jbtCadastros.setBorder(null);
		getContentPane().add(jbtCadastros);

		jlbCadastros = new JLabel("Cadastros");
		jlbCadastros.setFont(new Font("Arial", Font.BOLD, 16));
		jlbCadastros.setForeground(Color.darkGray);
		jlbCadastros.setBounds(350, 80, 190, 60);
		getContentPane().add(jlbCadastros);

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

		

		

		jbtCadastros.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				botaoCadastros();

			}
		});

		botaoOperacao();
		jbtOperacao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				botaoOperacao();

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

		// *************************************************************

		jbtAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtrRacaoCompra.isRowSelected(0)) {
					new CompraRacao_Inclusao(dtmDados);
				}
				if (jtrRacaoCad.isRowSelected(0)) {
					new Racao_Inclusao(dtmDados);
				}

			}
		});
		
		
		jbtPesquisar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(jtrRacaoCompra.isRowSelected(0)){
					int auxP=0;
					
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(auxP);
				}
				if(jtrRacaoCad.isRowSelected(0)){
					int auxP=-1;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(auxP);
				}
			}
		});

		jtrRacaoCompra.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrRacaoCad.setSelectionInterval(-1, -1);
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(6);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Data Compra", 0, 1);
					dtmDados.setValueAt("Fonecedor", 0, 2);
					dtmDados.setValueAt("Nota Compra", 0, 3);
					dtmDados.setValueAt("Ração", 0, 4);
					dtmDados.setValueAt("Quantidade", 0, 5);
					linha = 1;
					for (CompraRacao racao : compraRacaoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(racao.getCodigo(), linha, 0);
						dtmDados.setValueAt(racao.getData(), linha, 1);
						dtmDados.setValueAt(racao.getFornecedor().getNome(),
								linha, 2);
						dtmDados.setValueAt(racao.getNota().getNumero(), linha,
								3);
						dtmDados.setValueAt(racao.getRacao().getNome(), linha,
								4);
						dtmDados.setValueAt(racao.getQuantidade(), linha, 5);
						linha++;
					}
					jbtAdicionar
							.setToolTipText("Clique para Adicionar uma nova Raça");
					jbtEditar.setToolTipText("Clique para Editar uma Raça");
					jbtExcluir.setToolTipText("Clique para Excluir uma Raça");
					jbtPesquisar
							.setToolTipText("Clique para Pesquisar uma Raça");
				}
				if (arg0.getClickCount() == 2) {
					new CompraRacao_Inclusao(dtmDados);
				}
			}
		});

		jtrRacaoCad.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrRacaoCompra.setSelectionInterval(-1, -1);

					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(2);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Nome", 0, 1);

					linha = 1;
					for (Racao racao : racaoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(racao.getCodigo(), linha, 0);
						dtmDados.setValueAt(racao.getNome(), linha, 1);

						linha++;
					}
					jbtAdicionar
							.setToolTipText("Clique para Adicionar uma nova Raça");
					jbtEditar.setToolTipText("Clique para Editar uma Raça");
					jbtExcluir.setToolTipText("Clique para Excluir uma Raça");
					jbtPesquisar
							.setToolTipText("Clique para Pesquisar uma Raça");

				}
				if (arg0.getClickCount() == 2) {
					new Racao_Inclusao(dtmDados);
				}
			}
		});

		jbtEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtbDados.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null,
							"Selecione a nota que deseja alterar");
				} else if (jtbDados.getSelectedRow() == 0) {
					JOptionPane.showMessageDialog(null,
							"Não pode editar esta linha!");
				} else {
					if (jtrRacaoCompra.isRowSelected(0)) {
						CompraRacao_Edicao tela = new CompraRacao_Edicao(
								dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}

					if (jtrRacaoCad.isRowSelected(0)) {
						Racao_Editar tela = new Racao_Editar(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
			}
		});

		jbtExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (jtbDados.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null,
							"Selecione o registro que deseja excluir");
				} else if (jtbDados.getSelectedRow() == 0) {
					JOptionPane.showMessageDialog(null,
							"Não pode excluir essa linha");
				} else {
					int opcao = JOptionPane.showConfirmDialog(null,
							"Deseja Realmente Excluir o registro?");
					if(jtrRacaoCompra.isRowSelected(0)){
						if(opcao==0){
							compraRacaoDao.delete(compraRacaoDao.listarTodos().get(jtbDados.getSelectedRow()-1));
							
							dtmDados.setRowCount(1);
							linha=1;
							
							for (CompraRacao racao : compraRacaoDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(racao.getCodigo(), linha, 0);
								dtmDados.setValueAt(racao.getData(), linha, 1);
								dtmDados.setValueAt(racao.getFornecedor().getNome(),
										linha, 2);
								dtmDados.setValueAt(racao.getNota().getNumero(), linha,
										3);
								dtmDados.setValueAt(racao.getRacao().getNome(), linha,
										4);
								dtmDados.setValueAt(racao.getQuantidade(), linha, 5);
								linha++;
							}
							
							
						}
						
					}
					
					
					
					if (jtrRacaoCad.isRowSelected(0)) {

						if (opcao == 0) {
							int auxi2 = 0;
							for (CompraRacao compra : compraRacaoDao
									.listarTodos()) {

								if (compra
										.getRacao()
										.getCodigo()
										.equals(racaoDao
												.listarTodos()
												.get(jtbDados.getSelectedRow() - 1)
												.getCodigo())) {
									auxi2 = 1;
									break;
								}

							}
							if (auxi2 == 1) {
								JOptionPane
										.showMessageDialog(null,
												"Existe uma compra de ração para essa ração");
							}
							if (auxi2 == 0) {

								racaoDao.delete(racaoDao.listarTodos().get(
										jtbDados.getSelectedRow() - 1));

								dtmDados.setRowCount(1);
								linha = 1;

								for (Racao racao : racaoDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(racao.getCodigo(),
											linha, 0);
									dtmDados.setValueAt(racao.getNome(), linha,
											1);

									linha++;
								}
							}
						}
					}
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

	public void operacaoFalse() {
		jtrIngrediente.setVisible(false);
		jtrRacaoCompra.setVisible(false);
		jlbCompras.setVisible(false);

	}

	public void operacaoTrue() {
		jtrIngrediente.setVisible(true);
		jtrRacaoCompra.setVisible(true);
		jlbCompras.setVisible(true);

	}

	public void cadastroFalse() {
		jtrIngredienteCad.setVisible(false);
		jtrRacaoCad.setVisible(false);
		jlbPrincipais.setVisible(false);

	}

	public void cadastroTrue() {
		jtrIngredienteCad.setVisible(true);
		jtrRacaoCad.setVisible(true);
		jlbPrincipais.setVisible(true);

	}

	public void botaoCadastros() {
		operacaoFalse();
		cadastroTrue();
	}

	public void botaoOperacao() {
		operacaoTrue();
		cadastroFalse();
	}

	public void visibilidadeBotoes() {
		jbtAdicionar.setVisible(true);
		jbtEditar.setVisible(true);
		jbtExcluir.setVisible(true);
		jbtPesquisar.setVisible(true);
	}

	public void invisibilidadeBotoes() {
		jbtAdicionar.setVisible(false);
		jbtEditar.setVisible(false);
		jbtExcluir.setVisible(false);
		jbtPesquisar.setVisible(false);
	}
}