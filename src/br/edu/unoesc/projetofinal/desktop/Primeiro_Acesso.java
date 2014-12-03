package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.unoesc.projetofinal.dao.AcessoDAO;
import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.dao.GranjaDAO;
import br.edu.unoesc.projetofinal.dao.ProprietarioDAO;
import br.edu.unoesc.projetofinal.dao.UsuarioDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Acesso;
import br.edu.unoesc.projetofinal.model.Endereco;
import br.edu.unoesc.projetofinal.model.Granja;
import br.edu.unoesc.projetofinal.model.Proprietario;
import br.edu.unoesc.projetofinal.model.Usuario;

public class Primeiro_Acesso extends JFrame {
	private JLabel jlbInfGranja = new JLabel("Informações da Granja");
	private JLabel jlbInfUsuarios = new JLabel("Informações do Usuário");
	private JLabel jlbProprietario = new JLabel("Proprietario");
	private JLabel jlbCidade = new JLabel("Cidade");
	private JLabel jlbEstado = new JLabel("Estado");
	private JLabel jlbNome = new JLabel("Usuário");
	private JLabel jlbSenha = new JLabel("Senha");
	private JTextField jtfProprietario = new JTextField();
	private JTextField jtfCidade = new JTextField();
	private JTextField jtfEstado = new JTextField();
	private JTextField jtfNome = new JTextField();
	private JPasswordField jpfSenha = new JPasswordField();
	private JButton jbtCadastrar = new JButton("Cadastrar"), jbtSair = new JButton("Sair");
	private static AcessoDAO acessoDao = DaoFactory.get().acessoDao();
	private static EnderecoDAO enderecoDao = DaoFactory.get().enderecoDao();
	private static UsuarioDAO usuarioDao = DaoFactory.get().usuarioDao();
	private static ProprietarioDAO proprietarioDao = DaoFactory.get().proprietarioDao();
	private static GranjaDAO granjaDao = DaoFactory.get().granjaDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Primeiro_Acesso(final Tela_Inicial tela_inicial) {
		setLayout(null);
		jlbInfGranja.setFont(new Font("Arial", Font.BOLD, 24));
		jlbInfGranja.setForeground(Color.DARK_GRAY);
		jlbInfUsuarios.setFont(new Font("Arial", Font.BOLD, 24));
		jlbInfUsuarios.setForeground(Color.DARK_GRAY);

		posicionaObjeto(jlbInfGranja, 105, 15, 500, 25);
		posicionaObjeto(jlbProprietario, 110, 75, 70, 25);
		posicionaObjeto(jtfProprietario, 190, 75, 200, 25);
		posicionaObjeto(jlbCidade, 140, 105, 100, 25);
		posicionaObjeto(jtfCidade, 190, 105, 200, 25);
		posicionaObjeto(jlbEstado, 140, 135, 80, 25);
		posicionaObjeto(jtfEstado, 190, 135, 200, 25);
		posicionaObjeto(jlbInfUsuarios, 105, 190, 300, 25);
		posicionaObjeto(jlbNome, 140, 240, 140, 25);
		posicionaObjeto(jtfNome, 190, 240, 200, 25);
		posicionaObjeto(jlbSenha, 140, 270, 100, 25);
		posicionaObjeto(jpfSenha, 190, 270, 200, 25);
		posicionaObjeto(jbtCadastrar, 125, 330, 130, 30);
		posicionaObjeto(jbtSair, 285, 330, 80, 20);

		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Granja granja = new Granja();
				Endereco endereco = new Endereco();
				Proprietario proprietario = new Proprietario();
				Usuario usuario = new Usuario();
				proprietario.setNome(jtfProprietario.getText());
				proprietarioDao.store(proprietario);
				endereco.setCidade(jtfCidade.getText());
				endereco.setUf(jtfEstado.getText());
				enderecoDao.store(endereco);
				usuario.setNome(jtfNome.getText());
				usuario.setSenha(jpfSenha.getText());
				usuarioDao.store(usuario);
				Acesso acesso = new Acesso();
				acesso = acessoDao.get(1);
				acesso.setQuantidadeAcesso(acesso.getQuantidadeAcesso() + 1);
				acessoDao.alter(acesso);
				for(Endereco endereco1:enderecoDao.listarTodos()){
					if(endereco1.getCidade().equals(jtfCidade.getText())&&endereco1.getUf().equals(jtfEstado.getText())){
						granja.setEndereco(endereco1);
					}
				}
				for(Proprietario proprietario1:proprietarioDao.listarTodos()){
					if(proprietario1.getNome().equals(jtfProprietario.getText())){
						granja.setProprietario(proprietario1);
					}
				}
				granjaDao.store(granja);
				tela_inicial.setEnabled(true);
				dispose();
				JOptionPane.showMessageDialog(null, "Cadastro Realizado Com Sucesso!");
			}
		});

		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer opcao=JOptionPane.showConfirmDialog(null, "Deseja Mesmo Sair? Você pode realizar Seu Cadastro mais Tarde");
				if(opcao==0){
					System.exit(EXIT_ON_CLOSE);
				}
				
			}
		});

		this.setAlwaysOnTop(true);
		setTitle("Primeiro Acesso");
		setSize(500, 420);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}
