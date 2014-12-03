package br.edu.unoesc.projetofinal.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.edu.unoesc.projetofinal.dao.AbortoDAO;
import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.CompraMedicamentoDAO;
import br.edu.unoesc.projetofinal.dao.CompraRacaoDAO;
import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.DesmameDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MorteLeitaoCrecheDAO;
import br.edu.unoesc.projetofinal.dao.MorteMachoDAO;
import br.edu.unoesc.projetofinal.dao.MorteMaternidadeDAO;
import br.edu.unoesc.projetofinal.dao.MorteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.MovimentacaoDeLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.PartoDAO;
import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.RacaoDAO;
import br.edu.unoesc.projetofinal.dao.RepeticaoCioDAO;
import br.edu.unoesc.projetofinal.dao.SemenDAO;
import br.edu.unoesc.projetofinal.dao.TarefaDAO;
import br.edu.unoesc.projetofinal.dao.TransferenciaEntreLoteDAO;
import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.dao.VendaLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Aborto;
import br.edu.unoesc.projetofinal.model.Causa;
import br.edu.unoesc.projetofinal.model.CompraMedicamento;
import br.edu.unoesc.projetofinal.model.CompraRacao;
import br.edu.unoesc.projetofinal.model.Comprador;
import br.edu.unoesc.projetofinal.model.Desmame;
import br.edu.unoesc.projetofinal.model.Fornecedor;
import br.edu.unoesc.projetofinal.model.Funcionario;
import br.edu.unoesc.projetofinal.model.Lote;
import br.edu.unoesc.projetofinal.model.Macho;
import br.edu.unoesc.projetofinal.model.Matriz;
import br.edu.unoesc.projetofinal.model.MorteLeitaoCreche;
import br.edu.unoesc.projetofinal.model.MorteLeitaoMaternidade;
import br.edu.unoesc.projetofinal.model.MorteMacho;
import br.edu.unoesc.projetofinal.model.MorteMatriz;
import br.edu.unoesc.projetofinal.model.MovimentacaoDeLeitao;
import br.edu.unoesc.projetofinal.model.NotaCompra;
import br.edu.unoesc.projetofinal.model.NotaVenda;
import br.edu.unoesc.projetofinal.model.Parto;
import br.edu.unoesc.projetofinal.model.Raca;
import br.edu.unoesc.projetofinal.model.Racao;
import br.edu.unoesc.projetofinal.model.RepeticaoCio;
import br.edu.unoesc.projetofinal.model.Semen;
import br.edu.unoesc.projetofinal.model.Tarefa;
import br.edu.unoesc.projetofinal.model.TransferenciaEntreLotes;
import br.edu.unoesc.projetofinal.model.Vacina;
import br.edu.unoesc.projetofinal.model.VendaLeitao;

public class Pesquisar extends JFrame {
	private JLabel jlbTipo, jlbNome;
	private JRadioButton jrbCodigo, jrbNome;
	private ButtonGroup btgEscolha;
	private JTextField jtfDescricao;
	private JButton jbtPesquisar;
	private JTextField jtfGuardaValor = new JTextField();
	private RacaDAO racaDao = DaoFactory.get().racaDao();
	private LoteDAO loteDao = DaoFactory.get().loteDao();
	private Integer aux;
	private VacinaDAO vacinDao = DaoFactory.get().vacinaDao();
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private FuncionarioDAO funcionarioDao = DaoFactory.get().funcionarioDao();
	private FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();
	private TarefaDAO tarefaDao = DaoFactory.get().tareDao();
	private CompradorDAO compradorDao = DaoFactory.get().compradorDao();
	private NotaVendaDAO notaVendaDao = DaoFactory.get().notaVendaDao();
	private NotaCompraDAO notaCompraDao = DaoFactory.get().notaCompraDao();
	private RacaoDAO racaoDao = DaoFactory.get().racaoDao();
	private CompraRacaoDAO compraRacaoDao = DaoFactory.get().compraRacaoDao();
	private MorteLeitaoCrecheDAO morteleitaocrecheDAO = DaoFactory.get().morteLeitaoCrecheDaO();
	private VendaLeitaoDAO vendaleitaoDAO = DaoFactory.get().vendaLeitaoDao();
	private CompraMedicamentoDAO compraMedicamentoDao = DaoFactory.get().compraMedicamentoDao();
	private PartoDAO partoDao = DaoFactory.get().partoDao();
	private MovimentacaoDeLeitaoDAO movimentacaoDao = DaoFactory.get().movimentacaoDeLeitaoDao();
	private DesmameDAO desmameDao = DaoFactory.get().desmameDao();
	private MorteMaternidadeDAO morteDao = DaoFactory.get().morteMaternidadeDao();
	private TransferenciaEntreLoteDAO transferenciaDao = DaoFactory.get().transferenciaEntreLotesDao();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private AbortoDAO abortoDao = DaoFactory.get().abortoDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private SemenDAO semenDao = DaoFactory.get().semenDao();
	private List machoSemen = new ArrayList();
	private MorteMachoDAO morteMachoDao=DaoFactory.get().morteMachoDao();
	private MorteMatrizDAO morteMatrizDao=DaoFactory.get().morteMatrizDao();
	private RepeticaoCioDAO repeticaoCioDao = DaoFactory.get().repeticaoCioDao();
	private List mortes=new ArrayList();
	
	public void setValor(Integer aux) {
		jtfGuardaValor.setText(aux.toString());
		if (aux == 2 || aux == 9 || aux == 10) {
			jrbNome.setText("Número");
		}
		if (aux == -10 || aux == -11 || aux == -12 || aux == -13 || aux == -100) {
			jrbCodigo.setText("Matriz");
		}
		if (aux == 7 || aux == -10 || aux == -11 || aux == -12 || aux == -13 || aux == -14 || aux == -100 || aux == -15
				|| aux == -99 || aux==-98 || aux==-16) {
			jrbNome.setText("Data");
		}
		if (aux == 200) {
			jrbNome.setText("Causa");
		}
		if(aux==-98){
			jrbCodigo.setText("Animal");
		}
		if (aux == -99) {
			jrbCodigo.setText("Brinco");
		}
	}

	public Pesquisar(final JTable jtbDados) {
		setLayout(null);

		machoSemen.addAll(machoDao.listarTodos());
		machoSemen.addAll(semenDao.listarTodos());
		
		mortes.addAll(morteMachoDao.listarTodos());
		mortes.addAll(morteMatrizDao.listarTodos());
		
		jlbTipo = new JLabel("Pesquisar por");
		jlbTipo.setBounds(10, 10, 100, 25);
		getContentPane().add(jlbTipo);

		jrbCodigo = new JRadioButton("Código");
		jrbCodigo.setBounds(20, 40, 100, 25);
		getContentPane().add(jrbCodigo);

		jrbNome = new JRadioButton("Nome");
		jrbNome.setBounds(20, 60, 100, 25);
		getContentPane().add(jrbNome);

		btgEscolha = new ButtonGroup();
		btgEscolha.add(jrbCodigo);
		btgEscolha.add(jrbNome);

		jlbNome = new JLabel("Nome/Código");
		jlbNome.setBounds(10, 90, 100, 25);
		getContentPane().add(jlbNome);

		jtfDescricao = new JTextField();
		jtfDescricao.setBounds(20, 120, 200, 25);
		getContentPane().add(jtfDescricao);

		jbtPesquisar = new JButton("Pesquisar");
		jbtPesquisar.setBounds(70, 170, 100, 25);
		getContentPane().add(jbtPesquisar);

		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!jrbCodigo.isSelected() && !jrbNome.isSelected()) {
					JOptionPane.showMessageDialog(null, "Escolha o Tipo de Parâmetro para a Busca");
				} else if (jtfDescricao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite o Parâmetro de Busca");
				} else {
					aux = Integer.valueOf(jtfGuardaValor.getText());
					if(aux==-16){
						int linha=-1,posicao=1;
						if(jrbCodigo.isSelected()){
							for(RepeticaoCio repeticao:repeticaoCioDao.listarTodos()){
								if(repeticao.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))){
									linha=posicao;
									break;
								}
								posicao++;
							}
							if(linha==-1){
								JOptionPane.showMessageDialog(null, "Nenhuma Repeticao Encontrada");
								jtfDescricao.setText(null);
							}
							else{
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if(jrbNome.isSelected()){
							for(RepeticaoCio repeticao:repeticaoCioDao.listarTodos()){
								if(repeticao.getData().equals(Date.valueOf(jtfDescricao.getText()))){
									linha=posicao;
									break;
								}
								posicao++;
							}
							if(linha==-1){
								JOptionPane.showMessageDialog(null, "Nenhuma repetição encontrada");
								jtfDescricao.setText(null);
							}
							else{
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						
						
					}
					if(aux==-98){
						int linha=-1;
						if(jrbCodigo.isSelected()){
							for(int i=0;i<mortes.size();i++){
								if(mortes.get(i) instanceof MorteMacho){
									MorteMacho morteMacho=new MorteMacho();
									morteMacho=(MorteMacho)mortes.get(i);
									if(morteMacho.getMacho().getBrinco().equals(Long.valueOf(jtfDescricao.getText()))){
										linha=i+1;
										break;
									}
								}
								if(mortes.get(i) instanceof MorteMatriz){
									MorteMatriz morteMatriz=new MorteMatriz();
									morteMatriz=(MorteMatriz)mortes.get(i);
									if(morteMatriz.getMatriz().getBrinco().equals(Long.valueOf(jtfDescricao.getText()))){
										linha=i+1;
										break;
									}
								}
							}
							if(linha==-1){
								JOptionPane.showMessageDialog(null, "Nenhuma venda encontrada");
								jtfDescricao.setText(null);
							}
							else{
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if(jrbNome.isSelected()){
							for(int i=0;i<mortes.size();i++){
								if(mortes.get(i) instanceof MorteMacho){
									MorteMacho morteMacho=new MorteMacho();
									morteMacho=(MorteMacho)mortes.get(i);
									if(morteMacho.getData().equals(Date.valueOf(jtfDescricao.getText()))){
										linha=i+1;
										break;
									}
								}
								if(mortes.get(i) instanceof MorteMatriz){
									MorteMatriz morteMatriz=new MorteMatriz();
									morteMatriz=(MorteMatriz)mortes.get(i);
									if(morteMatriz.getData().equals(Date.valueOf(jtfDescricao.getText()))){
										linha=i+1;
										break;
									}
								}
							}
							if(linha==-1){
								JOptionPane.showMessageDialog(null, "Nenhuma venda encontrada");
								jtfDescricao.setText(null);
							}
							else{
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == -99) {
						int linha = -1;
						if (jrbCodigo.isSelected()) {
							for (int i = 0; i < machoSemen.size(); i++) {
								if (machoSemen.get(i) instanceof Macho) {
									Macho macho = new Macho();
									macho = (Macho) machoSemen.get(i);
									if (macho.getBrinco().equals(Long.valueOf(jtfDescricao.getText()))) {
										linha = i + 1;
										break;
									}
								}
								if (machoSemen.get(i) instanceof Semen) {
									Semen semen = new Semen();
									semen = (Semen) machoSemen.get(i);
									if (semen.getBrinco().equals(Long.valueOf(jtfDescricao.getText()))) {
										linha = i + 1;
										break;
									}
								}
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum macho/sêmen encontrado");
								jtfDescricao.setText(null);
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (int i = 0; i < machoSemen.size(); i++) {
								if (machoSemen.get(i) instanceof Macho) {
									Macho macho = new Macho();
									macho = (Macho) machoSemen.get(i);
									if (macho.getDataEntrada().equals(Date.valueOf(jtfDescricao.getText()))) {
										linha = i + 1;
										break;
									}
								}
								if (machoSemen.get(i) instanceof Semen) {
									Semen semen = new Semen();
									semen = (Semen) machoSemen.get(i);
									if (semen.getDataEntrada().equals(Date.valueOf(jtfDescricao.getText()))) {
										linha = i + 1;
										break;
									}
								}
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum macho/sêmen encontrado");
								jtfDescricao.setText(null);
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == -15) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Aborto aborto : abortoDao.listarTodos()) {
								if (aborto.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum aborto Encontrado");
								jtfDescricao.setText(null);
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Aborto aborto : abortoDao.listarTodos()) {
								if (aborto.getData().equals(Date.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum aborto encontrado");
								jtfDescricao.setText(null);
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == -100) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Matriz matriz : matrizDao.listarTodos()) {
								if (matriz.getBrinco().equals(Long.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma Matriz Encontrada");
								jtfDescricao.setText(null);
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Matriz matriz : matrizDao.listarTodos()) {
								if (matriz.getDataEntrada().equals(Date.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma Matriz Encontrada");
								jtfDescricao.setText(null);
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == -14) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (TransferenciaEntreLotes transferencia : transferenciaDao.listarTodos()) {
								if (transferencia.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma transferência Encontrada");
								jtfDescricao.setText(null);
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (TransferenciaEntreLotes transferencia : transferenciaDao.listarTodos()) {
								if (transferencia.getData().equals(Date.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma transferência encontrada");
								jtfDescricao.setText(null);
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == -13) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (MorteLeitaoMaternidade morte : morteDao.listarTodos()) {
								if (morte.getMatriz().getBrinco().equals(Long.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma morte encontrada");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (MorteLeitaoMaternidade morte : morteDao.listarTodos()) {
								if (morte.getData().equals(Date.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma morte encontrada");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == -12) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Desmame desmame : desmameDao.listarTodos()) {
								if (desmame.getMatriz().getBrinco().equals(Long.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum desmame encontrado");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Desmame desmame : desmameDao.listarTodos()) {
								if (desmame.getData().equals(Date.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum desmame encontrado");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == -11) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (MovimentacaoDeLeitao movimentacao : movimentacaoDao.listarTodos()) {
								if (movimentacao.getMatrizDoadora().getBrinco()
										.equals(Long.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma movimentação encontrada");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (MovimentacaoDeLeitao movimentacao : movimentacaoDao.listarTodos()) {
								if (movimentacao.getData().equals(Date.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma movimentação encontrada");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == -10) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Parto parto : partoDao.listarTodos()) {
								if (parto.getMatriz().getBrinco().equals(Long.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum parto Encontrado");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Parto parto : partoDao.listarTodos()) {
								if (parto.getData().equals(Date.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum parto Encontrado");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 15) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (CompraMedicamento compra1 : compraMedicamentoDao.listarTodos()) {
								if (compra1.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum Medicamento cadastrado com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (CompraMedicamento compra1 : compraMedicamentoDao.listarTodos()) {
								if (compra1.getVacina().getNome().equals(jtfDescricao.getText())) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum medicamento cadastrado com esse nome");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 1) {
						Integer linha = -1, posicao = 0;
						if (jrbCodigo.isSelected()) {
							for (Raca raca : racaDao.listarTodos()) {
								if (raca.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao + 1;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma Raça cadastrada com esse Código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Raca raca : racaDao.listarTodos()) {
								if (raca.getNome().equals(jtfDescricao.getText())) {
									linha = posicao + 1;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma Raça cadastrada com esse Nome");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 200) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (MorteLeitaoCreche morte : morteleitaocrecheDAO.listarTodos()) {
								if (morte.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma morte cadastrado com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}

						if (jrbNome.isSelected()) {
							for (MorteLeitaoCreche morte : morteleitaocrecheDAO.listarTodos()) {
								if (morte.getCausa().getNome().equals((jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma morte cadastrada com essa Causa");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}

					if (aux == 201) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (VendaLeitao venda : vendaleitaoDAO.listarTodos()) {
								if (venda.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma venda cadastrado com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}

						if (jrbNome.isSelected()) {
							for (VendaLeitao venda : vendaleitaoDAO.listarTodos()) {
								if (venda.getComprador().getNome().equals((jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma venda cadastrada com esse nome");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}

					if (aux == 2) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Lote lote : loteDao.listarTodos()) {
								if (lote.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum lote cadastrado com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Lote lote : loteDao.listarTodos()) {
								if (lote.getNumero().equals(Long.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum lote cadastrado com esse número");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 0) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (CompraRacao compra : compraRacaoDao.listarTodos()) {
								if (compra.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;

								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma Compra possui esse codigo");

							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							linha = -1;
							posicao = 1;
							for (CompraRacao compra : compraRacaoDao.listarTodos()) {
								if (compra.getRacao().getNome().equals(jtfDescricao.getText())) {
									linha = posicao;
									break;

								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma Compra possui esse codigo");

							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}

					}

					if (aux == -1) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Racao racao : racaoDao.listarTodos()) {
								if (racao.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;

								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma Ração possui esse codigo");

							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							linha = -1;
							posicao = 1;
							for (Racao racao : racaoDao.listarTodos()) {
								if (racao.getNome().equals(jtfDescricao.getText())) {
									linha = posicao;
									break;

								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma Compra possui esse codigo");

							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					} // aki diones
					if (aux == 3) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Vacina vacina : vacinDao.listarTodos()) {
								if (vacina.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma vacina cadastrado com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Vacina vacina : vacinDao.listarTodos()) {
								if (vacina.getNome().equals((jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma vacina cadastrada com esse nome");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 4) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Causa causa : causaDao.listarTodos()) {
								if (causa.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma causa cadastrada com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Causa causa : causaDao.listarTodos()) {
								if (causa.getNome().equals(jtfDescricao.getText())) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma causa cadastrada com esse nome");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 5) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Funcionario funcionario : funcionarioDao.listarTodos()) {
								if (funcionario.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Funcionario funcionario : funcionarioDao.listarTodos()) {
								if (funcionario.getNome().equals(jtfDescricao.getText())) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado com esse nome");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 6) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Fornecedor fornecedor : fornecedorDao.listarTodos()) {
								if (fornecedor.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null,
										"Nenhum Cliente/Fornecedor cadastrado com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Fornecedor fornecedor : fornecedorDao.listarTodos()) {
								if (fornecedor.getNome().equals(jtfDescricao.getText())) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null,
										"Nenhum Cliente/Fornecedor cadastrado com esse nome");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 8) {
						int linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (Comprador comprador : compradorDao.listarTodos()) {
								if (comprador.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum comprador cadastrado com esse número");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Comprador comprador : compradorDao.listarTodos()) {
								if (comprador.getNome().equals(jtfDescricao.getText())) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhum comprador cadastrado com esse nome");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 9) {
						Integer posicao = 1, linha = -1;
						if (jrbCodigo.isSelected()) {
							for (NotaCompra nota : notaCompraDao.listarTodos()) {
								if (nota.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane
										.showMessageDialog(null, "Nenhuma nota de Compra cadastrado com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (NotaCompra nota : notaCompraDao.listarTodos()) {
								if (nota.getNumero().equals(Long.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane
										.showMessageDialog(null, "Nenhuma nota de Compra cadastrado com esse número");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 10) {
						Integer linha = -1, posicao = 1;
						if (jrbCodigo.isSelected()) {
							for (NotaVenda nota : notaVendaDao.listarTodos()) {
								if (nota.getCodigo().equals(Integer.valueOf(jtfDescricao.getText().toString()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma nota de Venda cadastrada com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (NotaVenda nota : notaVendaDao.listarTodos()) {
								if (nota.getNumero().equals(Long.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
									break;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma nota de Venda cadastrada com esse número");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
					if (aux == 7) {
						Integer posicao = 1, linha = -1;
						if (jrbCodigo.isSelected()) {
							for (Tarefa tarefa : tarefaDao.listarTodos()) {
								if (tarefa.getCodigo().equals(Integer.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma tarefa cadastrada com esse código");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
						if (jrbNome.isSelected()) {
							for (Tarefa tarefa : tarefaDao.listarTodos()) {
								if (tarefa.getDataTarefa().equals(Date.valueOf(jtfDescricao.getText()))) {
									linha = posicao;
								}
								posicao++;
							}
							if (linha == -1) {
								JOptionPane.showMessageDialog(null, "Nenhuma tarefa cadastrada com essa data");
							} else {
								jtbDados.getSelectionModel().clearSelection();
								jtbDados.addRowSelectionInterval(linha, linha);
								dispose();
							}
						}
					}
				}
			}
		});

		setTitle("Pesquisar");
		setSize(250, 250);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}