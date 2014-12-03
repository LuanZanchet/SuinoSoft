package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import br.edu.unoesc.projetofinal.dao.AbortoDAO;
import br.edu.unoesc.projetofinal.dao.CoberturaDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMachoDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.DesmameDAO;
import br.edu.unoesc.projetofinal.dao.LactacaoDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MontaMachoDao;
import br.edu.unoesc.projetofinal.dao.MontaSemenDAO;
import br.edu.unoesc.projetofinal.dao.MorteLeitaoCrecheDAO;
import br.edu.unoesc.projetofinal.dao.MorteMachoDAO;
import br.edu.unoesc.projetofinal.dao.MorteMaternidadeDAO;
import br.edu.unoesc.projetofinal.dao.MorteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.MovimentacaoDeLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.PartoDAO;
import br.edu.unoesc.projetofinal.dao.RepeticaoCioDAO;
import br.edu.unoesc.projetofinal.dao.SemenDAO;
import br.edu.unoesc.projetofinal.dao.TransferenciaEntreLoteDAO;
import br.edu.unoesc.projetofinal.dao.VendaLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.VendaMachoDAO;
import br.edu.unoesc.projetofinal.dao.VendaMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Aborto;
import br.edu.unoesc.projetofinal.model.Cobertura;
import br.edu.unoesc.projetofinal.model.DescarteMacho;
import br.edu.unoesc.projetofinal.model.DescarteMatriz;
import br.edu.unoesc.projetofinal.model.Desmame;
import br.edu.unoesc.projetofinal.model.Entidade;
import br.edu.unoesc.projetofinal.model.Lactacao;
import br.edu.unoesc.projetofinal.model.Macho;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.MontaMacho;
import br.edu.unoesc.projetofinal.model.MontaSemen;
import br.edu.unoesc.projetofinal.model.MorteLeitaoCreche;
import br.edu.unoesc.projetofinal.model.MorteLeitaoMaternidade;
import br.edu.unoesc.projetofinal.model.MorteMacho;
import br.edu.unoesc.projetofinal.model.MorteMatriz;
import br.edu.unoesc.projetofinal.model.MovimentacaoDeLeitao;
import br.edu.unoesc.projetofinal.model.Parto;
import br.edu.unoesc.projetofinal.model.RepeticaoCio;
import br.edu.unoesc.projetofinal.model.Semen;
import br.edu.unoesc.projetofinal.model.TransferenciaEntreLotes;
import br.edu.unoesc.projetofinal.model.VendaMacho;
import br.edu.unoesc.projetofinal.model.VendaMatriz;

public class Manejo extends JFrame {
	private JLabel jlbSair, jlbVoltar, jlbPlantel, jlbCreche, jlbMaternidade, jlbReproducao;
	private JButton jbtSair, jbtVoltar, jbtPlantel, jbtCreche, jbtMaternidade, jbtReproducao;
	private ScrollPane scpRolagem = new ScrollPane();
	private JTable jtbDados = new JTable();
	private DefaultTableModel dtmDados = new DefaultTableModel();
	private JLabel jlbLancamentos;
	private JTree jtrFemea = new JTree(new DefaultMutableTreeNode("Entrada de Fêmea"));
	private JTree jtrMacho = new JTree(new DefaultMutableTreeNode("Entrada de Macho/Sêmen"));
	private JTree jtrMorte = new JTree(new DefaultMutableTreeNode("Morte de Macho/Fêmea"));
	private JTree jtrDescarte = new JTree(new DefaultMutableTreeNode("Descarte de Macho/Fêmea"));
	private JTree jtrVendaAnimal = new JTree(new DefaultMutableTreeNode("Venda de Macho/Fêmea"));
	private JTree jtrCobertura = new JTree(new DefaultMutableTreeNode("Cobertura"));
	private JTree jtrRepeticaoDoCio = new JTree(new DefaultMutableTreeNode("Repetição do Cio"));
	private JTree jtrAborto = new JTree(new DefaultMutableTreeNode("Aborto"));
	private JTree jtrPartoLeitao = new JTree(new DefaultMutableTreeNode("Parto"));
	private JTree jtrMovimentacaoLeitao = new JTree(new DefaultMutableTreeNode("Movimentação de Leitão"));
	private JTree jtrMorteLeitaoMaternidade = new JTree(new DefaultMutableTreeNode("Morte de Leitão"));
	private JTree jtrDesmameLeitao = new JTree(new DefaultMutableTreeNode("Desmame de Leitão"));
	private JTree jtrTransferenciaLotes = new JTree(new DefaultMutableTreeNode("Transferencia de Lotes"));
	private JTree jtrMorteLeitaoCreche = new JTree(new DefaultMutableTreeNode("Morte Leitão"));
	private JTree jtrVendaLeitao = new JTree(new DefaultMutableTreeNode("Venda de Leitão"));
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private SemenDAO semenDao = DaoFactory.get().semenDao();
	private Integer linha = 1;
	private MorteMatrizDAO morteMatrizDao = DaoFactory.get().morteMatrizDao();
	private MorteMachoDAO morteMachoDao = DaoFactory.get().morteMachoDao();
	private JButton jbtAdicionar, jbtEditar, jbtExcluir, jbtPesquisar;
	private DescarteMachoDAO descarteMachoDao = DaoFactory.get().descartaMachoDao();
	private DescarteMatrizDAO descarteMatrizDao = DaoFactory.get().descarteMatrizDao();
	private VendaMachoDAO vendaMachoDao = DaoFactory.get().vendaMachoDao();
	private VendaMatrizDAO vendaMatrizDao = DaoFactory.get().vendaMatrizDao();
	private CoberturaDAO coberturaDao = DaoFactory.get().coberturaDao();
	private PartoDAO partoDao = DaoFactory.get().partoDao();
	private List<Entidade> machoSemen = new ArrayList<>();
	private MorteLeitaoCrecheDAO morteleitaocrecheDAO = DaoFactory.get().morteLeitaoCrecheDaO();
	private LoteDAO loteDAO = DaoFactory.get().loteDao();
	private VendaLeitaoDAO vendaleitaoDAO = DaoFactory.get().vendaLeitaoDao();
	private TransferenciaEntreLoteDAO tranfereLoteDAO = DaoFactory.get().transferenciaEntreLotesDao();
	private MovimentacaoDeLeitaoDAO movimentacaoDao = DaoFactory.get().movimentacaoDeLeitaoDao();
	private DesmameDAO desmameDao = DaoFactory.get().desmameDao();
	private MorteMaternidadeDAO morteDao = DaoFactory.get().morteMaternidadeDao();
	private LactacaoDAO lactacaoDao = DaoFactory.get().lactacaoDao();
	private AbortoDAO abortoDao = DaoFactory.get().abortoDao();
	private RepeticaoCioDAO repeticaoDao = DaoFactory.get().repeticaoCioDao();
	private MontaSemenDAO montaSemenDao = DaoFactory.get().montaSemenDao();
	private MontaMachoDao montaMachoDao = DaoFactory.get().montaMachoDao();
	private List vendas = new ArrayList();
	private List descartes = new ArrayList<>();
	private List mortes=new ArrayList<>();
	
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Manejo() {
		setLayout(null);

		vendas.addAll(vendaMachoDao.listarTodos());
		vendas.addAll(vendaMatrizDao.listarTodos());

		descartes.addAll(descarteMachoDao.listarTodos());
		descartes.addAll(descarteMatrizDao.listarTodos());
		
		mortes.addAll(morteMachoDao.listarTodos());
		mortes.addAll(morteMatrizDao.listarTodos());
		
		jlbLancamentos = new JLabel("Lançamentos");
		jlbLancamentos.setBounds(40, 150, 150, 25);
		jlbLancamentos.setFont(new Font("Arial", Font.BOLD, 16));
		jlbLancamentos.setForeground(Color.darkGray);
		getContentPane().add(jlbLancamentos);

		posicionaObjeto(jtrFemea, 60, 190, 150, 25);
		posicionaObjeto(jtrMacho, 60, 220, 190, 25);
		posicionaObjeto(jtrDescarte, 60, 280, 190, 25);
		posicionaObjeto(jtrMorte, 60, 250, 190, 25);
		posicionaObjeto(jtrVendaAnimal, 60, 310, 190, 25);
		posicionaObjeto(jtrCobertura, 60, 190, 150, 25);
		posicionaObjeto(jtrAborto, 60, 220, 190, 25);
		posicionaObjeto(jtrRepeticaoDoCio, 60, 250, 190, 25);
		posicionaObjeto(jtrPartoLeitao, 60, 190, 150, 25);
		posicionaObjeto(jtrMovimentacaoLeitao, 60, 220, 190, 25);
		posicionaObjeto(jtrMorteLeitaoMaternidade, 60, 280, 190, 25);
		posicionaObjeto(jtrDesmameLeitao, 60, 250, 190, 25);
		posicionaObjeto(jtrTransferenciaLotes, 60, 190, 150, 25);
		posicionaObjeto(jtrMorteLeitaoCreche, 60, 220, 190, 25);
		posicionaObjeto(jtrVendaLeitao, 60, 250, 190, 25);

		botaoPlantel();

		machoSemen.addAll(machoDao.listarTodos());
		machoSemen.addAll(semenDao.listarTodos());

		jtrFemea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrDescarte.setSelectionInterval(-1, -1);
					jtrMacho.setSelectionInterval(-1, -1);
					jtrMorte.setSelectionInterval(-1, -1);
					jtrVendaAnimal.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para adicionar uma nova Matriz");
					jbtEditar.setToolTipText("Clique para editar uma Matriz");
					jbtExcluir.setToolTipText("Clique para excluir uma Matriz");
					jbtPesquisar.setToolTipText("Clique para pesquisar uma Matriz");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(12);
					dtmDados.setValueAt("Mossa", 0, 0);
					dtmDados.setValueAt("Brinco", 0, 1);
					dtmDados.setValueAt("Status", 0, 2);
					dtmDados.setValueAt("Nº de Ciclos", 0, 3);
					dtmDados.setValueAt("Data de Entrada", 0, 4);
					dtmDados.setValueAt("Data de Nascimento", 0, 5);
					dtmDados.setValueAt("Peso", 0, 6);
					dtmDados.setValueAt("Valor", 0, 7);
					dtmDados.setValueAt("Raça", 0, 8);
					dtmDados.setValueAt("Fornecedor", 0, 9);
					dtmDados.setValueAt("Idade", 0, 10);
					dtmDados.setValueAt("Observação", 0, 11);
					linha = 1;
					for (Matriz matriz : matrizDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(matriz.getMossa(), linha, 0);
						dtmDados.setValueAt(matriz.getBrinco(), linha, 1);
						dtmDados.setValueAt(matriz.getStatus(), linha, 2);
						dtmDados.setValueAt(matriz.getNumeroCiclos(), linha, 3);
						dtmDados.setValueAt(matriz.getDataEntrada(), linha, 4);
						dtmDados.setValueAt(matriz.getDataNascimento(), linha, 5);
						dtmDados.setValueAt(matriz.getPeso(), linha, 6);
						dtmDados.setValueAt(matriz.getValor(), linha, 7);
						dtmDados.setValueAt(matriz.getRaca().getNome(), linha, 8);
						dtmDados.setValueAt(matriz.getFornecedor().getNome(), linha, 9);
						dtmDados.setValueAt(matriz.getIdade(), linha, 10);
						dtmDados.setValueAt(matriz.getObservacao(), linha, 11);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Matriz_Inclusao(dtmDados);
				}
			}
		});

		jtrMacho.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jbtAdicionar.setToolTipText("Clique para adicionar um novo Macho/Sêmen");
					jbtEditar.setToolTipText("Clique para editar um Macho/Sêmen");
					jbtExcluir.setToolTipText("Clique para excluir um Macho/Sêmen");
					jbtPesquisar.setToolTipText("Clique para pesquisar um Macho/Sêmen");
					jtrDescarte.setSelectionInterval(-1, -1);
					jtrFemea.setSelectionInterval(-1, -1);
					jtrMorte.setSelectionInterval(-1, -1);
					jtrVendaAnimal.setSelectionInterval(-1, -1);
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(12);
					dtmDados.setValueAt("Mossa", 0, 0);
					dtmDados.setValueAt("Brinco", 0, 1);
					dtmDados.setValueAt("Status", 0, 2);
					dtmDados.setValueAt("Utilização", 0, 3);
					dtmDados.setValueAt("Data de Entrada", 0, 4);
					dtmDados.setValueAt("Data de Nascimento", 0, 5);
					dtmDados.setValueAt("Peso", 0, 6);
					dtmDados.setValueAt("Valor", 0, 7);
					dtmDados.setValueAt("Raça", 0, 8);
					dtmDados.setValueAt("Fornecedor", 0, 9);
					dtmDados.setValueAt("Idade", 0, 10);
					dtmDados.setValueAt("Observação", 0, 11);
					linha = 1;
					for (Macho macho : machoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(macho.getMossa(), linha, 0);
						dtmDados.setValueAt(macho.getBrinco(), linha, 1);
						dtmDados.setValueAt(macho.getStatus(), linha, 2);
						dtmDados.setValueAt(macho.getTipoUtilizacao(), linha, 3);
						dtmDados.setValueAt(macho.getDataEntrada(), linha, 4);
						dtmDados.setValueAt(macho.getDataNascimento(), linha, 5);
						dtmDados.setValueAt(macho.getPeso(), linha, 6);
						dtmDados.setValueAt(macho.getValor(), linha, 7);
						dtmDados.setValueAt(macho.getRaca().getNome(), linha, 8);
						dtmDados.setValueAt(macho.getFornecedor().getNome(), linha, 9);
						dtmDados.setValueAt(macho.getIdade(), linha, 10);
						dtmDados.setValueAt(macho.getObservacao(), linha, 11);
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
				}
				if (arg0.getClickCount() == 2) {
					new Macho_Inclusao(dtmDados);
				}
			}
		});

		jtrMorte.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrDescarte.setSelectionInterval(-1, -1);
					jtrMacho.setSelectionInterval(-1, -1);
					jtrFemea.setSelectionInterval(-1, -1);
					jtrVendaAnimal.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para adicionar uma nova morte");
					jbtEditar.setToolTipText("Clique para editar uma morte");
					jbtExcluir.setToolTipText("Clique para Excluir uma morte");
					jbtPesquisar.setToolTipText("Clique para pesquisar uma morte");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(3);
					dtmDados.setValueAt("Animal", 0, 0);
					dtmDados.setValueAt("Data", 0, 1);
					dtmDados.setValueAt("Causa", 0, 2);
					linha = 1;
					for (MorteMacho morteMacho : morteMachoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(morteMacho.getMacho().getBrinco(), linha, 0);
						dtmDados.setValueAt(morteMacho.getData(), linha, 1);
						dtmDados.setValueAt(morteMacho.getCausa().getNome(), linha, 2);
						linha++;
					}
					for (MorteMatriz morteMatriz : morteMatrizDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(morteMatriz.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(morteMatriz.getData(), linha, 1);
						dtmDados.setValueAt(morteMatriz.getCausa().getNome(), linha, 2);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Morte_Inclusao(dtmDados);
				}
			}
		});

		jtrDescarte.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrFemea.setSelectionInterval(-1, -1);
					jtrMacho.setSelectionInterval(-1, -1);
					jtrMorte.setSelectionInterval(-1, -1);
					jtrVendaAnimal.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para adicionar um novo descarte");
					jbtEditar.setToolTipText("Clique para editar um descarte");
					jbtExcluir.setToolTipText("Clique para excluir um descarte");
					jbtPesquisar.setToolTipText("Clique para pesquisar um descarte");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(3);
					dtmDados.setValueAt("Animal", 0, 0);
					dtmDados.setValueAt("Data", 0, 1);
					dtmDados.setValueAt("Causa", 0, 2);
					linha = 1;
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
				}
				if (arg0.getClickCount() == 2) {
					new Descarte_Inclusao(dtmDados);
				}
			}
		});

		jtrVendaAnimal.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrDescarte.setSelectionInterval(-1, -1);
					jtrMacho.setSelectionInterval(-1, -1);
					jtrMorte.setSelectionInterval(-1, -1);
					jtrFemea.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para adicionar uma nova venda");
					jbtEditar.setToolTipText("Clique para editar uma venda");
					jbtExcluir.setToolTipText("Clique para excluir uma venda");
					jbtPesquisar.setToolTipText("Clique para pesquisar uma venda");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(7);
					linha = 1;
					dtmDados.setValueAt("Brinco", 0, 0);
					dtmDados.setValueAt("Peso", 0, 1);
					dtmDados.setValueAt("Comprador", 0, 2);
					dtmDados.setValueAt("Valor", 0, 3);
					dtmDados.setValueAt("Data", 0, 4);
					dtmDados.setValueAt("GTA", 0, 5);
					dtmDados.setValueAt("Nota", 0, 6);
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
				}
				if (arg0.getClickCount() == 2) {
					new VendaAnimal_Inclusao(dtmDados);
				}
			}
		});

		jtrCobertura.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrAborto.setSelectionInterval(-1, -1);
					jtrRepeticaoDoCio.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para cadastrar uma nova cobertura");
					jbtEditar.setToolTipText("Clique para editar uma cobertura");
					jbtExcluir.setToolTipText("Clique para excluir uma cobertura");
					jbtPesquisar.setToolTipText("Clique para pesquisar uma cobertura");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(2);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Matriz", 0, 1);
					linha = 1;

					for (Cobertura cobertura : coberturaDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(cobertura.getCodigo(), linha, 0);
						dtmDados.setValueAt(cobertura.getMatriz().getBrinco(), linha, 01);
						linha++;
					}

				}
				if (arg0.getClickCount() == 2) {
					new Cobertura_Inclusao();
				}
			}
		});
		jtrAborto.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrCobertura.setSelectionInterval(-1, -1);
					jtrRepeticaoDoCio.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para cadastrar um novo aborto");
					jbtEditar.setToolTipText("Clique para editar um aborto");
					jbtExcluir.setToolTipText("Clique para excluir um aborto");
					jbtPesquisar.setToolTipText("Clique para pesquisar um aborto");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(3);
					dtmDados.setValueAt("Matriz", 0, 0);
					dtmDados.setValueAt("Data", 0, 1);
					dtmDados.setValueAt("Observação", 0, 2);
					linha = 1;
					for (Aborto aborto : abortoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(aborto.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(aborto.getData(), linha, 1);
						dtmDados.setValueAt(aborto.getObservacao(), linha, 2);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Aborto_Inclusao(dtmDados);
				}
			}
		});
		jtrRepeticaoDoCio.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrAborto.setSelectionInterval(-1, -1);
					jtrCobertura.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para cadastrar uma nova repetição de cio");
					jbtEditar.setToolTipText("Clique para editar uma repetição de cio");
					jbtExcluir.setToolTipText("Clique para excluir uma repetição de cio");
					jbtPesquisar.setToolTipText("Clique para pesquisar uma repetição de cio");
					linha = 1;
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(3);
					dtmDados.setValueAt("Matriz", 0, 0);
					dtmDados.setValueAt("Data", 0, 1);
					dtmDados.setValueAt("Observação", 0, 2);
					for (RepeticaoCio repeticao : repeticaoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(repeticao.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(repeticao.getData(), linha, 1);
						dtmDados.setValueAt(repeticao.getObservacao(), linha, 2);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new RepeticaoDoCio_Inclusao(dtmDados);
				}
			}
		});

		jtrPartoLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrMorteLeitaoMaternidade.setSelectionInterval(-1, -1);
					jtrDesmameLeitao.setSelectionInterval(-1, -1);
					jtrMovimentacaoLeitao.setSelectionInterval(-1, -1);

					jbtAdicionar.setToolTipText("Clique para adicionar um novo parto");
					jbtEditar.setToolTipText("Clique para editar um parto");
					jbtExcluir.setToolTipText("Clique para excluir um parto");
					jbtPesquisar.setToolTipText("Clique para pesquisar um parto");

					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(9);
					dtmDados.setValueAt("Matriz", 0, 0);
					dtmDados.setValueAt("Data", 0, 1);
					dtmDados.setValueAt("Tipo", 0, 2);
					dtmDados.setValueAt("Funcionário", 0, 3);
					dtmDados.setValueAt("Vivos", 0, 4);
					dtmDados.setValueAt("Mortos", 0, 5);
					dtmDados.setValueAt("Natimortos", 0, 6);
					dtmDados.setValueAt("Mumificados", 0, 7);
					dtmDados.setValueAt("Peso Total", 0, 8);
					linha = 1;
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
				}
				if (arg0.getClickCount() == 2) {
					new Parto_Inclusao(dtmDados);
				}
			}
		});
		jtrMorteLeitaoMaternidade.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrPartoLeitao.setSelectionInterval(-1, -1);
					jtrDesmameLeitao.setSelectionInterval(-1, -1);
					jtrMovimentacaoLeitao.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para cadastrar uma nova morte de leitão");
					jbtEditar.setToolTipText("Clique para editar uma morte de leitão");
					jbtExcluir.setToolTipText("Clique para excluir uma morte de leitão");
					jbtPesquisar.setToolTipText("Clique para pesquisar uma morte de leitão");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(4);
					dtmDados.setValueAt("Matriz", 0, 0);
					dtmDados.setValueAt("Data", 0, 1);
					dtmDados.setValueAt("Quantidade", 0, 2);
					dtmDados.setValueAt("Causa", 0, 3);
					linha = 1;
					for (MorteLeitaoMaternidade morte : morteDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(morte.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(morte.getData(), linha, 1);
						dtmDados.setValueAt(morte.getQuantidade(), linha, 2);
						dtmDados.setValueAt(morte.getCausa().getNome(), linha, 3);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Morte_Leitao_Maternidade(dtmDados);
				}
			}
		});

		jtrDesmameLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrMorteLeitaoMaternidade.setSelectionInterval(-1, -1);
					jtrPartoLeitao.setSelectionInterval(-1, -1);
					jtrMovimentacaoLeitao.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para cadastrar um novo desmame");
					jbtEditar.setToolTipText("Clique para editar um desmame");
					jbtExcluir.setToolTipText("Clique para excluir um desmame");
					jbtPesquisar.setToolTipText("Clique para pesquisar um desmame");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(6);
					dtmDados.setValueAt("Matriz", 0, 0);
					dtmDados.setValueAt("Data", 0, 1);
					dtmDados.setValueAt("Quantidade", 0, 2);
					dtmDados.setValueAt("Peso Total", 0, 3);
					dtmDados.setValueAt("Lote", 0, 4);
					dtmDados.setValueAt("Observação", 0, 5);
					linha = 1;
					for (Desmame desmame : desmameDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(desmame.getMatriz().getBrinco(), linha, 0);
						dtmDados.setValueAt(desmame.getData(), linha, 1);
						dtmDados.setValueAt(desmame.getQuantidade(), linha, 2);
						dtmDados.setValueAt(desmame.getPesoTotal(), linha, 3);
						dtmDados.setValueAt(desmame.getLote().getNumero(), linha, 4);
						dtmDados.setValueAt(desmame.getObsDesmame(), linha, 5);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Desmame_Inclusao(dtmDados);
				}
			}
		});

		jtrMovimentacaoLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrMorteLeitaoMaternidade.setSelectionInterval(-1, -1);
					jtrDesmameLeitao.setSelectionInterval(-1, -1);
					jtrPartoLeitao.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para cadastrar uma movimentação de leitão");
					jbtEditar.setToolTipText("Clique para editar uma movimentação de leitão");
					jbtExcluir.setToolTipText("Clique para excluir uma movimentação de leitão");
					jbtPesquisar.setToolTipText("Clique para pesquisar uma transferência de leitão");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(4);
					dtmDados.setValueAt("Matriz Doadora", 0, 0);
					dtmDados.setValueAt("Matriz Receptora", 0, 1);
					dtmDados.setValueAt("Data", 0, 2);
					dtmDados.setValueAt("Transferidos", 0, 3);
					linha = 1;
					for (MovimentacaoDeLeitao movimentacao : movimentacaoDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(movimentacao.getMatrizDoadora().getBrinco(), linha, 0);
						dtmDados.setValueAt(movimentacao.getMatrizReceptora().getBrinco(), linha, 1);
						dtmDados.setValueAt(movimentacao.getData(), linha, 2);
						dtmDados.setValueAt(movimentacao.getQuantidadeLeitao(), linha, 3);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Movimentacao_Leitao_Inclusao(dtmDados);
				}
			}
		});

		jtrMorteLeitaoCreche.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrTransferenciaLotes.setSelectionInterval(-1, -1);
					jtrVendaLeitao.setSelectionInterval(-1, -1);
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(5);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Lote", 0, 1);
					dtmDados.setValueAt("Data Morte", 0, 2);
					dtmDados.setValueAt("Quantidade", 0, 3);
					dtmDados.setValueAt("Causa", 0, 4);
					linha = 1;
					for (MorteLeitaoCreche morteLeitao : morteleitaocrecheDAO.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(morteLeitao.getCodigo(), linha, 0);
						dtmDados.setValueAt(morteLeitao.getLote().getNumero(), linha, 1);
						dtmDados.setValueAt(morteLeitao.getData(), linha, 2);
						dtmDados.setValueAt(morteLeitao.getQuantidade(), linha, 3);
						dtmDados.setValueAt(morteLeitao.getCausa().getNome(), linha, 4);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Morte_Leitao_Creche(dtmDados);
				}
			}
		});

		jtrTransferenciaLotes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrMorteLeitaoCreche.setSelectionInterval(-1, -1);
					jtrDesmameLeitao.setSelectionInterval(-1, -1);
					jtrVendaLeitao.setSelectionInterval(-1, -1);
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(6);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Lote de Origem", 0, 1);
					dtmDados.setValueAt("Lote de Destino", 0, 2);
					dtmDados.setValueAt("Data Transferencia", 0, 3);
					dtmDados.setValueAt("Quantidade", 0, 4);
					dtmDados.setValueAt("Peso Total", 0, 5);
					linha = 1;
					for (TransferenciaEntreLotes transferencia : tranfereLoteDAO.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(transferencia.getCodigo(), linha, 0);
						dtmDados.setValueAt(transferencia.getLoteOrigem().getNumero(), linha, 1);
						dtmDados.setValueAt(transferencia.getLoteDestino().getNumero(), linha, 2);
						dtmDados.setValueAt(transferencia.getData(), linha, 3);
						dtmDados.setValueAt(transferencia.getQuantidade(), linha, 4);
						dtmDados.setValueAt(transferencia.getPesoTotal(), linha, 5);
						linha++;
					}

				}
				if (arg0.getClickCount() == 2) {
					new Transferencia_Lote_Inclusao(dtmDados);
				}
			}
		});

		jtrVendaLeitao.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrMorteLeitaoCreche.setSelectionInterval(-1, -1);
					jtrTransferenciaLotes.setSelectionInterval(-1, -1);
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(11);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Comprador", 0, 1);
					dtmDados.setValueAt("Lote", 0, 2);
					dtmDados.setValueAt("Valor", 0, 3);
					dtmDados.setValueAt("Data Venda", 0, 4);
					dtmDados.setValueAt("Peso Médio", 0, 5);
					dtmDados.setValueAt("Peso Total", 0, 6);
					dtmDados.setValueAt("Lote", 0, 7);
					dtmDados.setValueAt("Gta", 0, 8);
					dtmDados.setValueAt("Nota", 0, 9);
					dtmDados.setValueAt("Quantidade", 0, 10);
					linha = 1;
					for (br.edu.unoesc.projetofinal.model.VendaLeitao vendaLeitao : vendaleitaoDAO.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(vendaLeitao.getCodigo(), linha, 0);
						dtmDados.setValueAt(vendaLeitao.getComprador().getNome(), linha, 1);
						dtmDados.setValueAt(vendaLeitao.getLote().getNumero(), linha, 2);
						dtmDados.setValueAt(vendaLeitao.getValor(), linha, 3);
						dtmDados.setValueAt(vendaLeitao.getData(), linha, 4);
						dtmDados.setValueAt(vendaLeitao.getPesoMedio(), linha, 5);
						dtmDados.setValueAt(vendaLeitao.getPesoTotal(), linha, 6);
						dtmDados.setValueAt(vendaLeitao.getLote().getNumero(), linha, 7);
						dtmDados.setValueAt(vendaLeitao.getGta(), linha, 8);
						dtmDados.setValueAt(vendaLeitao.getNota().getNumero(), linha, 9);
						dtmDados.setValueAt(vendaLeitao.getQuantidade(), linha, 10);
						linha++;
					}

				}
				if (arg0.getClickCount() == 2) {
					new VendaLeitao_Inclusao(dtmDados);
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

		jbtPlantel = new JButton(new ImageIcon("Plantel.png"));
		jbtPlantel.setBounds(100, 20, 75, 75);
		jbtPlantel.setBorder(null);
		getContentPane().add(jbtPlantel);

		jlbPlantel = new JLabel("Plantel");
		jlbPlantel.setFont(new Font("Arial", Font.BOLD, 16));
		jlbPlantel.setForeground(Color.darkGray);
		jlbPlantel.setBounds(105, 80, 90, 60);
		getContentPane().add(jlbPlantel);

		jbtReproducao = new JButton(new ImageIcon("Reproducao.jpg"));
		jbtReproducao.setBounds(305, 20, 75, 75);
		jbtReproducao.setBackground(Color.white);
		jbtReproducao.setBorder(null);
		getContentPane().add(jbtReproducao);

		jlbReproducao = new JLabel("Reprodução");
		jlbReproducao.setFont(new Font("Arial", Font.BOLD, 16));
		jlbReproducao.setForeground(Color.darkGray);
		jlbReproducao.setBounds(300, 80, 190, 60);
		getContentPane().add(jlbReproducao);

		jbtMaternidade = new JButton(new ImageIcon("Maternidade.png"));
		jbtMaternidade.setBounds(505, 20, 75, 75);
		jbtMaternidade.setBackground(Color.white);
		jbtMaternidade.setBorder(null);
		getContentPane().add(jbtMaternidade);

		jlbMaternidade = new JLabel("Maternidade");
		jlbMaternidade.setFont(new Font("Arial", Font.BOLD, 16));
		jlbMaternidade.setForeground(Color.darkGray);
		jlbMaternidade.setBounds(500, 80, 140, 60);
		getContentPane().add(jlbMaternidade);

		jbtCreche = new JButton(new ImageIcon("Creche.png"));
		jbtCreche.setBounds(705, 20, 75, 75);
		jbtCreche.setBackground(Color.white);
		jbtCreche.setBorder(null);
		getContentPane().add(jbtCreche);

		jlbCreche = new JLabel("Creche");
		jlbCreche.setFont(new Font("Arial", Font.BOLD, 16));
		jlbCreche.setForeground(Color.darkGray);
		jlbCreche.setBounds(720, 80, 140, 60);
		getContentPane().add(jlbCreche);

		jbtVoltar = new JButton(new ImageIcon("Voltar.jpg"));
		jbtVoltar.setBounds(905, 20, 75, 75);
		jbtVoltar.setBackground(Color.white);
		jbtVoltar.setBorder(null);
		getContentPane().add(jbtVoltar);

		jlbVoltar = new JLabel("Voltar");
		jlbVoltar.setFont(new Font("Arial", Font.BOLD, 16));
		jlbVoltar.setForeground(Color.darkGray);
		jlbVoltar.setBounds(915, 80, 140, 60);
		getContentPane().add(jlbVoltar);

		jbtSair = new JButton(new ImageIcon("Sair.png"));
		jbtSair.setBounds(1105, 20, 75, 75);
		jbtSair.setBackground(Color.white);
		jbtSair.setBorder(null);
		getContentPane().add(jbtSair);

		jlbSair = new JLabel("Sair");
		jlbSair.setFont(new Font("Arial", Font.BOLD, 16));
		jlbSair.setForeground(Color.darkGray);
		jlbSair.setBounds(1125, 80, 140, 60);
		getContentPane().add(jlbSair);

		jbtPlantel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				botaoPlantel();
				clearSelectionCreche();
				clearSelectionMaternidade();
				clearSelectionReproducao();
			}
		});

		jbtReproducao.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				botaoReproducao();
				clearSelectionCreche();
				clearSelectionMaternidade();
				clearSelectionPlantel();
			}
		});

		jbtMaternidade.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaoMaternidade();
				clearSelectionCreche();
				clearSelectionPlantel();
				clearSelectionReproducao();
			}
		});

		jbtCreche.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				botaoCreche();
				clearSelectionMaternidade();
				clearSelectionPlantel();
				clearSelectionReproducao();
			}
		});
		jbtVoltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(jtrMorte.isRowSelected(0)){
					int aux=-98;
							
					Pesquisar tela=new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(jtrMacho.isRowSelected(0)){
					int aux=-99;
					
					Pesquisar tela=new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(jtrAborto.isRowSelected(0)){
					int aux=-15;
					
					Pesquisar tela=new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(jtrFemea.isRowSelected(0)){
					int aux=-100;
					
					Pesquisar tela=new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if (jtrTransferenciaLotes.isRowSelected(0)) {
					int aux = -14;

					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if (jtrMorteLeitaoMaternidade.isRowSelected(0)) {
					int aux = -13;

					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if (jtrDesmameLeitao.isRowSelected(0)) {
					int aux = -12;

					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if (jtrMovimentacaoLeitao.isRowSelected(0)) {
					int aux = -11;

					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if (jtrPartoLeitao.isRowSelected(0)) {
					int auxP = -10;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(auxP);
				}
				if (jtrMorteLeitaoCreche.isRowSelected(0)) {
					int auxP = 200;

					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(auxP);
				}

				if (jtrVendaLeitao.isRowSelected(0)) {
					int auxP = 201;

					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(auxP);
				}

			}
		});
		
		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		jbtExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtbDados.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o registro que deseja excluir");
				} else if (jtbDados.getSelectedRow() == 0) {
					JOptionPane.showMessageDialog(null, "Não pode excluir essa linha");
				} else {
					int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registor?");
					if (opcao == 0) {
						if(jtrMorte.isRowSelected(0)){
							if(mortes.get(jtbDados.getSelectedRow()-1) instanceof MorteMacho){
								MorteMacho morteMacho=new MorteMacho();
								morteMacho=(MorteMacho)mortes.get(jtbDados.getSelectedRow()-1);
								morteMacho.getMacho().setStatus("Ativo");
								machoDao.alter(morteMacho.getMacho());
								morteMachoDao.delete(morteMacho);
							}
							if(mortes.get(jtbDados.getSelectedRow()-1) instanceof MorteMatriz){
								MorteMatriz morteMatriz=new MorteMatriz();
								morteMatriz=(MorteMatriz)mortes.get(jtbDados.getSelectedRow()-1);
								morteMatriz.getMatriz().setStatus("Vazio");
								matrizDao.alter(morteMatriz.getMatriz());
								morteMatrizDao.delete(morteMatriz);
							}
							JOptionPane.showMessageDialog(null, "Morte excluída com Sucesso");
							dtmDados.setRowCount(1);
							linha=1;
							for (MorteMacho morteMacho : morteMachoDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(morteMacho.getMacho().getBrinco(), linha, 0);
								dtmDados.setValueAt(morteMacho.getData(), linha, 1);
								dtmDados.setValueAt(morteMacho.getCausa().getNome(), linha, 2);
								linha++;
							}
							for (MorteMatriz morteMatriz : morteMatrizDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(morteMatriz.getMatriz().getBrinco(), linha, 0);
								dtmDados.setValueAt(morteMatriz.getData(), linha, 1);
								dtmDados.setValueAt(morteMatriz.getCausa().getNome(), linha, 2);
								linha++;
							}
						}
						if(jtrAborto.isRowSelected(0)){
							
							abortoDao.delete(abortoDao.listarTodos().get(jtbDados.getSelectedRow()-1));
							
							dtmDados.setRowCount(1);
							int linha = 1;
							for (Aborto aborto1 : abortoDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(aborto1.getMatriz().getBrinco(), linha, 0);
								dtmDados.setValueAt(aborto1.getData(), linha, 1);
								dtmDados.setValueAt(aborto1.getObservacao(), linha, 2);
								linha++;
							}
						}
						if(jtrRepeticaoDoCio.isRowSelected(0)){
							
							repeticaoDao.delete(repeticaoDao.listarTodos().get(jtbDados.getSelectedRow()-1));
							
							int linha=1;
							dtmDados.setRowCount(1);
							for(RepeticaoCio repeticao:repeticaoDao.listarTodos()){
								dtmDados.setRowCount(dtmDados.getRowCount()+1);
								dtmDados.setValueAt(repeticao.getMatriz().getBrinco(), linha, 0);
								dtmDados.setValueAt(repeticao.getData(), linha, 1);
								dtmDados.setValueAt(repeticao.getObservacao(), linha, 2);
								linha++;
							}
						}
						
						if (jtrDescarte.isRowSelected(0)) {
							if (descartes.get(jtbDados.getSelectedRow() - 1) instanceof DescarteMacho) {
								DescarteMacho descarteMacho = new DescarteMacho();
								descarteMacho = (DescarteMacho) descartes.get(jtbDados.getSelectedRow() - 1);
								descarteMacho.getMacho().setStatus("Ativo");
								machoDao.alter(descarteMacho.getMacho());
								descarteMachoDao.delete(descarteMacho);
							}
							if (descartes.get(jtbDados.getSelectedRow() - 1) instanceof DescarteMatriz) {
								DescarteMatriz descarteMatriz = new DescarteMatriz();
								descarteMatriz = (DescarteMatriz) descartes.get(jtbDados.getSelectedRow() - 1);
								descarteMatriz.getMatriz().setStatus("Vazia");
								matrizDao.alter(descarteMatriz.getMatriz());
								descarteMatrizDao.delete(descarteMatriz);
							}
							linha = 1;
							dtmDados.setRowCount(1);
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
							JOptionPane.showMessageDialog(null, "Descarte excluído com Sucesso");
						}
						if (jtrVendaAnimal.isRowSelected(0)) {
							if (vendas.get(jtbDados.getSelectedRow() - 1) instanceof VendaMacho) {
								VendaMacho vendaMacho = new VendaMacho();
								vendaMacho = (VendaMacho) vendas.get(jtbDados.getSelectedRow() - 1);
								vendaMacho.getMacho().setStatus("Descartado");
								machoDao.alter(vendaMacho.getMacho());
								vendaMachoDao.delete(vendaMacho);
							}
							if (vendas.get(jtbDados.getSelectedRow() - 1) instanceof VendaMatriz) {
								VendaMatriz vendaMatriz = new VendaMatriz();
								vendaMatriz = (VendaMatriz) vendas.get(jtbDados.getSelectedRow() - 1);
								vendaMatriz.getMatriz().setStatus("Descartada");
								matrizDao.alter(vendaMatriz.getMatriz());
								vendaMatrizDao.delete(vendaMatriz);
							}
							JOptionPane.showMessageDialog(null, "Venda Excluída com Sucesso");
							dtmDados.setRowCount(1);
							linha = 1;
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
						}
						if (jtrMorteLeitaoMaternidade.isRowSelected(0)) {
							MorteLeitaoMaternidade morte = new MorteLeitaoMaternidade();
							morte = morteDao.listarTodos().get(jtbDados.getSelectedRow() - 1);
							Lactacao lactacao = new Lactacao();
							lactacao = morte.getMatriz().getLactacoes()
									.get(morte.getMatriz().getLactacoes().size() - 1);
							lactacao.setQuantAtual(lactacao.getQuantAtual() + morte.getQuantidade());
							lactacao.setQuantMortos(lactacao.getQuantMortos() - morte.getQuantidade());
							lactacaoDao.alter(lactacao);
							morteDao.delete(morte);
							JOptionPane.showMessageDialog(null, "Morte Excluída com Sucesso");
							dtmDados.setRowCount(1);
							linha = 1;
							for (MorteLeitaoMaternidade morte1 : morteDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(morte1.getMatriz().getBrinco(), linha, 0);
								dtmDados.setValueAt(morte1.getData(), linha, 1);
								dtmDados.setValueAt(morte1.getQuantidade(), linha, 2);
								dtmDados.setValueAt(morte1.getCausa().getNome(), linha, 3);
								linha++;
							}
						}
						if (jtrDesmameLeitao.isRowSelected(0)) {
							Desmame desmameExclusao = new Desmame();
							desmameExclusao = desmameDao.listarTodos().get(jtbDados.getSelectedRow() - 1);
							desmameExclusao.getMatriz().setStatus("Lactante");
							desmameExclusao.getLote().setQuantidadeLeitao(
									desmameExclusao.getLote().getQuantidadeLeitao() - desmameExclusao.getQuantidade());
							loteDAO.alter(desmameExclusao.getLote());
							desmameDao.delete(desmameExclusao);
							dtmDados.setRowCount(1);
							linha = 1;
							for (Desmame desmame : desmameDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(desmame.getMatriz().getBrinco(), linha, 0);
								dtmDados.setValueAt(desmame.getData(), linha, 1);
								dtmDados.setValueAt(desmame.getQuantidade(), linha, 2);
								dtmDados.setValueAt(desmame.getPesoTotal(), linha, 3);
								dtmDados.setValueAt(desmame.getLote().getNumero(), linha, 4);
								dtmDados.setValueAt(desmame.getObsDesmame(), linha, 5);
								linha++;
							}
							JOptionPane.showMessageDialog(null, "Desmame excluído com Sucesso");
						}
						if (jtrMovimentacaoLeitao.isRowSelected(0)) {
							MovimentacaoDeLeitao movimentacaoExclusao = new MovimentacaoDeLeitao();
							movimentacaoExclusao = movimentacaoDao.listarTodos().get(jtbDados.getSelectedRow() - 1);
							Lactacao lactacaoDoadora = new Lactacao();
							lactacaoDoadora = movimentacaoExclusao.getMatrizDoadora().getLactacoes()
									.get(movimentacaoExclusao.getMatrizDoadora().getLactacoes().size() - 1);
							lactacaoDoadora.setQuantAtual(lactacaoDoadora.getQuantAtual()
									+ movimentacaoExclusao.getQuantidadeLeitao());
							lactacaoDoadora.setQuantDoados(lactacaoDoadora.getQuantDoados()
									- movimentacaoExclusao.getQuantidadeLeitao());
							Lactacao lactacaoReceptora = new Lactacao();
							lactacaoReceptora = movimentacaoExclusao.getMatrizReceptora().getLactacoes()
									.get(movimentacaoExclusao.getMatrizReceptora().getLactacoes().size() - 1);
							lactacaoReceptora.setQuantAtual(lactacaoReceptora.getQuantAtual()
									- movimentacaoExclusao.getQuantidadeLeitao());
							lactacaoReceptora.setQuantRecebidos(lactacaoReceptora.getQuantRecebidos()
									- movimentacaoExclusao.getQuantidadeLeitao());

							lactacaoDao.alter(lactacaoReceptora);
							lactacaoDao.alter(lactacaoDoadora);
							movimentacaoDao.delete(movimentacaoExclusao);

							dtmDados.setRowCount(1);
							linha = 1;

							for (MovimentacaoDeLeitao movimentacao : movimentacaoDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(movimentacao.getMatrizDoadora().getBrinco(), linha, 0);
								dtmDados.setValueAt(movimentacao.getMatrizReceptora().getBrinco(), linha, 1);
								dtmDados.setValueAt(movimentacao.getData(), linha, 2);
								dtmDados.setValueAt(movimentacao.getQuantidadeLeitao(), linha, 3);
								linha++;
							}

							JOptionPane.showMessageDialog(null, "Movimentação excluída com Sucesso");
						}
						if (jtrPartoLeitao.isRowSelected(0)) {
							Parto partoExclusao = new Parto();
							partoExclusao = partoDao.listarTodos().get(jtbDados.getSelectedRow() - 1);
							partoExclusao.getMatriz().setStatus("Gestante");
							lactacaoDao.delete(lactacaoDao.get(partoExclusao.getCodigo()));
							matrizDao.alter(partoExclusao.getMatriz());
							partoDao.delete(partoExclusao);
							JOptionPane.showMessageDialog(null, "Parto Excluído com Sucesso!");

							dtmDados.setRowCount(1);
							linha = 1;

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
						}
						if (jtrMorteLeitaoCreche.isRowSelected(0)) {
							if (opcao == 0) {
								morteleitaocrecheDAO.delete(morteleitaocrecheDAO.listarTodos().get(
										jtbDados.getSelectedRow() - 1));

								dtmDados.setRowCount(1);
								linha = 1;

								for (MorteLeitaoCreche morte : morteleitaocrecheDAO.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(morte.getCodigo(), linha, 0);
									dtmDados.setValueAt(morte.getLote().getNumero(), linha, 1);
									dtmDados.setValueAt(morte.getData(), linha, 2);
									dtmDados.setValueAt(morte.getQuantidade(), linha, 3);
									dtmDados.setValueAt(morte.getCausa().getNome(), linha, 4);

									linha++;
								}

							}

						}
						if (jtrVendaLeitao.isRowSelected(0)) {
							if (opcao == 0) {
								vendaleitaoDAO.delete(vendaleitaoDAO.listarTodos().get(jtbDados.getSelectedRow() - 1));

								dtmDados.setRowCount(1);
								linha = 1;

								for (br.edu.unoesc.projetofinal.model.VendaLeitao venda : vendaleitaoDAO.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(venda.getCodigo(), linha, 0);
									dtmDados.setValueAt(venda.getLote().getNumero(), linha, 1);
									dtmDados.setValueAt(venda.getValor(), linha, 2);
									dtmDados.setValueAt(venda.getData(), linha, 3);
									dtmDados.setValueAt(venda.getQuantidade(), linha, 4);
									dtmDados.setValueAt(venda.getPesoMedio(), linha, 5);
									dtmDados.setValueAt(venda.getPesoTotal(), linha, 6);
									dtmDados.setValueAt(venda.getLote().getNumero(), linha, 7);
									dtmDados.setValueAt(venda.getGta(), linha, 8);
									dtmDados.setValueAt(venda.getNota().getNumero(), linha, 9);

									linha++;
								}

							}

						}
						if (jtrTransferenciaLotes.isRowSelected(0)) {
							if (opcao == 0) {
								tranfereLoteDAO.delete(tranfereLoteDAO.listarTodos().get(jtbDados.getSelectedRow() - 1));

								dtmDados.setRowCount(1);
								linha = 1;

								for (TransferenciaEntreLotes transferencia : tranfereLoteDAO.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(transferencia.getCodigo(), linha, 0);
									dtmDados.setValueAt(transferencia.getLoteOrigem().getNumero(), linha, 1);
									dtmDados.setValueAt(transferencia.getLoteDestino().getNumero(), linha, 2);
									dtmDados.setValueAt(transferencia.getData(), linha, 3);
									dtmDados.setValueAt(transferencia.getQuantidade(), linha, 4);
									dtmDados.setValueAt(transferencia.getPesoTotal(), linha, 5);

									linha++;
								}
							}
						}
						if (jtrMacho.isRowSelected(0)) {
							if (machoSemen.get(jtbDados.getSelectedRow() - 1) instanceof Macho) {
								Macho machoExclusao = new Macho();
								machoExclusao = (Macho) machoSemen.get(jtbDados.getSelectedRow() - 1);
								int aux = 0;
								for (MontaMacho monta : montaMachoDao.listarTodos()) {
									if (monta.getMacho().getBrinco().equals(machoExclusao.getBrinco())) {
										aux = 1;
										break;
									}
								}
								if (!machoExclusao.getStatus().equals("Ativo")) {
									aux = 1;
								}
								if (aux == 0) {
									machoDao.delete(machoExclusao);
									JOptionPane.showMessageDialog(null, "Macho excluído com Sucesso");
									linha = 1;
									dtmDados.setRowCount(1);
									for (Macho macho : machoDao.listarTodos()) {
										dtmDados.setRowCount(dtmDados.getRowCount() + 1);
										dtmDados.setValueAt(macho.getMossa(), linha, 0);
										dtmDados.setValueAt(macho.getBrinco(), linha, 1);
										dtmDados.setValueAt(macho.getStatus(), linha, 2);
										dtmDados.setValueAt(macho.getTipoUtilizacao(), linha, 3);
										dtmDados.setValueAt(macho.getDataEntrada(), linha, 4);
										dtmDados.setValueAt(macho.getDataNascimento(), linha, 5);
										dtmDados.setValueAt(macho.getPeso(), linha, 6);
										dtmDados.setValueAt(macho.getValor(), linha, 7);
										dtmDados.setValueAt(macho.getRaca().getNome(), linha, 8);
										dtmDados.setValueAt(macho.getFornecedor().getNome(), linha, 9);
										dtmDados.setValueAt(macho.getIdade(), linha, 10);
										dtmDados.setValueAt(macho.getObservacao(), linha, 11);
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
								}
								if (aux == 1) {
									JOptionPane
											.showMessageDialog(null,
													"Operação não permitida, existem informações cadastradas relacionadas a este macho");
								}
							}
							if (machoSemen.get(jtbDados.getSelectedRow() - 1) instanceof Semen) {
								Semen semenExclusao = new Semen();
								semenExclusao = (Semen) machoSemen.get(jtbDados.getSelectedRow() - 1);
								int aux = 0;
								for (MontaSemen monta : montaSemenDao.listarTodos()) {
									if (monta.getSemen().getBrinco().equals(semenExclusao.getBrinco())) {
										aux = 1;
										break;
									}
								}
								if (aux == 0) {
									JOptionPane.showMessageDialog(null, "Semen excluído com Sucesso");
									semenDao.delete(semenExclusao);
									dtmDados.setRowCount(1);
									linha = 1;
									for (Macho macho : machoDao.listarTodos()) {
										dtmDados.setRowCount(dtmDados.getRowCount() + 1);
										dtmDados.setValueAt(macho.getMossa(), linha, 0);
										dtmDados.setValueAt(macho.getBrinco(), linha, 1);
										dtmDados.setValueAt(macho.getStatus(), linha, 2);
										dtmDados.setValueAt(macho.getTipoUtilizacao(), linha, 3);
										dtmDados.setValueAt(macho.getDataEntrada(), linha, 4);
										dtmDados.setValueAt(macho.getDataNascimento(), linha, 5);
										dtmDados.setValueAt(macho.getPeso(), linha, 6);
										dtmDados.setValueAt(macho.getValor(), linha, 7);
										dtmDados.setValueAt(macho.getRaca().getNome(), linha, 8);
										dtmDados.setValueAt(macho.getFornecedor().getNome(), linha, 9);
										dtmDados.setValueAt(macho.getIdade(), linha, 10);
										dtmDados.setValueAt(macho.getObservacao(), linha, 11);
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
								}
								if (aux == 1) {
									JOptionPane
											.showMessageDialog(null,
													"Operação não permitida, existem informações cadastradas relacionadas a este semen");
								}
							}
						}
						if (jtrFemea.isRowSelected(0)) {
							int aux = 0;
							for (Cobertura cobertura : coberturaDao.listarTodos()) {
								if (cobertura.getMatriz().getBrinco()
										.equals(matrizDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getBrinco())) {
									aux = 1;
									break;
								}
							}
							for (Parto parto : partoDao.listarTodos()) {
								if (parto.getMatriz().getBrinco()
										.equals(matrizDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getBrinco())) {
									aux = 1;
									break;
								}
							}

							if (!matrizDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getStatus().equals("Vazia")) {
								aux = 1;
							}
							if (aux == 0) {
								matrizDao.delete(matrizDao.listarTodos().get(jtbDados.getSelectedRow() - 1));
								linha = 1;
								dtmDados.setRowCount(1);
								for (Matriz matriz : matrizDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(matriz.getMossa(), linha, 0);
									dtmDados.setValueAt(matriz.getBrinco(), linha, 1);
									dtmDados.setValueAt(matriz.getStatus(), linha, 2);
									dtmDados.setValueAt(matriz.getNumeroCiclos(), linha, 3);
									dtmDados.setValueAt(matriz.getDataEntrada(), linha, 4);
									dtmDados.setValueAt(matriz.getDataNascimento(), linha, 5);
									dtmDados.setValueAt(matriz.getPeso(), linha, 6);
									dtmDados.setValueAt(matriz.getValor(), linha, 7);
									dtmDados.setValueAt(matriz.getRaca().getNome(), linha, 8);
									dtmDados.setValueAt(matriz.getFornecedor().getNome(), linha, 9);
									dtmDados.setValueAt(matriz.getIdade(), linha, 10);
									dtmDados.setValueAt(matriz.getObservacao(), linha, 11);
									linha++;
								}
								JOptionPane.showMessageDialog(null, "Matriz excluída com Sucesso");
							}
							if (aux == 1) {
								JOptionPane
										.showMessageDialog(null,
												"Operação não permitida. Existem informações cadastradas relacionadas a esta matriz");
							}
						}
					}
				}
			}
		});

		jbtEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtbDados.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o registro que deseja editar");
				} else if (jtbDados.getSelectedRow() == 0) {
					JOptionPane.showMessageDialog(null, "Não pode editar essa linha");
				} else {
					if(jtrAborto.isRowSelected(0)){
						Aborto_Edicao tela=new Aborto_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow()-1);
					}
					if (jtrMorteLeitaoMaternidade.isRowSelected(0)) {
						Morte_LeitaoMaternidadeEdicao tela = new Morte_LeitaoMaternidadeEdicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrDesmameLeitao.isRowSelected(0)) {
						Desmame_Edicao tela = new Desmame_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrMovimentacaoLeitao.isRowSelected(0)) {
						Movimentacao_Leitao_Edicao tela = new Movimentacao_Leitao_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrPartoLeitao.isRowSelected(0)) {
						Parto_Edicao tela = new Parto_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrFemea.isRowSelected(0)) {
						Matriz_Edicao tela = new Matriz_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrMacho.isRowSelected(0)) {
						Macho_Edicao tela = new Macho_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrMorte.isRowSelected(0)) {
						Morte_Edicao tela = new Morte_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrDescarte.isRowSelected(0)) {
						Descarte_Edicao tela = new Descarte_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}

					if (jtrVendaAnimal.isRowSelected(0)) {
						VendaAnimal_Edicao tela = new VendaAnimal_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrMorteLeitaoCreche.isRowSelected(0)) {
						Morte_Leitao_CrecheEditar tela = new Morte_Leitao_CrecheEditar(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}

					if (jtrVendaLeitao.isRowSelected(0)) {
						VendaLeitao_Edicao tela = new VendaLeitao_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
					if (jtrTransferenciaLotes.isRowSelected(0)) {
						Transferencia_Lote_Edicao tela = new Transferencia_Lote_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
			}
		});

		jbtAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtrPartoLeitao.isRowSelected(0)) {
					new Parto_Inclusao(dtmDados);
				}
				if (jtrMovimentacaoLeitao.isRowSelected(0)) {
					new Movimentacao_Leitao_Inclusao(dtmDados);
				}
				if (jtrDesmameLeitao.isRowSelected(0)) {
					new Desmame_Inclusao(dtmDados);
				}
				if (jtrMorteLeitaoMaternidade.isRowSelected(0)) {
					new Morte_Leitao_Maternidade(dtmDados);
				}
				if (jtrMorteLeitaoCreche.isRowSelected(0)) {
					new Morte_Leitao_Creche(dtmDados);
				}

				if (jtrVendaLeitao.isRowSelected(0)) {
					new VendaLeitao_Inclusao(dtmDados);
				}
				if (jtrTransferenciaLotes.isRowSelected(0)) {
					new Transferencia_Lote_Inclusao(dtmDados);
				}
				if (jtrFemea.isRowSelected(0)) {
					new Matriz_Inclusao(dtmDados);
				}
				if (jtrMacho.isRowSelected(0)) {
					new Macho_Inclusao(dtmDados);
				}
				if (jtrMorte.isRowSelected(0)) {
					new Morte_Inclusao(dtmDados);
				}
				if (jtrDescarte.isRowSelected(0)) {
					new Descarte_Inclusao(dtmDados);
				}
				if (jtrVendaAnimal.isRowSelected(0)) {
					new VendaAnimal_Inclusao(dtmDados);
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

	public void plantelFalse() {
		jtrDescarte.setVisible(false);
		jtrFemea.setVisible(false);
		jtrMacho.setVisible(false);
		jtrMorte.setVisible(false);
		jtrVendaAnimal.setVisible(false);

	}

	public void plantelTrue() {
		jtrDescarte.setVisible(true);
		jtrFemea.setVisible(true);
		jtrMacho.setVisible(true);
		jtrMorte.setVisible(true);
		jtrVendaAnimal.setVisible(true);

	}

	public void reproducaoFalse() {
		jtrAborto.setVisible(false);
		jtrCobertura.setVisible(false);
		jtrRepeticaoDoCio.setVisible(false);
	}

	public void reproducaoTrue() {
		jtrAborto.setVisible(true);
		jtrCobertura.setVisible(true);
		jtrRepeticaoDoCio.setVisible(true);
	}

	public void maternidadeFalse() {
		jtrPartoLeitao.setVisible(false);
		jtrMovimentacaoLeitao.setVisible(false);
		jtrMorteLeitaoMaternidade.setVisible(false);
		jtrDesmameLeitao.setVisible(false);

	}

	public void maternidadeTrue() {
		jtrPartoLeitao.setVisible(true);
		jtrMovimentacaoLeitao.setVisible(true);
		jtrMorteLeitaoMaternidade.setVisible(true);
		jtrDesmameLeitao.setVisible(true);

	}

	public void crecheFalse() {
		jtrTransferenciaLotes.setVisible(false);
		jtrMorteLeitaoCreche.setVisible(false);
		jtrVendaLeitao.setVisible(false);

	}

	public void crecheTrue() {
		jtrTransferenciaLotes.setVisible(true);
		jtrMorteLeitaoCreche.setVisible(true);
		jtrVendaLeitao.setVisible(true);
	}

	public void botaoPlantel() {
		plantelTrue();
		maternidadeFalse();
		crecheFalse();
		reproducaoFalse();
	}

	public void botaoMaternidade() {
		plantelFalse();
		maternidadeTrue();
		crecheFalse();
		reproducaoFalse();
	}

	public void botaoCreche() {
		plantelFalse();
		maternidadeFalse();
		crecheTrue();
		reproducaoFalse();
	}

	public void botaoReproducao() {
		plantelFalse();
		maternidadeFalse();
		crecheFalse();
		reproducaoTrue();
	}

	public void clearSelectionPlantel() {
		jtrMacho.setSelectionInterval(-1, -1);
		jtrFemea.setSelectionInterval(-1, -1);
		jtrMorte.setSelectionInterval(-1, -1);
		jtrDescarte.setSelectionInterval(-1, -1);
		jtrVendaAnimal.setSelectionInterval(-1, -1);
	}

	public void clearSelectionReproducao() {
		jtrCobertura.setSelectionInterval(-1, -1);
		jtrAborto.setSelectionInterval(-1, -1);
		jtrRepeticaoDoCio.setSelectionInterval(-1, -1);
	}

	public void clearSelectionMaternidade() {
		jtrPartoLeitao.setSelectionInterval(-1, -1);
		jtrMovimentacaoLeitao.setSelectionInterval(-1, -1);
		jtrDesmameLeitao.setSelectionInterval(-1, -1);
		jtrMorteLeitaoMaternidade.setSelectionInterval(-1, -1);
	}

	public void clearSelectionCreche() {
		jtrTransferenciaLotes.setSelectionInterval(-1, -1);
		jtrMorteLeitaoCreche.setSelectionInterval(-1, -1);
		jtrVendaLeitao.setSelectionInterval(-1, -1);
	}
}