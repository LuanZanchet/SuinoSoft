package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import br.edu.unoesc.projetofinal.jdbc.dao.ConexaoUtil;
import br.edu.unoesc.projetofinal.jdbc.relatorio.RelatorioUtil;

public class Relatorios_Manejo extends JFrame {
	private JTree jtrEntradaFemea = new JTree(new DefaultMutableTreeNode("Lista de Fêmeas"));
	private JTree jtrEntradaMacho = new JTree(new DefaultMutableTreeNode("Lista de Machos"));
	private JTree jtrEntradaSemen = new JTree(new DefaultMutableTreeNode("Lista de Sêmen"));
	private JTree jtrMortedePlantel = new JTree(new DefaultMutableTreeNode("Lista de Morte do Plantel"));
	private JTree jtrDescartePlantel = new JTree(new DefaultMutableTreeNode("Lista de Descarte do Plantel"));
	private JTree jtrVendaPlantel = new JTree(new DefaultMutableTreeNode("Lista de Venda do Plantel"));
	private JTree jtrCobertura = new JTree(new DefaultMutableTreeNode("Lista de Cobertura"));
	private JTree jtrAborto = new JTree(new DefaultMutableTreeNode("Lista de Abortos"));
	private JTree jtrRepeticaoDoCio = new JTree(new DefaultMutableTreeNode("Lista de Repeticao do Cio"));
	private JTree jtrRacao = new JTree(new DefaultMutableTreeNode("Lista de Venda de Racao"));
	private JTree jtrVacinaMedicamento = new JTree(new DefaultMutableTreeNode("Lista de Vacinas"));
	private JTree jtrParto = new JTree(new DefaultMutableTreeNode("Parto"));
	private JTree jtrMovimentacaoDeLeitao = new JTree(new DefaultMutableTreeNode("Movimentação de Leitão"));
	private JTree jtrDesmamedeLeitao = new JTree(new DefaultMutableTreeNode("Desmame de Leitão"));
	private JTree jtrMortedeLeitao = new JTree(new DefaultMutableTreeNode("Morte de Leitão Maternidade"));
	private JTree jtrTranferenciadeLotes = new JTree(new DefaultMutableTreeNode("Transferencia de Lotes"));
	private JTree jtrMorteLeitao = new JTree(new DefaultMutableTreeNode("Morte de Leitão cheche"));
	private JTree jtrVendaDeLeitao = new JTree(new DefaultMutableTreeNode("Venda de Leitão"));

	public Relatorios_Manejo() {
		setLayout(null);
		jtrEntradaFemea.setBounds(60, 30, 200, 25);
		getContentPane().add(jtrEntradaFemea);
		jtrEntradaMacho.setBounds(60, 60, 200, 25);
		getContentPane().add(jtrEntradaMacho);
		jtrMortedePlantel.setBounds(60, 90, 200, 25);
		getContentPane().add(jtrMortedePlantel);
		jtrDescartePlantel.setBounds(60, 120, 200, 25);
		getContentPane().add(jtrDescartePlantel);
		jtrVendaPlantel.setBounds(60, 150, 200, 25);
		getContentPane().add(jtrVendaPlantel);
		jtrCobertura.setBounds(60, 180, 200, 25);
		getContentPane().add(jtrCobertura);
		jtrAborto.setBounds(60, 210, 200, 25);
		getContentPane().add(jtrAborto);
		jtrRepeticaoDoCio.setBounds(60, 240, 200, 25);
		getContentPane().add(jtrRepeticaoDoCio);
		jtrRacao.setBounds(60, 270, 200, 25);
		getContentPane().add(jtrRacao);
		jtrVacinaMedicamento.setBounds(60, 300, 200, 25);
		getContentPane().add(jtrVacinaMedicamento);
		jtrParto.setBounds(60, 330, 200, 25);
		getContentPane().add(jtrParto);
		jtrMovimentacaoDeLeitao.setBounds(60, 360, 200, 25);
		getContentPane().add(jtrMovimentacaoDeLeitao);
		jtrDesmamedeLeitao.setBounds(60, 390, 200, 25);
		getContentPane().add(jtrDesmamedeLeitao);
		jtrMortedeLeitao.setBounds(60, 420, 200, 25);
		getContentPane().add(jtrMortedeLeitao);
		jtrTranferenciadeLotes.setBounds(60, 450, 200, 25);
		getContentPane().add(jtrTranferenciadeLotes);
		jtrMorteLeitao.setBounds(60, 480, 200, 25);
		getContentPane().add(jtrMorteLeitao);
		jtrVendaDeLeitao.setBounds(60, 510, 200, 25);
		getContentPane().add(jtrVendaDeLeitao);
		jtrEntradaSemen.setBounds(60, 540, 200, 25);
		getContentPane().add(jtrEntradaSemen);
		jtrAborto.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrCobertura.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrDescartePlantel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrDesmamedeLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrEntradaFemea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrEntradaMacho.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrEntradaSemen.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrMortedeLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrMortedePlantel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrMorteLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrMovimentacaoDeLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrParto.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrRacao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrRepeticaoDoCio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrTranferenciadeLotes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrVacinaMedicamento.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrVendaDeLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});
		jtrVendaPlantel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					relatorio();

				}

			}
		});

		setTitle("Relatório");
		setSize(300, 600);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Relatorios_Manejo();
	}

	public void relatorio() {
		dispose();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("codigo", Integer.valueOf(1));
		RelatorioUtil relatorio = new RelatorioUtil();
		if (jtrAborto.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/aborto.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrCobertura.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/Cobertura.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrDescartePlantel.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/descartePlantel.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrDesmamedeLeitao.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/desmame.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrEntradaFemea.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/Matriz.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrEntradaMacho.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/Macho.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrEntradaSemen.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/Semen.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrMortedeLeitao.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/MorteLeitaoMaternidade.jrxml", ConexaoUtil.getConexao(),
					parametros);
		}
		if (jtrMorteLeitao.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/MorteLeitaoCreche.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrMortedePlantel.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/MortePlantel.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrMovimentacaoDeLeitao.isRowSelected(0)) {
			relatorio
					.CompileViewReport("src/relatorios/MovimentacaoLeitao.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrParto.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/Parto.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrRacao.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/VendaRacao.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrRepeticaoDoCio.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/RepeticaoCio.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrVacinaMedicamento.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/Vacina.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrTranferenciadeLotes.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/TransferenciaDeLotes.jrxml", ConexaoUtil.getConexao(),
					parametros);
		}
		if (jtrVendaDeLeitao.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/VendaLeitao.jrxml", ConexaoUtil.getConexao(), parametros);
		}
		if (jtrVendaPlantel.isRowSelected(0)) {
			relatorio.CompileViewReport("src/relatorios/VendaPlantel.jrxml", ConexaoUtil.getConexao(), parametros);
		}

	}

}
