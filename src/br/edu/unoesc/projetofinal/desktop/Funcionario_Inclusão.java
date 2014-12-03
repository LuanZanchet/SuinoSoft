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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Funcionario;

public class Funcionario_Inclusão extends JFrame {

	private JTextField jtfNomeFunci = new JTextField();
	private JTextField jtfSalarioFunci = new JTextField();
	private JLabel jlbNome = new JLabel("Nome ");
	private JLabel jlbSalario = new JLabel("Salario ");
	private JLabel jlbInfGranja = new JLabel("Cadastra Funcionario");
	private JButton jbtCadastrarFuncionario = new JButton("Cadastrar"), jbtSair = new JButton("Sair");
	private FuncionarioDAO funcionarioDao = DaoFactory.get().funcionarioDao();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public Funcionario_Inclusão(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbInfGranja.setFont(new Font("Arial", Font.BOLD, 24));
		jlbInfGranja.setForeground(Color.DARK_GRAY);

		posicionaObjeto(jlbInfGranja, 80, 15, 500, 25);
		posicionaObjeto(jlbNome, 100, 60, 200, 25);
		posicionaObjeto(jlbSalario, 95, 90, 140, 25);
		posicionaObjeto(jtfNomeFunci, 150, 60, 150, 25);
		posicionaObjeto(jtfSalarioFunci, 150, 90, 150, 25);

		posicionaObjeto(jbtCadastrarFuncionario, 125, 175, 100, 30);
		posicionaObjeto(jbtSair, 285, 175, 80, 20);

		jbtCadastrarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfNomeFunci.getText().isEmpty() || jtfSalarioFunci.getText().isEmpty()) {
					if (jtfNomeFunci.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o nome do Funcionário!");
					}
					if (jtfSalarioFunci.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o Salário do Funcionário");
					}
				} else {
					Funcionario funcionario = new Funcionario();
					funcionario.setNome(jtfNomeFunci.getText());
					funcionario.setSalario(Double.valueOf(jtfSalarioFunci.getText()));
					funcionarioDao.store(funcionario);
					JOptionPane.showMessageDialog(null, "Funcionário cadastrado com Sucesso!");
					int linha = 1;
					dtmDados.setRowCount(1);
					for (Funcionario funcionario1 : funcionarioDao.listarTodos()) {
						dtmDados.setRowCount(dtmDados.getRowCount() + 1);
						dtmDados.setValueAt(funcionario1.getCodigo(), linha, 0);
						dtmDados.setValueAt(funcionario1.getNome(), linha, 1);
						dtmDados.setValueAt(funcionario1.getSalario(), linha, 2);
						linha++;
					}
					dispose();
				}
			}
		});

		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setTitle("Funcionario Inclusao");
		setSize(400, 285);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}