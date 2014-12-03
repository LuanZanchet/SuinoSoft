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
import br.edu.unoesc.projetofinal.jdbc.dao.AbortoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.AcessoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.CausaJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.CoberturaJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.CompraMedicamentoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.CompraRacaoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.CompradorJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.DescarteMachoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.DescarteMatrizJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.DesmameJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.EnderecoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.FornecedorJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.FuncionarioJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.GranjaJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.LactacaoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.LoteJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MachoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MatrizJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MontaMachoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MontaSemenJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MorteLeitaoCrecheJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MorteMachoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MorteMaternidadeJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MorteMatrizJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.MovimentacaoDeLeitaoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.NotaCompraJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.NotaVendaJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.OutrasSaidasJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.PartoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.ProprietarioJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.RacaJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.RacaoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.RepeticaoCioJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.SaidaRacaoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.SemenJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.TarefaJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.TransferenciaEntreLotesJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.UsuarioJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.VacinaJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.VacinacaoMachoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.VacinacaoMatrizJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.VendaLeitaoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.VendaMachoJDBC;
import br.edu.unoesc.projetofinal.jdbc.dao.VendaMatrizJDBC;

public class JDBCDaoFactory implements AbstractDaoFactory {

	public AcessoDAO acessoDao() {
		return new AcessoJDBC();
	}

	public AbortoDAO abortoDao() {
		return new AbortoJDBC();
	}

	public CausaDAO causaDao() {
		return new CausaJDBC();
	}

	public CoberturaDAO coberturaDao() {
		return new CoberturaJDBC();
	}

	public CompraMedicamentoDAO compraMedicamentodao() {
		return new CompraMedicamentoJDBC();
	}

	public DesmameDAO desmamedao() {
		return new DesmameJDBC();
	}

	public EnderecoDAO enderecodao() {
		return new EnderecoJDBC();
	}

	public FornecedorDAO fornecedordao() {
		return new FornecedorJDBC();
	}

	public FuncionarioDAO funcionariodao() {
		return new FuncionarioJDBC();
	}

	public GranjaDAO granjadao() {
		return new GranjaJDBC();
	}

	public LactacaoDAO lactacaodao() {
		return new LactacaoJDBC();
	}

	public LoteDAO lotedao() {
		return new LoteJDBC();
	}

	public MachoDAO machodao() {
		return new MachoJDBC();
	}

	public MatrizDAO matrizdao() {
		return new MatrizJDBC();
	}

	public MorteLeitaoCrecheDAO morteleitaocrechedao() {
		return new MorteLeitaoCrecheJDBC();
	}

	public MorteMaternidadeDAO mortematernidadedao() {
		return new MorteMaternidadeJDBC();
	}

	public MovimentacaoDeLeitaoDAO movimentacaodeleitao() {
		return new MovimentacaoDeLeitaoJDBC();
	}

	public NotaCompraDAO notacompradao() {
		return new NotaCompraJDBC();
	}

	public NotaVendaDAO notavendadao() {
		return new NotaVendaJDBC();
	}

	public OutrasSaidasDAO outrassaidasdao() {
		return new OutrasSaidasJDBC();
	}

	public PartoDAO partodao() {
		return new PartoJDBC();
	}

	public ProprietarioDAO proprietariodao() {
		return new ProprietarioJDBC();
	}

	public RacaDAO racadao() {
		return new RacaJDBC();
	}

	public RacaoDAO racaodao() {
		return new RacaoJDBC();
	}

	public RepeticaoCioDAO repeticaociodao() {
		return new RepeticaoCioJDBC();
	}

	public SaidaRacaoDAO saidaracaodao() {
		return new SaidaRacaoJDBC();
	}

	public SemenDAO semendao() {
		return new SemenJDBC();
	}

	public TransferenciaEntreLoteDAO transferenciaentrelotedao() {
		return new TransferenciaEntreLotesJDBC();
	}

	public UsuarioDAO usuariodao() {
		return new UsuarioJDBC();
	}

	public VacinaDAO vacinadao() {
		return new VacinaJDBC();
	}

	public VendaLeitaoDAO vendaleitaodao() {
		return new VendaLeitaoJDBC();
	}

	public CompraRacaoDAO compraRacaoDao() {
		return new CompraRacaoJDBC();
	}

	public DescarteMatrizDAO descarteMatrizDao() {
		return new DescarteMatrizJDBC();
	}

	public DescarteMachoDAO descarteMachoDao() {
		return new DescarteMachoJDBC();
	}

	public MorteMachoDAO mortemachodao() {
		return new MorteMachoJDBC();
	}

	public MorteMatrizDAO mortematrizdao() {
		return new MorteMatrizJDBC();
	}

	public VendaMachoDAO vendamachodao() {
		return new VendaMachoJDBC();
	}

	public VendaMatrizDAO vendamatrizdao() {
		return new VendaMatrizJDBC();
	}

	public VacinacaoMatrizDAO vacinacaomatrizdao() {
		return new VacinacaoMatrizJDBC();
	}

	public VacinacaoMachoDAO vacinacaomachodao() {
		return new VacinacaoMachoJDBC();
	}

	public TarefaDAO tarefaDao() {		
		return new TarefaJDBC();
	}

	public CompradorDAO compradorDao() {
		return new CompradorJDBC();
	}

	public MontaMachoDao montaMachoDao() {
		return new MontaMachoJDBC();
	}

	public MontaSemenDAO montaSemenDao() {
		return new MontaSemenJDBC();
	}
	
}