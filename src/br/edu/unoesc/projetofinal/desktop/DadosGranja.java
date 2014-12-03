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
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.CompraMedicamentoDAO;
import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMachoDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.DesmameDAO;
import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.GranjaDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MorteMachoDAO;
import br.edu.unoesc.projetofinal.dao.MorteMaternidadeDAO;
import br.edu.unoesc.projetofinal.dao.MorteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.PartoDAO;
import br.edu.unoesc.projetofinal.dao.ProprietarioDAO;
import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.TarefaDAO;
import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Causa;
import br.edu.unoesc.projetofinal.model.CompraMedicamento;
import br.edu.unoesc.projetofinal.model.Comprador;
import br.edu.unoesc.projetofinal.model.DescarteMacho;
import br.edu.unoesc.projetofinal.model.DescarteMatriz;
import br.edu.unoesc.projetofinal.model.Desmame;
import br.edu.unoesc.projetofinal.model.Endereco;
import br.edu.unoesc.projetofinal.model.Fornecedor;
import br.edu.unoesc.projetofinal.model.Funcionario;
import br.edu.unoesc.projetofinal.model.Granja;
import br.edu.unoesc.projetofinal.model.Lote;
import br.edu.unoesc.projetofinal.model.Macho;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.MorteLeitaoMaternidade;
import br.edu.unoesc.projetofinal.model.MorteMacho;
import br.edu.unoesc.projetofinal.model.MorteMatriz;
import br.edu.unoesc.projetofinal.model.Parto;
import br.edu.unoesc.projetofinal.model.Proprietario;
import br.edu.unoesc.projetofinal.model.Raca;
import br.edu.unoesc.projetofinal.model.Tarefa;
import br.edu.unoesc.projetofinal.model.Vacina;

public class DadosGranja extends JFrame {
	private JLabel jlbSair, jlbLancamentos;
	private JButton jbtSair, jbtConfiguracoes, jbtCadastros, jbtTarefas;
	private JTree jtrRaca = new JTree(new DefaultMutableTreeNode("Raça"));
	private JTree jtrLote = new JTree(new DefaultMutableTreeNode("Lote"));
	private JTree jtrCausa = new JTree(new DefaultMutableTreeNode("Causa"));
	private JTree jtrVacina = new JTree(new DefaultMutableTreeNode("Vacina/Medicamento"));
	private JTree jtrFuncionario = new JTree(new DefaultMutableTreeNode("Funcionário"));
	private JTree jtrFornecedor = new JTree(new DefaultMutableTreeNode("Fornecedor"));
	private JTable jtbDados = new JTable();
	private DefaultTableModel dtmDados = new DefaultTableModel();
	private ScrollPane scpRolagem = new ScrollPane();
	private JLabel jlbTarefas, jlbConfiguracoes, jlbTarefas1, jlbCadastros;
	private JButton jbtAdicionar, jbtEditar, jbtExcluir, jbtPesquisar, jbtAlterar;
	private JLabel jlbConfiguracoesGranja, jlbProprietario, jlbCidade, jlbUF, jlbAnimais, jlbFuncionarios, jlbUsuarios;
	private JTextField jtfProprietario, jtfCidade, jtfUF, jtfAnimais, jtfFuncionarios, jtfUsuarios;
	private ProprietarioDAO proprietarioDao = DaoFactory.get().proprietarioDao();
	private EnderecoDAO enderecoDao = DaoFactory.get().enderecoDao();
	private GranjaDAO granjaDao = DaoFactory.get().granjaDao();
	private int linha = 1;
	private RacaDAO racaDao = DaoFactory.get().racaDao();
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private FuncionarioDAO funcionarioDao = DaoFactory.get().funcionarioDao();
	private FornecedorDAO fornnecedorDao = DaoFactory.get().fornecedorDao();
	private VacinaDAO vacinaDao = DaoFactory.get().vacinaDao();
	private LoteDAO loteDao = DaoFactory.get().loteDao();
	private TarefaDAO tarefaDao = DaoFactory.get().tareDao();
	private int auxTela = 0;
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private DesmameDAO desmameDao = DaoFactory.get().desmameDao();
	private CompraMedicamentoDAO compraDao = DaoFactory.get().compraMedicamentoDao();
	private MorteMachoDAO morteMachoDao = DaoFactory.get().morteMachoDao();
	private MorteMatrizDAO morteMatrizDao = DaoFactory.get().morteMatrizDao();
	private DescarteMachoDAO descarteMachoDao = DaoFactory.get().descartaMachoDao();
	private DescarteMatrizDAO descarteMatrizDao = DaoFactory.get().descarteMatrizDao();
	private MorteMaternidadeDAO morteMaternidadeDao = DaoFactory.get().morteMaternidadeDao();
	private PartoDAO partoDao = DaoFactory.get().partoDao();
	private Proprietario proprietario;
	private Endereco endereco;
	private Granja granja;
	private JTree jtrComprador=new JTree(new DefaultMutableTreeNode("Comprador"));
	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}
	private CompradorDAO compradorDao=DaoFactory.get().compradorDao();
	
	public DadosGranja() {
		setLayout(null);

		jbtConfiguracoes = new JButton(new ImageIcon("configuracoes.jpg"));
		jbtConfiguracoes.setBounds(100, 20, 75, 75);
		jbtConfiguracoes.setBackground(Color.white);
		jbtConfiguracoes.setBorder(null);
		getContentPane().add(jbtConfiguracoes);

		jbtCadastros = new JButton(new ImageIcon("cadastros.jpg"));
		jbtCadastros.setBounds(300, 20, 75, 75);
		jbtCadastros.setBackground(Color.white);
		jbtCadastros.setBorder(null);
		getContentPane().add(jbtCadastros);

		jbtTarefas = new JButton(new ImageIcon("tarefas.png"));
		jbtTarefas.setBounds(500, 20, 75, 75);
		jbtTarefas.setBorder(null);
		jbtTarefas.setBackground(Color.white);
		getContentPane().add(jbtTarefas);

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

		jbtSair = new JButton(new ImageIcon("Sair.png"));
		jbtSair.setBounds(1120, 20, 75, 75);
		jbtSair.setBackground(Color.white);
		jbtSair.setBorder(null);
		getContentPane().add(jbtSair);

		jbtAdicionar.setBorder(null);
		jbtEditar.setBorder(null);
		jbtPesquisar.setBorder(null);
		jbtExcluir.setBorder(null);

		jlbSair = new JLabel("Sair");
		jlbSair.setFont(new Font("Arial", Font.BOLD, 16));
		jlbSair.setForeground(Color.darkGray);
		jlbSair.setBounds(1140, 80, 140, 60);
		getContentPane().add(jlbSair);

		jlbLancamentos = new JLabel("Lançamentos");
		jlbLancamentos.setBounds(40, 150, 150, 25);
		jlbLancamentos.setFont(new Font("Arial", Font.BOLD, 16));
		jlbLancamentos.setForeground(Color.darkGray);
		getContentPane().add(jlbLancamentos);

		jlbTarefas = new JLabel("Tarefas");
		jlbTarefas.setBounds(40, 150, 150, 25);
		jlbTarefas.setForeground(Color.DARK_GRAY);
		jlbTarefas.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbTarefas);

		jlbConfiguracoes = new JLabel("Configurações");
		jlbConfiguracoes.setBounds(80, 100, 125, 25);
		jlbConfiguracoes.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbConfiguracoes);

		jlbProprietario = new JLabel("Proprietário");
		jlbProprietario.setBounds(505, 240, 100, 25);
		jlbProprietario.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbProprietario);

		proprietario = new Proprietario();
		proprietario = proprietarioDao.get(1);
		jtfProprietario = new JTextField();
		jtfProprietario.setBounds(700, 240, 205, 25);
		jtfProprietario.setText(proprietario.getNome());
		getContentPane().add(jtfProprietario);

		endereco = new Endereco();
		endereco = enderecoDao.get(1);

		jlbCidade = new JLabel("Cidade");
		jlbCidade.setBounds(505, 270, 100, 25);
		jlbCidade.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbCidade);

		jtfCidade = new JTextField();
		jtfCidade.setBounds(700, 270, 205, 25);
		jtfCidade.setText(endereco.getCidade());
		getContentPane().add(jtfCidade);

		jlbUF = new JLabel("Estado");
		jlbUF.setBounds(505, 300, 100, 25);
		jlbUF.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbUF);

		jtfUF = new JTextField();
		jtfUF.setBounds(700, 300, 205, 25);
		jtfUF.setText(endereco.getUf());
		getContentPane().add(jtfUF);

		granja = new Granja();
		granja = granjaDao.get(1);

		jlbAnimais = new JLabel("Total de Animais");
		jlbAnimais.setBounds(505, 330, 135, 25);
		jlbAnimais.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbAnimais);

		jtfAnimais = new JTextField();
		jtfAnimais.setBounds(700, 330, 205, 25);
		jtfAnimais.setEditable(false);
		jtfAnimais.setText(String.valueOf(granja.totalAnimais()));
		getContentPane().add(jtfAnimais);

		jlbFuncionarios = new JLabel("Total de Funcionários");
		jlbFuncionarios.setBounds(505, 360, 175, 25);
		jlbFuncionarios.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbFuncionarios);

		jtfFuncionarios = new JTextField();
		jtfFuncionarios.setBounds(700, 360, 205, 25);
		jtfFuncionarios.setEditable(false);
		jtfFuncionarios.setText(String.valueOf(granja.totalFuncionarios()));
		getContentPane().add(jtfFuncionarios);

		jlbUsuarios = new JLabel("Total de Usuários");
		jlbUsuarios.setBounds(505, 390, 170, 25);
		jlbUsuarios.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbUsuarios);

		jtfUsuarios = new JTextField();
		jtfUsuarios.setBounds(700, 390, 205, 25);
		jtfUsuarios.setText(String.valueOf(granja.totalUsuarios()));
		jtfUsuarios.setEditable(false);
		getContentPane().add(jtfUsuarios);

		jbtAlterar = new JButton("Alterar Configurações");
		jbtAlterar.setBounds(600, 440, 200, 25);
		getContentPane().add(jbtAlterar);

		jlbCadastros = new JLabel("Cadastros");
		jlbCadastros.setBounds(300, 100, 100, 25);
		jlbCadastros.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbCadastros);

		jlbTarefas1 = new JLabel("Tarefas");
		jlbTarefas1.setBounds(510, 100, 100, 25);
		jlbTarefas1.setFont(new Font("Arial", Font.BOLD, 16));
		getContentPane().add(jlbTarefas1);

		jlbConfiguracoesGranja = new JLabel("Configurações da Granja");
		jlbConfiguracoesGranja.setBounds(500, 150, 500, 60);
		jlbConfiguracoesGranja.setFont(new Font("Arial", Font.BOLD, 35));
		getContentPane().add(jlbConfiguracoesGranja);

		posicionaObjeto(jtrRaca, 60, 190, 150, 25);
		posicionaObjeto(jtrLote, 60, 220, 190, 25);
		posicionaObjeto(jtrCausa, 60, 280, 190, 25);
		posicionaObjeto(jtrVacina, 60, 250, 190, 25);
		posicionaObjeto(jtrFuncionario, 60, 310, 190, 25);
		posicionaObjeto(jtrFornecedor, 60, 340, 190, 25);
		posicionaObjeto(jtrComprador, 60, 370, 190, 25);
		
		scpRolagem.setBounds(250, 170, 1100, 500);
		jtbDados.setModel(dtmDados);
		scpRolagem.add(jtbDados);
		getContentPane().add(scpRolagem);

		invisibilidadeTarefas();
		invisibilidadeConfiguracoes();

		jbtAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtrCausa.isRowSelected(0)) {
					new Causa_Inclusao(dtmDados);
				}
				if (jtrFornecedor.isRowSelected(0)) {
					new Fornecedor_Inclusao(dtmDados);
				}
				if (jtrFuncionario.isRowSelected(0)) {
					new Funcionario_Inclusão(dtmDados);
				}
				if (jtrLote.isRowSelected(0)) {
					new Lote_Inclusao(dtmDados);
				}
				if (jtrRaca.isRowSelected(0)) {
					new Raca_Inclusao(dtmDados);
				}
				if (jtrVacina.isRowSelected(0)) {
					new VacinaMedicamento_Inclusao(dtmDados);
				}
				if (auxTela == 1) {
					new TarefasGranja_Inclusao(dtmDados);
				}
				if(jtrComprador.isRowSelected(0)){
					new Comprador_Inclusao(dtmDados);
				}
			}
		});
		
		jbtEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtrRaca.isRowSelected(0)) {
					if (jtbDados.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione a Raça que Deseja Editar!");
					} else if (jtbDados.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null, "Não pode Editar essa linha!");
					} else {
						Raca_Edicao tela = new Raca_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
				if (jtrLote.isRowSelected(0)) {
					if (jtbDados.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione o Lote que Deseja Editar!");
					} else if (jtbDados.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null, "Não pode Editar essa linha!");
					} else {
						Lote_Edicao tela = new Lote_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
				if (jtrVacina.isRowSelected(0)) {
					if (jtbDados.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione a Vacina/Medicamento que deseja Editar");
					} else if (jtbDados.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null, "Não pode Editar essa Linha!");
					} else {
						VacinaMedicamento_Edicao tela = new VacinaMedicamento_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
				if (jtrCausa.isRowSelected(0)) {
					if (jtbDados.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione a Causa que deseja Editar");
					} else if (jtbDados.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null, "Não pode Editar essa Linha");
					} else {
						Causa_Edicao tela = new Causa_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
				if (jtrFuncionario.isRowSelected(0)) {
					if (jtbDados.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione o Funcionário que Deseja Editar");
					} else if (jtbDados.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null, "Não pode Editar essa Linha");
					} else {
						Funcionario_Edicao tela = new Funcionario_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
				if (jtrFornecedor.isRowSelected(0)) {
					if (jtbDados.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Escolha o Fornecedor que deseja Editar");
					} else if (jtbDados.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null, "Não pode Editar essa Linha");
					} else {
						Fornecedor_Edicao tela = new Fornecedor_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow() - 1);
					}
				}
				if(jtrComprador.isRowSelected(0)){
					if(jtbDados.getSelectedRow()==-1){
						JOptionPane.showMessageDialog(null, "Selecione o comprador que deseja editar");
					}
					else if(jtbDados.getSelectedRow()==0){
						JOptionPane.showMessageDialog(null, "Não pode editar essa Linha");
					}
					else{
						Comprador_Edicao tela=new Comprador_Edicao(dtmDados);
						tela.setValor(jtbDados.getSelectedRow()-1);
					}
				}
				if (auxTela == 1) {
					if (jtbDados.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null, "Selecione a Tarefa que deseja Editar");
					} else if (jtbDados.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null, "Não pode editar essa linha!");
					} else {
						TarefasGranja_Edicao tela = new TarefasGranja_Edicao(dtmDados);
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
						if (auxTela == 1) {
							tarefaDao.delete(tarefaDao.listarTodos().get(jtbDados.getSelectedRow() - 1));
							JOptionPane.showMessageDialog(null, "Tarefa Excluida com Sucesso");
							dtmDados.setRowCount(1);
							linha = 1;
							for (Tarefa tarefa1 : tarefaDao.listarTodos()) {
								dtmDados.setRowCount(dtmDados.getRowCount() + 1);
								dtmDados.setValueAt(tarefa1.getCodigo(), linha, 0);
								dtmDados.setValueAt(tarefa1.getDataTarefa(), linha, 1);
								dtmDados.setValueAt(tarefa1.getDescricao(), linha, 2);
								linha++;
							}
						}
						if (jtrRaca.isRowSelected(0)) {
							int aux = 0;
							for (Matriz matriz : matrizDao.listarTodos()) {
								if (matriz.getRaca().getNome()
										.equals(racaDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNome())) {
									aux = 1;
									break;
								}
							}
							for (Macho macho : machoDao.listarTodos()) {
								if (macho.getRaca().getNome()
										.equals(racaDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNome())) {
									aux = 1;
									break;
								}
							}
							if (aux == 0) {
								racaDao.delete(racaDao.listarTodos().get(jtbDados.getSelectedRow() - 1));
								dtmDados.setRowCount(1);
								linha = 1;
								for (Raca raca1 : racaDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(raca1.getCodigo(), linha, 0);
									dtmDados.setValueAt(raca1.getNome(), linha, 1);
									linha++;
								}
								JOptionPane.showMessageDialog(null, "Raça deletada com Sucesso");
							}
							if (aux == 1) {
								JOptionPane.showMessageDialog(null,
										"Operação não permitida, existem animais cadastrados com essa raça");
							}
						}
						if (jtrLote.isRowSelected(0)) {
							int aux = 0;
							for (Desmame desmame : desmameDao.listarTodos()) {
								if (desmame.getLote().getNumero()
										.equals(loteDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNumero())) {
									aux = 1;
									break;
								}
							}
							if (aux == 0) {
								loteDao.delete(loteDao.listarTodos().get(jtbDados.getSelectedRow() - 1));
								int linha = 1;
								dtmDados.setRowCount(1);
								for (Lote lote1 : loteDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(lote1.getCodigo(), linha, 0);
									dtmDados.setValueAt(lote1.getNumero(), linha, 1);
									dtmDados.setValueAt(lote1.getQuantidadeLeitao(), linha, 2);
									dtmDados.setValueAt(lote1.getIdade(), linha, 3);
									dtmDados.setValueAt(lote1.getObservacao(), linha, 4);
									linha++;
								}
								JOptionPane.showMessageDialog(null, "Lote excluído Com sucesso");
							}
							if (aux == 1) {
								JOptionPane.showMessageDialog(null,
										"Operação não permitida existem desmames cadastrados com esse lote");
							}
						}
						if (jtrVacina.isRowSelected(0)) {
							int aux = 0;
							for (CompraMedicamento compra : compraDao.listarTodos()) {
								if (compra.getVacina().getNome()
										.equals(vacinaDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNome())) {
									aux = 1;
								}
							}
							if (aux == 0) {
								vacinaDao.delete(vacinaDao.listarTodos().get(jtbDados.getSelectedRow() - 1));
								linha = 1;
								dtmDados.setRowCount(1);
								for (Vacina vacina1 : vacinaDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(vacina1.getCodigo(), linha, 0);
									dtmDados.setValueAt(vacina1.getNome(), linha, 1);
									linha++;
								}
								JOptionPane.showMessageDialog(null, "Vacina/Medicamento excluído com Sucesso");
							}
							if (aux == 1) {
								JOptionPane.showMessageDialog(null,
										"Operação não permitida, existem compras realizadas para este medicamento");
							}
						}
						if (jtrCausa.isRowSelected(0)) {
							int aux = 0;
							for (MorteMatriz morte : morteMatrizDao.listarTodos()) {
								if (morte.getCausa().getNome()
										.equals(causaDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNome())) {
									aux = 1;
									break;
								}
							}
							for (MorteMacho morte : morteMachoDao.listarTodos()) {
								if (morte.getCausa().getNome()
										.equals(causaDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNome())) {
									aux = 1;
									break;
								}
							}
							for (DescarteMatriz descarte : descarteMatrizDao.listarTodos()) {
								if (descarte.getCausa().getNome()
										.equals(causaDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNome())) {
									aux = 1;
									break;
								}
							}
							for (DescarteMacho descarte : descarteMachoDao.listarTodos()) {
								if (descarte.getCausa().getNome()
										.equals(causaDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNome())) {
									aux = 1;
									break;
								}
							}
							for (MorteLeitaoMaternidade morte : morteMaternidadeDao.listarTodos()) {
								if (morte.getCausa().getNome()
										.equals(causaDao.listarTodos().get(jtbDados.getSelectedRow() - 1).getNome())) {
									aux = 1;
									break;
								}
							}
							if (aux == 0) {
								causaDao.delete(causaDao.listarTodos().get(jtbDados.getSelectedRow() - 1));
								dtmDados.setRowCount(1);
								linha = 1;
								for (Causa causa1 : causaDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(causa1.getCodigo(), linha, 0);
									dtmDados.setValueAt(causa1.getNome(), linha, 1);
									linha++;
								}
								JOptionPane.showMessageDialog(null, "Causa Excluída com Sucesso");
							}
							if (aux == 1) {
								JOptionPane.showMessageDialog(null,
										"Operação não permitida. Existem outras operações que utilizam esta causa");
							}
						}
						
						if (jtrFuncionario.isRowSelected(0)) {
							int aux = 0;
							for (Parto parto : partoDao.listarTodos()) {
								if (parto
										.getFuncionario()
										.getNome()
										.equals(funcionarioDao.listarTodos().get(jtbDados.getSelectedRow() - 1)
												.getNome())) {
									aux = 1;
									break;
								}
							}
							if (aux == 0) {
								funcionarioDao.delete(funcionarioDao.listarTodos().get(jtbDados.getSelectedRow() - 1));
								linha = 1;
								dtmDados.setRowCount(1);
								for (Funcionario funcionario1 : funcionarioDao.listarTodos()) {
									dtmDados.setRowCount(dtmDados.getRowCount() + 1);
									dtmDados.setValueAt(funcionario1.getCodigo(), linha, 0);
									dtmDados.setValueAt(funcionario1.getNome(), linha, 1);
									dtmDados.setValueAt(funcionario1.getSalario(), linha, 2);
									linha++;
								}
								JOptionPane.showMessageDialog(null, "Funcioário Excluído com Sucesso");
							}
							if (aux == 1) {
								JOptionPane.showMessageDialog(null,
										"Operação não permitida. Existem tarefas realizadas por este Funcionário");
							}
						}
					}
				}
			}
		});
		
		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer aux=0;
				if(jtrRaca.isRowSelected(0)){
					aux=1;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);			
				}
				if(jtrLote.isRowSelected(0)){
					aux=2;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(jtrVacina.isRowSelected(0)){
					aux=3;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(jtrCausa.isRowSelected(0)){
					aux=4;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(jtrFuncionario.isRowSelected(0)){
					aux=5;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(jtrFornecedor.isRowSelected(0)){
					aux=6;
					Pesquisar tela = new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(auxTela==1){
					aux=7;
					Pesquisar tela=new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
				if(jtrComprador.isRowSelected(0)){
					aux=8;
					Pesquisar tela=new Pesquisar(jtbDados);
					tela.setValor(aux);
				}
			}
		});
		
		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);

			}
		});

		

		jbtCadastros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				auxTela=0;
				jtrCausa.setSelectionInterval(-1, -1);
				jtrComprador.setSelectionInterval(-1, -1);
				jtrFornecedor.setSelectionInterval(-1, -1);
				jtrFuncionario.setSelectionInterval(-1, -1);
				jtrLote.setSelectionInterval(-1, -1);
				jtrVacina.setSelectionInterval(-1, -1);
				visibilidadeBotoes();
				invisibilidadeTarefas();
				visibilidadeCadastros();
				invisibilidadeConfiguracoes();
				scpRolagem.setVisible(true);
				jtrRaca.setSelectionInterval(0, 0);
				dtmDados.setRowCount(1);
				dtmDados.setColumnCount(2);
				dtmDados.setValueAt("Código", 0, 0);
				dtmDados.setValueAt("Nome", 0, 1);
				linha = 1;
				for (Raca raca : racaDao.listarTodos()) {
					dtmDados.setRowCount(dtmDados.getRowCount() + 1);
					dtmDados.setValueAt(raca.getCodigo(), linha, 0);
					dtmDados.setValueAt(raca.getNome(), linha, 1);
					linha++;
				}
				jbtAdicionar.setToolTipText("Clique para Adicionar uma nova Raça");
				jbtEditar.setToolTipText("Clique para Editar uma Raça");
				jbtExcluir.setToolTipText("Clique para Excluir uma Raça");
				jbtPesquisar.setToolTipText("Clique para Pesquisar uma Raça");
			}
		});

		jbtConfiguracoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				granja=granjaDao.get(1);
				proprietario=proprietarioDao.get(1);
				endereco=enderecoDao.get(1);
				jtfUF.setText(endereco.getUf());
				jtfCidade.setText(endereco.getCidade());
				jtfProprietario.setText(proprietario.getNome());
				jtfAnimais.setText(granja.totalAnimais().toString());
				jtfFuncionarios.setText(granja.totalFuncionarios().toString());
				jtfUsuarios.setText(granja.totalUsuarios().toString());
				invisibilidadeBotoes();
				invisibilidadeTarefas();
				invisibilidadeCadastros();
				visibilidadeConfiguracoes();
				scpRolagem.setVisible(false);
			}
		});

		jbtTarefas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtrCausa.setSelectionInterval(-1, -1);
				jtrRaca.setSelectionInterval(-1, -1);
				jtrLote.setSelectionInterval(-1, -1);
				jtrFornecedor.setSelectionInterval(-1, -1);
				jtrFuncionario.setSelectionInterval(-1, -1);
				jtrVacina.setSelectionInterval(-1, -1);
				auxTela = 1;
				visibilidadeBotoes();
				invisibilidadeCadastros();
				invisibilidadeConfiguracoes();
				visibilidadeTarefas();
				scpRolagem.setVisible(true);
				dtmDados.setRowCount(1);
				dtmDados.setColumnCount(3);
				dtmDados.setValueAt("Código", 0, 0);
				dtmDados.setValueAt("Data", 0, 1);
				dtmDados.setValueAt("Descrição", 0, 2);
				linha = 1;
				for (Tarefa tarefa : tarefaDao.listarTodos()) {
					dtmDados.setRowCount(dtmDados.getRowCount() + 1);
					dtmDados.setValueAt(tarefa.getCodigo(), linha, 0);
					dtmDados.setValueAt(tarefa.getDataTarefa(), linha, 1);
					dtmDados.setValueAt(tarefa.getDescricao(), linha, 2);
					linha++;
				}
				jbtAdicionar.setToolTipText("Clique para Adicionar uma nova Tarefa");
				jbtEditar.setToolTipText("Clique para Editar uma Tarefa");
				jbtExcluir.setToolTipText("Clique para Excluir uma Tarefa");
				jbtPesquisar.setToolTipText("Clique para Pesquisar uma Tarefa");
			}
		});

		jbtAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfProprietario.getText().isEmpty() || jtfCidade.getText().isEmpty() || jtfUF.getText().isEmpty()) {
					if (jtfProprietario.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nome do Proprietário obrigarório");
					}
					if (jtfCidade.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nome da Cidade obrigatória");
					}
					if (jtfUF.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nome do Estado obrigatório");
					}
				} else {
					proprietario = new Proprietario();
					proprietario = proprietarioDao.get(1);
					Endereco endereco = new Endereco();
					endereco = enderecoDao.get(1);
					proprietario.setNome(jtfProprietario.getText());
					endereco.setCidade(jtfCidade.getText());
					endereco.setUf(jtfUF.getText());
					enderecoDao.alter(endereco);
					proprietarioDao.alter(proprietario);
					JOptionPane.showMessageDialog(null, "Dados Atualizados com Sucesso!");
					dispose();
					new DadosGranja();
				}
			}
		});

		jtrRaca.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrCausa.setSelectionInterval(-1, -1);
					jtrComprador.setSelectionInterval(-1, -1);
					jtrFornecedor.setSelectionInterval(-1, -1);
					jtrFuncionario.setSelectionInterval(-1, -1);
					jtrLote.setSelectionInterval(-1, -1);
					jtrVacina.setSelectionInterval(-1, -1);
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(2);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Nome", 0, 1);
					linha = 1;
					for (Raca raca : racaDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(raca.getCodigo(), linha, 0);
						dtmDados.setValueAt(raca.getNome(), linha, 1);
						linha++;
					}
					jbtAdicionar.setToolTipText("Clique para Adicionar uma nova Raça");
					jbtEditar.setToolTipText("Clique para Editar uma Raça");
					jbtExcluir.setToolTipText("Clique para Excluir uma Raça");
					jbtPesquisar.setToolTipText("Clique para Pesquisar uma Raça");
				}
				if (arg0.getClickCount() == 2) {
					new Raca_Inclusao(dtmDados);
				}
			}
		});

		jtrCausa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrRaca.setSelectionInterval(-1, -1);
					jtrFornecedor.setSelectionInterval(-1, -1);
					jtrFuncionario.setSelectionInterval(-1, -1);
					jtrLote.setSelectionInterval(-1, -1);
					jtrVacina.setSelectionInterval(-1, -1);
					jtrComprador.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para Adicionar uma nova Causa");
					jbtEditar.setToolTipText("Clique para Alterar uma Causa");
					jbtExcluir.setToolTipText("Clique para Excluir uma Causa");
					jbtPesquisar.setToolTipText("Clique para Pesquisar uma Causa");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(2);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Nome", 0, 1);
					linha = 1;
					for (Causa causa : causaDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(causa.getCodigo(), linha, 0);
						dtmDados.setValueAt(causa.getNome(), linha, 1);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Causa_Inclusao(dtmDados);
				}
			}
		});

		jtrFornecedor.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrCausa.setSelectionInterval(-1, -1);
					jtrRaca.setSelectionInterval(-1, -1);
					jtrFuncionario.setSelectionInterval(-1, -1);
					jtrLote.setSelectionInterval(-1, -1);
					jtrVacina.setSelectionInterval(-1, -1);
					jtrComprador.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para Adicionar um novo Fornecedor");
					jbtEditar.setToolTipText("Clique para Alterar um Fornecedor");
					jbtExcluir.setToolTipText("Clique para Excluir um Fornecedor");
					jbtPesquisar.setToolTipText("Clique para Pesquisar um Fornecedor");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(6);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Nome", 0, 1);
					dtmDados.setValueAt("Tipo", 0, 2);
					dtmDados.setValueAt("CPF/CNPJ", 0, 3);
					dtmDados.setValueAt("Endereco", 0, 4);
					dtmDados.setValueAt("Telefone", 0, 5);
					linha = 1;
					for (Fornecedor fornecedor : fornnecedorDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(fornecedor.getCodigo(), linha, 0);
						dtmDados.setValueAt(fornecedor.getNome(), linha, 1);
						dtmDados.setValueAt(fornecedor.getTipo(), linha, 2);
						dtmDados.setValueAt(fornecedor.getCpfCnpj(), linha, 3);
						dtmDados.setValueAt(fornecedor.getEndereco().getCidade() + "/"
								+ fornecedor.getEndereco().getUf(), linha, 4);
						dtmDados.setValueAt(fornecedor.getTelefone(), linha, 5);
						linha++;
					}
					
				}
				if (arg0.getClickCount() == 2) {
					new Fornecedor_Inclusao(dtmDados);
				}
			}
		});

		jtrFuncionario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrCausa.setSelectionInterval(-1, -1);
					jtrFornecedor.setSelectionInterval(-1, -1);
					jtrRaca.setSelectionInterval(-1, -1);
					jtrLote.setSelectionInterval(-1, -1);
					jtrVacina.setSelectionInterval(-1, -1);
					jtrComprador.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para Adicionar um novo Funcionário");
					jbtEditar.setToolTipText("Clique para Alterar um Funcionário ");
					jbtExcluir.setToolTipText("Clique para Excluir um Funcionário");
					jbtPesquisar.setToolTipText("Clique para Pesquisar um Funcionário");
					linha = 1;
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(3);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Nome", 0, 1);
					dtmDados.setValueAt("Salário", 0, 2);
					for (Funcionario funcionario : funcionarioDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(funcionario.getCodigo(), linha, 0);
						dtmDados.setValueAt(funcionario.getNome(), linha, 1);
						dtmDados.setValueAt(funcionario.getSalario(), linha, 2);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new Funcionario_Inclusão(dtmDados);
				}
			}
		});

		jtrLote.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrCausa.setSelectionInterval(-1, -1);
					jtrFornecedor.setSelectionInterval(-1, -1);
					jtrFuncionario.setSelectionInterval(-1, -1);
					jtrRaca.setSelectionInterval(-1, -1);
					jtrVacina.setSelectionInterval(-1, -1);
					jtrComprador.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para Adicionar um novo Lote");
					jbtEditar.setToolTipText("Clique para Alterar um Lote");
					jbtExcluir.setToolTipText("Clique para Excluir um Lote");
					jbtPesquisar.setToolTipText("Clique para Pesquisar um Lote");
					linha = 1;
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(5);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Número", 0, 1);
					dtmDados.setValueAt("Quantidade de Leitão", 0, 2);
					dtmDados.setValueAt("Idade(dias)", 0, 3);
					dtmDados.setValueAt("Observacao", 0, 4);
					for (Lote lote : loteDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(lote.getCodigo(), linha, 0);
						dtmDados.setValueAt(lote.getNumero(), linha, 1);
						dtmDados.setValueAt(lote.getQuantidadeLeitao(), linha, 2);
						dtmDados.setValueAt(lote.getIdade(), linha, 3);
						dtmDados.setValueAt(lote.getObservacao(), linha, 4);
						linha++;
					}

				}
				if (arg0.getClickCount() == 2) {
					new Lote_Inclusao(dtmDados);
				}
			}
		});

		jtrVacina.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtrCausa.setSelectionInterval(-1, -1);
					jtrFornecedor.setSelectionInterval(-1, -1);
					jtrFuncionario.setSelectionInterval(-1, -1);
					jtrLote.setSelectionInterval(-1, -1);
					jtrRaca.setSelectionInterval(-1, -1);
					jtrComprador.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para Adicionar uma nova  Vacina/Medicamento");
					jbtEditar.setToolTipText("Clique para Alterar uma Vacina/Medicamento");
					jbtExcluir.setToolTipText("Clique para Excluir uma Vacina/Medicamento");
					jbtPesquisar.setToolTipText("Clique para Pesquisar uma Vacina/Medicamento");
					linha = 1;
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(2);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Nome", 0, 1);
					for (Vacina vacina : vacinaDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(vacina.getCodigo(), linha, 0);
						dtmDados.setValueAt(vacina.getNome(), linha, 1);
						linha++;
					}
				}
				if (arg0.getClickCount() == 2) {
					new VacinaMedicamento_Inclusao(dtmDados);
				}
			}
		});
		
		jtrComprador.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==1){
					jtrCausa.setSelectionInterval(-1, -1);
					jtrFornecedor.setSelectionInterval(-1, -1);
					jtrFuncionario.setSelectionInterval(-1, -1);
					jtrLote.setSelectionInterval(-1, -1);
					jtrRaca.setSelectionInterval(-1, -1);
					jtrVacina.setSelectionInterval(-1, -1);
					jbtAdicionar.setToolTipText("Clique para Adicionar uma novo Comprador");
					jbtEditar.setToolTipText("Clique para Alterar um Comprador");
					jbtExcluir.setToolTipText("Clique para Excluir um Comprador");
					jbtPesquisar.setToolTipText("Clique para Pesquisar uma Comprador");
					dtmDados.setRowCount(1);
					dtmDados.setColumnCount(6);
					dtmDados.setValueAt("Código", 0, 0);
					dtmDados.setValueAt("Nome", 0, 1);
					dtmDados.setValueAt("Tipo", 0, 2);
					dtmDados.setValueAt("CPF/CNPJ", 0, 3);
					dtmDados.setValueAt("Endereco", 0, 4);
					dtmDados.setValueAt("Telefone", 0, 5);
					linha = 1;
					for(Comprador comprador:compradorDao.listarTodos()){
						dtmDados.setRowCount(dtmDados.getRowCount()+1);
						dtmDados.setValueAt(comprador.getCodigo(), linha, 0);
						dtmDados.setValueAt(comprador.getNome(), linha, 1);
						dtmDados.setValueAt(comprador.getTipo(), linha, 2);
						dtmDados.setValueAt(comprador.getCpfCnpj(), linha, 3);
						dtmDados.setValueAt(comprador.getEndereco().getCidade()+"/"+comprador.getEndereco().getUf(), linha, 4);
						dtmDados.setValueAt(comprador.getTelefone(), linha, 5);
						linha++;
					}
				}
				if(arg0.getClickCount()==2){
					new Comprador_Inclusao(dtmDados);
				}
			}
		});

		this.setAutoRequestFocus(false);
		setTitle("SUINOSOFT");
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void invisibilidadeCadastros() {
		jlbLancamentos.setVisible(false);
		jtrCausa.setVisible(false);
		jtrFornecedor.setVisible(false);
		jtrFuncionario.setVisible(false);
		jtrLote.setVisible(false);
		jtrRaca.setVisible(false);
		jtrVacina.setVisible(false);
		jtrComprador.setVisible(false);
	}

	public void visibilidadeCadastros() {
		jlbLancamentos.setVisible(true);
		jtrCausa.setVisible(true);
		jtrFornecedor.setVisible(true);
		jtrFuncionario.setVisible(true);
		jtrLote.setVisible(true);
		jtrRaca.setVisible(true);
		jtrVacina.setVisible(true);
		jtrComprador.setVisible(true);
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

	public void visibilidadeConfiguracoes() {
		jlbConfiguracoesGranja.setVisible(true);
		jlbProprietario.setVisible(true);
		jlbCidade.setVisible(true);
		jlbUF.setVisible(true);
		jlbAnimais.setVisible(true);
		jlbFuncionarios.setVisible(true);
		jlbUsuarios.setVisible(true);
		jtfProprietario.setVisible(true);
		jtfCidade.setVisible(true);
		jtfUF.setVisible(true);
		jtfAnimais.setVisible(true);
		jtfFuncionarios.setVisible(true);
		jtfUsuarios.setVisible(true);
		jbtAlterar.setVisible(true);
	}

	public void invisibilidadeConfiguracoes() {
		jlbConfiguracoesGranja.setVisible(false);
		jlbProprietario.setVisible(false);
		jlbCidade.setVisible(false);
		jlbUF.setVisible(false);
		jlbAnimais.setVisible(false);
		jlbFuncionarios.setVisible(false);
		jlbUsuarios.setVisible(false);
		jtfProprietario.setVisible(false);
		jtfCidade.setVisible(false);
		jtfUF.setVisible(false);
		jtfAnimais.setVisible(false);
		jtfFuncionarios.setVisible(false);
		jtfUsuarios.setVisible(false);
		jbtAlterar.setVisible(false);
	}

	public void invisibilidadeTarefas() {
		jlbTarefas.setVisible(false);
	}

	public void visibilidadeTarefas() {
		jlbTarefas.setVisible(true);
	}
}