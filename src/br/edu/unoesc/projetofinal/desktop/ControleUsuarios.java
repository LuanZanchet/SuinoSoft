package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.unoesc.projetofinal.dao.UsuarioDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Usuario;

public class ControleUsuarios extends JFrame implements ActionListener {
	private JLabel jlbFrase, jlbUsuarios, jlbAtual, jlbNova, jlbConfirmar, jlbNovo;
	private JTextField jtfAtual, jtfNova, jtfConfirmar, jtfNovo;
	private JButton jbtInserir, jbtExcluir, jbtAlterar;
	private JList<String> jltUsuarios = new JList<>();
	private Vector<String> vcrUsuarios = new Vector<>();
	private ScrollPane rolagem = new ScrollPane();
	private UsuarioDAO usuarioDao = DaoFactory.get().usuarioDao();

	ControleUsuarios() {
		setLayout(null);

		rolagem.setBounds(20, 70, 250, 200);
		jltUsuarios.setListData(vcrUsuarios);
		rolagem.add(jltUsuarios);
		getContentPane().add(rolagem);

		jlbFrase = new JLabel(">>>Para excluir um usuário, selecione-o na lista 'Usuários' depois clique em 'Excluir'.");
		jlbFrase.setBounds(30, 0, 500, 25);
		getContentPane().add(jlbFrase);

		jlbUsuarios = new JLabel("Usuários:");
		jlbUsuarios.setBounds(20, 40, 100, 25);
		getContentPane().add(jlbUsuarios);

		jlbAtual = new JLabel("Senha Atual:");
		jlbAtual.setBounds(290, 40, 100, 25);
		getContentPane().add(jlbAtual);

		jtfAtual = new JTextField();
		jtfAtual.setBounds(290, 70, 150, 25);
		jtfAtual.setEditable(false);
		getContentPane().add(jtfAtual);

		jlbNova = new JLabel("Nova Senha:");
		jlbNova.setBounds(290, 110, 100, 25);
		getContentPane().add(jlbNova);

		jtfNova = new JTextField();
		jtfNova.setBounds(290, 140, 150, 25);
		getContentPane().add(jtfNova);

		jlbConfirmar = new JLabel("Confirmar Senha:");
		jlbConfirmar.setBounds(290, 180, 100, 25);
		getContentPane().add(jlbConfirmar);

		jtfConfirmar = new JTextField();
		jtfConfirmar.setBounds(290, 210, 150, 25);
		getContentPane().add(jtfConfirmar);

		jlbNovo = new JLabel("Novo Usuário:");
		jlbNovo.setBounds(10, 290, 100, 25);
		getContentPane().add(jlbNovo);

		jtfNovo = new JTextField();
		jtfNovo.setBounds(10, 320, 150, 25);
		getContentPane().add(jtfNovo);

		jbtInserir = new JButton("Inserir");
		jbtInserir.setBounds(170, 320, 80, 25);
		jbtInserir.addActionListener(this);
		getContentPane().add(jbtInserir);

		jbtExcluir = new JButton("Excluir");
		jbtExcluir.setBounds(260, 320, 80, 25);
		jbtExcluir.addActionListener(this);
		getContentPane().add(jbtExcluir);

		jbtAlterar = new JButton("Alterar");
		jbtAlterar.setBounds(350, 320, 80, 25);
		jbtAlterar.addActionListener(this);
		getContentPane().add(jbtAlterar);

		for (Usuario usuario : usuarioDao.listarTodos()) {
			vcrUsuarios.add(usuario.getNome());
			jltUsuarios.setListData(vcrUsuarios);
		}

		jltUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 || e.getClickCount() == 2) {
					jtfAtual.setText(usuarioDao.listarTodos().get(jltUsuarios.getSelectedIndex()).getSenha());
				}
			}
		});

		setTitle("Cadastro de Usuário");
		setSize(550, 400);
		setVisible(true);
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == jbtAlterar) {
			int aux = 0;
			if (jltUsuarios.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "Selecione o Usuário que deseja alterar");
			} else if (jtfNova.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Digite uma Senha");
			} else {
				if(jtfNova.getText().equals(jtfConfirmar.getText())){
					aux=1;
				}
				if(aux==0){
					JOptionPane.showMessageDialog(null, "As senhas não estão iguais");
				}
				if(aux==1){
					Usuario usuario=new Usuario();
					usuario=usuarioDao.listarTodos().get(jltUsuarios.getSelectedIndex());
					usuario.setSenha(jtfNova.getText());
					usuarioDao.alter(usuario);
					JOptionPane.showMessageDialog(null, "Senha Alterada com Sucesso!");
					dispose();
					new ControleUsuarios();
				}
			}
		}
		if (arg0.getSource() == jbtInserir) {
			if(jtfNovo.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Digite o nome do novo Usuários");
			}
			else if(jtfNova.getText().isEmpty()||jtfConfirmar.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Digite sua senha");
			}
			else{
				int aux=0;
				if(jtfNova.getText().equals(jtfConfirmar.getText())){
					aux=1;
				}
				
				if(aux==0){
					JOptionPane.showMessageDialog(null, "As senhas não estão iguais");
				}
				if(aux==1){
					Usuario usuario=new Usuario();
					usuario.setNome(jtfNovo.getText());
					usuario.setSenha(jtfNova.getText());
					usuarioDao.store(usuario);
					JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");
					dispose();
					new ControleUsuarios();
				}
			}
		}
		if (arg0.getSource() == jbtExcluir) {
			if(jltUsuarios.getSelectedIndex()==-1){
				JOptionPane.showMessageDialog(null, "Selecione o usuário que deseja Excluir!");
			}
			else if(jltUsuarios.getSelectedIndex()==0){
				JOptionPane.showMessageDialog(null, "Não Permitido");
			}
			else{
				usuarioDao.delete(usuarioDao.listarTodos().get(jltUsuarios.getSelectedIndex()));
				JOptionPane.showMessageDialog(null, "Usuário Excluído com Sucesso!");
				dispose();
				new ControleUsuarios();
			}
		}
	}
}