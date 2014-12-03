package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.edu.unoesc.projetofinal.dao.AcessoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Acesso;

public class Tela_Inicial extends JFrame {

	private JMenuBar jmbBarra = new JMenuBar();
	private JMenu jmnMinhaGranja = new JMenu("Minha Granja");
	private JMenu jmnManejo = new JMenu("Manejo");
	private JMenu jmnFabricaRacao = new JMenu("Fabrica de Ração");
	private JMenu jmnFarmacia = new JMenu("Farmácia");
	private JMenu jmnFinanceiro = new JMenu("Financeiro");
	private JMenu jmnSuporte = new JMenu("Suporte");
	private JMenuItem jmiLancamentos = new JMenuItem("Lançamentos");
	private JMenuItem jmiRelatorios = new JMenuItem("Relatórios");
	private JMenuItem jmiContato = new JMenuItem("Contato");
	private JMenuItem jmiDados = new JMenuItem("Dados da Granja");
	private JMenuItem jmiControle = new JMenuItem("Controle de Usuários");
	private JMenuItem jmiOutro = new JMenuItem("Mudar de Usuário");
	private JMenuItem jmiSair = new JMenuItem("Sair");
	private JMenuItem jmiLancamentos1 = new JMenuItem("Lançamentos");
	private JMenuItem jmiRelatorios1 = new JMenuItem("Relatórios");
	private JMenuItem jmiLancamentos2 = new JMenuItem("Lançamentos");
	private JMenuItem jmiRelatorios2 = new JMenuItem("Relatórios");
	private JMenuItem jmiNotas = new JMenuItem("Notas de Compra e Venda");
	private JMenuItem jmiRelatorios3 = new JMenuItem("Relatórios");
	private JLabel jlbLayout1, jlbMinha, jlbGranja, jlbManejo, jlbRacao, jlbFarmacia, jlbFinanceiro, jlbSair;
	private JButton jbtGranja, jbtManejo, jbtRacao, jbtFarmacia, jbtFinanceiro, jbtSair;
	private static AcessoDAO acessoDao = DaoFactory.get().acessoDao();

	public Tela_Inicial() {
		setLayout(null);
		jmbBarra.add(jmnMinhaGranja);
		jmbBarra.add(jmnManejo);
		jmbBarra.add(jmnFabricaRacao);
		jmbBarra.add(jmnFarmacia);
		jmbBarra.add(jmnFinanceiro);
		jmbBarra.add(jmnSuporte);
		jmnManejo.add(jmiLancamentos);
		jmnManejo.add(jmiRelatorios);
		jmnSuporte.add(jmiContato);
		jmnMinhaGranja.add(jmiDados);
		jmnMinhaGranja.add(jmiControle);
		jmnMinhaGranja.add(jmiOutro);
		jmnMinhaGranja.add(jmiSair);
		jmnFabricaRacao.add(jmiLancamentos1);
		jmnFabricaRacao.add(jmiRelatorios1);
		jmnFarmacia.add(jmiLancamentos2);
		jmnFarmacia.add(jmiRelatorios2);
		jmnFinanceiro.add(jmiNotas);
		jmnFinanceiro.add(jmiRelatorios3);

		jbtGranja = new JButton(new ImageIcon("Granja.jpg"));
		jbtGranja.setBounds(220, 20, 75, 75);
		jbtGranja.setBorder(null);
		getContentPane().add(jbtGranja);

		jlbMinha = new JLabel("Minha");
		jlbMinha.setFont(new Font("Arial", Font.BOLD, 16));
		jlbMinha.setForeground(Color.DARK_GRAY);
		jlbMinha.setBounds(205, 80, 90, 60);
		getContentPane().add(jlbMinha);

		jlbGranja = new JLabel("Granja");
		jlbGranja.setFont(new Font("Arial", Font.BOLD, 16));
		jlbGranja.setForeground(Color.darkGray);
		jlbGranja.setBounds(255, 80, 90, 60);
		getContentPane().add(jlbGranja);

		jbtManejo = new JButton(new ImageIcon("Manejo.png"));
		jbtManejo.setBounds(400, 20, 75, 75);
		jbtManejo.setBackground(Color.white);
		jbtManejo.setBorder(null);
		getContentPane().add(jbtManejo);

		jlbManejo = new JLabel("Manejo");
		jlbManejo.setFont(new Font("Arial", Font.BOLD, 16));
		jlbManejo.setForeground(Color.darkGray);
		jlbManejo.setBounds(415, 80, 90, 60);
		getContentPane().add(jlbManejo);

		jbtRacao = new JButton(new ImageIcon("Racao.jpg"));
		jbtRacao.setBounds(580, 20, 75, 75);
		jbtRacao.setBackground(Color.white);
		jbtRacao.setBorder(null);
		getContentPane().add(jbtRacao);

		jlbRacao = new JLabel("Fabrica de Ração");
		jlbRacao.setFont(new Font("Arial", Font.BOLD, 16));
		jlbRacao.setForeground(Color.darkGray);
		jlbRacao.setBounds(560, 80, 140, 60);
		getContentPane().add(jlbRacao);

		jbtFarmacia = new JButton(new ImageIcon("Farmacia.jpg"));
		jbtFarmacia.setBounds(760, 20, 75, 75);
		jbtFarmacia.setBackground(Color.white);
		jbtFarmacia.setBorder(null);
		getContentPane().add(jbtFarmacia);

		jlbFarmacia = new JLabel("Farmacia");
		jlbFarmacia.setFont(new Font("Arial", Font.BOLD, 16));
		jlbFarmacia.setForeground(Color.darkGray);
		jlbFarmacia.setBounds(760, 80, 140, 60);
		getContentPane().add(jlbFarmacia);

		jbtFinanceiro = new JButton(new ImageIcon("Financeiro.jpg"));
		jbtFinanceiro.setBounds(940, 20, 75, 75);
		jbtFinanceiro.setBackground(Color.white);
		jbtFinanceiro.setBorder(null);
		getContentPane().add(jbtFinanceiro);

		jlbFinanceiro = new JLabel("Financeiro");
		jlbFinanceiro.setFont(new Font("Arial", Font.BOLD, 16));
		jlbFinanceiro.setForeground(Color.darkGray);
		jlbFinanceiro.setBounds(940, 80, 140, 60);
		getContentPane().add(jlbFinanceiro);

		jbtSair = new JButton(new ImageIcon("Sair.png"));
		jbtSair.setBounds(1120, 20, 75, 75);
		jbtSair.setBackground(Color.white);
		jbtSair.setBorder(null);
		getContentPane().add(jbtSair);

		jlbSair = new JLabel("Sair");
		jlbSair.setFont(new Font("Arial", Font.BOLD, 16));
		jlbSair.setForeground(Color.darkGray);
		jlbSair.setBounds(1140, 80, 140, 60);
		getContentPane().add(jlbSair);

		jlbLayout1 = new JLabel(new ImageIcon("Fundo.Jpg"));
		jlbLayout1.setBounds(1, 1, 1370, 706);
		jlbLayout1.setBackground(Color.white);
		getContentPane().add(jlbLayout1);

		this.setEnabled(false);

		Acesso acesso = new Acesso();
		acesso = acessoDao.get(1);

		if (acesso.getQuantidadeAcesso() == 0) {
			new Primeiro_Acesso(this);
		} else {
			new Tela_Login(this);
		}

		jbtGranja.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new DadosGranja();

			}
		});

		jbtManejo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Manejo();

			}
		});

		jbtRacao.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new FabricaDeRacao();

			}
		});

		jbtFarmacia.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Farmacia();

			}
		});

		jbtFinanceiro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Financeiro();
			}
		});

		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);

			}
		});

		jmiLancamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Manejo();

			}
		});

		jmiControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ControleUsuarios();
			}
		});

		jmiContato.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Suporte();

			}
		});

		jmiOutro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new Tela_Inicial();
			}
		});

		jmiSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		jmiLancamentos1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FabricaDeRacao();
			}
		});

		jmiLancamentos2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Farmacia();
			}
		});

		jmiNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Financeiro();
			}
		});

		jmiDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DadosGranja();
			}
		});
		jmiRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Relatorios_Manejo();
			}
		});
		jmiRelatorios1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Relatorios_FabricaDeRacao();
			}
		});
		jmiRelatorios2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Relatorios_Farmacia();
			}
		});
		jmiRelatorios3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Relatorios_Financeiro();
			}
		});
		this.setAutoRequestFocus(false);
		setJMenuBar(jmbBarra);
		setTitle("SUINOSOFT");
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}