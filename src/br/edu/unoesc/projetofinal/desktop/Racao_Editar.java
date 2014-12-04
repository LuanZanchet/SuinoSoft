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

import br.edu.unoesc.projetofinal.dao.RacaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Racao;

public class Racao_Editar extends JFrame {

	private JLabel jlbracao = new JLabel("Compra de Ração");
	private JLabel jlbNome = new JLabel("Nome ");

	private JTextField jtfNomeRacao = new JTextField();

	private RacaoDAO racaoDao = DaoFactory.get().racaoDao();

	private JButton jbtCadastrarFornCli = new JButton("Cadastrar"), jbtSair = new JButton("Sair");

	private JTextField jtfGuardaValorR = new JTextField();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor(Integer posicao) {
		jtfGuardaValorR.setText(posicao.toString());
		jtfNomeRacao.setText(racaoDao.listarTodos().get(posicao).getNome());

		jtfGuardaValorR.setText(posicao.toString());
	}

	public Racao_Editar(final DefaultTableModel dtmDados) {
		setLayout(null);

		jlbracao.setFont(new Font("Arial", Font.BOLD, 24));
		jlbracao.setForeground(Color.DARK_GRAY);
		posicionaObjeto(jlbracao, 85, 45, 500, 25);

		posicionaObjeto(jlbNome, 90, 105, 100, 25);
		posicionaObjeto(jtfNomeRacao, 130, 105, 180, 25);

		posicionaObjeto(jbtCadastrarFornCli, 90, 185, 100, 30);
		posicionaObjeto(jbtSair, 230, 185, 80, 20);
		jbtSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		jbtCadastrarFornCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (jtfNomeRacao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Existem informações não preenchidas no formulário");
				} else {
					int aux = 0;
					for (Racao racao : racaoDao.listarTodos()) {
						if (racao.getNome().equals(jtfNomeRacao.getText())) {
							aux = 1;
							break;
						}

					}
					if (aux == 0) {
						Racao Racao = new Racao();
						Racao = racaoDao.listarTodos().get(Integer.valueOf(jtfGuardaValorR.getText()));
						Racao.setNome(jtfNomeRacao.getText());

						racaoDao.alter(Racao);
						JOptionPane.showMessageDialog(null, "Ração Editada com Sucesso");
						dtmDados.setRowCount(1);
						int linha = 1;
						for (Racao racao : racaoDao.listarTodos()) {
							dtmDados.setRowCount(dtmDados.getRowCount() + 1);
							dtmDados.setValueAt(racao.getCodigo(), linha, 0);
							dtmDados.setValueAt(racao.getNome(), linha, 1);
							linha++;
						}

					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Já existe a Ração cadastrada");
					}

				}
				dispose();
			}
		});

		setTitle("Ração");
		setSize(400, 275);
		setVisible(true);
		this.setResizable(false);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.lightGray);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}
