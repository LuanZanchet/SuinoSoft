package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Usuario_Inclusao extends JFrame {

	private JLabel jlbUsuario = new JLabel("Modificar Usuario/Senha");
	private JLabel jlbNome = new JLabel("Usuário");
	private JLabel jlbSenha = new JLabel("Senha");
	private JLabel jlbNovoUsuario = new JLabel("Novo nome Usuario");
	private JLabel jlbNovaSenha = new JLabel("Nova Senha");

	private JTextField jtfNomeUsuario = new JTextField();
	private JTextField jtfSenhaUsuario = new JTextField();
	private JTextField jtfNovoUsuario = new JTextField();
	private JTextField jtfNovaSenha = new JTextField();

	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"), jbtSair = new JButton("Sair");

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Usuario_Inclusao() {
		setLayout(null);

		jlbUsuario.setFont(new Font("Arial", Font.BOLD, 24));
		jlbUsuario.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbUsuario, 85, 45, 500, 25);

		posicionaObjeto(jlbNome, 95, 105, 100, 25);
		posicionaObjeto(jlbSenha, 95, 145, 100, 25);
		posicionaObjeto(jlbNovoUsuario, 25, 185, 150, 25);
		posicionaObjeto(jlbNovaSenha, 70, 225, 150, 25);

		posicionaObjeto(jtfNomeUsuario, 150, 105, 150, 25);
		posicionaObjeto(jtfSenhaUsuario, 150, 145, 150, 25);
		posicionaObjeto(jtfNovoUsuario, 150, 185, 150, 25);
		posicionaObjeto(jtfNovaSenha, 150, 225, 150, 25);

		posicionaObjeto(jbtCadastrarFornCli, 90, 285, 100, 30);
		posicionaObjeto(jbtSair, 230, 285, 80, 20);
		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Modificar Usúario");
		setSize(400, 385);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}