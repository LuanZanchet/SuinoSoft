package br.edu.unoesc.projetofinal.dao.factory;

import br.edu.unoesc.projetofinal.dao.AbortoDAO;
import br.edu.unoesc.projetofinal.dao.AcessoDAO;
import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.CoberturaDAO;
import br.edu.unoesc.projetofinal.dao.CompraMedicamentoDAO;
import br.edu.unoesc.projetofinal.dao.CompraRacaoDAO;
import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMachoDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.DesmameDAO;
import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.GranjaDAO;
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
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.OutrasSaidasDAO;
import br.edu.unoesc.projetofinal.dao.PartoDAO;
import br.edu.unoesc.projetofinal.dao.ProprietarioDAO;
import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.RacaoDAO;
import br.edu.unoesc.projetofinal.dao.RepeticaoCioDAO;
import br.edu.unoesc.projetofinal.dao.SaidaRacaoDAO;
import br.edu.unoesc.projetofinal.dao.SemenDAO;
import br.edu.unoesc.projetofinal.dao.TarefaDAO;
import br.edu.unoesc.projetofinal.dao.TransferenciaEntreLoteDAO;
import br.edu.unoesc.projetofinal.dao.UsuarioDAO;
import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.dao.VacinacaoMachoDAO;
import br.edu.unoesc.projetofinal.dao.VacinacaoMatrizDAO;
import br.edu.unoesc.projetofinal.dao.VendaLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.VendaMachoDAO;
import br.edu.unoesc.projetofinal.dao.VendaMatrizDAO;

public class DaoFactory {
	private static DaoFactory factory;
	private static AbstractDaoFactory daoFactory;

	public static DaoFactory get() {
		if (factory == null) {
			factory = new DaoFactory();
		}
		daoFactory = new JDBCDaoFactory();
		return factory;
	}

	public AbortoDAO abortoDao() {
		return daoFactory.abortoDao();
	}

	public MontaMachoDao montaMachoDao() {
		return daoFactory.montaMachoDao();
	}

	public MontaSemenDAO montaSemenDao() {
		return daoFactory.montaSemenDao();
	}

	public CompradorDAO compradorDao() {
		return daoFactory.compradorDao();
	}

	public AcessoDAO acessoDao() {
		return daoFactory.acessoDao();
	}

	public CausaDAO causaDao() {
		return daoFactory.causaDao();
	}

	public CoberturaDAO coberturaDao() {
		return daoFactory.coberturaDao();
	}

	public CompraMedicamentoDAO compraMedicamentoDao() {
		return daoFactory.compraMedicamentodao();
	}

	public CompraRacaoDAO compraRacaoDao() {
		return daoFactory.compraRacaoDao();
	}

	public DesmameDAO desmameDao() {
		return daoFactory.desmamedao();
	}

	public EnderecoDAO enderecoDao() {
		return daoFactory.enderecodao();
	}

	public FornecedorDAO fornecedorDao() {
		return daoFactory.fornecedordao();
	}

	public FuncionarioDAO funcionarioDao() {
		return daoFactory.funcionariodao();
	}

	public GranjaDAO granjaDao() {
		return daoFactory.granjadao();
	}

	public LactacaoDAO lactacaoDao() {
		return daoFactory.lactacaodao();
	}

	public LoteDAO loteDao() {
		return daoFactory.lotedao();
	}

	public MachoDAO machoDao() {
		return daoFactory.machodao();
	}

	public MatrizDAO matrizDao() {
		return daoFactory.matrizdao();
	}

	public MorteLeitaoCrecheDAO morteLeitaoCrecheDaO() {
		return daoFactory.morteleitaocrechedao();
	}

	public MorteMaternidadeDAO morteMaternidadeDao() {
		return daoFactory.mortematernidadedao();
	}

	public MovimentacaoDeLeitaoDAO movimentacaoDeLeitaoDao() {
		return daoFactory.movimentacaodeleitao();
	}

	public NotaCompraDAO notaCompraDao() {
		return daoFactory.notacompradao();
	}

	public NotaVendaDAO notaVendaDao() {
		return daoFactory.notavendadao();
	}

	public OutrasSaidasDAO outrasSaidasDao() {
		return daoFactory.outrassaidasdao();
	}

	public PartoDAO partoDao() {
		return daoFactory.partodao();
	}

	public ProprietarioDAO proprietarioDao() {
		return daoFactory.proprietariodao();
	}

	public RacaDAO racaDao() {
		return daoFactory.racadao();
	}

	public RacaoDAO racaoDao() {
		return daoFactory.racaodao();
	}

	public RepeticaoCioDAO repeticaoCioDao() {
		return daoFactory.repeticaociodao();
	}

	public SaidaRacaoDAO saidaRacaoDao() {
		return daoFactory.saidaracaodao();
	}

	public SemenDAO semenDao() {
		return daoFactory.semendao();
	}

	public TransferenciaEntreLoteDAO transferenciaEntreLotesDao() {
		return daoFactory.transferenciaentrelotedao();
	}

	public UsuarioDAO usuarioDao() {
		return daoFactory.usuariodao();
	}

	public VacinaDAO vacinaDao() {
		return daoFactory.vacinadao();
	}

	public VendaLeitaoDAO vendaLeitaoDao() {
		return daoFactory.vendaleitaodao();
	}

	public DescarteMachoDAO descartaMachoDao() {
		return daoFactory.descarteMachoDao();
	}

	public DescarteMatrizDAO descarteMatrizDao() {
		return daoFactory.descarteMatrizDao();
	}

	public MorteMachoDAO morteMachoDao() {
		return daoFactory.mortemachodao();
	}

	public MorteMatrizDAO morteMatrizDao() {
		return daoFactory.mortematrizdao();
	}

	public VendaMachoDAO vendaMachoDao() {
		return daoFactory.vendamachodao();
	}

	public VendaMatrizDAO vendaMatrizDao() {
		return daoFactory.vendamatrizdao();
	}

	public VacinacaoMachoDAO vacinacaoMachoDao() {
		return daoFactory.vacinacaomachodao();
	}

	public VacinacaoMatrizDAO vacinacaoMatrizDao() {
		return daoFactory.vacinacaomatrizdao();
	}

	public TarefaDAO tareDao() {
		return daoFactory.tarefaDao();
	}
}