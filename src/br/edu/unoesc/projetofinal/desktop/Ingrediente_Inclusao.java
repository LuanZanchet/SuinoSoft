package br.edu.unoesc.projetofinal.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.TarefaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Lote;
import br.edu.unoesc.projetofinal.model.Tarefa;
import br.edu.unoesc.projetofinal.model.Vacina;

public class Ingrediente_Inclusao extends JFrame {

	private JLabel jlbIngredientes = new JLabel("Ingredientes");
	private JLabel jlbNome = new JLabel("Nome");
	private JLabel jlbEstoqueMinimo = new JLabel("Estoque Mínimo");

	private JTextField jtfNomeIngre = new JTextField();
	private JTextField jtfEstoqueIngre = new JTextField();

	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"), jbtSair = new JButton("Sair");

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	Ingrediente_Inclusao() {
		setLayout(null);

		jlbIngredientes.setFont(new Font("Arial", Font.BOLD, 24));
		jlbIngredientes.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbIngredientes, 120, 15, 500, 25);

		posicionaObjeto(jlbNome, 110, 70, 200, 25);
		posicionaObjeto(jlbEstoqueMinimo, 50, 125, 500, 25);

		posicionaObjeto(jtfNomeIngre, 150, 70, 200, 25);
		posicionaObjeto(jtfEstoqueIngre, 150, 125, 200, 25);

		posicionaObjeto(jbtCadastrarFornCli, 90, 205, 100, 30);
		posicionaObjeto(jbtSair, 230, 205, 80, 20);
		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		setTitle("Ingredintes");
		setSize(400, 295);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Ingrediente_Inclusao();
	}
}