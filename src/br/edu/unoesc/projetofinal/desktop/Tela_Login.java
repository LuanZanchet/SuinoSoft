package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.unoesc.projetofinal.dao.UsuarioDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Usuario;

public class Tela_Login extends JFrame {
	private JLabel jlbUsuario = new JLabel("Usuário"), jlbSenha = new JLabel("Senha"), jlbLogo = new JLabel(
			new ImageIcon("LogoSuinoSoft.png"));
	private JTextField jtfUsuario = new JTextField("Digite seu Usuário");
	private JPasswordField jpfSenha = new JPasswordField("************");
	private JButton jbtConfirmar = new JButton("Confirmar"), jbtSair = new JButton("Sair");
	private UsuarioDAO usuarioDao = DaoFactory.get().usuarioDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Tela_Login(final Tela_Inicial tela) {
		setLayout(null);

		posicionaObjeto(jlbLogo, 75, 10, 195, 145);
		posicionaObjeto(jlbUsuario, 65, 160, 80, 25);
		posicionaObjeto(jtfUsuario, 115, 160, 150, 25);
		posicionaObjeto(jlbSenha, 70, 190, 80, 25);
		posicionaObjeto(jpfSenha, 115, 190, 150, 25);
		posicionaObjeto(jbtConfirmar, 65, 240, 100, 30);
		posicionaObjeto(jbtSair, 175, 245, 80, 20);

		jtfUsuario.selectAll();
		jpfSenha.selectAll();

		jtfUsuario.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jtfUsuario.setText(null);
				}

			}
		});

		jpfSenha.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 1) {
					jpfSenha.setText(null);
				}
			}
		});

		jbtConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setAlwaysOnTop(false);
				int aux = 0;
				if (jtfUsuario.getText().isEmpty() || jpfSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite Seu Usuário/Senha");
				} else {
					for (Usuario usuario : usuarioDao.listarTodos()) {
						if (usuario.getNome().equals(jtfUsuario.getText())
								&& usuario.getSenha().equals(jpfSenha.getText())) {
							aux = 1;
							break;
						}
					}
					if (aux == 0) {
						JOptionPane.showMessageDialog(null, "Combinação Ususário/Senha Incorreta");
					}
					if (aux == 1) {
						dispose();
						tela.setEnabled(true);
					}
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});

		setTitle("Login");
		this.setAlwaysOnTop(true);
		setSize(360, 320);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}